
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="buildingURL" value="/admin-building" />
<c:url var="userAPI" value="/api-user" />

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
						class="ace-icon fa fa-angle-double-right"></i> List Building
					</small>
				</h1>
			</div>
			<!-- /.page-header -->

			<div class="row">
				<form action="<c:url value='/admin-building'/>" method="get"
					id="formSearchBuilding">
					<div class="widget-box">
						<div class="widget-header">
							<h4 class="widget-title">Tìm kiếm</h4>
							<div class="widget-toolbar">
								<a href="#" data-action="collapse"> <i
									class="ace-icon fa fa-chevron-up"></i>
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<div class="row">

									<div class="col-xs-12">
										<!-- PAGE CONTENT BEGINS -->
										<div class="col-sm-6">
											<div>
												<label for="name">Tên tòa nhà</label> <input type="text"
													id="name" name="name" class="form-control"
													value="${model.name }">
											</div>
										</div>
										<div class="col-xs-12 col-sm-6">
											<div>
												<label for="buildingArea">Diện tích sàn </label> <input
													type="number" id="buildingArea" name="buildingArea"
													class="form-control" value="${model.buildingArea }">
											</div>
										</div>
									</div>
									<div class="col-xs-12">
										<div class="col-xs-12 col-sm-6">
											<div class="form-group " style="margin: 0px;">
												<label for="inputState">Quận hiện có</label> <select
													id="district" name="district" class="form-control">
													<option value="" selected>Choose...</option>
													<c:forEach var="item" items="${districts}">
														<option value="${item.key }"
															${item.key == model.district ? 'selected' : '' }>${item.value }</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-xs-12 col-sm-6">
											<div>
												<label for="ward">Phường</label> <input type="text"
													id="ward" name="ward" class="form-control"
													value="${model.ward }">
											</div>
										</div>

									</div>
									<div class="col-xs-12">
										<div class="col-xs-12 col-sm-6">
											<div>
												<label for="numberOfBasement">Số tầng hầm </label> <input
													type="number" id="numberOfBasement" name="numberOfBasement"
													class="form-control">
											</div>
										</div>
										<div class="col-xs-12 col-sm-6">
											<div>
												<label for="street">Đường </label> <input type="text"
													id="street" name="street" class="form-control"
													value="${model.street }">
											</div>
										</div>
									</div>
									<div class="col-xs-12">
										<!-- PAGE CONTENT BEGINS -->
										<div class="col-sm-3">
											<div>
												<label for="areaRentFrom">Diện tích từ</label> <input
													type="text" id="areaRentFrom" name="areaRentFrom"
													class="form-control" value="${model.areaRentFrom}">
											</div>
										</div>
										<div class="col-xs-12 col-sm-3">
											<div>
												<label for="areaRentTo">Diện tích đến </label> <input
													type="number" id="areaRentTo"  onsubmit ="return false" name="areaRentTo"
													class="form-control" value="${model.areaRentTo}">
											</div>
										</div>
										<div class="col-xs-12 col-sm-3">
											<div>
												<label for="costRentFrom">Giá thuê từ </label> <input
													type="number" id="costRentFrom"  onsubmit ="return false" name="costRentFrom"
													class="form-control" value="${model.costRentFrom }">
											</div>
										</div>
										<div class="col-xs-12 col-sm-3">
											<div>
												<label for="costRentTo">Giá thuê đến </label> <input
													type="number" id="costRentTo" name="costRentTo"
													class="form-control" value="${model.costRentTo }">
											</div>
										</div>
									
									</div>
									<div class="col-xs-12">
										<div class="col-xs-12 col-sm-4">
											<div>
												<label for="managerName">Tên quản lý </label> <input
													type="text" id="managerName" name="managerName"
													class="form-control">
											</div>
										</div>
										<div class="col-xs-12 col-sm-4">
											<div>
												<label for="managerPhone">Điện thoại quản lý </label> <input
													type="number" id="managerPhone" name="managerPhone"
													class="form-control">
											</div>
										</div>
										<div class="col-xs-12 col-sm-4">
											<div class="form-group ">
												<label for="managerName">Chọn nhân viên phụ trách </label> <select
													id="managerName" name="managerName" class="form-control">
													<option selected>Choose...</option>
													<option>Đạt</option>
													<option>Linh</option>
													<option>Vy</option>
													<option>Đỉnh</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-xs-12">
										<div class="col-xs-12 col-sm-4">
											<c:forEach var="item" items="${buildingTypes}">
												<label class="checkbox-inline"> <input
													type="checkbox" id="buildingTypes" name="buildingTypes"
													value="${item.key}"
													${fn:contains(fn.join(model.buildingTypes,','),item.key) ? 'checked' : '' } />
													${item.value }
												</label>
											</c:forEach>
											<div class="col-xs-12 col-sm-4"></div>
											<!-- PAGE CONTENT ENDS -->
										</div>
										<!-- /.col -->
										<div class="col-xs-12">
											<div class="col-xs-12 col-sm-4">
												<button
													style="border: 0; margin-left: -12px; margin-top: 12px; padding: 8px 18px"
													class="btn btn-info" type="submit" id="btnSearchBuilding">
													Tìm kiếm</button>
											</div>
										</div>
									</div>
									<!-- gán parameter bằng Input  -->
									<input type="hidden" value="LIST" name="action" /> <input
										type="hidden" value="1" id="page" name="page" /> <input
										type="hidden" value="4" id="limit" name="limit" />

								</div>
							</div>
						</div>
						<!-- /.row -->
					</div>
					<!-- /.page-content -->
				</form>
			</div>
			<div class="row">

				<div class="col-xs-12">
					<div class="pull-right">
						<a title="Thêm tòa nhà" class="btn btn-white btn-info btn-bold"
							data-toggle="tooltip"
							href="<c:url value='/admin-building?action=EDIT'/>"> <i
							class="fa fa-plus-circle"></i>
						</a>
						<button class="btn btn-white btn-info btn-bold"
							data-toggle="tooltip" title="Xóa tòa nhà" id="btnDeleteBuilding">
							<i class=" fa fa-trash"></i>
						</button>
					</div>
				</div>
			</div>
			<div class="row">
					<div class="row">
						<div class="col-xs-12">
							<table id="buildingList"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th></th>
										<th>Tên sản phẩm</th>
										<th>Địa chỉ</th>
										<th>Tên quản lý</th>
										<th>Số điện thoại quản lý</th>
										<th>Diện tích sàn</th>
										<th>Diện tích trống</th>
										<th>Giá thuê</th>
										<th>Giá thuê</th>
										<th>Diện tích thuê</th>
										<th>Phí dịch vụ</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${buildings}">
										<tr>
											<td><input type="checkbox" id="checkbox_${item.id}"
												value="${item.id}"></td>

											<td>${item.name}</td>
											<td>${item.street},${item.ward},${item.district}</td>
											<td>${item.managerName}</td>
											<td>${item.managerPhone}</td>
											<td>${item.buildingArea}</td>
											<td>${item.type}</td>
											<td>${item.costrent}</td>
											<td></td>
											<td>${item.areaRent}</td>
											<td>
												<button class="btn btn-xs btn-info" data-toggle="tooltip"
													title="Giao tòa nhà"
													onclick="assignmentBuilding(${item.id})">
													<i class="fa fa-eye" aria-hidden="true" id="button"></i>
												</button> <c:url value="/admin-building" var="editBuilding">
													<c:param name="action" value="EDIT" />
													<c:param name="id" value="${item.id}" />
													
x												</c:url> <a href="${editBuilding}" class="btn btn-xs btn-info"
												data-toggle="tooltip" title="Cập nhật tòa nhà"> <i
													class="fa fa-pencil" aria-hidden="true"></i>
											</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<ul style="  margin-left: 320px;" class="pagination" id="pagination"></ul>
							<input type="hidden" value="LIST" name="action" /> <input
							type="hidden" value="1" id="page" name="page" /> <input
							type="hidden" value="4" id="limit" name="limit" />

						</div>
					</div>
			
			</div>
		</div>

	</div>
</div>

<script type="text/javascript">
	var totalPages = ${model.totalPage};
	var currentPage = ${model.page};
	var limit =4;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 3,
        	startPage: currentPage,
            onPageClick: function (event, page) {
                console.info(page + ' (from options)');
            	if (currentPage != page) {
            	$('#page').val(page);
            	$('#limit').val(limit);
				$('#formSubmit').submit();
				$('#formSearchBuilding').submit();
			
            	}
            }
        }).on('page', function (event, page) {
            console.info(page + ' (from event listening)');
        });
    });
     
</script>

<!-- START modal giao toa nha cho nhan vien quan ly -->
<div class="modal fade" id="assignmentBuildingModal" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Danh Sách Nhân Viên</h4>
			</div>
			<div class="modal-body">
				<table class="table table-bordered" id="staffList">
					<thead>
						<tr>
							<th>Chọn nhân viên</th>
							<th>Tên nhân viên</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
				<input type="hidden" id="buildingId" name="buildingId" value="">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" id="btnAssignBuilding">Giao
					tòa nhà</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
			</div>
		</div>
	</div>
</div>
