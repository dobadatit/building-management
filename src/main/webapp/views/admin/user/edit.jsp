<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="buildingAPI" value="/api-building" />
<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>

			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
				</li>
				<li class="active">Danh Sách Tòa Nhà</li>
			</ul>
			<!-- /.breadcrumb -->


		</div>

		<div class="page-content">
			<div class="ace-settings-container" id="ace-settings-container">


				<div class="ace-settings-box clearfix" id="ace-settings-box">
					<div class="pull-left width-50">
						<div class="ace-settings-item">
							<div class="pull-left">
								<select id="skin-colorpicker" class="hide">
									<option data-skin="no-skin" value="#438EB9">#438EB9</option>
									<option data-skin="skin-1" value="#222A2D">#222A2D</option>
									<option data-skin="skin-2" value="#C6487E">#C6487E</option>
									<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>
							<span>&nbsp; Choose Skin</span>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-navbar" /> <label class="lbl"
								for="ace-settings-navbar"> Fixed Navbar</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-sidebar" /> <label class="lbl"
								for="ace-settings-sidebar"> Fixed Sidebar</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-breadcrumbs" /> <label class="lbl"
								for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-rtl" /> <label class="lbl"
								for="ace-settings-rtl"> Right To Left (rtl)</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-add-container" /> <label class="lbl"
								for="ace-settings-add-container"> Inside <b>.container</b>
							</label>
						</div>
					</div>
					<!-- /.pull-left -->

					<div class="pull-left width-50">
						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-hover" /> <label class="lbl"
								for="ace-settings-hover"> Submenu on Hover</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-compact" /> <label class="lbl"
								for="ace-settings-compact"> Compact Sidebar</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-highlight" /> <label class="lbl"
								for="ace-settings-highlight"> Alt. Active Item</label>
						</div>
					</div>
					<!-- /.pull-left -->
				</div>
				<!-- /.ace-settings-box -->
			</div>
			<!-- /.ace-settings-container -->

			<div class="page-header">
				<h1>
					Danh Sách Tòa Nhà <small> <i
						class="ace-icon fa fa-angle-double-right"></i> Edit Building
					</small>
				</h1>
			</div>
			<!-- /.page-header -->

			<div class="row">
				<form class="form-horizontal" role="form" id="formEditUser">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="fullName">Tên Khách Hàng </label>
						<div class="col-sm-9">
							<input type="text" id="fullName" name="fullName"
								class="form-control" value="${model.fullName }" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="phoneNumber"> Di Động </label>
						<div class="col-sm-9">
							<input type="tel" id="phoneNumber" name="phoneNumber"
								class="form-control" value="${model.phoneNumber}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="email">
							Email </label>
						<div class="col-sm-9">
							<input type="email" id="email" name="email" class="form-control"
								value="${model.email}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="userName"> NickName </label>
						<div class="col-sm-9">
							<input type="text" id="userName" name="userName"
								class="form-control" value="${model.userName}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="password"> Password </label>
						<div class="col-sm-9">
							<input type="password" id="password" name="password"
								class="form-control" value="${model.password}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="demand"> Demand </label>
						<div class="col-sm-9">
							<input type="text" id="demand" name="demand" class="form-control"
								value="${model.demand}" />
						</div>
					</div>
					<input type="hidden" id="id" name="id" value="${model.id}">
					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<c:if test="${not empty model.id}">
								<button class="btn btn-info" type="submit" id="btnAddUser">
									<i class="ace-icon fa fa-check bigger-110"></i> Cập Nhật
								</button>
							</c:if>
							<c:if test="${empty model.id}">
								<button class="btn btn-info" type="submit" id="btnAddUser">
									<i class="ace-icon fa fa-check bigger-110"></i> Thêm
								</button>
							</c:if>
							<button class="btn" type="reset">
								<i class="ace-icon fa fa-undo bigger-110"></i> Reset
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- /.page-content -->
	</div>
</div>

