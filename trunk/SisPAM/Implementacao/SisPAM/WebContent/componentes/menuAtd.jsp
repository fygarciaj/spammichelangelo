<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@page import="br.com.sispam.dominio.Usuario"%><html>
<head>
<%Usuario usuario = (Usuario) session.getAttribute("usuarioLogado"); %>
<script type="text/javascript">
	function selecionaMenu(valor){
		switch (valor) {
		case "agendaMedica":
			var div = document.getElementById("menuAgendaMedica"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
			}
			else{
				div.style.display = 'block'
				document.getElementById("menuReceita").style.display = 'none';
				document.getElementById("menuHistoricoProntuario").style.display = 'none';
				document.getElementById("menuAgendamento").style.display = 'none';
				document.getElementById("menuConsultaRealizados").style.display = 'none';
				}
			break;
		case "agendamento":
			var div = document.getElementById("menuAgendamento"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
			}
			else{
				div.style.display = 'block'
				document.getElementById("menuReceita").style.display = 'none';
				document.getElementById("menuHistoricoProntuario").style.display = 'none';
				document.getElementById("menuAgendaMedica").style.display = 'none';
				document.getElementById("menuConsultaRealizados").style.display = 'none';
				}
			break;

		case "consultaRealizados":
			var div = document.getElementById("menuConsultaRealizados"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
			}
			else{
				div.style.display = 'block'
		        document.getElementById("menuAgendaMedica").style.display = 'none';
				document.getElementById("menuReceita").style.display = 'none';
				document.getElementById("menuAgendamento").style.display = 'none';
				document.getElementById("menuHistoricoProntuario").style.display = 'none';
				}
			break;
						
		case "historicoProntuario":
			var div = document.getElementById("menuHistoricoProntuario"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
			}
			else{
				div.style.display = 'block'
		        document.getElementById("menuAgendaMedica").style.display = 'none';
				document.getElementById("menuReceita").style.display = 'none';
				document.getElementById("menuAgendamento").style.display = 'none';
				document.getElementById("menuConsultaRealizados").style.display = 'none';
				}
			break;
		case "receita":
			var div = document.getElementById("menuReceita"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
			}
			else{
				div.style.display = 'block'
		        document.getElementById("menuAgendaMedica").style.display = 'none';
				document.getElementById("menuHistoricoProntuario").style.display = 'none';
				document.getElementById("menuAgendamento").style.display = 'none';
				document.getElementById("menuConsultaRealizados").style.display = 'none';
				}
			break;
		default:
			break;
		}
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../resources/styleMenu.css" type="text/css" />
</head>
<body>
<div id="menu">
<ul>
	<%if(usuario.getPerfil() == 1 || usuario.getPerfil() == 2 || usuario.getPerfil() == 3){ %>
	<li><a href="defaut.jsp" onclick="selecionaMenu('agendaMedica')" title="Manter Agenda Médica"
		target="CENTRAL">AGENDA MÉDICA</a>
	<div id="menuAgendaMedica" style="display: none">
	<ul id="subMenu">
		<li><a href="compromissoAction!carregarInclusao.action" title="Incluir" target="CENTRAL">Incluir Compromisso</a></li>
		<li><a href="compromissoAction!carregarConsulta.action" title="Alterar/Excluir" target="CENTRAL">Alterar/Excluir Compromisso</a></li>
	</ul>
	</div>
	</li>
	<%} if(usuario.getPerfil() == 1 || usuario.getPerfil() == 2) {%>
	<li><a href="defaut.jsp" onclick="selecionaMenu('agendamento')" title="Manter Agendamento"
		target="CENTRAL">AGENDAMENTO</a>
	<div id="menuAgendamento" style="display: none">
	<ul id="subMenu">
		<li><a href="../agendamento/inclui-agendamento.jsp" title="Incluir" target="CENTRAL">Incluir Agendamento</a></li>
		<li><a href="agendamentoAction!carregarAgendamentos.action" title="Alterar/Excluir" target="CENTRAL">Alterar/Excluir</a></li>
	</ul>
	</div>
	</li>
	<%} %>
	<%if(usuario.getPerfil() == 4) {%>
	<li><a href="defaut.jsp" onclick="selecionaMenu('consultaRealizados')" title="Consulta Agendamento Realizado"
		target="CENTRAL">CONSULTAS REALIZADAS</a>
	<div id="menuConsultaRealizados" style="display: none">
	<ul id="subMenu">
		<li><a href="agendamentoAction!carregarAgendamentosPaciente.action" title="Consultar" target="CENTRAL">Consultar</a></li>
	</ul>
	</div>
	</li>
	<%} %>
	<%if(usuario.getPerfil() == 3){ %>
	<li><a href="defaut.jsp" onclick="selecionaMenu('historicoProntuario')" title="Atualizar Histórico de Prontuário"
		target="CENTRAL">HISTÓRICO PRONTUÁRIO</a>
	<div id="menuHistoricoProntuario" style="display: none">
	<ul id="subMenu">
		<li><a href="historicoProntuarioAction!carregarAgendamentos.action" title="Atualizar" target="CENTRAL">Atualizar</a></li>
	</ul>
	</div>
	</li>
  	<li>
		<a href="defaut.jsp" onclick="selecionaMenu('receita')" title = "Emitir Receita" target="CENTRAL">RECEITA</a>
		<div id="menuReceita" style="display:none">
			<ul id="subMenu">
			<li ><a href="receitaAction!carregarAgendamentos.action" title = "Emitir" target="CENTRAL">Emitir</a></li>
			</ul>
		</div>
	</li>
	<%} %>
</ul>
</div>
</body>
</html>