<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<html>
<head>
<script type="text/javascript">
	function selecionaMenu(valor){
		switch (valor) {		
		case "atualizaParametro":
			var div = document.getElementById("menuAtualizaParametro"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
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
	<li>
		<a href="javaScript:void(0)" onclick="selecionaMenu('atualizaParametro')" title = "Atualizar Parâmetro" target="CENTRAL">ATUALIZA PARÂMETRO</a>
		<div id="menuAtualizaParametro" style="display:none">
			<ul id="subMenu">
			<li ><a href="../relatorioConvenio/relatorio-convenio.jsp" title = "Atualizar" target="CENTRAL">Atualizar</a></li>
			</ul>
		</div>
	</li>
  </ul>
</div>
</body>
</html>