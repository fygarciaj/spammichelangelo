<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../componentes/css/estilo.css" type="text/css" />
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
<script src="../componentes/js/jquery/jquery.maskedinput-1.2.2.js" type="text/javascript"></script>
<script src="componentes/js/jquery/jquery.maskedinput-1.2.2.js" type="text/javascript"></script>  

<title>Insert title here</title>


<script type="text/javascript">
		 $(document).ready(function(){
			calendario('data');
			calendario('data1');
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

	  $(document).ready(
  			function(){
  			  $(".horario").mask("99:99");	
  			}
  		      );
    </script>

</head>
<body>
<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
		<td><br>
		<div>Atendimento<img src="../componentes/img/seta.gif" /> Agendamento<img
			src="../componentes/img/seta.gif" /> Alterar</div>
		</td>
	</tr>
</table>
<h2>Agendamento</h2>
	<s:fielderror theme="simple" cssClass="errorMessage"  cssErrorStyle="errorMessage" cssErrorClass="errorMessage"/>
	<s:actionmessage theme="simple" cssClass="sucessMessage" />
	
	<s:form action="agendamentoAction!salvarAgendamento.action" theme="simple">	
		<s:hidden name="agendamento.id" value="%{agendamento.id}"/>
		<table  border="0"  class="tabela_moldura" cellpadding="3" cellspacing="4">
			<tr>
				<td><label class="label">Médicos</label></td><td><s:select headerKey="0" headerValue="--Selecione--" list="medicos" name="agendamento.medico.id" listKey="id" listValue="usuario.nome" theme="simple"/></td>
				<td><label class="label">Especialidades</label></td><td><s:select headerKey="0" headerValue="--Selecione--" list="especialidades" name="agendamento.especialidadeMedica.id" listKey="id" listValue="descricao" theme="simple"/></td>
			</tr>
			<tr>
				<td><label class="label">Tipo</label></td><td><s:select headerKey="0" headerValue="--Selecione--" list="tipoAgendamento" name="agendamento.tipo" listKey="codigo" theme="simple"/></td>
				<td><label class="label">Data</label></td><td><s:textfield theme="simple" name="dataAgendamento" size="12" id="data"/>&nbsp;
					<label class="label">Horário</label>
					<s:textfield id="hor" theme="simple" name="horario" cssClass="horario" size="7" maxlength="5"/>
				</td>
			</tr>
			<tr>
				<td><label class="label">Paciente</label></td><td><s:select list="pacientes" headerKey="0" headerValue="--Selecione--" listKey="id" name="agendamento.paciente.id" listValue="usuario.nome" theme="simple"/></td>
			</tr>
			<tr >
				<td><label class="label">Observação</label></td>			
			</tr>
			<tr>
				<td colspan="4">
				<s:textarea name="agendamento.observacao" cols="90" rows="4" theme="simple"/></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<s:submit value="Alterar" cssClass="button" theme="simple" />
				</td>
			</tr>
		</table>
	</s:form>

	
</body>
</html>