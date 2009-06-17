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
	<title>Emite Receita</title>
	
	<script type="text/javascript">

		function emiteReceita(paciente, dataAg, horaAg){
			document.getElementById("pacienteForm").value = paciente;
			document.getElementById("horaForm").value = horaAg;
			document.getElementById("dataForm").value = dataAg;
			document.forms[1].submit();
		}

		 $(document).ready(function(){
				calendario('dataAgendamento');
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
		<div>Atendimento<img src="/SisPAM/componentes/img/seta.gif" />Receita<img
			src="/SisPAM/componentes/img/seta.gif" />Emitir</div>
		</td>
</table>
<h2>Emissão de Receita</h2>

<s:form action="receitaAction!consultarAgendamento.action" theme="simple">
	<s:hidden name="paciente.id" value="%{paciente.id}"/>
	<div id="MensagensErro" >	
		<s:fielderror theme="simple" cssClass="errorMessage" />
		<s:actionmessage theme="simple" cssClass="sucessMessage" />
	</div>	
	<table class="tabela_consulta" >			
			<tr>				
				<td><label>Data de Atendimento:</label>&nbsp;<s:textfield id="dataAgendamento" size="12" maxlength="7" name="dataAgendamento" theme="simple"/></td>
				<td></td><td><s:submit value="Consultar" cssClass="button" theme="simple"/></td>
			</tr>			
		</table>
	</s:form>
	
	
	<h3>Atenção! Clique no botão "Emitir Receita" para gerar a receita</h3>
	<!-- Lista agendamentos realizados -->
	<s:if test="agendamentosCadastrados != null && agendamentosCadastrados.size() > 0">
	<br>
	<table class="tabela_listagem"  cellspacing="1" cellpadding="2" align="left">
		<tr>
			<th colspan="6" class="principal style2" scope="col">Agendamentos Atendidos do Dia</th>
		</tr>
		<tr>		
			<th width="250px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Paciente</span>
			</th>
			<th width="140px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Data de Nascimento</span>
			</th>
			<th width="60px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Convênio</span>
			</th>
			<th width="140px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Data Agendamento</span>
			</th>
			<th width="100px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Hora Agendada</span>
			</th>
			<th width="100px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Especialidade</span>
			</th>		
			<th width="25px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Emitir Receita</span>
			</th>			
		</tr>
		<s:iterator value="agendamentosCadastrados" status="status">
			<tr class="<s:if test="#status.odd == true"></s:if><s:else>zebra</s:else>">
				<s:hidden name="agendamento.paciente.id" value="%{paciente.id}" id="paciente"/>
				<s:hidden name="hora" value="%{hora}" id="hora"/>
				<s:hidden name="data" value="%{data}" id="data"/>
		
				<td>
					<s:property value="paciente.usuario.nome" />
				</td>
				<td>
					<s:date name="paciente.usuario.dataNascimento" format="dd/MM/yyyy"/>
				</td>
				<td>
					<s:property value="paciente.usuario.convenio.nome"/>
				</td>
				<td>
					<s:date name="data" format="dd/MM/yyyy"/>
					<td>
					<s:property value="hora"/>
				</td>				
				<td>
					<s:property value="especialidadeMedica.descricao"/>
				</td>
					<td align="center">
					<a href="#" onclick="return emiteReceita(<s:property value="paciente.id"/>, '<s:property value="data"/>', <s:property value="hora"/>)"><img src="/SisPAM/componentes/img/relatorio.gif" alt="emitir receita" height="27px" width="27px"/></a>
				</td>			
			</tr>
		</s:iterator>	
	</table>
	</s:if>	
	<form action="/SisPAM/emiteReceita.sispam" method="post" name="relatorio">
		<input type="hidden" name="paciente" id="pacienteForm"/>
		<input type="hidden" name="hora" id="horaForm">
		<input type="hidden" name="data" id="dataForm">
		<input type="hidden" name="relatorioChamado" value="receita">
	</form>	
</body>
</html>