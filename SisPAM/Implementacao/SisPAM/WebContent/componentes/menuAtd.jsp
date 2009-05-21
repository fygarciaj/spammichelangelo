<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<html>
<head>
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
	<li><a href="javaScript:void(0)" onclick="selecionaMenu('agendaMedica')" title="Mantem Compromissos Médicos"
		target="CENTRAL">AGENDA MÉDICA</a>
	<div id="menuAgendaMedica" style="display: none">
	<ul id="subMenu">
		<li><a href="compromissoAction!carregarInclusao.action" title="Incluir" target="CENTRAL">Incluir Compromisso</a></li>
		<li><a href="compromissoAction!carregarConsulta.action" title="Alterar/Excluir" target="CENTRAL">Alterar/Excluir Compromisso</a></li>
	</ul>
	</div>
	</li>
	<li><a href="javaScript:void(0)" onclick="selecionaMenu('historicoProntuario')" title="Mantem Compromissos Médicos"
		target="CENTRAL">HISTÓRICO PRONTUÁRIO</a>
	<div id="menuHistoricoProntuario" style="display: none">
	<ul id="subMenu">
		<li><a href="historicoProntuarioAction!carregarAgendamentos.action" title="Alterar/Excluir" target="CENTRAL">Atualizar</a></li>
	</ul>
	</div>
	</li>
  	<li>
		<a href="javaScript:void(0)" onclick="selecionaMenu('receita')" title = "Emitir Receita" target="CENTRAL">RECEITA</a>
		<div id="menuReceita" style="display:none">
			<ul id="subMenu">
			<li ><a href="../receita/emite-receita.jsp" title = "Emitir" target="CENTRAL">Emitir</a></li>
			</ul>
		</div>
	</li>
</ul>
</div>
</body>
</html>