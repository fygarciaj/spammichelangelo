<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
<head>
<script type="text/javascript">
	function selecionaMenu(valor){
		switch (valor) {		
		case "relatorioConvenio":
			var div = document.getElementById("menuRelatorioConvenio"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
			}
			else{
				div.style.display = 'block'
				document.getElementById("menuReceita").style.display = 'none';	
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
	<li>
		<a href="javaScript:void(0)" onclick="selecionaMenu('relatorioConvenio')" title = "Emitir Relatorio Convenio" target="CENTRAL">RELATÓRIO CONVÊNIO</a>
		<div id="menuRelatorioConvenio" style="display:none">
			<ul id="subMenu">
			<li ><a href="../relatorioConvenio/relatorio-convenio.jsp" title = "Emitir" target="CENTRAL">Emitir</a></li>
			</ul>
		</div>
	</li>
  </ul>
</div>
</body>
</html>