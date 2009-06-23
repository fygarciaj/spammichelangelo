<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
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
			<td>
				<br>
				<div>Atendimento <img src="/SisPAM/componentes/img/seta.gif" /> Agenda Médica
				<img src="/SisPAM/componentes/img/seta.gif" />Alterar/Excluir</div>
			</td>
		</tr>
	</table>
<h2>Consulta de Compromissos</h2>
	<table width="89%" id="AreaMsg" class="AreaMsg">			
	<tr>	
	<td>	
		<div id="MensagensErro" >						
			<s:fielderror theme="simple" cssClass="errorMessage"  cssErrorStyle="errorMessage" cssErrorClass="errorMessage"/>
			<s:actionmessage theme="simple" cssClass="sucessMessage" />

		</div>
	</td>
	</tr>
	</table>
<s:form id="formConsultaCompromisso" action="compromissoAction!consultarCompromisso.action">
			<table border="0" width="90%" class="tabela_moldura" cellpadding="3" cellspacing="4">
				<tr>
					<td><label class="label">Médico:</label></td>
					<td colspan="3">
					<s:select theme="simple" name="compromisso.medico.id" list="medicos" headerValue="--Selecione--" headerKey="0" listValue="usuario.nome" listKey="id"/>
					</td>
					<td><label class="label">Data:</label></td>
					<td><s:textfield theme="simple"  name="compromisso.data" id="data" size="10" maxlength="10"/>&nbsp;</td>
					<td><input type="submit" value="Consultar" class="button"></td>
				</tr>
			</table>
</s:form>
	<!-- Lista os compromissos do dia-->
	<s:if test="compromissosCadastrados != null && compromissosCadastrados.size() > 0">
	<br>
	<table class="tabela_listagem" width="90%" cellspacing="1" cellpadding="2" align="left">
		<tr>
			<th colspan="6" class="principal style2" scope="col">Compromissos do Médico: <s:text name="nomMedico"/></th>
			
		</tr>
		<tr>
			<th width="10%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Data</span>
			</th>		
			<th width="10%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Hora Inicial</span>
			</th>
			<th width="10%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Hora Final</span>
			</th>
			<th width="15%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Tipo</span>
			</th>
			<th width="50%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Descrição</span>
			</th>
			<th width="5%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Alterar</span>
			</th>
			<th width="5%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Excluir</span>
			</th>
		</tr>

		
		<s:iterator value="compromissosCadastrados" status="status">
			<tr class="<s:if test="#status.odd == true"></s:if><s:else>zebra</s:else>" >
				<!-- Monta a url para carregar a edição do compromisso -->
				<s:url id="editarCompromisso" action="compromissoAction!carregaEdicaoCompromisso.action">
					<s:param name="compromisso.id" value="%{id}"/>
				</s:url>
					<!-- Monta a url para carregar a exclusão do compromisso -->
				<s:url id="excluirCompromisso" action="compromissoAction!excluirCompromisso.action">
					<s:param name="compromisso.id" value="%{id}"/>
				</s:url>
				<td align="center">
					<s:date name="data" format="dd/MM/yyyy" />
				</td>				
				<td align="center">
					<s:property value="horaInicialFormatada" />
				</td>
				<td align="center">
					<s:property value="horaFinalFormatada"/>
				</td>
				<td align="center">
					<s:property value="tipoCompromisso"/>
				</td>
				<td>
					<s:property value="descricao"/>
				</td>
				<td align="center">
					<s:a href="%{#editarCompromisso}" cssClass="linkEditar" cssStyle="linkEditar"></s:a>
				</td>
				<td align="center">
					<s:a href="%{#excluirCompromisso}" cssClass="linkExcluir" onclick="return confirmaExclusao()" cssStyle="linkExcluir"></s:a>
				</td>
			
			</tr>
		</s:iterator>	
	</table>
	</s:if>	

	<!-- Lista os Agendamentos do dia-->
	<s:if test="agendamentosCadastrados != null && agendamentosCadastrados.size() > 0">
	<br>
	<table class="tabela_listagem" width="90%" cellspacing="1" cellpadding="2" align="left">
		<tr>
			<th colspan="6" class="principal style2" scope="col">Agendamentos</th>
			
		</tr>
		<tr>
			<th width="8%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Data</span>
			</th>					
			<th width="9%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Hora</span>
			</th>
			<th width="15%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Tipo</span>
			</th>			
			<th width="23%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Especialidade</span>
			</th>
			<th width="45%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Paciente</span>
			</th>
		</tr>

		
		<s:iterator value="agendamentosCadastrados" status="status">
			<tr class="<s:if test="#status.odd == true"></s:if><s:else>zebra</s:else>" >
								
				<td align="center">
					<s:date name="data" format="dd/MM/yyyy" />
				</td>								
				<td align="center">
					<s:property value="horaFormatada"/>
				</td>
				<td align="center">
					<s:property value="tipoAgendamento"/>
				</td>				
				<td>
					<s:property value="especialidadeMedica.descricao"/>
				</td>
				<td align="left">
					<s:property value="paciente.usuario.nome" />
				</td>
			</tr>
		</s:iterator>	
	</table>
	</s:if>	
</body>
</html>