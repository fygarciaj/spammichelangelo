<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

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
				document.getElementById("menuRelatorioLog").style.display = 'none';	
				document.getElementById("menuRelatorioUsuario").style.display = 'none';
				}
			break;
		case "relatorioLog":
			var div = document.getElementById("menuRelatorioLog"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
			}
			else{
				div.style.display = 'block'
				document.getElementById("menuRelatorioConvenio").style.display = 'none';	
				document.getElementById("menuRelatorioUsuario").style.display = 'none';
				}
			break;
		case "relatorioUsuario":
			var div = document.getElementById("menuRelatorioUsuario"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
			}
			else{
				div.style.display = 'block'
				document.getElementById("menuRelatorioConvenio").style.display = 'none';
				document.getElementById("menuRelatorioLog").style.display = 'none';		
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
		<a href="javaScript:void(0)" onclick="selecionaMenu('relatorioConvenio')" title = "Emitir Relatorio Convenio" target="CENTRAL">RELATÓRIO CONVÊNIO</a>
		<div id="menuRelatorioConvenio" style="display:none">
			<ul id="subMenu">
			<li ><a href="../relatorioConvenio/relatorio-convenio.jsp" title = "Emitir" target="CENTRAL">Emitir</a></li>
			</ul>
		</div>
	</li>
	<li>
		<a href="javaScript:void(0)" onclick="selecionaMenu('relatorioLog')" title = "Emitir Relatorio Log" target="CENTRAL">RELATÓRIO LOG</a>
		<div id="menuRelatorioLog" style="display:none">
			<ul id="subMenu">
			<li ><a href="../relatorioLogAuditoria/relatorioLogAuditoria.jsp" title = "Emitir" target="CENTRAL">Emitir</a></li>
			</ul>
		</div>
	</li>
	<li>
		<a href="javaScript:void(0)" onclick="selecionaMenu('relatorioUsuario')" title = "Emitir Relatorio Usuário" target="CENTRAL">RELATÓRIO USUÁRIO</a>
		<div id="menuRelatorioUsuario" style="display:none">
			<ul id="subMenu">
			<li ><a href="../relatorioUsuario/relatorio-usuario.jsp" title = "Emitir" target="CENTRAL">Emitir</a></li>
			</ul>
		</div>
	</li>
  </ul>
</div>
</body>
</html>