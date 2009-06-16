<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../componentes/css/estilo.css"	type="text/css" />
	<script type="text/javascript" src="../componentes/js/sispam.js"></script>
	<link rel="stylesheet" href="componentes/css/estilo.css" type="text/css" />
	<script type="text/javascript" src="componentes/js/sispam.js"></script>
	<script type="text/javascript" src="componentes/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="../componentes/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="js/jquery/ui.core.js"></script>
	<script type="text/javascript" src="componentes/js/jquery/ui.datepicker.js"></script>
	<script type="text/javascript" src="../componentes/js/jquery/ui.datepicker.js"></script>
	<script type="text/javascript" src="componentes/js/jquery/ui.datepicker-pt-BR.js"></script>
	<script type="text/javascript" src="../componentes/js/jquery/ui.datepicker-pt-BR.js"></script>
	<link rel="stylesheet" href="../componentes/js/jquery/css/ui.all.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="componentes/js/jquery/css/ui.all.css" type="text/css" media="screen" />
	<title>Emite Prontuário</title>
	
	<script type="text/javascript">

		function emiteProntuario(){
			document.getElementById("pacienteForm").value;			
			document.forms[1].submit();
		}

	</script>
</head>
<body>
<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
		<td><br>
		<div>Relatórios<img src="/SisPAM/componentes/img/seta.gif" />Prontuário<img
			src="/SisPAM/componentes/img/seta.gif" />Emitir</div>
		</td>
</table>
<h2>Emissão de Prontuário</h2>

<s:form action="prontuarioAction!carregarPacientes.action" theme="simple">
		<table class="tabela_consulta" >			
			<tr>
				<td><label class="label">Paciente</label></td><td><s:select list="pacientes" headerKey="0" headerValue="--Selecione--" listKey="id" name="prontuario.paciente.id" listValue="usuario.nome" theme="simple"/></td>				
				<td></td><td><s:submit onclick="return emiteProntuario()"  value="Emitir" cssClass="button" theme="simple"/></td>
			</tr>			
		</table>
	</s:form>
	
	<s:hidden name="paciente.id" value="%{paciente.id}"/>
	<div id="MensagensErro" >	
		<s:fielderror theme="simple" cssClass="errorMessage" />
		<s:actionmessage theme="simple" cssClass="sucessMessage" />
	</div>
	
	<form action="/SisPAM/emiteProntuario.sispam" method="post" name="relatorio">
		<input type="hidden" name="paciente" id="pacienteForm"/>		
		<input type="hidden" name="relatorioChamado" value="prontuario">
	</form>	
</body>
</html>