<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>

<html>
<head>
	<jsp:include page="inc_head.jsp" flush="true" />
</head>
<body>
<s:actionmessage/>
<s:form action="register" method="post">
	<s:textfield label="名  称" name="userName"/>
	<s:textfield label="用户名" name="loginName"/>
    <s:textfield label="密  码" name="password"/>
    <s:textfield label="身份证号" name="identifier"/>
    <s:textarea label="备  注" name="description" />
    <s:submit/>
</s:form>
</body>
</html>