<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원가입" name="title"/>
</jsp:include>
<style>
.guide{
	display:none;
}
#memberEnrollContainer{
	border: 1px solid #000;
	margin-top : 200px;
	width : 450px;
	height : 700px;
}
#memberEnrollTbl th{
	padding: 10px 5px;
}
#memberEnrollTbl input:not([type="checkbox"]){
	width : 100%;
}
</style>
<div id="memberEnrollContainer" class="mx-auto text-center">
	<form action="${pageContext.request.contextPath}/member/memberEnroll.do" name="memberEnrollFrm" method="post">
		<h1 class="text-brand mt-3">Dev<span class="color-b">Run</span></h1>
		<span class="font-weight-light">더 나은 개발라이프를 위한 적절한 해결책</span>
		<hr />
		<table id="memberEnrollTbl" class="mx-auto">
			<tr>
				<th>아이디</th>
				<td>
					<div id="memberId-container">
						<input type="text" name="id" id="id" placeholder="4글자 이상" required/>
						<span class="guide ok">사용 가능한 아이디입니다.</span>
						<span class="guide error">사용 중인 아이디입니다.</span>
						<input type="hidden" name="idValid" value="0" />
					</div>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="password" id="password" required/>
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" name="password" id="passwordCheck" required/>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text" name="name" id="name" />
				</td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td>
					<input type="text" name="nickname" id="nickname" />
				</td>
			</tr>
			<tr>
				<th>생일</th>
				<td>
					<input type="date" name="birthday" id="birthday" />
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="email" name="email" id="email" />
				</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>
					<input type="tel" name="phone" id="phone" />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-left checkbox-group">
				  <input type="checkbox" id="checkAll" >
				  <label for="checkAll" class="">전체 동의</label>
				  <br />
				  <input type="checkbox" id="check1" class="normal" >
				  <label for="check1"><span class="text-danger">(필수)</span>개인정보 처리방침 동의</label>
				  <br />
				  <input type="checkbox" id="check2" class="normal" >
				  <label for="check2"><span class="text-danger">(필수)</span>서비스 이용약관 동의</label>
				  <br />
				  <input type="checkbox" id="check3" class="normal" name="smsYn">
				  <label for="check3"><span>(선택)</span>마케팅 수신 동의</label>
				</td>
			</tr>
		</table>
		<hr />
		<button type="button" class="btn btn-primary">가입</button>
		<button type="button" class="btn btn-primary">취소</button>
	</form>
</div>
<script>
//체크박스 전체 선택
$(".checkbox-group").on("click", "#checkAll", ((e)=>{
  let checked = $(e.target).is(":checked");

  if(checked){
  	$(e.target).parents(".checkbox-group").find('input').prop("checked", true);
  } else {
  	$(e.target).parents(".checkbox-group").find('input').prop("checked", false);
  }
}));

//체크박스 개별 선택
$(".checkbox-group").on("click", ".normal", ((e)=>{
    let isChecked = true;
    
    $(".checkbox-group .normal").each((e)=>{
        isChecked = isChecked && $(e.target).is(":checked");
    });
    
    $("#checkAll").prop("checked", isChecked);
}));

$(id).keyup((e)=>{
	const $id = $(e.target);
	const $error = $(".guide.error");
	const $ok = $(".guide.ok");
	const $idValid = $(idValid);
	
	
})
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
