<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="componentes/css/estilo.css" type="text/css" />
	<script type="text/javascript" src="componentes/js/sispam.js"></script>
	<link rel="stylesheet" href="../componentes/css/estilo.css" type="text/css" />
	<script type="text/javascript" src="../componentes/js/sispam.js"></script>
	<title>Atualizar Hist�rico de Prontuario</title>
</head>
<body>
	<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
    <td>
    	<br>
		<div>Atendimento<img src="/SisPAM/componentes/img/seta.gif" />	
    	Hist�rico de Prontu�rio<img src="/SisPAM/componentes/img/seta.gif" />
		Atualizar				    		
    	</div>		
    </td>	
	</tr>	
	</table>
	<h2>Atualiza Hist�rico de Prontu�rio</h2>
	<s:form action="historicoProntuarioAction!carregaAtualizacaoHistorico.action" theme="simple">	
	<s:hidden name="paciente.id" value="%{paciente.id}"/>
	</s:form>
	<div id="MensagensErro" >	
		<s:fielderror theme="simple" cssClass="errorMessage" />
		<s:actionmessage theme="simple" cssClass="sucessMessage" />
	</div>
	<!-- Lista agendamentos do dia -->
	<s:if test="agendamentosCadastrados != null && agendamentosCadastrados.size() > 0">
	<br>
	<table class="tabela_listagem"  cellspacing="1" cellpadding="2" align="left">
		<tr>
			<th colspan="7" class="principal style2" scope="col">Agendamentos do Dia</th>
		</tr>
		<tr>		
			<th width="220px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Paciente</span>
			</th>
			<th width="70px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Data de Nascimento</span>
			</th>
			<th width="220px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Conv�nio</span>
			</th>
			<th width="70px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Data Agendada</span>
			</th>
			<th width="70px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Hora Agendada</span>
			</th>
			<th width="150px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Especialidade</span>
			</th>		
			<th width="25px" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Atender</span>
			</th>			
		</tr>
		<s:iterator value="agendamentosCadastrados" status="status">
			<tr class="<s:if test="#status.odd == true"></s:if><s:else>zebra</s:else>">
			
				<!-- Monta a url para carregar a atualizacao de prontuario -->
				<s:url id="atualizarProntuario" action="historicoProntuarioAction!carregaAtualizacaoHistorico.action">
					<s:param name="agendamento.id" value="%{id}"/>
				</s:url>					
				<td>
					<s:property value="paciente.usuario.nome" />
				</td>
				<td align="center">
					<s:date name="paciente.usuario.dataNascimento" format="dd/MM/yyyy"/>
				</td>
				<td>
					<s:property value="paciente.convenio.nome"/>
				</td>
				<td align="center">
					<s:date name="data" format="dd/MM/yyyy"/>
					<td align="center">
					<s:property value="horaFormatada"/>
				</td>				
				<td>
					<s:property value="especialidadeMedica.descricao"/>
				</td>
					<td align="center">
					<s:a href="%{#atualizarProntuario}" cssClass="linkAtualizar" cssStyle="linkAtualizar"></s:a>
				</td>			
			</tr>
		</s:iterator>	
	</table>
	</s:if>		
</body>
</html>