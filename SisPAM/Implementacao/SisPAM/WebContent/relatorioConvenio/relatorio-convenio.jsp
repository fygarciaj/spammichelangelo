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
	<title>Relatório de Convênio</title>
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
<h2>Emissão de Relatório de Convênio</h2>


<form action="../relatorioConvenio.sispam" method="post">
		<input type="hidden" name="relatorioChamado" value="convenio"/>
		<table class="tabela_moldura" >
			<tr>			
        	<td><label class="label">Tipo de Relatório</label>
        	</td><td>
        	<!-- Tipo de Relatorio -->        	
			<select id="cmbTipRel" name="tipoRelatorio">
				<option value="1">Sintético</option>			
				<option value="2">Analítico</option>
			</select>
        	</td>
			</tr>			
			<tr>			        	
        	<!-- Filtro -->        	
			<td><label class="label" >Nome do Convênio</label></td><td><input type="text" name="convenio.nome" size="60" maxlength="60"/></td>
        	
			<td><input type="submit" value="Consultar" class="button"/></td>
			</tr>	
		</table>
	</form>
	
	
</body>
</html>