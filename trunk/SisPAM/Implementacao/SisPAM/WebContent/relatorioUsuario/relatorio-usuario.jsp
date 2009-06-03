<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="componentes/css/estilo.css" type="text/css" />
	<script type="text/javascript" src="componentes/js/sispam.js"></script>
	<link rel="stylesheet" href="../componentes/css/estilo.css" type="text/css" />
	<script type="text/javascript" src="../componentes/js/sispam.js"></script>
	<title>Relatório de Usuário</title>
</head>
<body>
<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
		<td><br>
		<div>Relatório<img src="../componentes/img/seta.gif" />Relatório de Convênio<img
			src="../componentes/img/seta.gif" />Emitir</div>
		</td>
	</tr>
</table>
<h2>Emissão de Relatório de Usuário</h2>


<form action="../relatorioUsuario.sispam" method="post" name="relatorio"> 
		<input type="hidden" name="relatorioChamado" value="usuario"/>
		<table class="tabela_moldura" >
			<tr>			
        	<td><label class="label">Perfil</label>
        	</td><td>
        	<!-- Perfil -->        	
			<select id="cmbPerfil" name="perfil">
				<option value="0">--Selecione--</option>			
				<option value="1">Administrador</option>
				<option value="2">Atendente</option>
				<option value="3">Médico</option>
				<option value="4">Paciente</option>
			</select>
        	</td>
			</tr>			
			<tr>			        	        	
			<td><input type="submit" value="Emitir" class="button"/></td>
			</tr>	
		</table>
	</form>

</body>
</html>