<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<%@page import="br.com.sispam.dominio.Usuario"%>
<%@page import="br.com.sispam.action.LoginAction"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="../resources/styles.css" type="text/css" />
</head>
<body>

<div id="tabs">
<img src="img/simbolo.jpg" width="248px" align="left">
<br>
<s:url id="deslogar" action="loginAction!deslogar.action"/>
<table width="750px">
	<tr>
		<td class="nomeSistema" width="500px">Sistema de Pronto Atendimento Médico</td>
		<td class="userLogado" width="240">Usuário logado: <%=((Usuario) session.getAttribute("usuarioLogado")).getAcesso().toUpperCase()%>
		<br>Login em: <%=((Usuario) session.getAttribute("dtHrAcess")).getDtHoraAcesso()%></td>
		<td>
			<s:form action="loginAction!deslogar.action">
				<s:submit theme="simple" cssClass="deslogar" value="Sair"/>
			</s:form>
		</td>
	</tr>
</table>

<br>
<ul>
	
    <li><a href="menuAdm.jsp" target="MENU" onclick="CENTRAL"><span>ADMINISTRATIVO</span></a></li>
	<li><a href="menuAtd.jsp" target="MENU"><span>ATENDIMENTO</span></a></li>
    <li><a href="menuCad.jsp" target="MENU"><span>CADASTRO</span></a></li>
    <li><a href="menuRel.jsp" target="MENU"><span>RELATÓRIOS</span></a></li>
   </ul>
</div>
</body>
</html>