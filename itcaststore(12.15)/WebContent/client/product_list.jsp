<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>bookStore列表</title>
	<%--导入css --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
</head>
<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/client/index.jsp">首页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;
						${requestScope.pg.category }
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;
						图书列表
					</div>
					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>${requestScope.pg.category }</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${requestScope.pg.totalCount }种商品
								<hr />
								<div style="margin-top:20px; margin-bottom:5px">
									<img src="${pageContext.request.contextPath }/client/images/productlist.gif" width="100%" height="38" />
								</div>

								<table cellspacing="0" class="booklist">
									<tr>
										<c:forEach items="${requestScope.pg.plist }" var="p" varStatus="vs">
											<td>
												<div class="divbookpic">
													<p>
														<a href="${pageContext.request.contextPath}/findProductById?id=${p.id}">
															<img src="${pageContext.request.contextPath}${p.imgurl}" width="115" height="129" border="0" /> 
														</a>
													</p>
												</div>
												<div class="divlisttitle">
													<a href="${pageContext.request.contextPath}/findProductById?id=${p.id}">书名：${p.name }<br />售价：￥${p.price } </a>
												</div>
											</td>
										</c:forEach>
									</tr>
								</table>
								<div class="pagination">
									<ul>
										<c:if test="${requestScope.pg.currentPage!=1 }">
											<li class="disablepage_p">
												<a class="disablepage_a" href="${pageContext.request.contextPath}/showProductByPage?currentPage=${requestScope.pg.currentPage-1 }&category=${requestScope.pg.category }"></a>
											</li>
										</c:if>
										<c:if test="${requestScope.pg.currentPage==1 }">
											<li class="disablepage_p2"></li>
										</c:if>
										<c:forEach begin="1" end="${requestScope.pg.totalPage }" var="pnum">
											<c:if test="${requestScope.pg.currentPage==pnum }">
												<li class="currentpage">${pnum }</li>
											</c:if>
											<c:if test="${requestScope.pg.currentPage!=pnum }">
												<li><a href="${pageContext.request.contextPath}/showProductByPage?currentPage=${pnum }&category=${requestScope.pg.category }">${pnum }</a>
												</li>
											</c:if>
										</c:forEach>

										<c:if test="${requestScope.pg.currentPage==requestScope.pg.totalPage }">
											<li class="disablepage_n2"></li>
										</c:if>
										<c:if test="${requestScope.pg.currentPage!=requestScope.pg.totalPage }">
											<li class="disablepage_n">
												<a class="disablepage_a" href="${pageContext.request.contextPath}/showProductByPage?currentPage=${requestScope.pg.currentPage+1 }&category=${requestScope.pg.category }"></a>
											</li>
										</c:if>
									</ul>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>
