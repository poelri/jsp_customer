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
	<div class="wrap">
		<header>
			<div>
				<h1>고객리스트</h1>
			</div>			
		</header>
		
		<main>
			<div class="customerBoard">
				<table class="table table-danger table-striped  table-hover">		  
				  <thead>
				    <tr>	   
				      <th scope="col" >아이디</th>
				      <th scope="col">이름</th>
				      <th scope="col" >주소</th>
				      <th scope="col" >핸드폰</th>
				      <th scope="col">포인트</th>
				      <th scope="col" >등급</th>
				    </tr>	    
				 </thead>			 
				  <tbody>
				  <c:forEach var="customer" items="${customerList}">
			          <tr>
			            <td  scope="row">${customer.id}</td>
			            <td ><a href="./view?id=${customer.id}">${customer.name}</a></td>
			            <td>${customer.address}</td>
			            <td>${customer.phone}</td>
			            <td>${customer.point}</td>
			            <td>${customer.grade}</td>
			          </tr>         
				     </c:forEach>
				  </tbody>   
			     </table> 
			     
			     <div class="bt_wrap bt_list">
	       			<a href="write">고객등록</a>
	     		</div> 
     		</div>   
		</main>
		
		<footer>
			<nav aria-label="Page navigation example">
			  <ul class="pagination justify-content-center">
			    <li class="page-item disabled">
			      <a class="page-link">Previous</a>
			    </li>
			    <li class="page-item"><a class="page-link" href="#">1</a></li>
			    <li class="page-item"><a class="page-link" href="#">2</a></li>
			    <li class="page-item"><a class="page-link" href="#">3</a></li>
			    <li class="page-item">
			      <a class="page-link" href="#">Next</a>
			    </li>
			  </ul>
			</nav>
		</footer>  
   </div>
   <script>
   		// request 객체에 error가 있을 경우 에러 메세지출력
   		// 쿼리스트링에 error가 있을 경우 에러메세지출력 (request 객체에 포함되므로)
   		<c:if test="${error != null}">  // 에러가 null이 아니라면
   			alert("${error}");
   		</c:if>
   		
   		// 쿼리스트링에 error가 있을 경우 에러메세지출력
   		<c:if test="${param.error != null}">  // 에러가 null이 아니라면
   			alert("${param.error}");
   		</c:if>
   </script>
</body>
</html>