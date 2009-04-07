<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<s:if test="stringPagina != null">
	<s:property value="stringPagina" />
</s:if>
<s:url id="linkSucesso" action="teste!sucesso" />
<s:url id="linkFalha" action="teste!falha" />
<s:a href="%{linkSucesso}">Teste Sucesso</s:a>
<br>
<s:a href="%{linkFalha}">Teste Falha</s:a>
</body>
</html>