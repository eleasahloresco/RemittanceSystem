<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Employee List</title>
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
		    <a href="${homeUrl}"> Home </a>
		    |
		    <c:url var="addEmployeeUrl" value="/addEmployee" />
		    <a href="${addEmployeeUrl}"> Add Employee </a>
		    |
		    <c:url var="getAllEmployeeUrl" value="/getAllEmployees" />
			<a href="${getAllEmployeeUrl}"> Employee List </a>
       	</div>
    </div>

	<div class="body">
		<div class="bodyContent">
			<h2>All Employees</h2>
			<table width="100%" style="text-align: center">
				<tr>
					<th>ID</th>
					<th>Employee No.</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Position</th>
					<th>Branch</th>
					<th></th>
				</tr>

				<c:forEach var="employee" items="${employees}">
					<tr>
						<td><c:out value="${employee.id}" /></td>
						<td><c:out value="${employee.employeeNo}" /></td>
						<td><c:out value="${employee.firstName}" /></td>
						<td><c:out value="${employee.lastName}" /></td>
						<td><c:out value="${employee.position}" /></td>
						<td><c:out value="${employee.branch.name}" /></td>
						<td><c:url var="update" value="/updateEmployee">
								<c:param name="employee.id" value="${employee.id}" />
							</c:url> <a href="${update}">Update</a>
						</td>
						<td><c:url var="delete" value="/delete">
								<c:param name="employee.id" value="${employee.id}" />
							</c:url> <a href="${delete}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="footer">All rights reserved 2012</div>
</body>



</html>
