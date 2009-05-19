<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
			<td>
				<br>
				<div>Atendimento <img src="../componentes/img/seta.gif" /> Agenda Médica
				<img src="../componentes/img/seta.gif" /> Consultar</div>
			</td>
		</tr>
	</table>
<h2>Consulta de Compromissos</h2>

<s:form id="formConsultaCompromisso" action="compromissoAction!consultarCompromisso.action">
	<table class="tabela_moldura">
		<tr>
			<td>
			<table border="0" width="90%" class="tabela_moldura" cellpadding="3" cellspacing="4">
				<tr>
					<td><label class="label">Médico:</label></td>
					<td colspan="3">
					<s:select theme="simple" name="compromisso.medico.id" list="medicos" headerValue="--Selecione--" headerKey="0" listValue="usuario.nome" listKey="id" />
					</td>
				</tr>
				<tr>
					<td><label class="label">Data:</label></td>
					<td><s:textfield theme="simple" name="compromisso.data" id="data"
						size="10" maxlength="10"/>&nbsp;</td>
			
					<td><label class="label">Tipo de Evento:</label></td>
					<td><select name="compromisso.tipo">
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
	<!-- Lista os compromissos do dia-->
	<s:if test="compromissosCadastrados != null && compromissosCadastrados.size() > 0">
	<br>
	<table class="tabela_moldura" width="90%" cellspacing="1" cellpadding="2" align="center">
		<tr>
			<th colspan="6" class="principal style2" scope="col">Compromissos do Dia</th>
		</tr>
		<tr>		
			<th width="40%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Hora Inicial</span>
			</th>
			<th width="40%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Hora Final</span>
			</th>
			<th width="12%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Tipo</span>
			</th>
			<th width="12%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Descrição</span>
			</th>
			<th width="5%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Editar</span>
			</th>
			<th width="5%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Excluir</span>
			</th>
		</tr>
		<s:iterator value="compromissosCadastrados" status="status">
			<tr class="<s:if test="#status.odd == true"></s:if><s:else>zebra</s:else>">
			
				<!-- Monta a url para carregar a edição do compromisso -->
				<s:url id="editarCompromisso" action="compromissoAction!carregaEdicaoCompromisso.action">
					<s:param name="compromisso.id" value="%{id}"/>
				</s:url>
					<!-- Monta a url para carregar a exclusão do compromisso -->
				<s:url id="excluirCompromisso" action="compromissoAction!excluirCompromisso.action">
					<s:param name="compromisso.id" value="%{id}"/>
				</s:url>
				<td>
					<s:date value="compromisso.data" format="dd/MM/yyyy"/>
					<s:property value="horaInicial" />
				</td>
				<td>
					<s:property value="horaFinal"/>
				</td>
				<td>
					<s:property value="tipo"/>
				</td>
				<td>
					<s:property value="descricao"/>
				</td>
				<td align="center">
					<s:a href="%{#editarCompromisso}">
						<img src="../componentes/img/editar.png" alt="Alterar" />
					</s:a>
				</td>
				<td align="center">
					<s:a href="%{#excluirCompromisso}">
						<img src="../componentes/img/excluir.png" alt="Excluir" />
					</s:a>
				</td>
			
			</tr>
		</s:iterator>	
	</table>
	</s:if>	
</body>
</html>