<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee</title>
        <style type="text/css">
        	<%@ include file="css/header.css" %>
        	<%@ include file="css/commons.css" %>
        	<%@ include file="css/body.css" %>
        	<%@ include file="css/footer.css" %>
        </style>
    </head>
    <body>
    	<div class="header">
    		<div class="headerContent"><h1>Remittance System</h1></div>
    		
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
    	
    	<div class="body"></div>
       	<div class="footer">All rights reserved 2012</div>
    </body>
    
</html>
