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
		case "convenio":
			var div = document.getElementById("menu123"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
			}
			else{
				div.style.display = 'block'
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
		<li><a href="../agendaMedica/inclui-compromisso.jsp" title="Incluir" target="CENTRAL">Incluir Compromisso</a></li>
		<li><a href="../agendaMedica/consulta-compromisso.jsp" title="Alterar/Excluir" target="CENTRAL">Alterar/Excluir Compromisso</a></li>
	</ul>
	</div>
	</li>
</ul>
</div>
</body>
</html>