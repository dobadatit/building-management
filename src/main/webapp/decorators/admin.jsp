<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/css/bootstrap.min.css' />" />
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/font-awesome/4.2.0/css/font-awesome.min.css' />" />

<!-- text fonts -->
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/fonts/fonts.googleapis.com.css' />" />

<!-- ace styles -->
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/css/ace.min.css' />"
	class="ace-main-stylesheet" id="main-ace-style" />

<!-- ace settings handler -->
<script
	src="<c:url value='/template/admin/assets/js/ace-extra.min.js' />"></script>
<script
	src="<c:url value='/template/admin/assets/js/jquery.2.1.1.min.js' />"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
<script
	src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>
<script
	src="<c:url value='/template/paging/jquery.twbsPagination.min.js' />"></script>
<script src="<c:url value='/template/paging/Gruntfile.js' />"></script>



</head>
<body class="no-skin">

	<!-- header -->
	<%@ include file="/common/admin/header.jsp"%>
	<!-- END header -->

	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<!-- menu -->
		<%@ include file="/common/admin/menu.jsp"%>
		<!-- END menu -->

		<dec:body />


		<!-- footer -->
		<%@ include file="/common/admin/footer.jsp"%>
		<!-- END footer -->
		
	</div>
	<script
		src="<c:url value='/template/admin/assets/js/ace-extra.min.js' />"></script>
	<script
		src="<c:url value='/template/admin/assets/js/jquery-ui.custom.min.js' />"></script>
	<script
		src="<c:url value='/template/admin/assets/js/jquery.ui.touch-punch.min.js' />"></script>
	<script
		src="<c:url value='/template/admin/assets/js/jquery.easypiechart.min.js' />"></script>
	<script
		src="<c:url value='/template/admin/assets/js/jquery.sparkline.min.js' />"></script>
	<script
		src="<c:url value='/template/admin/assets/js/jquery.flot.min.js' />"></script>
	<script
		src="<c:url value='/template/admin/assets/js/jquery.flot.pie.min.js' />"></script>
	<script
		src="<c:url value='/template/admin/assets/js/jquery.flot.resize.min.js' />"></script>
	<script
		src="<c:url value='/template/admin/assets/js/bootstrap.min.js' />"></script>
	<script
		src="<c:url value='/template/admin/assets/js/ace-elements.min.js' />"></script>
	<script src="<c:url value='/template/admin/js/building-list.js' />"></script>
	<script src="<c:url value='/template/admin/js/user-list.js' />"></script>
	<script src="<c:url value='/template/admin/js/user-sua.js' />"></script>
	<script src="<c:url value='/template/admin/js/building-edit.js' />"></script>
	
	<script
		src="<c:url value='/template/admin/assets/js/jquery-ui.min.js'/>"></script>

</body>
</html>