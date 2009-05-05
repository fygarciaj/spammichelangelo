<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="componentes/css/estilo.css" type="text/css" />
<BODY>
<center>
</center>
	<img src="componentes/img/simbLogin.jpg"  width="30%">
<center> 
<s:form action="loginAction!logar.action">
	<table width=225 border=0 cellpadding=3 height="123" class="tabela_login" >
		<tr>
			<td colspan=6 height="13">
				<center>
					<p><font face="Arial Black">Autenticação do sistema</font></p>
				</center>
			</td>
		</tr>
		<tr>
			<td rowspan="3">
				<p align="right"><img border="0" src="componentes\img\user.gif"
				width="80" height="80" align="left"></font>
			</td>
		</tr>
		<tr>
			<td><font face="Verdana" style="font-size: 8pt; font-weight: 700"><b>Usuário</b></font></td>
			<td align="right"><s:textfield name="usuario" theme="simple"/></td>
		</tr>
		<tr>
			<td><font face="Verdana" style="font-size: 8pt; font-weight: 700"><b>Senha</b></font></td>
			<td><s:password name="senha" cssClass="" theme="simple"/></td>
			<td valign="bottom" height="26" ><s:submit theme="simple" cssClass="botao_logar" value="Entrar"/></td>
		</tr>
	</table>
</s:form>
</center>

<p>

<p align="center" style="margin-top: 0; margin-bottom: 0"> </p>