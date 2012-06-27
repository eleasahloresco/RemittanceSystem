<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Branch List</title>
        <style type="text/css">
        	<%@ include file="css/header.css" %>
        	<%@ include file="css/commons.css" %>
        	<%@ include file="css/body.css" %>
        	<%@ include file="css/footer.css" %>
        </style>
    </head>
    <body>
        <div class="header">
			<div class="headerContent">
				<h1>Remittance System</h1>
			</div>
	
			<div class="menu">
				<c:url var="homeUrl" value="/" />
				<a href="${homeUrl}"> Home </a> |
				<c:url var="addBranchUrl" value="/addBranch" />
				<a href="${addBranchUrl}"> Add Branch </a> |
				<c:url var="getAllBranchUrl" value="/getAllBranches" />
				<a href="${getAllBranchUrl}"> Branch List </a>
			</div>
		</div>

		<div class="body">
			<div class="bodyContent">
				<h2>All Branches</h2>
				<table width="100%" style="text-align: center">
					<tr>
						<th>ID</th>
						<th>Branch Code</th>
						<th>Name</th>
						<th>Location</th>
						<th></th>
					</tr>
	
					<c:forEach var="branch" items="${branches}">
						<tr>
							<td><c:out value="${branch.id}" />
							</td>
							<td><c:out value="${branch.code}" />
							</td>
							<td><c:out value="${branch.name}" />
							</td>
							<td><c:out value="${branch.location}" />
							</td>
							<td><c:url var="update" value="/updateBranch">
									<c:param name="branch.id" value="${branch.id}" />
								</c:url> <a href="${update}">Update</a></td>
							<td><c:url var="deleteBranch" value="/deleteBranch">
									<c:param name="branch.id" value="${branch.id}" />
								</c:url> <a href="${deleteBranch}">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="footer">All rights reserved 2012</div>
	</body>
</html>
