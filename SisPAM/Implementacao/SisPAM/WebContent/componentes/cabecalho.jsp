<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<%@page import="br.com.sispam.dominio.Usuario"%>
<%@page import="br.com.sispam.action.LoginAction"%><html>
<head>

<script language="JavaScript">
	function logoff(){
		window.parent.frames.location = "../login.jsp"
		}

	function acessaMenu(nmMenu){
		switch (nmMenu){		
		case "ADM":
			window.parent.frames[1].location = "menuAdm.jsp"
		break;
		case "ATD":
			window.parent.frames[1].location = "menuAtd.jsp"
		break;
		case "CAD":
			window.parent.frames[1].location = "menuCad.jsp"
		break;
		case "REL":
			window.parent.frames[1].location = "menuRel.jsp"
		break;
		default:
			break;		
		}
		window.parent.frames[2].location = "defaut.jsp"
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="../resources/styles.css" type="text/css" />
</head>
<body>

<div class="tabs" >
<img src="img/simbolo.jpg" width="240px" align="left">
<br>
<s:url id="deslogar" action="loginAction!deslogar.action"/>
<table width="750px" height="51px">
	<tr>
		<%Usuario usuario = (Usuario) session.getAttribute("usuarioLogado"); %>
		<td class="nomeSistema" width="500px">Sistema de Pronto Atendimento Médico</td>
		<td class="userLogado" width="240">Usuário logado: <%=usuario.getAcesso().toUpperCase()%>
		<br>Login em: <%=((Usuario) session.getAttribute("dtHrAcess")).getDtHoraAcesso()%><br>
		<a href="javascript:logoff()" ><b>Sair</b></a>&nbsp;&nbsp;&nbsp;
		<a href = "../alteracaoSenha/alteraSenha.jsp" target="CENTRAL"><b>Alterar Senha</b></a></td>

	</tr>
</table>

<ul class="ul">
	<%if (usuario.getPerfil() == 1) {%>
	    <li class="li"><a class="link" href="javascript:acessaMenu('ADM')"><span>ADMINISTRATIVO</span></a></li>
		<li class="li"><a class="link" href="javascript:acessaMenu('ATD')"><span>ATENDIMENTO</span></a></li>
	    <li class="li"><a class="link" href="javascript:acessaMenu('CAD')"><span>CADASTRO</span></a></li>
	    <li class="li"><a class="link" href="javascript:acessaMenu('REL')"><span>RELATÓRIOS</span></a></li>
	 <%} else if(usuario.getPerfil() == 2) {%>
       	<li class="li"><a class="link" href="javascript:acessaMenu('ATD')"><span>ATENDIMENTO</span></a></li>
	    <li class="li"><a class="link" href="javascript:acessaMenu('CAD')"><span>CADASTRO</span></a></li>
	    <li class="li"><a class="link" href="javascript:acessaMenu('REL')"><span>RELATÓRIOS</span></a></li>
	  <%}else if(usuario.getPerfil() == 3){ %>
       	<li class="li"><a class="link" href="javascript:acessaMenu('ATD')"><span>ATENDIMENTO</span></a></li>
    	<li class="li"><a class="link" href="javascript:acessaMenu('REL')"><span>RELATÓRIOS</span></a></li>
      <%}else{ %>
       	<li class="li"><a class="link" href="javascript:acessaMenu('ATD')"><span>ATENDIMENTO</span></a></li>
       	<li class="li"><a class="link" href="javascript:acessaMenu('CAD')"><span>CADASTRO</span></a></li>
       	<li class="li"><a class="link" href="javascript:acessaMenu('REL')"><span>RELATÓRIOS</span></a></li>
       <%} %>
   </ul>
</div>
</body>
</html>