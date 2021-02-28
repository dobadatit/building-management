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
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">Dashboard</li>
                </ul><!-- /.breadcrumb -->


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
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar"/>
                                <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar"/>
                                <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs"/>
                                <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl"/>
                                <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container"/>
                                <label class="lbl" for="ace-settings-add-container">
                                    Inside
                                    <b>.container</b>
                                </label>
                            </div>
                        </div><!-- /.pull-left -->

                        <div class="pull-left width-50">
                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover"/>
                                <label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact"/>
                                <label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
                            </div>

                            <div class="ace-settings-item">
                                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight"/>
                                <label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
                            </div>
                        </div><!-- /.pull-left -->
                    </div><!-- /.ace-settings-box -->
                </div><!-- /.ace-settings-container -->

                <div class="page-header">
                    <h1>
                        Dashboard
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            overview &amp; stats
                        </small>
                    </h1>
                </div><!-- /.page-header -->

                <div class="row">
                    <form class="form-horizontal" role="form" id="formEdit">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="name"> Tên tòa nhà
                             </label>
                            <div class="col-sm-9">
                                <input type="text" id="name" name="name" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="numberOfBasement"> Quận </label>
                            <div class="col-sm-9">
                                <div class="form-group col-sm-6">
                                    <select id="inputState" class="form-control">
                                        <option selected>Choose...</option>
                                        <option>Quận 1</option>
                                        <option>Quận 3</option>
                                        <option>Quận Tân Bình</option>
                                        <option>Quận Thủ Đức</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="numberOfBasement"> Số tầng
                                hầm </label>

                            <div class="col-sm-9">
                                <input type="text" id="numberOfBasement" name="numberOfBasement" class="form-control"/>
                            </div>
                        </div>
                        <!--                     check box - type building    -->
                       
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="ward"> Phường</label>
                            <div class="col-sm-9">
                                <input type="text" id="ward" name="ward"  class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="buildingArea"> Diện tích sàn </label>
                            <div class="col-sm-9">
                                <input type="text" id="buildingArea" name="buildingArea" class="form-control"/>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="costRentFrom"> Giá thuê từ </label>

                            <div class="col-sm-9">
                                <input type="text" id="costRentFrom" name="costRentFrom"  class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="costRentTo"> Giá thuê đến </label>

                            <div class="col-sm-9">
                                <input type="text" id="costRentTo"  name="costRentTo" class="form-control"/>
                                
                            </div>
                        </div>
                       <!--  <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="derection"> Hướng  </label>

                            <div class="col-sm-9">
                                <input type="text" id="derection"  class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="grade">Hạng</label>

                            <div class="col-sm-9">
                                <input type="text" id="grade"  class="form-control"/>
                            </div>
                        </div> -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="areaRentFrom"> Diện tích từ </label>

                            <div class="col-sm-9">
                                <input type="text" id="areaRentFrom" name="areaRentFrom" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="areaRentTo"> Diện tích đến </label>

                            <div class="col-sm-9">
                                <input type="text" id="areaRentTo" name="areaRentTo" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="managerName"> Tên quản lý </label>

                            <div class="col-sm-9">
                                <input type="text" id="managerName" name="managerName" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="managerPhone"> Điện thoại quản lý </label>

                            <div class="col-sm-9">
                                <input type="text" id="managerPhone" name="managerPhone" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="buildingTypes"> Loại tòa nhà </label>
                            <!--                         check id = buildingTypes-->
                            <div class="col-sm-9" id="">
                                <lable class="checkbox-inline"><input name="buildingTypes" type="checkbox" value=""> Tầng trệt</lable>
                                <lable class="checkbox-inline"><input name="buildingTypes"  type="checkbox" value=""> Nguyên căn</lable>
                                <lable class="checkbox-inline"><input  name="buildingTypes"  type="checkbox" value=""> Nội thất</lable>

                            </div>
                        </div>
                         <!--  <div class="form-group">
                           <label class="col-sm-3 control-label no-padding-right"> Nhân viên phụ trách </label>
                                            <div class="form-group col-sm-6">
                                                <select id="staff" class="form-control">
                                                    <option selected>Choose...</option>
                                                    <option>Đạt</option>
                                                    <option>Linh</option>
                                                    <option>Vy</option>
                                                    <option>Đỉnh</option>
                                                </select>
                                            </div>
                          </div>-->
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="button">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    Submit
                                </button>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    Reset
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div><!-- /.page-content -->
        </div>
    </div>
