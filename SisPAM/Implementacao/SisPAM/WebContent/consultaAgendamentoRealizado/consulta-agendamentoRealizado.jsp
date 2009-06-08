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
	</script>
</head>
<body>
	<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
    <td>
    	<br>
		<div>Atendimento<img src="../componentes/img/seta.gif" />    		
    	Consulta Realizado<img src="../componentes/img/seta.gif" />
		Consultar				    		
    	</div>		
    </td>	
	</tr>	
	</table>
	<h2>Consulta de Agendamentos Realizados</h2>
	<s:form action="agendamentoAction!consultarAgendamentoRealizado.action">
		<table class="tabela_moldura">
			<tr>			        	
	        	<td>
	        		<label class="label">Data</label>
	        		<s:textfield id="data" size="12" maxlength="7" name="dataAgendamento" theme="simple"/>
	        	</td>
	        	<td>
	        		<label class="label">Tipo</label>
	        		<s:select list="tipoAgendamento" name="agendamento.tipo" headerKey="0" headerValue="--Selecione--" listKey="codigo" theme="simple"/>
	        	</td>
				<td>
					<s:submit value="Consultar" cssClass="button" theme="simple"/>
				</td>
			</tr>
		</table>	
	</s:form>
		
	<!-- Lista dos últimos Agendamentos Realizados -->
	<s:if test="agendamentos != null && agendamentos.size() > 0">
	<br>
	<table class="tabela_moldura" width="90%" cellspacing="1" cellpadding="2" align="center">
		<tr>
			<th colspan="8" class="principal style2" scope="col">Últimos Agendamentos Realizados<s:property value="agendamentoRetornado"/></th>
		</tr>
		<tr>		
			<th width="10%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Data</span>
			</th>
			<th width="20%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Médico</span>
			</th>
			<th width="20%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Paciente</span>
			</th>
			<th width="15%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Especialidade</span>
			</th>
			<th width="10%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Horário</span>
			</th>
			<th width="10%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Tipo</span>
			</th>			
		</tr>
		<s:iterator value="agendamentos" status="status">
			<tr class="<s:if test="#status.odd == true"></s:if><s:else>zebra</s:else>">					
				<td align="center">
					<s:date name="data" format="dd/MM/yyyy" />
				</td>
				<td>
					<s:property value="medico.usuario.nome"/>
				</td>
				<td>
					<s:property value="paciente.usuario.nome"/>
				</td>
				<td>
					<s:property value="especialidadeMedica.descricao"/>
				</td>
				<td align="center">
					<s:property value="hora"/>
				</td>
				<td align="center">
					<s:property value="tipoAgendamento"/>
				</td>			
			</tr>
		</s:iterator>	
	</table>
	</s:if>		
</body>
</html>