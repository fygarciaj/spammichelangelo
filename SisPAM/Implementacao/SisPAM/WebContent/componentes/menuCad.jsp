<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<html>
<head>
<script type="text/javascript">
	function selecionaMenu(valor){
		switch (valor) {
		case "usuario":
			var div = document.getElementById("menuUsuario"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
			}
			else{
				div.style.display = 'block'
				document.getElementById("menuConvenio").style.display = 'none';
					
				}
			break;
		case "convenio":
			var div = document.getElementById("menuConvenio"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
			}
			else{
				div.style.display = 'block'
					document.getElementById("menuUsuario").style.display = 'none';
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
		<a href="defaut.jsp" onclick="selecionaMenu('usuario')" title = "Manter Usuário" target="CENTRAL">USUÁRIO</a>
		<div id="menuUsuario" style="display:none">
			<ul id="subMenu">
			<li ><a href="usuarioAction!carregarNovoUsuario.action" title = "Incluir" target="CENTRAL">Incluir</a></li>
			<li ><a href="usuarioAction!carregarConsulta.action" title = "Alterar/Excluir" target="CENTRAL">Alterar/Excluir</a></li>
			
			</ul>
		</div>
	</li>
    <li>
		<a href="defaut.jsp" onclick="selecionaMenu('convenio')" title = "Manter Convênio" target="CENTRAL">CONVÊNIO</a>
		<div id="menuConvenio" style="display:none">
			<ul id="subMenu">
			<li ><a href="../convenio/inclui-convenio.jsp" title = "Incluir" target="CENTRAL">Incluir</a></li>
			<li ><a href="convenioAction!carregarConsulta.action" title = "Alterar/Excluir" target="CENTRAL">Alterar/Excluir</a></li>
			</ul>
		</div>
	</li>
  </ul>
</div>
</body>
</html>