<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Contact Info Form</h1>
	<font color="green" size="5"><b>${successMsg }</b></font>
	<font color="red" size="5"><b>${errorMsg }</b></font>
	<form:form action="handleSubmitBtn" method="post" modelAttribute="contact">
		<form:hidden path="contactId"/>
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input path="contactName"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input path="contactEmail"/></td>
			</tr>
			<tr>
				<td>Phno:</td>
				<td><form:input path="phno"/></td>
			</tr>
			<tr>
				<td><input type="reset" value="Reset"></td>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
		<a href="viewAllContacts">View All Contacts</a>
	</form:form>
</body>
</html>