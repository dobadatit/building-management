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
				<form class="form-horizontal" role="form" id="formEdit">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="name">Tên
							tòa nhà </label>
						<div class="col-sm-9">
							<input type="text" id="name" name="name" class="form-control"
								value="${model.name}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="district"> Quận</label>
						<div class="col-sm-9">
							<select id="district" name="district" class="form-control">
								<option value="" selected>Choose...</option>
								<c:forEach var="item" items="${districts}">
									<option value="${item.key }"
										${item.key == model.district ? 'selected' : '' }>${item.value }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="numberOfBasement"> Số tầng hầm </label>
						<div class="col-sm-9">
							<input type="number" id="numberOfBasement"
								name="numberOfBasement" class="form-control"
								value="${model.numberOfBasement }" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="buildingArea"> Diện tích sàn </label>
						<div class="col-sm-9">
							<input type="number" id="buildingArea" name="buildingArea"
								class="form-control" value="${model.buildingArea }" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="costrent"> Giá thuê </label>

						<div class="col-sm-9">
							<input type="number" id="costrent" name="costrent"
								class="form-control" value="${model.costrent}" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="ward">
							Phường </label>

						<div class="col-sm-9">
							<input type="text" id="ward" name="ward" class="form-control"
								value="${model.ward}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="street">Đường</label>

						<div class="col-sm-9">
							<input type="text" id="street" name="street" class="form-control"
								value="${model.street}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="areaRent"> Diện tích </label>

						<div class="col-sm-9">
							<input type="text" id="areaRent" name="areaRent"
								class="form-control" value="${model.areaRent}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="managerName"> Tên quản lý </label>

						<div class="col-sm-9">
							<input type="text" id="managerName" name="managerName"
								class="form-control" value="${model.managerName}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="managerPhone"> Điện thoại quản lý </label>

						<div class="col-sm-9">
							<input type="number" id="managerPhone" name="managerPhone"
								class="form-control" value="${model.managerPhone}" />
						</div>
					</div>
					<!-- input id -->
					<input type="hidden" value="${model.id}" name="id" />
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="buildingTypes"> Loại tòa nhà </label>
						<div class="col-sm-9">
							<c:set var="string1" value="${model.buildingTypes}" />
							<c:set var="string3" value="${fn:join(string1, ',')}" />
							<c:forEach var="item" items="${buildingTypes}">
								<label class="checkbox-inline"> <input type="checkbox"
									id="buildingTypes" name="buildingTypes" value="${item.key}"
									${fn:contains(string3, item.key ) ? 'checked' : ''}>
									${item.value }
								</label>
							</c:forEach>
						</div>
					</div>
					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<c:if test="${not empty model.id}">
								<button class="btn btn-info" type="submit" id="btnAddBuilding">
									<i class="ace-icon fa fa-check bigger-110"></i> Cập Nhật
								</button>
							</c:if>
							<c:if test="${empty model.id}">
								<button class="btn btn-info" type="submit" id="btnAddBuilding">
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

