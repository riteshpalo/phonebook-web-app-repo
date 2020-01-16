<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function confirmation() {
		return confirm("Ary You Sure You Want To Delete?");
	}
</script>
</head>
<body>
	<h1>All Contacts</h1>
	<a href="/">+Add New Contact</a>
	<table border="1">
		<thead>
			<tr>
				<th>S.No</th>
				<th>Name</th>
				<th>Email</th>
				<th>Phno</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${contacts }" var="ct" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${ct.contactName }</td>
					<td>${ct.contactEmail}</td>
					<td>${ct.phno}</td>
					<td>
						<a href="editContact?contactId=${ct.contactId }">Edit</a> &nbsp; &nbsp;
						<a href="deleteContact?contactId=${ct.contactId }" onclick="return confirmation()">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>