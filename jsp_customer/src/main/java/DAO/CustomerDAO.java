package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.Part;

import DTO.Customer;

public class CustomerDAO {
	final static String JDBC_DRIVER="oracle.jdbc.driver.OracleDriver";
	final static String JDBC_URL="jdbc:oracle:thin:@localhost:1521:xe";

	//데이터베이스 연결 open 메소드 생성
	public Connection open() {
		Connection conn = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "test", "test1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn; //데이터베이스 연결 객체를 리턴
	}
	
	//게시판 리스트 가져오는 메소드
		public ArrayList<Customer> getList() throws Exception {
			Connection conn = open(); //DB 커넥션 열기
			ArrayList<Customer> customerList = new ArrayList<>(); //Board 객체를 저장할 ArrayList
			
			String sql = "SELECT id, name, address,phone,gender, age, img, point, grade FROM CUSTOMER ORDER BY ID"; // 쿼리문
			PreparedStatement pstmt = conn.prepareStatement(sql); //쿼리문 등록
			ResultSet rs = pstmt.executeQuery(); //쿼리문 실행 => 데이터베이스 결과 저장
			
			
			/*
			 try {} catch(Exception e) {..}
			 finally {
			    conn.close();
			    pstmt.close();
			    rs.close();
			 }
			  */
			//Exception 에서 사용하는 리소스 자동 닫기(try-with-resource)
			try(conn; pstmt; rs){
				while (rs.next()) {
					Customer b = new Customer();
					
					b.setId(rs.getInt("id"));
					b.setName(rs.getString("name"));
					b.setAddress(rs.getString("address"));
					b.setPhone(rs.getString("phone"));
					b.setGender(rs.getString("gender"));
					b.setAge(rs.getInt("age"));
					b.setImg(rs.getString("img"));
					b.setPoint(rs.getInt("point"));
					b.setGrade(rs.getString("grade"));
					
					customerList.add(b);
				}
				return customerList;
			}
			
}

	//게시판 내용 가져오는 메소드
	public Customer getView(int id) throws Exception {
		Connection conn = open();
		Customer b = new Customer();
		
		String sql = "SELECT id, name, address,phone,gender, age, img, point, grade FROM CUSTOMER WHERE id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id); //물음표에 들어갈 값을 반드시 먼저 지정
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs){
			while(rs.next()) {
				
				b.setId(rs.getInt("id"));
				b.setName(rs.getString("name"));
				b.setAddress(rs.getString("address"));
				b.setPhone(rs.getString("phone"));
				b.setGender(rs.getString("gender"));
				b.setAge(rs.getInt("age"));
				b.setImg(rs.getString("img"));
				b.setPoint(rs.getInt("point"));
				b.setGrade(rs.getString("grade"));
			}
			return b;
		}
	}
	
	//게시판 글등록 메소드
		public void insertCustomer(Customer b) throws Exception {

			Connection conn = open();
			String sql = "insert into customer values(customer_seq.nextval, ?, ?, ?,?, ? ,?, ?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try (conn; pstmt) {
				pstmt.setString(1, b.getName());
				pstmt.setString(2, b.getAddress());
				pstmt.setString(3, b.getPhone());
				pstmt.setString(4, b.getGender());
				pstmt.setInt(5, b.getAge());
				pstmt.setString(6, b.getImg());
				pstmt.setInt(7, b.getPoint());
				pstmt.setString(8, b.getGrade());
				pstmt.executeUpdate();
			}
		}
	
	// 게시판 글수정 메소드
	public void updateCustomer(Customer b) throws Exception {
        Connection conn = open();
		
		String sql = "update customer set name = ?, address = ?,phone = ?, gender = ? ,age = ?, img = ?, point = ?, grade =? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		
		try (conn; pstmt) {
			
			pstmt.setString(1, b.getName());
			pstmt.setString(2, b.getAddress());
			pstmt.setString(3, b.getPhone());
			pstmt.setString(4, b.getGender());
			pstmt.setInt(5, b.getAge());
			pstmt.setString(6, b.getImg());
			pstmt.setInt(7, b.getPoint());
			pstmt.setString(8, b.getGrade());
			pstmt.setInt(9, b.getId());
			
			//수정된 글이 없을 경우
			if (pstmt.executeUpdate() != 1) {
				throw new Exception("수정된 글이 없습니다.");
			} 
			
		}
	}
	
	// 게시판 글삭제 메소드
		public void deleteCustomer(int id) throws Exception{
			Connection conn = open();
			String sql = "DELETE FROM Customer WHERE id = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			
			try(conn; pstmt) {
				pstmt.setInt(1, id);
				
				// 삭제된 글이 없을 경우
				if(pstmt.executeUpdate() != 1) {
					throw new Exception("삭제된 글이 없습니다.");
				};
			}
			
		}
	
	
}























