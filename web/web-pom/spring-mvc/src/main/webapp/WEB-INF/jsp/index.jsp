<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Hello World! ${user.userName } &nbsp; ${user.age }</h2>
	<form:form action="selfinfo" commandName="user">
		userName: <form:input path="userName"/> <form:errors path="userName" />
		age   : <form:input path="age"/> <form:errors path="age" />
		<input type="submit" value="submit" name="testSubmit"/>   
		<input type="reset" value="reset" />
	</form:form>
</body>
</html>
