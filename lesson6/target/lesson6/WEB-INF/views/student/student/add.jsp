<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<lesson:page title="student.title.${cmd}">
    <jsp:attribute name="script">

        <link rel="stylesheet" href="static-resource/ace/assets/css/jquery.gritter.min.css" />

        <link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-editable.min.css" />




        <script src="static-resource/ace/assets/js/jquery.gritter.min.js"></script>

        <script src="static-resource/ace/assets/js/bootstrap-editable.min.js"></script>
		<script src="static-resource/ace/assets/js/ace-editable.min.js"></script>
        <script src="static-resource/ace/assets/js/jquery.iframe-transport.js"></script>
        <script src="static-resource/ace/assets/js/jquery.fileupload.js"></script>


		<script src="static-resource/ace/assets/js/bootstrap-datepicker.min.js"></script>
        <script src="static-resource/ace/assets/js/bootstrap-tag.min.js"></script>
        <script type="application/javascript">
            <c:forEach items="${admin.roles}" var="role" varStatus="status">
            var obj${status.count} = document.getElementById('roleId_${role.id}');
            if (obj${status.count}) obj${status.count}.checked = true;
            </c:forEach>
            jQuery(function ($) {
                $('.date-picker').datepicker({
                    autoclose: true,
                    format: 'yyyy-mm-dd',
                    todayHighlight: true,
                    zIndex: 999
                })
                //show datepicker when clicking on the icon
                    .next().on(ace.click_event, function () {
                    $(this).prev().focus();
                });



                editable();

                $("#password").on("blur",function () {
                    var reg = '(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,})$';

                    if($(this).val().match(reg)){
                        $("#confirm").attr('disabled',false);
                        return;
                    }else {
                        $("#confirm").attr('disabled',true);
                        layer.alert("<spring:message code='common.password.regexp'/> ");
                    }
                });

                bindTextChange( $("#emailPassword"));

                bindTextChange( $("#smtpServer"))



            });



            jQuery(document).ready(function () {
                var tag_input = $('#form-synonyms-tags');
                try {
                    tag_input.tag(
                        {
                            placeholder: tag_input.attr('placeholder')
                        }
                    )
                }
                catch (e) {
                    //display a textarea for old IE, because it doesn't support this plugin or another one I tried!
                    tag_input.after('<textarea id="' + tag_input.attr('id') + '" name="' + tag_input.attr('name') + '" rows="3">' + tag_input.val() + '</textarea>').remove();
                    //autosize($('#form-field-tags'));
                }
            });
            function doSubmit() {
                var cmd = $("#cmd").val();
                if (cmd == 'add') {
                    var pwd = $("#pwd").val();
                    var repeat = $("#repeat").val();
                    if (pwd != repeat) {
                        bootbox.alert('<spring:message code="user.info.passwordRepeatNotMatched"/>');
                        $("#pwd").select();
                        $("#pwd").focus();
                        return;
                    }
                }
                $("#admin-add-form").submit();
            };
            function checkUserNameExist() {
                var url = "manage/user/check.do?userId="+$('#userId').val();
                $.ajax({
                    url: url,
                    type: 'get',
                    success: function (result) {
                        updatePage(result);
                    }
                });
            }

            function updatePage(result) {
                if (result == true){
                    bootbox.alert('<spring:message code='users.valid.remote'/>');
                }
                if (result == false) {
                    bootbox.alert('<spring:message code='button.ok'/>');
                }
            }

            function editable() {
                $.fn.editable.defaults.mode = 'inline';
                $.fn.editableform.loading = "<div class='editableform-loading' ><i class='ace-icon fa fa-spinner fa-spin fa-2x light-blue'></i></div>";
                $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="ace-icon fa fa-check"></i></button>' +
                    '<button type="button" class="btn editable-cancel"><i class="ace-icon fa fa-times"></i></button>';

                // *** editable avatar *** //
                try {//ie8 throws some harmless exceptions, so let's catch'em

                    //first let's add a fake appendChild method for Image element for browsers that have a problem with this
                    //because editable plugin calls appendChild, and it causes errors on IE at unpredicted points
                    try {
                        document.createElement('IMG').appendChild(document.createElement('B'));
                    } catch (e) {
                        Image.prototype.appendChild = function (el) {
                        }
                    }

                    var last_gritter;
                    $('#avatar').editable({
                        type: 'image',
                        name: 'avatar',
                        value: null,
                        //onblur: 'ignore',  //don't reset or hide editable onblur?!
                        image: {
                            //specify ace file input plugin's options here
                            btn_choose: 'Change Avatar',
                            droppable: true,
                            maxSize: 2110000,//~2100Kb
                            //and a few extra ones here
                            name: 'file',//put the field name here as well, will be used inside the custom plugin
                            on_error: function (error_type) {//on_error function will be called when the selected file has a problem
                                if (last_gritter) $.gritter.remove(last_gritter);
                                if (error_type == 1) {//file format error
                                    last_gritter = $.gritter.add({
                                        title: 'File is not an image!',
                                        text: 'Please choose a jpg|gif|png image!',
                                        class_name: 'gritter-error gritter-center'
                                    });
                                } else if (error_type == 2) {//file size rror
                                    last_gritter = $.gritter.add({
                                        title: 'File too big!',
                                        text: 'Image size should not exceed 100Kb!',
                                        class_name: 'gritter-error gritter-center'
                                    });
                                }
                                else {//other error
                                }
                            },
                            on_success: function () {
                                $.gritter.removeAll();
                            }
                        },

                        url: function (params) {
                            // ***UPDATE AVATAR HERE*** //
                            //for a working upload example you can replace the contents of this function with
                            //examples/profile-avatar-update.js
                            console.log(params);
                            var deferred = new $.Deferred;

                            $("input[name='file']").prop('id', "file");
                            var value = $('#avatar').next().find('input[type=hidden]:eq(0)').val();

                            if (!value || value.length == 0) {
                                deferred.resolve();
                                return deferred.promise();
                            }

                            var data = new FormData($(".editableform")[0]);
                            $.ajax({
                                type: 'POST',
                                url: "file/upload.do",
                                dataType: 'json',
                                cache: false,
                                processData: false,    //需要正确设置此项
                                contentType: false,
                                enctype: '/form-data',    //需要正确设置此项
                                data: data,
                                success: function (data) {
                                    $('#logo').val(data.url);
                                    var form = $("#admin-add-form").serialize();
                                    $.post("manage/user/save_${cmd}.do", form, function (data) {

                                    });
                                    if ("FileReader" in window) {
                                        //for browsers that have a thumbnail of selected image
                                        var thumb = $('#avatar').next().find('img').data('thumb');
                                        if (thumb) $('#avatar').get(0).src = thumb;
                                    }

                                    deferred.resolve({'status': 'OK'});

                                    if (last_gritter) $.gritter.remove(last_gritter);
                                    last_gritter = $.gritter.add({
                                        title: '<spring:message code="prompt.update.success"/>',
                                        class_name: 'gritter-info gritter-left'
                                    });
                                },
                                error: function (XMLHttpRequest, textStatus, errorThrown) {
                                    alert('error:');
                                }
                            });

                            //dummy upload
//                        setTimeout(function () {
//                            if ("FileReader" in window) {
//                                //for browsers that have a thumbnail of selected image
//                                var thumb = $('#avatar').next().find('img').data('thumb');
//                                if (thumb) $('#avatar').get(0).src = thumb;
//                            }
//
//                            deferred.resolve({'status': 'OK'});
//
//                            if (last_gritter) $.gritter.remove(last_gritter);
//                            last_gritter = $.gritter.add({
//                                title: 'Avatar Updated!',
//                                text: 'Uploading to server can be easily implemented. A working example is included with the template.',
//                                class_name: 'gritter-info gritter-center'
//                            });
//
//                        }, 500);

                            return deferred.promise();

                            // ***END OF UPDATE AVATAR HERE*** //
                        },

                        success: function (response, newValue) {
                        }

                    });


                } catch (e) {
                }

            }

        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        <spring:message code="common.homepage"/>
                    </a>
                </li>

                <li>
                    <a href="manage/users.do">
                        <spring:message code="user.title.list"/>
                    </a>
                </li>
                <li class="active">
                    <spring:message code="user.title.${cmd}"/>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                <spring:message code="student.title.${cmd}"/>
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="javascript:history.go(-1)" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    <spring:message code="common.back"/>
                                </a>
                            </span>
                            </h3>

                            <div class="clearfix"></div>
                            <div  class="user-profile row col-sm-offset-3">
                                <div class="col-xs-12 col-sm-3 center">
                                    <div>
												<span class="profile-picture">
													<img id="avatar" height="180px" width="180px" class="editable img-responsive" alt="Logo"  src="${empty admin.logo?'static-resource/ace/assets/images/avatars/profile-pic.jpg':admin.logo}" />
												</span>
                                        <div class="space-4"></div>

                                    </div>
                                </div>
                            </div>

                            <form action="student/user/save_${cmd}.do" method="post" class="form-horizontal" role="form"
                                  id="admin-add-form">
                                <input type="hidden" value="${cmd}" id="cmd"/>
                                <input   type="hidden" id="logo" name="logo" value="${admin.logo}">

                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-3 control-label no-padding-right" for="userId">--%>
                                        <%--<spring:message code="student.birthday"/>--%>
                                    <%--</label>--%>

                                    <%--<div class="col-sm-3">--%>
                                        <%--<div class="input-group">--%>
                                            <%--<input ${empty student.id? '' : 'readonly'} type="text" autocomplete="off" id="userId"--%>
                                                                                           <%--name="userId"--%>
                                                                                           <%--value="${admin.userId}"--%>
                                                                                           <%--maxlength="20"--%>
                                                                                           <%--class="required form-control"/>--%>
                                            <%--<span class="input-group-btn">--%>
                                             <%--<button class="btn btn-sm btn-primary " onclick="checkUserNameExist();" type="button">--%>
                                                 <%--<i class="ace-icon fa fa-check-circle-o"></i>--%>
                                                 <%--<spring:message code="user.nameExist"/></button>--%>
                                             <%--</span>--%>

                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<c:if test="${empty admin.userId }">--%>
                                        <%--<label class="col-md-2 control-label no-padding-right" for="password">--%>
                                            <%--<spring:message code="user.password"/>--%>
                                        <%--</label>--%>

                                        <%--<div class="col-sm-3">--%>
                                            <%--<input type="text" autocomplete="off" id="password" maxlength="50" name="pwd"--%>
                                                   <%--value="" class="required form-control"/>--%>
                                        <%--</div>--%>
                                    <%--</c:if>--%>
                                <%--</div>--%>
                                <%--<c:if test="${empty admin}">--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="col-sm-3 control-label no-padding-right" for="pwd">--%>
                                            <%--<spring:message code="user.password"/>--%>
                                        <%--</label>--%>

                                        <%--<div class="col-sm-3">--%>
                                            <%--<input type="text" autocomplete="off" id="pwd" name="pwd" class="required form-control"/>--%>
                                        <%--</div>--%>

                                        <%--<label class="col-md-2 control-label no-padding-right" for="repeat">--%>
                                            <%--<spring:message code="user.repeat"/>--%>
                                        <%--</label>--%>

                                        <%--<div class="col-sm-3">--%>
                                            <%--<input type="text" autocomplete="off" id="repeat"--%>
                                                   <%--placeholder="<spring:message code="common.password.repeat"/>"--%>
                                                   <%--name="repeat" class="required form-control"/>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</c:if>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-3 control-label no-padding-right" for="name">--%>
                                        <%--ID--%>
                                    <%--</label>--%>

                                    <%--<div class="col-sm-3">--%>
                                        <%--<input type="text" autocomplete="off" id="id" name="name" maxlength="50" value="${student.id}"--%>
                                               <%--class="required form-control"/>--%>
                                    <%--</div>--%>

                                        <%--&lt;%&ndash;<label class="col-md-2 control-label no-padding-right" for="nameEn">&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<spring:message code="user.nameEn"/>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</label>&ndash;%&gt;--%>

                                        <%--&lt;%&ndash;<div class="col-sm-3">&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<input type="text" autocomplete="off" id="nameEn" name="nameEn" maxlength="50" value="${admin.nameEn}"&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;class="required form-control"/>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--</div>--%>

                                <%--<div class="col-sm-4">--%>
                                    <%--<div class="widget-box">--%>
                                        <%--<div class="widget-header">--%>
                                            <%--<h4 class="widget-title"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">自定义文件输入</font></font></h4>--%>

                                            <%--<div class="widget-toolbar">--%>
                                                <%--<a href="#" data-action="collapse">--%>
                                                    <%--<i class="ace-icon fa fa-chevron-up"></i>--%>
                                                <%--</a>--%>

                                                <%--<a href="#" data-action="close">--%>
                                                    <%--<i class="ace-icon fa fa-times"></i>--%>
                                                <%--</a>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>

                                        <%--<div class="widget-body">--%>
                                            <%--<div class="widget-main">--%>
                                                <%--<div class="form-group">--%>
                                                    <%--<div class="col-xs-12">--%>
                                                        <%--<label class="ace-file-input"><input type="file" id="id-input-file-2"><span class="ace-file-container" data-title="Choose"><span class="ace-file-name" data-title="No File ..."><i class=" ace-icon fa fa-upload"></i></span></span><a class="remove" href="#"><i class=" ace-icon fa fa-times"></i></a></label>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>

                                                <%--<div class="form-group">--%>
                                                    <%--<div class="col-xs-12">--%>
                                                        <%--<label class="ace-file-input ace-file-multiple"><input multiple="" type="file" id="id-input-file-3"><span class="ace-file-container" data-title="Drop files here or click to choose"><span class="ace-file-name" data-title="No File ..."><i class=" ace-icon ace-icon fa fa-cloud-upload"></i></span></span><a class="remove" href="#"><i class=" ace-icon fa fa-times"></i></a></label>--%>
                                                    <%--</div>--%>
                                                <%--</div>--%>

                                                <%--<label>--%>
                                                    <%--<input type="checkbox" name="file-format" id="id-file-format" class="ace">--%>
                                                    <%--<span class="lbl"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;"> 仅允许图像</font></font></span>--%>
                                                <%--</label>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="name">
                                        学号
                                    </label>

                                    <div class="col-sm-3">
                                        <input type="text" autocomplete="off" id="studentId" name="studentId" maxlength="50" value="${student.studentId}"
                                               class="required form-control"/>
                                    </div>

                                        <%--<label class="col-md-2 control-label no-padding-right" for="nameEn">--%>
                                        <%--<spring:message code="user.nameEn"/>--%>
                                        <%--</label>--%>

                                        <%--<div class="col-sm-3">--%>
                                        <%--<input type="text" autocomplete="off" id="nameEn" name="nameEn" maxlength="50" value="${admin.nameEn}"--%>
                                        <%--class="required form-control"/>--%>
                                        <%--</div>--%>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="name">
                                        姓名
                                    </label>

                                    <div class="col-sm-3">
                                        <input type="text" autocomplete="off" id="name" name="name" maxlength="50" value="${student.name}"
                                               class="required form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="sex">
                                        性别
                                    </label>
                                    <div class="col-sm-3">
                                        <label class="checkbox-inline">
                                            <input type="radio" name="sex" value="M"
                                                   <c:if test="${student.sex=='M'}">checked</c:if>/>&nbsp;
                                            &nbsp;男
                                        </label>
                                        &nbsp; &nbsp; &nbsp;&nbsp;
                                        <label class="checkbox-inline">
                                            <input type="radio" name="sex" value="F"
                                                   <c:if test="${student.sex=='F'}">checked</c:if>/>&nbsp;
                                            &nbsp;女
                                        </label>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="birthday">
                                        日期选择器
                                    </label>
                                    <div class="col-sm-3">
                                        <input class="form-control date-picker" id="id-date-picker-1" type="text" maxlength="50" data-date-format="dd-mm-yyyy" name="birthday">
                                        <span class="input-group-addon">
                                            <i class="fa fa-calendar bigger-110"></i>
                                        </span>
                                    </div>
                                </div>

                                <sec:authorize ifAnyGranted="OPT_USER_ADD,OPT_USER_EDIT">
                                    <div class="clearfix form-actions">
                                        <div class="text-center">
                                            <button id="confirm" class="btn btn-info" type="button" onclick="doSubmit();">
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                <spring:message code="button.submit"/>
                                            </button>

                                        </div>
                                    </div>
                                </sec:authorize>

                            </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</lesson:page>