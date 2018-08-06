<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<lesson:page title="student.title.list">
    <jsp:attribute name="css">
        <style type="text/css">
            #name-of-ban-user, #name-of-reset-user {
                font-weight: bold;
                color: red;
            }

            #password-not-match-msg {
                display: none;
                color: #a94442;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">



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
                <li class="active">
                    <spring:message code="student.title.list"/>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-user">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                <spring:message code="user.title.list"/>
                                <span class=" btn-group pull-right">
                                <sec:authorize ifAnyGranted="OPT_USER_ADD">
                                    <a href="student/user/add.do" class="btn btn-sm btn-primary">
                                        <i class="ace-icon glyphicon glyphicon-plus"></i>
                                        <spring:message code="button.add"/>
                                    </a>
                                </sec:authorize>
                            	</span>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace" />
                                            <span class="lbl"></span>
                                        </label>
                                    </th>
                                    <th class="detail-col">Details</th>
                                    <th>Id</th>
                                    <th>学号</th>
                                    <th class="hidden-480">姓名</th>
                                    <th>性别</th>
                                    <th>生日</th>
                                    <th>
                                        <i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>
                                        班级
                                    </th>
                                    <th class="hidden-480">选修课数目</th>
                                    <th>平均分</th>
                                    <th>分数录入</th>
                                    <th>选课</th>
                                    <th>修改</th>
                                    <th>删除</th>

                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${students}" var="student">
                                    <tr>
                                        <td class="center">
                                            <label class="pos-rel">
                                                <input type="checkbox" class="ace" />
                                                <span class="lbl"></span>
                                            </label>
                                        </td>

                                        <td class="center">
                                            <div class="action-buttons">
                                                <a href="#" class="green bigger-140 show-details-btn" title="Show Details">
                                                    <i class="ace-icon fa fa-angle-double-down"></i>
                                                    <span class="sr-only">Details</span>
                                                </a>
                                            </div>
                                        </td>

                                        <td>
                                            <a href="#">${student.id}</a>
                                        </td>
                                        <td>${student.studentId}</td>
                                        <td class="hidden-480">${student.name}</td>
                                        <td>${student.sex}</td>
                                        <td>${student.birthday}</td>
                                        <td>${student.grade}</td>

                                        <td class="hidden-480">
                                            <span class="label label-sm label-warning">${student.courseNumber}</span>
                                        </td>

                                        <td>${student.average}</td>

                                        <%--<td>--%>
                                            <%--<div class="hidden-sm hidden-xs btn-group">--%>
                                                <%--<button class="btn btn-xs btn-success" value="录入分数">--%>
                                                    <%--<i class="ace-icon fa fa-check bigger-120"></i>--%>
                                                <%--</button>--%>
                                            <%--</div>--%>
                                        <%--</td>--%>

                                        <%--<td>--%>
                                            <%--<div class="hidden-sm hidden-xs btn-group">--%>
                                                <%--<button class="btn btn-xs btn-warning" value="选课">--%>
                                                    <%--<i class="ace-icon fa fa-flag bigger-120"></i>--%>
                                                <%--</button>--%>
                                            <%--</div>--%>
                                        <%--</td>--%>

                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <a
                                                        href="student/user/doScore.do?id=${student.id}" class="btn btn-xs btn-warning">
                                                    <i class="ace-icon fa fa-flag bigger-120"></i>
                                                </a>
                                            </div>
                                        </td>

                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <a
                                                   href="student/user/select_subject.do?id=${student.id}" class="btn btn-xs btn-warning">
                                                    <i class="ace-icon fa fa-flag bigger-120"></i>
                                                </a>
                                            </div>
                                        </td>

                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <a
                                                    href="student/user/update.do?id=${student.id}" class="btn btn-xs btn-warning">
                                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                </a>
                                            </div>
                                        </td>

                                        <%--<td>--%>
                                            <%--<div class="hidden-sm hidden-xs btn-group">--%>
                                                <%--<button class="btn btn-xs btn-info" value="修改">--%>
                                                    <%--<i class="ace-icon fa fa-pencil bigger-120"></i>--%>
                                                <%--</button>--%>
                                            <%--</div>--%>
                                        <%--</td>--%>

                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                            <a data-id="${student.id}"
                                               data-url="student/user/save_delete.do" class="red btn-delete-modal">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </a>
                                            </div>
                                        </td>


                                            <div class="hidden-md hidden-lg">
                                                <div class="inline pos-rel">
                                                    <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                                                        <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                                    </button>

                                                    <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                        <li>
                                                            <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																			<span class="blue">
																				<i class="ace-icon fa fa-search-plus bigger-120"></i>
																			</span>
                                                            </a>
                                                        </li>

                                                        <li>
                                                            <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>
                                                            </a>
                                                        </li>

                                                        <li>
                                                            <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																			<span class="red">
																				<i class="ace-icon fa fa-trash-o bigger-120"></i>
																			</span>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                    </tr>

                                    <tr class="detail-row">
                                        <td colspan="8">
                                            <div class="table-detail">
                                                <div class="row">
                                                    <div class="col-xs-12 col-sm-2">
                                                        <div class="text-center">
                                                            <img height="150" class="thumbnail inline no-margin-bottom" alt="Domain Owner's Avatar" src="static-resource/ace/assets/images/avatars/profile-pic.jpg" />
                                                            <br />
                                                            <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                                                                <div class="inline position-relative">
                                                                    <a class="user-title-label" href="#">
                                                                        <i class="ace-icon fa fa-circle light-green"></i>
                                                                        &nbsp;
                                                                        <span class="white">${student.name}</span>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12 col-sm-7">
                                                        <div class="space visible-xs"></div>

                                                        <div class="profile-user-info profile-user-info-striped">
                                                            <div class="profile-info-row">
                                                                <div class="profile-info-name"> Username </div>

                                                                <div class="profile-info-value">
                                                                    <span>${student.name}</span>
                                                                </div>
                                                            </div>

                                                            <div class="profile-info-row">
                                                                <div class="profile-info-name"> StudentId </div>

                                                                <div class="profile-info-value">
                                                                    <i class="fa fa-map-marker light-orange bigger-110"></i>
                                                                    <span>${student.studentId}</span>
                                                                </div>
                                                            </div>

                                                            <div class="profile-info-row">
                                                                <div class="profile-info-name"> Sex </div>

                                                                <div class="profile-info-value">
                                                                    <span>${student.sex}</span>
                                                                </div>
                                                            </div>

                                                            <div class="profile-info-row">
                                                                <div class="profile-info-name"> Grade </div>

                                                                <div class="profile-info-value">
                                                                    <span>${student.grade}</span>
                                                                </div>
                                                            </div>

                                                            <div class="profile-info-row">
                                                                <div class="profile-info-name"> Birthday </div>

                                                                <div class="profile-info-value">
                                                                    <span>${student.birthday}</span>
                                                                </div>
                                                            </div>

                                                            <div class="profile-info-row">
                                                                <div class="profile-info-name"> CourseNumber </div>

                                                                <div class="profile-info-value">
                                                                    <span data-value="0">${student.courseNumber}</span>
                                                                </div>
                                                            </div>

                                                            <div class="profile-info-row">
                                                                <div class="profile-info-name"> Average </div>

                                                                <div class="profile-info-value">
                                                                    <span data-value="0">${student.average}</span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12 col-sm-3">
                                                        <div class="space visible-xs"></div>
                                                        <h4 class="header blue lighter less-margin">Send a message to Alex</h4>

                                                        <div class="space-6"></div>

                                                        <form>
                                                            <fieldset>
                                                                <textarea class="width-100" resize="none" placeholder="Type something…"></textarea>
                                                            </fieldset>

                                                            <div class="hr hr-dotted"></div>

                                                            <div class="clearfix">
                                                                <label class="pull-left">
                                                                    <input type="checkbox" class="ace" />
                                                                    <span class="lbl"> Email me a copy</span>
                                                                </label>

                                                                <button class="pull-right btn btn-sm btn-primary btn-white btn-round" type="button">
                                                                    Submit
                                                                    <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                                </button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                        </table>
                    </div><!-- /.span -->
                </div><!-- /.row -->

                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
        </div>
    </jsp:body>
</lesson:page>
