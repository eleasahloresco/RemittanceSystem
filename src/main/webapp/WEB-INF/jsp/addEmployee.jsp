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
	<title>Add Employee</title>
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
			<h2>Add Employee</h2>
		
			<c:url var="submitURL" value="/addEmployee" />
		
			<springform:form commandName="employeeModel" action="${submitURL}" method="POST">
				<div class="formLabel">
					<springform:label path="employeeNo">Employee No</springform:label>
				</div>
				<div class="formInput">
					<springform:input path="employeeNo" />
					<springform:errors path="employeeNo" />
				</div>
				<br />

				<div class="formLabel">
					<springform:label path="firstName">First Name</springform:label>
				</div>
				<div class="formInput">
					<springform:input path="firstName" />
					<springform:errors path="firstName" />
				</div>
				<br />
		
				<div class="formLabel">
					<springform:label path="lastName">Last Name</springform:label>
				</div>
				<div class="formInput">
					<springform:input path="lastName" />
					<springform:errors path="lastName" />
				</div>	
				<br />
		
				<div class="formLabel">
					<springform:label path="position">Position</springform:label>
				</div>
				<div class="formInput">
					<springform:input path="position" />
					<springform:errors path="position" />
				</div>
				<br />
		
				<div class="formLabel">
					<springform:label path="branch.id">Branch Name</springform:label>
				</div>
				<div class="formInput">
					<springform:select path="branch.id">
						<c:forEach var="branch" items="${branches}">
							<springform:option value="${branch.id}">
								<c:out value="${branch.name}" />
							</springform:option>
						</c:forEach>
					</springform:select>
				</div>
				<br />
				
				<input type="submit" value="Add Employee" />
			</springform:form>
		</div>
	</div>
	<div class="footer">All rights reserved 2012</div>
</body>


</html>
