
function chkForm() {
	var f = document.frm; //form 태그 요소
	
	if(f.name.value == '') {
		alert("이름을 입력해주세요.");
		return false; //함수 종료시킴(걸리면 뒤에 있는 코드 실행할 필요 x)
	}
	
	if(f.address.value == '') {
		alert("주소를 입력해주세요.");
		return false; //함수 종료시
	}
	
	if(f.phone.value == '') {
		alert("전화번호를 입력해주세요.");
		return false; //함수 종료시
	}
	
	
	
	if(f.age.value == '') {
		alert("나이를 입력해주세요.");
		return false; //함수 종료시
	}
	
	if(f.point.value == '') {
		alert("포인트를 입력해주세요.");
		return false; //함수 종료시
	}
	
	f.submit(); //서버로 폼태그 안의 데이터 전송
}

function chkDelete(id) {
	const result= confirm("삭제하시겠습니까?");
	
	if(result) {
		const url = location.origin;
		
		// 페이지 이동(request)
		location.href = url + "/jsp_customer/delete?id=" + id;
	} else {
		return false;
	}
}