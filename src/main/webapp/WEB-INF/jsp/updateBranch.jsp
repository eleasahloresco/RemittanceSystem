<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Branch</title>
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
				<h2>Add Branch</h2>
	
				<c:url var="submitURL" value="/updateBranch">
					<c:param name="branch.id" value="${branch.id}" />
				</c:url>
	
				<springform:form commandName="branchModel" action="${submitURL}" method="POST">
					<div class="formLabel">
						<springform:label path="id">ID</springform:label>
					</div>
					<div class="formInput">
						<springform:input path="id" value="${branch.id}" readonly="true" disabled="true" />
					</div>
					<br />
	
					<div class="formLabel">
						<springform:label path="code">Branch Code</springform:label>
					</div>
					<div class="formInput">
						<springform:input path="code" value="${branch.code}" readonly="true" disabled="true"/>
					</div>
					<br />
					
					<div class="formLabel">
						<springform:label path="name">Branch Name</springform:label>
					</div>
					<div class="formInput">
						<springform:input path="name" value="${branch.name}" />
						<springform:errors path="name" />
					</div>	
					<br />
	
					<div class="formLabel">
						<springform:label path="location">Location</springform:label>
					</div>
					<div class="formInput">
						<springform:input path="location" value="${branch.location}" />
						<springform:errors path="location" />
					</div>
					<br />
	
					<input type="submit" value="Update Branch" />
				</springform:form>
			</div>
		</div>
		<div class="footer">All rights reserved 2012</div>
	</body>
</html>
