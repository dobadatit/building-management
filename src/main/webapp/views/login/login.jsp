	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<form action="<c:url value ="/dang-nhap"/>" method="post" class="signin-form">
	<div class="form-input">
		<input type="text" name="userName" placeholder="Username" required
			autofocus>
	</div>
	<div class="form-input">
		<input type="password" name="password" placeholder="Password"
			required>
	</div>
	<input type="hidden" value="login" name="action">
	<button type="submit" class="btn btn-primary theme-button mt-4">Log	in</button>
</form>