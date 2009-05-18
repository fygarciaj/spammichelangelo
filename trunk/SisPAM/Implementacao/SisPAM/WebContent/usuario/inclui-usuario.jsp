<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../componentes/css/estilo.css" type="text/css" />
<script type="text/javascript" src="../componentes/js/sispam.js"></script>
<link rel="stylesheet" href="componentes/css/estilo.css" type="text/css" />
<script type="text/javascript" src="componentes/js/sispam.js"></script>
<title>Insert title here</title>
</head>
<body>
<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
		<td><br>
		<div>Cadastro<img src="../componentes/img/seta.gif" /> Usuário<img
			src="../componentes/img/seta.gif" /> Incluir</div>
		</td>
	</tr>
</table>
<h2>Cadastro de Usuários</h2>
<s:form action="usuarioAction!definirTelaUsuario.action">
	<table class="tabela_moldura">
		<tr>
			<td><label class="label">Selecione o Perfil&nbsp;</label> <s:select
				list="perfils" headerKey="0" id="perfil" headerValue="Selecione"
				name="codigoPerfilSelecionado" onchange="return definirPerfil()"
				listKey="codigo" theme="simple" /></td>
		</tr>
	</table>
</s:form>

<div id="MensagensErro" >	
	<s:fielderror theme="simple" cssClass="errorMessage" />
	<s:actionmessage theme="simple" cssClass="sucessMessage" />
</div>

<s:if test="codigoPerfilSelecionado == 1 || codigoPerfilSelecionado == 2">
	<s:form id="formPerfil" action="usuarioAction!salvarUsuario.action">
	<s:hidden name="codigoPerfilSelecionado" value="%{codigoPerfilSelecionado}" />
	<s:hidden name="usuario.id" value="%{usuario.id}"/>
		<table border="0" width="90%" class="tabela_moldura" cellpadding="3" cellspacing="4">
			<tr>
				<td><label class="label">Nome:</label></td>
				<td><s:textfield theme="simple" name="usuario.nome" size="60" maxlength="60" /></td>
				<td><label class="label">CPF:</label></td>
				<td><s:textfield theme="simple" name="usuario.cpf" size="12" maxlength="11" /></td>
			</tr>
			<tr>
				<td><label class="label">RG:</label></td>
				<td><s:textfield theme="simple" name="rgAux" size="15"	maxlength="15" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="label">Expedidor:&nbsp;&nbsp;</label>
				<s:textfield theme="simple" name="usuario.expedidorRg" size="15" maxlength="15" /></td>
				<td><label class="label">Sexo:</label></td>
				<td><s:select list="sexos" theme="simple" name="usuario.sexo" headerKey="0" headerValue="" listKey="sigla" /></td>
			</tr>
			<tr>
				<td><label class="label">Endereço:</label></td>
				<td><s:textfield theme="simple" name="usuario.endereco"	size="60" maxlength="60" /></td>
				<td><label class="label">Cidade:</label></td>
				<td><s:textfield theme="simple" name="usuario.cidade" size="12" maxlength="11" /></td>
			</tr>
			<tr>
				<td><label class="label">Estado:</label></td>
				<td>
				<s:select name="usuario.uf" theme="simple"  list="#{'':'Selecione','AC':'Acre', 'AL':'Alagoas', 'AP': 'Amapá', 
					'AM':'Amazônas', 'BA': 'Bahia', 'CE':'Ceará', 'DF':'Distrito Federal', 'ES':'Espírito Santo', 'GO':'Goiás', 
					'MA': 'Maranhão', 'MT':'Mato Grosso', 'MS':'Mato Grosso do Sul', 'MG':'Minas Gerais', 'PA':'Pará', 
					'PB':'Paraíba', 'PR':'Paraná', 'PE':'Pernambuco', 'PI':'Piauí', 'RJ':'Rio de Janeiro', 'RN':'Rio Grande do Norte', 
					'RS':'Rio Grande do Sul', 'RO':'Rondônia', 'RR':'Roraima', 'SC':'Santa Catariana', 'SP':'São Paulo', 
					'SE':'Sergipe', 'TO': 'Tocantins'}" />	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label class="label">CEP:&nbsp;&nbsp;</label>
					<s:textfield theme="simple" name="cepAux" size="15" maxlength="8" />
				</td>
				<td><label class="label">DDD:</label></td>
				<td>
					<s:textfield theme="simple" name="dddAux" size="2"	maxlength="2" />&nbsp;&nbsp; 
					<label class="label">Tel:&nbsp;&nbsp;</label>
					<s:textfield theme="simple" name="telefoneAux" size="8" maxlength="8" />
				</td>
			</tr>
			<tr>
				<td><label class="label">Login:</label></td>
				<td><s:textfield theme="simple" name="usuario.acesso" maxlength="25" size="25" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="label">Senha:&nbsp;&nbsp;</label><s:password theme="simple" name="usuario.senha"	maxlength="8" size="6" /></td>
				<td><label class="label">E-mail:</label></td>
				<td><s:textfield theme="simple" name="usuario.email" size="30" maxlength="30" /></td>
				
			</tr>
			<tr>
				<td><s:submit value="Salvar" cssClass="button" /></td>
			</tr>
	</table>
</s:form>
</s:if>	
<s:elseif test="codigoPerfilSelecionado == 3">
	<s:form action="medicoAction!salvarMedico.action" name="formMedico">
	<s:hidden name="codigoPerfilSelecionado" value="%{codigoPerfilSelecionado}" />
	<s:hidden name="usuario.id" value="%{usuario.id}"/>
		<table border="0" width="90%" class="tabela_moldura" cellpadding="3" cellspacing="4">
			<tr>
				<td><label class="label">Nome:</label></td>
				<td><s:textfield theme="simple" name="medico.usuario.nome" size="60" maxlength="60" /></td>
				<td><label class="label">CPF:</label></td>
				<td><s:textfield theme="simple" name="medico.usuario.cpf" size="12" maxlength="11" /></td>
			</tr>
			<tr>
				<td><label class="label">RG:</label></td>
				<td><s:textfield theme="simple" name="rgAux" size="15"	maxlength="15" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="label">Expedidor:&nbsp;&nbsp;</label>
				<s:textfield theme="simple" name="medico.usuario.expedidorRg" size="15" maxlength="15" /></td>
				<td><label class="label">Sexo:</label></td>
				<td><s:select list="sexos" theme="simple" name="medico.usuario.sexo" headerKey="0" headerValue="" listKey="sigla" /></td>
			</tr>
			<tr>
				<td><label class="label">Endereço:</label></td>
				<td><s:textfield theme="simple" name="medico.usuario.endereco"	size="60" maxlength="60" /></td>
				<td><label class="label">Cidade:</label></td>
				<td><s:textfield theme="simple" name="medico.usuario.cidade" size="12" maxlength="11" /></td>
			</tr>
			<tr>
				<td><label class="label">Estado:</label></td>
				<td>
				<s:select name="medico.usuario.uf" theme="simple"  list="#{'':'Selecione','AC':'Acre', 'AL':'Alagoas', 'AP': 'Amapá', 
					'AM':'Amazônas', 'BA': 'Bahia', 'CE':'Ceará', 'DF':'Distrito Federal', 'ES':'Espírito Santo', 'GO':'Goiás', 
					'MA': 'Maranhão', 'MT':'Mato Grosso', 'MS':'Mato Grosso do Sul', 'MG':'Minas Gerais', 'PA':'Pará', 
					'PB':'Paraíba', 'PR':'Paraná', 'PE':'Pernambuco', 'PI':'Piauí', 'RJ':'Rio de Janeiro', 'RN':'Rio Grande do Norte', 
					'RS':'Rio Grande do Sul', 'RO':'Rondônia', 'RR':'Roraima', 'SC':'Santa Catariana', 'SP':'São Paulo', 
					'SE':'Sergipe', 'TO': 'Tocantins'}" />	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label class="label">CEP:&nbsp;&nbsp;</label>
					<s:textfield theme="simple" name="cepAux" size="15" maxlength="8" />
				</td>
				<td><label class="label">DDD:</label></td>
				<td>
					<s:textfield theme="simple" name="dddAux" size="2"	maxlength="2" />&nbsp;&nbsp; 
					<label class="label">Tel:&nbsp;&nbsp;</label>
					<s:textfield theme="simple" name="telefoneAux" size="8" maxlength="8" />
				</td>
			</tr>
			<tr>
				<td><label class="label">Consultório:</label></td><td><s:textfield theme="simple" name="consultorioAux" maxlength="7" size="7" />&nbsp;&nbsp;&nbsp;
				<label class="label">Hora início:&nbsp;&nbsp;</label><s:textfield theme="simple" name="horaIni" maxlength="4" size="4" />&nbsp;&nbsp;&nbsp;
				<label class="label">Hora Fim:</label>&nbsp;&nbsp;<s:textfield theme="simple" name="horaFim" maxlength="4" size="4" /></td>
				<td><label class="label">CRM:</label></td>
				<td><s:textfield theme="simple" name="crmAux" size="7" maxlength="7" />&nbsp;&nbsp; 
				<label class="label">UF-CRM:</label>&nbsp;
					<s:select name="medico.crmUf" theme="simple"  list="#{'':'Selecione','AC':'Acre', 'AL':'Alagoas', 'AP': 'Amapá', 
					'AM':'Amazônas', 'BA': 'Bahia', 'CE':'Ceará', 'DF':'Distrito Federal', 'ES':'Espírito Santo', 'GO':'Goiás', 
					'MA': 'Maranhão', 'MT':'Mato Grosso', 'MS':'Mato Grosso do Sul', 'MG':'Minas Gerais', 'PA':'Pará', 
					'PB':'Paraíba', 'PR':'Paraná', 'PE':'Pernambuco', 'PI':'Piauí', 'RJ':'Rio de Janeiro', 'RN':'Rio Grande do Norte', 
					'RS':'Rio Grande do Sul', 'RO':'Rondônia', 'RR':'Roraima', 'SC':'Santa Catariana', 'SP':'São Paulo', 
					'SE':'Sergipe', 'TO': 'Tocantins'}" />	
			 </td>

			</tr>
			<tr>
				<td><label class="label">Login:</label></td>
				<td><s:textfield theme="simple" name="medico.usuario.acesso" maxlength="25" size="25" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="label">Senha:</label>&nbsp;&nbsp;&nbsp;<s:password theme="simple" name="medico.usuario.senha"	maxlength="8" size="6" /></td>
				<td><label class="label">E-mail:</label></td>
				<td><s:textfield theme="simple" name="medico.usuario.email" size="30" maxlength="30" /></td>
			</tr>
			<tr>				
			</tr>			
			<tr>
			<td colspan="3">
				 	<s:optiontransferselect theme="simple"
				 		list="%{especialidades}"
				 		listKey="id" listValue="descricao"
				 		doubleName="teste" 
				 		doubleList="%{medico.especialidades}"
				 		doubleListKey="id" doubleListValue="descricao"></s:optiontransferselect>			
			</td>							
				<td ><label class="label">Dias de Atendimento</label>&nbsp;
				<s:iterator value="dias">
					<br>
					<s:if test="medico.dias.contains('QUARTA')">					
						<s:checkbox value="true" name="dia-%{codigo}" theme="simple"/><s:property value="descricao" />
					</s:if>
					<s:else>
						<s:checkbox value="false" name="dia-%{codigo}" theme="simple"/><s:property value="descricao" />
					</s:else>					
				</s:iterator>
				</td>						
				<td><s:submit value="Salvar" cssClass="button" /></td>
			</tr>
	</table>
	</s:form>		
</s:elseif>
</body>
</html>