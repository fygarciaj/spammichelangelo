<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="componentes/css/estilo.css" type="text/css"/>
<script type="text/javascript" src="componentes/js/sispam.js"></script>
<title>Login SisPAM</title>
</HEAD>
<BODY >
	<img src="componentes/img/simbLogin.jpg"  width="100%">
<br><table align="center" width="352px">
<tr><td>
<s:fielderror theme="simple" cssClass="errorMessage"  cssErrorStyle="errorMessage" cssErrorClass="errorMessage"/>
<s:actionmessage theme="simple" cssClass="errorMessage" cssErrorStyle="errorMessage" cssErrorClass="errorMessage"/>
</td>
</tr>
</table>

<center> 
<s:form action="loginAction!logar.action">
	<table width="235" border="0"cellpadding="3" height="123" class="tabela_login" >
		<tr>
			<td colspan="6" height="13">
				<center>
					<p><font face="Arial Black">Autenticação do sistema</font></p>
				</center>
			</td>
		</tr>
		<tr>
			<td rowspan="3">
				<p align="right">
					<img border="0" src="componentes/img/user.gif"	width="80" height="80" align="left">
				</p>
			</td>
		</tr>
		<tr>
			<td><font face="Verdana"  style="font-size: 8pt; font-weight: 700"><b>Usuário</b></font></td>
			<td align="justify" colspan="2"><s:textfield  name="acesso" cssClass="campos" theme="simple" size="30" maxlength="25"/></td>
		</tr>
		<tr>
			<td><font face="Verdana" style="font-size: 8pt; font-weight: 700"><b>Senha</b></font></td>
			<td align="left"><s:password name="senha" cssClass="campos" theme="simple" size="8" maxlength="6" /></td> 
			<td align="right"><s:submit theme="simple" cssClass="button" value="Entrar"/></td>
			
			<td valign="bottom" height="26" ></td>
		</tr>
	</table>
</s:form>
</center>
</BODY>


<p align="center" style="margin-top: 0; margin-bottom: 0"> </p>
