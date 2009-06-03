<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="componentes/css/estilo.css" type="text/css"/>
<script type="text/javascript" src="componentes/js/sispam.js"></script>

<link rel="stylesheet" href="../componentes/css/estilo.css" type="text/css"/>
<script type="text/javascript" src="../componentes/js/sispam.js"></script>
<title>Login SisPAM</title>
<%
	String erro = request.getParameter("code");
%>
</HEAD>
<BODY>
	
<br><br>
<center>
	<table width="750px" class="errorMessage" >
		<tr>
			<td>
			 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Informe os campos para obrigatórios.
			</td>
		</tr>
	</table>

</center>
</BODY>


<p align="center" style="margin-top: 0; margin-bottom: 0"> </p>
