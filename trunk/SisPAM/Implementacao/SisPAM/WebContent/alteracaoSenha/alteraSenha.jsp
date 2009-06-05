<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="br.com.sispam.dominio.Usuario"%>
<%@page import="br.com.sispam.action.LoginAction"%><html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="componentes/css/estilo.css" type="text/css"/>
<script type="text/javascript" src="componentes/js/sispam.js"></script>
<link rel="stylesheet" href="../componentes/css/estilo.css" type="text/css"/>
<script type="text/javascript" src="../componentes/js/sispam.js"></script>


</HEAD>
<BODY>
<br>

<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
		<td><br>
		<div>Usuário <img src="../componentes/img/seta.gif" />
		Senha <img src="../componentes/img/seta.gif" /> Alterar</div>
		</td>
	</tr>
</table>
<h2>Alteração de Senha</h2>
<table>
<tr><td>
<s:fielderror theme="simple" cssClass="errorMessage"  cssErrorStyle="errorMessage" cssErrorClass="errorMessage"/>
<s:actionmessage theme="simple" cssClass="errorMessage" cssErrorStyle="errorMessage" cssErrorClass="errorMessage"/>
</td>
</tr>
</table>

<s:form  action="loginAction!alterarSenha.action" >
	<table class="tabelaAlteraSenha">
<tr>
	<td>
	<table class="tabelaAlteraSenha">
		<tr>
			<td colspan="4" height="13">
				<center>
					<p><b>Usuário: <%=((Usuario) session.getAttribute("usuarioLogado")).getAcesso()%></b></p>
				</center>
			</td>
		</tr>
		<tr>
			<td rowspan="4" align="left">
				<p align="right">
					<img border="0" src="../componentes/img/Senha.gif"	width="80" height="80" align="left">
				</p>
			</td>
		</tr>
		<tr align="left">
			<td width="150px"><b>Senha</b></td>
			<td width="50px"><s:password  name="senha" cssClass="campos" theme="simple" size="8" maxlength="6"/></td>
		</tr>
		<tr>
			<td width="150px"><b>Nova Senha</b></td>
			<td width="50px"><s:password name="novaSenha" cssClass="campos" theme="simple" size="8" maxlength="6" /></td> 			
		</tr>
		<tr>
			<td width="150px"><b>Redigite a Nova Senha</b></td>
			<td width="50px"><s:password name="novaSenha" cssClass="campos" theme="simple" size="8" maxlength="6" /></td> 			
		</tr>
		</table>
	</td>
</tr>
		<tr>	
			<td valign="middle" height="40px" align="center" ><s:submit theme="simple" cssClass="button" value="Alterar"/></td>
		</tr>
	</table>
</s:form>

</BODY>
