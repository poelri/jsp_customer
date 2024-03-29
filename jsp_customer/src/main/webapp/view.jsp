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
				<h1>고객정보</h1>
			</div>			
	</header>
	
	<main>
	<div style="width:100rem;">
		<div class="card text-center">
		  <div class="card-header cardtitle">
		    ${customer.name}
		  </div>
		  <div class="card-body">
		    
		    <p class="card-text">
		    <div class="board_view">
          <div class="name"></div>
          <div class="info" style="position:relative; text-align: -webkit-center;">
          <div class="person">  
	            <div class="client-details">
	              <div class="person-info">아이디</div>
	              <div  class="person-info2">${customer.id}</div>
	            </div>
	            
	            <div class="client-details">
	              <div class="person-info">주소</div>
	              <div  class="person-info2">${customer.address}</div>
	            </div>
	            
	            <div class="client-details">
	              <div class="person-info">핸드폰</div>
	              <div  class="person-info2">${customer.phone}</div>
	            </div>
	            
	            <div class="client-details">
	              <div class="person-info">성별</div>
	              <div  class="person-info2">${customer.gender}</div>
	            </div>
	            
	            <div class="client-details">
	              <div class="person-info">나이</div>
	              <div  class="person-info2">${customer.age}</div>
	            </div>
	            
	            <div class="client-details">
	              <div class="person-info">포인트</div>
	              <div  class="person-info2">${customer.point}</div>
	            </div>
	            
	            <div class="client-details">
	              <div class="person-info">고객등급</div>
	              <div  class="person-info2">${customer.grade}</div>
	            </div>
	            
	            <div class="client-details">
	              <div class="person-info">포인트</div>
	              <div  class="person-info2">${customer.point}</div>
	            </div> 
	            
	             
           </div>                    
          </div>
          <c:if test="${customer.img != null}">
            <div class="cont"><img src="${customer.img}" alt="업로드 이미지"></div>
          </c:if>
          
        </div>
		    </p>
		    
		   <div class="twobutton">
			    <div class="allbutton">
			    	<a href="index">목록</a>
			    </div>
			    <div class="allbutton">			    
			    	<a href="edit?id=${customer.id}">수정</a>
			    </div>
		    </div>
		    
		    <div class ="delete">
	           <div class="allbutton">
	              <a onclick="chkDelete(${customer.id}); return false;">삭제하기</a>
	           </div>
			</div>
		  </div>		  
		</div>
	</div>
	</main>	     
     </div>
     
     <script type="text/javascript" src="./js/script.js"></script> 
   </div> 
      
	  <script>
	   		// request 객체에 error가 있을 경우 에러 메세지출력
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