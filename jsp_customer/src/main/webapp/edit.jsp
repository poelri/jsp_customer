<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="./css/style.css"/>
</head>
<body>
    <div class="board_wrap">
     <header>
			<div>
				<h1>고객정보수정 페이지</h1>
			</div>			
	</header>
	
	<main>	
	
     <div class="board_write_wrap"  style="width:50rem;">
       <form name="frm" method="post" action="update?id=${customer.id}"  enctype="multipart/form-data">
         <div class="board_write">  
               
           <div class="name details">
               <div class="person_info_write">이름</div>
               <div>
               		<input type="text" name="name" maxlength="10" value="${customer.name}" />
             </div>             
           </div>
           
           <div class="address details">
               <div class="person_info_write">주소</div>
               <div>
               		<input type="text" name="address" maxlength="100" value="${customer.address}" />
               </div>
           </div>
           
           <div class="phone  details">
               <div class="person_info_write">핸드폰번호</div>
               <div>
               		<input type="text" name="phone" maxlength="20" value="${customer.phone}" />
               </div>
           </div>  
           
			<div class="gender details">
					<div class="person_info_write">성별</div>
					<div class="form-check">
					  <input class="form-check-input" type="radio" name="gender" id="flexRadioDefault1" value="여자" ${customer.gender eq '여자' ? 'checked' : ''}>
					  <label class="form-check-label" for="flexRadioDefault1">
					    여자
					  </label>
					</div>
					<div class="form-check">
					  <input class="form-check-input" type="radio" name="gender" id="flexRadioDefault2" checked value="남자" ${customer.gender eq '남자' ? 'checked' : ''}>
					  <label class="form-check-label" for="flexRadioDefault2">
					    남자
					  </label>
					</div>
					</div>	           
			</div>		
				
			<div class="age details">
               <div class="person_info_write">나이</div>
               <div>
               		<input type="text" name="age" maxlength="3" value="${customer.age}" />
               </div>
           </div> 

			<div class="point  details">
               <div class="person_info_write">포인트</div>
               <div>
               		<input type="text" name="point" maxlength="20" value="${customer.point}" />
               </div>
           </div> 
           
           	<div class="grade  details">
					<div  class="person_info_write">등급</div>
					<div>
						<select class="form-select" aria-label="Default select example" name="grade">
						   	  <option value="DIAMOND" ${customer.grade eq 'DIAMOND' ? 'selected' : ''}>DIAMOND</option>
							  <option value="VVIP" ${customer.grade eq 'VVIP' ? 'selected' : ''}>VVIP</option>
							  <option value="VIP" ${customer.grade eq 'VIP' ? 'selected' : ''}>VIP</option>
							  <option value="GOLD" ${customer.grade eq 'GOLD' ? 'selected' : ''}>GOLD</option>
							  <option value="SilVER" ${customer.grade eq 'SilVER' ? 'selected' : ''}>SILVER</option>
						</select>
					</div>           
			</div>
	           <div style="padding-top:10px;">
	             <label style="font-size: 1.4rem; padding-right: 20px;" for="file">이미지 업로드</label>
	             <input type="file" name="file" id="file" />
	             <br />
					<c:if test="${customer.img != null }">
						<img src="${customer.img}" alt="업로드 이미지" width="100"/>
					</c:if>
					<input type="hidden" name="origin_file" value="${customer.img}" />	          
	           </div>                      
         </div>
       </form>  
       
             <div class="twobutton"> 
		       <div class="allbutton">
		         <a onclick = "chkForm(); return false;" class="on">수정</a>
		       </div> 
		       
		       <div class="allbutton">
		         <a href="index">취소</a>
		        </div> 
        	</div>
     </main>	
   </div>
    <script type="text/javascript" src="./js/script.js"></script>
</body>
</html>