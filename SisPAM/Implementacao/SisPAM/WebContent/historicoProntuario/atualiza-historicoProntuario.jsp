<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	<s:form action="historicoProntuarioAction!carregarAgendamentos.action" theme="simple">	

	<s:hidden name="paciente.id" value="%{paciente.id}"/>
		<table border="0" class="tabela_moldura" cellpadding="3" cellspacing="4" >
			<tr>
				<td width="271px"><label class="label">Nome:</label></td>
				<td><s:label value="%{nome}" theme="simple" name="agendamento.paciente.nome"/></td>
				<td width="271px"><label class="label">CPF:</label></td>
				<td><s:label  value="%{usuario.cpf}" theme="simple" name="usuario.cpf"/></td>
			</tr>
			<tr>
				<td><label class="label">RG:</label></td>
				<td><s:label value="%{rgAux}" theme="simple" name="rgAux"/>
				<td><label class="label">Expedidor:</label></td>
				<td><s:label value="%{usuario.expedidorRg}" theme="simple" name="usuario.expedidorRg"/></td>				
			</tr>
			<tr>
				<td><label class="label">Endereço:</label></td>
				<td><s:label value="%{usuario.endereco}" theme="simple" name="usuario.endereco"/></td>
				<td><label class="label">Cidade:</label></td>
				<td><s:label value="%{usuario.cidade}" theme="simple" name="usuario.cidade"/></td>
			</tr>
			<tr>
				<td><label class="label">Estado:</label></td>
				<td>
				<s:label value="%{usuario.uf}" name="usuario.uf" theme="simple"/></td>						
					<td><label class="label">CEP:</label></td>
					<td><s:label value="%{cepAux}" theme="simple" name="cepAux"/></td>				
			</tr>
			<tr>
				<td><label class="label">DDD:</label></td>
				<td>
					<s:label value="%{dddAux}" theme="simple" name="dddAux"/></td>
					<td><label class="label">Telefone:</label></td><td>
					<s:label value="%{telefoneAux}" theme="simple" name="telefoneAux"/>
				</td>
			</tr>
			<tr>				
				<td><label class="label">E-mail:</label></td>
				<td><s:label  value="%{usuario.email}" theme="simple" name="usuario.email"/></td>
				<td><label class="label">Sexo:</label></td>
				<td><s:label value="%{usuario.sexo}" theme="simple" name="usuario.sexo"/></td>				
			</tr>			
		</table>
		<table class="tabela_moldura">
			<tr>
				<td><label>Sintoma</label>
			</tr>
			<tr>
				<td><s:textarea name="historicoProntuario.sintoma" id="sintoma" theme="simple" cols="70" rows="5"/></td>
			</tr>
			<tr>	
				<td><label>Laudo</label></td>
			</tr>
			<tr>
				<td><s:textarea name="historicoProntuario.laudo" id="sintoma" theme="simple" cols="70" rows="5"/></td>
			</tr>
			<tr>	
				<td><label>Prescrição</label>
			</tr>
			<tr>
				<td><s:textarea name="historicoProntuario.prescricao" id="sintoma" theme="simple" cols="70" rows="5"/></td>
			</tr>
			<tr>	
				<td><label>Observação</label></td>
			</tr>
			<tr>
				<td><s:textarea name="historicoProntuario.observacao" id="sintoma" theme="simple" cols="70" rows="5"/></td>
			</tr>
			<tr>				
				<td align="center"><s:submit value="Atualizar" cssClass="button" theme="simple"/></td>
			</tr>
		</table>
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
				<span class="style5">X</span>
			</th>
			<th width="5%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Editar</span>
			</th>
			<th width="5%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Excluir</span>
			</th>
		</tr>
		<s:iterator value="agendamentosCadastrados" status="status">
			<tr class="<s:if test="#status.odd == true"></s:if><s:else>zebra</s:else>">
			
				<!-- Monta a url para carregar a edição dos agendamentos -->
				<s:url id="editarConvenio" action="historicoProntuarioAction!carregarAgendamentos.action">
					<s:param name="agendamento.id" value="%{id}"/>
				</s:url>
					<!-- Monta a url para carregar a exclusão do convênio -->
				<s:url id="excluirConvenio" action="historicoProntuarioAction!carregarAgendamentos.action">
					<s:param name="agendamento.id" value="%{id}"/>
				</s:url>
				<td>
					<s:property value="agendamento.paciente.usuario.nome" />
				</td>
				<td>
					<s:property value="agendamento.paciente.usuario.cpf"/>
				</td>
				<td>
					<s:property value="agendamento.paciente.usuario.telefone"/>
				</td>
				<td>
					<s:property value="agendamento.data"/>
				</td>
				<td align="center">
					<s:a href="%{#editarConvenio}">
						<img src="../componentes/img/editar.png" alt="Alterar" />
					</s:a>
				</td>
				<td align="center">
					<s:a href="%{#excluirConvenio}">
						<img src="../componentes/img/excluir.png" alt="Excluir" />
					</s:a>
				</td>
			
			</tr>
		</s:iterator>	
	</table>
	</s:if>		
</body>
</html>