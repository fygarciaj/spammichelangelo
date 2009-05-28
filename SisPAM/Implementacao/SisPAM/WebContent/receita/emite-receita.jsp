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
	<title>Insert title here</title>
</head>
<body>
<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
		<td><br>
		<div>Relatório<img src="../componentes/img/seta.gif" />Receita<img
			src="../componentes/img/seta.gif" />Emitir</div>
		</td>
</table>
<h2>Emissão de Receita</h2>

<s:form action="receitaAction!carregaEmissaoReceita.action" theme="simple">
		<table class="tabela_moldura" >
			<tr>
				<td><label>Paciente:</label></td><td><s:textfield name="usuario.nome" id="nome" theme="simple" size="40" maxlength="40"/></td>
			</tr>
			<tr>				
				<td><label>Data de Atendimento:</label></td><td><s:textfield name="usuario.nome" id="nome" theme="simple" size="10" maxlength="10"/></td>
			</tr>
			<tr>	
				<td colspan="1"></td><td><s:submit value="Consultar" cssClass="button" theme="simple"/></td>
			</tr>
		</table>
	</s:form>
	
	<s:hidden name="paciente.id" value="%{paciente.id}"/>
	<div id="MensagensErro" >	
		<s:fielderror theme="simple" cssClass="errorMessage" />
		<s:actionmessage theme="simple" cssClass="sucessMessage" />
	</div>
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
			
				<!-- Monta a url para carregar a atualizacao de prontuario -->
				<s:url id="emitirReceita" action="../relatorioConvenio.sispam" method="post">
					<s:param name="agendamento.id" value="%{id}"/>
				</s:url>					
				<td>
					<s:property value="paciente.usuario.nome" />
				</td>
				<td>
					<s:property value="paciente.usuario.dataNascimento"/>
				</td>
				<td>
					<s:property value="paciente.usuario.convenio.nome"/>
				</td>
				<td>
					<s:property value="data"/>
					<td>
					<s:property value="hora"/>
				</td>				
				<td>
					<s:property value="especialidadeMedica.descricao"/>
				</td>
					<td align="center">
					<s:a href="%{#emitirReceita}">
						<img src="../componentes/img/editar.png" alt="emitir receita" />
					</s:a>
				</td>			
			</tr>
		</s:iterator>	
	</table>
	</s:if>		
</body>
</html>