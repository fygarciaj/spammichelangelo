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
	<title>Atualizar Histórico de Prontuario</title>
</head>
<body>
	<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
    <td>
    	<br>
		<div>Atendimento<img src="../componentes/img/seta.gif" />    		
    	Histórico de Prontuário<img src="../componentes/img/seta.gif" />
		Atualizar				    		
    	</div>		
    </td>	
	</tr>	
	</table>
	<h2>Atualiza Histórico de Prontuário</h2>
	<s:form action="historicoProntuarioAction!carregaAtualizacaoHistorico.action" theme="simple">	
	<s:hidden name="paciente.id" value="%{paciente.id}"/>
	</s:form>

	<!-- Lista agendamentos do dia -->
	<s:if test="agendamentosCadastrados != null && agendamentosCadastrados.size() > 0">
	<br>
	<table class="tabela_moldura" width="90%" cellspacing="1" cellpadding="2" align="left">
		<tr>
			<th colspan="6" class="principal style2" scope="col">Agendamentos do Dia</th>
		</tr>
		<tr>		
			<th width="40%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Paciente</span>
			</th>
			<th width="12%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">CPF</span>
			</th>
			<th width="12%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Telefone</span>
			</th>
			<th width="12%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Data Agendamento</span>
			</th>
			<th width="5%" bgcolor="#A7C2DA" scope="col">
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
				<td>
					<s:property value="paciente.usuario.cpf"/>
				</td>
				<td>
					<s:property value="paciente.usuario.telefone"/>
				</td>
				<td>
					<s:property value="data"/>
				</td>
				<td align="center">
					<s:a href="%{#atualizarProntuario}">
						<img src="../componentes/img/editar.png" alt="Alterar" />
					</s:a>
				</td>			
			</tr>
		</s:iterator>	
	</table>
	</s:if>		
</body>
</html>