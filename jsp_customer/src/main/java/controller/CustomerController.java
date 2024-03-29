package controller;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import DAO.CustomerDAO;

import DTO.Customer;

//웹 어플리케이션에서 발생하는 모든 request 는 전부 CustomerController 로 온다
@WebServlet("/")
@MultipartConfig(maxFileSize=1024*1024*50, location="c:/Temp/img")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  private CustomerDAO dao;
  private ServletContext ctx;
  
  @Override
  public void init(ServletConfig config) throws ServletException {
  	super.init(config);
  	
  	//init 은 서블릿 객체 생성시 딱 한번만 실행되므로 객체를 한번만 생성해 공유한다.
  	dao = new CustomerDAO();
  	ctx = getServletContext(); // getServletContext: 웹 어플리케이션의 자원관리
  }
  
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //request 객체 한글깨짐 방지
		
		String command = request.getServletPath();
		String site = null;
		
		System.out.println("command: " + command);
		
		switch (command) {
		  case "/index": site = getList(request); break; //index.jsp로 이동
		  case "/view": site = getView(request); break; //view.jsp로 이동
		  case "/write": site = "write.jsp"; break; //write.jsp로 이동
		  case "/insert": site = insertCustomer(request); break; //게시글 등록
		  case "/edit": site =getViewForEdit(request); break; //edit.jsp로 이동
		  case "/update": site = updateCustomer(request); break; // 게시글 수정
		  case "/delete": site = deleteCustomer(request); break; // 게시글 삭제
	}
		if(site.startsWith("redirect:/")) { //redirect 처리
			String rview = site.substring("redirect:/".length()); //index번호: 10
			response.sendRedirect(rview); //rview: /index
		}else { //forward 처리
			ctx.getRequestDispatcher("/" + site).forward(request, response);			
		}
		
	 
	}

	//CustomerDAO객체의 getList 메소드 실행: 게시물 전체 목록을 가져온 후 request 객체에 넣어준다.
	public String getList(HttpServletRequest request) {
	    List<Customer> list;
	
	    try {
	        list= dao.getList();
	        request.setAttribute("customerList", list);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	request.setAttribute("error", "게시물 목록을 정상적으로 가져오기 못했습니다");	       
	    }
	    return "index.jsp";
	}
	
	//게시물의 상세 페이지를 가지고 와서 request 객체에 넣어준다.
		public String getView(HttpServletRequest request) {
			//쿼리 파라메터에 있는 id 값을 가져온다.
			int id = Integer.parseInt(request.getParameter("id"));
			
			try {
				Customer b = dao.getView(id);
				request.setAttribute("customer", b);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error", "게시글을 정상적으로 가져오기 못했습니다");
			}
			
			return "view.jsp";
		 }
	
	// 수정할 게시물의 기존 데이터를 가지고 와서 request 객체에 넣어준다.
	public String getViewForEdit(HttpServletRequest request) {
		//쿼리 파라메터에 있는 id 값을 가져온다.
		int id = Integer.parseInt(request.getParameter("id"));		
		try {
			Customer b = dao.getView(id);
			request.setAttribute("customer", b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit.jsp";
	 }
	
	//게시글을 등록해준다.
	public String insertCustomer(HttpServletRequest request) {
		Customer b = new Customer();
		
		try {
			BeanUtils.populate(b, request.getParameterMap());
			
			//1. 이미지 파일 서버(c:/Temp/img) 컴퓨터에 저장
			Part part = request.getPart("file"); //파일에 대한 정보
			String fileName = getFilename(part); //파일명 얻음
			
			//fileName이 null이 아니고 length()도 0이 아니라면
			//업로드된 파일이 있는지 확인
			if(fileName != null && !fileName.isEmpty()){
				part.write(fileName);  //서버에 파일 업로드
				
				//2.경로를 포함한 이미지 파일 이름을 Customer 객체에 저장
				b.setImg("/img/"+fileName);
			} else {
				b.setImg(null); //업로드한 이미지가 없을 경우 빈문자열 저장
			}
				       
			dao.insertCustomer(b);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				// 쿼리스트링의 한글깨짐을 방지하기 위해 UTF-8로 인코딩
				String encodeName = URLEncoder.encode("게시물이 정삭적으로 등록되지 않았습니다!", "UTF-8");
				return "redirect:/index?error=" + encodeName;
			} catch (UnsupportedEncodingException e1) {				
				e1.printStackTrace();
			}
		} 
		return "redirect:/index"; //새로고침
	 }
	
	// 게시글을 수정해준다.
	 public String updateCustomer(HttpServletRequest request) {
		 Customer b = new Customer();
	 		String origin_file = request.getParameter("origin_file");
	 		
	 		try {
	 			BeanUtils.populate(b, request.getParameterMap());
	 			
	 			//1. 이미지 파일 서버(c:/Temp/img) 컴퓨터에 저장
				Part part = request.getPart("file"); //파일에 대한 정보
				String fileName = getFilename(part); //파일명 얻음
				
				//fileName이 null이 아니고 length()도 0이 아니라면
				//업로드된 파일이 있는지 확인
				if(fileName != null && !fileName.isEmpty()){
					part.write(fileName);  //서버에 파일 업로드
					        
					//2.경로를 포함한 이미지 파일 이름을 Customer 객체에 저장
					b.setImg("/img/" + fileName);					
				}else { //업로드된 파일이 없을때
					if(origin_file == null || origin_file.equals("")) {
					}else {
						b.setImg(origin_file);
					}
				}
				
				dao.updateCustomer(b);
				
	 		}catch (Exception e) {
	 			e.printStackTrace();
	 			
	 			try {
					// 쿼리스트링의 한글깨짐을 방지하기 위해 UTF-8로 인코딩
					String encodeName = URLEncoder.encode("게시물이 정삭적으로 수정되지 않았습니다!", "UTF-8");
					return "redirect:/view?id="+ b.getId() + "&error="+ encodeName;
				} catch (UnsupportedEncodingException e1) {				
					e1.printStackTrace();
				}
	 		}
	 		return "redirect:/view?id=" + b.getId();
		
	}
	
	// 게시글을 삭제해준다.
	public String deleteCustomer(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			dao.deleteCustomer(id);
		} catch(Exception e) {
			e.printStackTrace();
			
			try {
				// 쿼리스트링의 한글깨짐을 방지하기 위해 UTF-8로 인코딩
				String encodeName = URLEncoder.encode("게시물이 정삭적으로 삭제 되지 않았습니다!", "UTF-8");
				return "redirect:/index?error=" + encodeName;
			} catch (UnsupportedEncodingException e1) {				
				e1.printStackTrace();
			}
		}
		return "redirect:/index";
	}
	
	//파일에서 이미지명을 추출하는 메소드
	private String getFilename(Part part) {
	    String fileName = null;   
	    // 파일이름이 들어있는 헤더 영역을 가지고 옴
	    String header = part.getHeader("content-disposition");
	    System.out.println("Header => "+header);
	    
	    //파일 이름이 들어있는 속성부분의 시작위치(인덱스 번호)를 가지고옴
	    int start = header.indexOf("filename=");
	    //쌍따옴표 사이의 값 부분만 가지고 옴
	    fileName = header.substring(start+10,header.length()-1);        
	    ctx.log("파일명:"+fileName);        
	    return fileName; 
	 }
	}
