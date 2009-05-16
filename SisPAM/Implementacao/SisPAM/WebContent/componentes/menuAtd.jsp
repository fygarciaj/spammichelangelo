<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
				}
			break;
		case "receita":
			var div = document.getElementById("menuReceita"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
			}
			else{
				div.style.display = 'block'
		        document.getElementById("menuRelatorioConvenio").style.display = 'none';
				}
			break;
		default:
			break;
		}
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="../resources/styleMenu.css" type="text/css" />
</head>
<body>
<div id="menu">
<ul>
	<li><a href="javaScript:void(0)"
		onclick="selecionaMenu('agendaMedica')" title="Mantem Compromissos Médicos"
		target="CENTRAL">AGENDA MÉDICA</a>
	<div id="menuAgendaMedica" style="display: none">
	<ul id="subMenu">
		<li><a href="compromissoAction!carregarInclusao.action" title="Incluir" target="CENTRAL">Incluir Compromisso</a></li>
		<li><a href="../agendaMedica/consulta-compromisso.jsp" title="Alterar/Excluir" target="CENTRAL">Alterar/Excluir Compromisso</a></li>
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