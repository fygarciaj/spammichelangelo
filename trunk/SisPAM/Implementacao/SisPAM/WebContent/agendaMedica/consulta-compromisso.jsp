<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="componentes/css/estilo.css" type="text/css" />
	<script type="text/javascript" src="componentes/js/sispam.js"></script>
	<link rel="stylesheet" href="../componentes/css/estilo.css" type="text/css" />
	<script type="text/javascript" src="../componentes/js/sispam.js"></script>
</head>
<body>
	<table width="89%" id="cmnUsr" class="caminhoUsuario">
		<tr>
			<td>
				<br>
				<div>Atendimento <img src="../componentes/img/seta.gif" /> Agenda Médica
				<img src="../componentes/img/seta.gif" /> Consultar</div>
			</td>
		</tr>
	</table>
<h2>Consulta de Compromissos</h2>

<s:form action="">
	<table class="tabela_moldura">
		<tr>
			<td>
			<table border="0" width="90%" class="tabela_moldura" cellpadding="3" cellspacing="4">
				<tr>
					<td><label class="label">Médico:</label></td>
					<td colspan="3"><s:textfield theme="simple" name="agendaMedica.medico"
						size="35" maxlength="50"/>&nbsp;</td>
				</tr>
				<tr>
					<td><label class="label">Data:</label></td>
					<td><s:textfield theme="simple" name="agendaMedica.data"
						size="10" maxlength="10"/>&nbsp;</td>
			
					<td><label class="label">Tipo:</label></td>
					<td><select name="agendaMedica.nome">
						<option value="0">Selecione</option>
						<option value="1">Consulta</option>
						<option value="2">Cirurgia</option>
						<option value="3">Reunião</option>
						<option value="4">Palestra</option>
						<option value="5">Seminário</option>
					</select></td>
				</tr>
			</table>
		<table border="0" align="center">
		<tr>
			<td align="center"><br><input type="submit" value="Consultar" class="button"><br></td>		
		</tr>
		</table>
			</td>
		</tr>
	</table>
</s:form>
</body>
</html>