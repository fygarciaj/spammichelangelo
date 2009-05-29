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
	<s:form action="historicoProntuarioAction!atualizarHistoricoProntuario.action" theme="simple">	
	<div id="MensagensErro" >	
		<s:fielderror theme="simple" cssClass="errorMessage" />
		<s:actionmessage theme="simple" cssClass="sucessMessage" />
	</div>
	<s:hidden name="historicoProntuario.paciente.id" value="%{agendamento.paciente.id}"/>
	<s:hidden name="agendamento.id" value="%{agendamento.id}"/>
		<table class="tabela_listagem">
			<tr>
				<td><label class="label">Nome:</label></td>
				<td><s:property value="agendamento.paciente.usuario.nome"></s:property></td>
				
				<td ><label class="label">CPF:</label></td>
				<td><s:property  value="agendamento.paciente.usuario.cpf" /></td>
			</tr>
			<tr>
				<td><label class="label">RG:</label></td>
				<td><s:property value="agendamento.paciente.usuario.rg" /></td>
				<td><label class="label">Data de Nascimento:</label></td>
				<td><s:date name="agendamento.paciente.usuario.dataNascimento" format="dd/MM/yyyy"/></td>				
			</tr>
			<tr>
				<td><label class="label">Endereço:</label></td>
				<td><s:property value="agendamento.paciente.usuario.endereco"/></td>
				<td><label class="label">Cidade:</label></td>
				<td><s:property value="agendamento.paciente.usuario.cidade"/></td>
			</tr>
			<tr>
				<td><label class="label">Estado:</label></td>
				<td>
				<s:property value="agendamento.paciente.usuario.uf"/></td>						
					<td><label class="label">CEP:</label></td>
					<td><s:property value="agendamento.paciente.usuario.cep"/></td>				
			</tr>
			<tr>
				<td><label class="label">DDD:</label></td>
				<td>
					<s:property value="agendamento.paciente.usuario.ddd"/></td>
					<td><label class="label">Telefone:</label></td><td>
					<s:property value="agendamento.paciente.usuario.telefone"/>
				</td>
			</tr>
			<tr>				
				<td><label class="label">E-mail:</label></td>
				<td><s:property  value="agendamento.paciente.usuario.email"/></td>
				<td><label class="label">Sexo:</label></td>
				<td><s:property value="agendamento.paciente.usuario.sexo"/></td>				
			</tr>			
		</table>
		<table class="tabela_listagem">
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
				<td><label class="label">CID:</label></td>
				<td colspan="3">
				<s:select theme="simple" name="historicoProntuario.codigoDoenca.id" list="codigosDoencas" headerValue="--Selecione--" headerKey="0" listValue="codigoDoenca.abreviatura" listKey="id" />
				</td>
			</tr>
			<tr>				
				<td align="center"><s:submit value="Atualizar" cssClass="button" theme="simple"/></td>
			</tr>
		</table>
	</s:form>	
</body>
</html>