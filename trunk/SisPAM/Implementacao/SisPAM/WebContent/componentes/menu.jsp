<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
<head>
<script type="text/javascript">
	function selecionaMenu(valor){
		if(valor == 'usuario'){
			var div = document.getElementById("menuUsuario"); 
			if(div.style.display == 'block'){
				div.style.display = 'none';
			}
			else{
				div.style.display = 'block'
				}
		}
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="..\resources\styleMenu.css" type="text/css" />
</head>
<body>
<div id="menu">
  <ul>
    <li>
		<a href="javaScript:void(0)" onclick="selecionaMenu('usuario')" title = "Incluir Usuário" target="CENTRAL">Usuário</a>
		<div id="menuUsuario" style="display:none">
			<ul class="subMenu">
			<li ><a href="usuarioAction!carregarNovoUsuario.action" title = "Incluir" target="CENTRAL">Incluir</a></li>
			<li ><a href="usuarioAction!carregarNovoUsuario.action" title = "Incluir" target="CENTRAL">Alterar</a></li>
			<li ><a href="usuarioAction!carregarNovoUsuario.action" title = "Incluir" target="CENTRAL">Excluir</a></li>
			<li ><a href="usuarioAction!carregarNovoUsuario.action" title = "Incluir" target="CENTRAL">Consultar</a></li>
			</ul>
		</div>
	</li>
    <li><a href="http://www.free-css-templates.co.uk/">Link 2</a></li>
    <li><a href="http://www.free-css-templates.co.uk/">Link 3</a></li>
    <li><a href="http://www.free-css-templates.co.uk/">Link 5</a></li>
    <li><a href="http://www.free-css-templates.co.uk/">Link 6</a></li>
  </ul>
</div>
</body>
</html>