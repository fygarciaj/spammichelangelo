<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../componentes/css/estilo.css" type="text/css" />
<script type="text/javascript" src="../componentes/js/sispam.js"></script>
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
	<table>
		<tr>
				<td><label>Selecione o Perfil&nbsp;</label> <s:select
				list="perfils" headerKey="0" id="perfil" headerValue="--Selecione--"
				name="codigoPerfilSelecionado" onchange="return definirPerfilInclusao()"
				listKey="codigo" theme="simple" /></td>
		</tr>
	</table>
</s:form>
<s:fielderror theme="simple" cssClass="errorMessage"  cssErrorStyle="errorMessage" cssErrorClass="errorMessage"/>
<s:actionmessage theme="simple" cssClass="errorMessage" cssErrorStyle="errorMessage" cssErrorClass="errorMessage"/>

	<s:if test="codigoPerfilSelecionado == 1 || codigoPerfilSelecionado == 2">
	<s:form id="formPerfil" action="usuarioAction!salvarUsuario.action">
	<s:hidden name="codigoPerfilSelecionado"
		value="%{codigoPerfilSelecionado}" />
		<table border="0" width="90%" class="tabela_moldura" cellpadding="3"
			cellspacing="4">
			<tr>
				<td>Nome:&nbsp;<s:textfield theme="simple" name="usuario.nome"
					size="60" maxlength="60" /></td>
				<td>Sexo:&nbsp;<s:select list="sexos" theme="simple"
					name="usuario.sexo" headerKey="0" headerValue="" listKey="sigla" /></td>
				<td align="left">CPF:&nbsp;<s:textfield theme="simple"
					name="usuario.cpf" size="12" maxlength="11" /></td>
			</tr>
			<tr>
				<td>RG:&nbsp;<s:textfield theme="simple" name="rgAux"
					size="15" maxlength="15" />&nbsp; Expedidor:&nbsp;<s:textfield
					theme="simple" name="usuario.expedidorRg" size="15" maxlength="15" />
				</td>
				<td colspan="2">E-mail:&nbsp;<s:textfield theme="simple"
					name="usuario.email" size="30" maxlength="30" /></td>
			</tr>
			<tr>
				<td>Endereço:&nbsp;<s:textfield theme="simple"
					name="usuario.endereco" size="60" maxlength="60" /></td>
				<td align="left" colspan="2">Cidade:&nbsp;<s:textfield
					theme="simple" name="usuario.cidade" size="12" maxlength="11" />&nbsp;
				UF:&nbsp;<select name="usuario.uf">
					<option value="1">Distrito Federal</option>
					<option value="2">Goiás</option>
					<option value="3">São Paulo</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="1" width="40%">Cep:&nbsp;<s:textfield
					theme="simple" name="cepAux" size="15" maxlength="8" />&nbsp;
				DDD:&nbsp;<s:textfield theme="simple" name="dddAux" size="3"
					maxlength="3" />&nbsp; Telefone:&nbsp;<s:textfield theme="simple"
					name="telefoneAux" size="8" maxlength="8" /></td>
				<td colspan="2">Senha:&nbsp;<s:password theme="simple"
					name="usuario.senha" maxlength="25" size="25" /></td>
			</tr>
			<tr>
				<s:submit value="Salvar" cssClass="botao_salvar" />
			</tr>
		</table>
	</s:form>
	</s:if>
	<s:elseif test="codigoPerfilSelecionado == 3">
		<s:form id="formPerfil" action="medicoAction!salvarMedico.action">
			<s:hidden name="codigoPerfilSelecionado" value="%{codigoPerfilSelecionado}" />
			<table border="0" width="100%" class="tabela_moldura" cellpadding="3"
				cellspacing="4">
				<tr>
					<td>Nome:&nbsp;<s:textfield theme="simple" name="usuario.nome"
						size="60" maxlength="60" /></td>
					<td>Sexo:&nbsp;<s:select list="sexos" theme="simple"
						headerKey="0" headerValue="" listKey="sigla" /></td>
					<td align="left">CPF:&nbsp;<s:textfield theme="simple"
						name="usuario.cpf" size="12" maxlength="11" /></td>
				</tr>
				<tr>
					<td>RG:&nbsp;<s:textfield theme="simple" name="usuario.rg"
						size="15" maxlength="15" />&nbsp; Expedidor:&nbsp;<s:textfield
						theme="simple" name="usuario.expedidorRg" size="15" maxlength="15" />
					</td>
					<td colspan="2">E-mail:&nbsp;<s:textfield theme="simple"
						name="usuario.email" size="30" maxlength="30" /></td>
				</tr>
				<tr>
				<td>Endereço:&nbsp;<s:textfield theme="simple"
					name="usuario.endereco" size="60" maxlength="60" /></td>
				<td align="left" colspan="2">Cidade:&nbsp;<s:textfield
					theme="simple" name="usuario.cidade" size="12" maxlength="11" />&nbsp;
				UF:&nbsp;<select name="estado">
					<option>Distrito Federal</option>
					<option>Goiás</option>
					<option>São Paulo</option>
				</select></td>
				</tr>
				<tr>
					<td colspan="1" width="40%">Cep:&nbsp;<s:textfield
						theme="simple" name="usuario.cep" size="15" maxlength="8" />&nbsp;
					DDD:&nbsp;<s:textfield theme="simple" name="usuario.ddd" size="3"
						maxlength="3" />&nbsp; Telefone:&nbsp;<s:textfield theme="simple"
						name="usuario.telefone" size="8" maxlength="8" /></td>
					<td colspan="2">Senha:&nbsp;<s:password theme="simple"
						name="usuario.senha" maxlength="25" size="25" /></td>
				</tr>
				<tr>
					<td>CRM:&nbsp;<s:textfield theme="simple" name="medico.crm"
						size="7" maxlength="7" />&nbsp; UF:&nbsp;<select
						name="medico.crmUf">
						<option>Distrito Federal</option>
						<option>Goiás</option>
						<option>São Paulo</option>
					</select></td>
				</tr>
			<s:submit value="Salvar" cssClass="botao_salvar" />
		</table>
		</s:form>
	</s:elseif>
</body>
</html>