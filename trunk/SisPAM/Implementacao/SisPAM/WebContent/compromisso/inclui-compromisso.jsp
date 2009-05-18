<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<script type="text/javascript">
		 $(document).ready(function(){
			calendario('data');
	 });
	 function calendario(idCampo){
				var id = '#'+idCampo;
				 $(id).datepicker({
							 showMonthAfterYear: false,
							 showOtherMonths: true,
							 changeMonth: true,
							 changeYear: true,
							 gotoCurrent: true
				 });
			}
	</script>
	

</head>
<body>
<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
		<td><br>
		<div>Atendimento <img src="../componentes/img/seta.gif"/> Agenda Médica <img
			src="../componentes/img/seta.gif" /> Incluir</div>
		</td>
	</tr>
</table>
<h2>Cadastro de Compromissos</h2>
	<table width="89%" id="AreaMsg" class="AreaMsg">			
	<tr>	
	<td>	
		<div id="MensagensErro" >						
			<s:fielderror theme="simple" cssClass="errorMessage" />
			<s:actionmessage theme="simple" cssClass="sucessMessage" />
		</div>
	</td>
	</tr>
	</table>

<s:form id="formConvenio" action="compromissoAction!incluirCompromisso.action">
<s:hidden name="compromisso.id" value="%{compromisso.id}"/>
	<table class="tabela_moldura">
		<tr>
			<td>
			<table border="0" width="90%" class="tabela_moldura" cellpadding="3"cellspacing="4">
				<tr>
					<td><label class="label">Médico:</label></td>
					<td colspan="3">
					<s:select theme="simple" name="compromisso.medico.id" list="medicos" headerValue="--Selecione--" headerKey="0" listValue="usuario.nome" listKey="id" />
					</td>
				</tr>
				<tr>
					<td><label class="label">Tipo de Evento:</label></td>
					<td><select name="compromisso.tipo">
						<option value="0">--Selecione--</option>
						<option value="1">Consulta</option>
						<option value="2">Cirurgia</option>
						<option value="3">Reunião</option>
						<option value="4">Palestra</option>
						<option value="5">Seminário</option>
					</select></td>
				</tr>
				<tr>
					<td><label class="label">Data:</label></td>
					<td><s:textfield theme="simple" name="compromisso.data" id="data" size="10" maxlength="10"/>&nbsp;</td>
				</tr>
				<tr>
					<td><label class="label">Hora Inicial:</label></td>
					<td><s:textfield theme="simple" name="horaInicialAux"
						size="4" maxlength="4" />&nbsp;&nbsp;&nbsp;&nbsp; <label
						class="label">Hora Final:</label>&nbsp; <s:textfield
						theme="simple" name="horaFinalAux" size="4" maxlength="4" />&nbsp;</td>

				</tr>
				<tr>
					<td><label class="label">Descrição:</label></td>
					<td colspan="4"><s:textfield theme="simple" name="compromisso.descricao" size="65" maxlength="50" />&nbsp;</td>
				</tr>
			</table>
		<table border="0" align="center">
		<tr>
			<td align="center"><br><input type="submit" value="Incluir" class="button"><br></td>		
		</tr>
		</table>
			</td>
		</tr>
	</table>
</s:form>
</body>
</html>