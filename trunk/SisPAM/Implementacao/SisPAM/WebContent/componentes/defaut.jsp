<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/estilo.css" type="text/css" />
	<script type="text/javascript" src="js/sispam.js"></script>
	<link rel="stylesheet" href="../css/estilo.css" type="text/css" />
	<script type="text/javascript" src="../js/sispam.js"></script>
<title>Insert title here</title>
</head>
<body>
<br>

<table width="89%" id="AreaMsg" class="AreaMsg">			
	<tr>	
	<td>	
		<div id="MensagensErro" >						
			<s:fielderror theme="simple" cssClass="errorMessage"  cssErrorStyle="errorMessage" cssErrorClass="errorMessage"/>
			<s:actionmessage theme="simple" cssClass="sucessMessage" cssErrorStyle="sucessMessage" cssErrorClass="sucessMessage"/>
		</div>
	</td>
	</tr>
	</table>
</body>
</html>