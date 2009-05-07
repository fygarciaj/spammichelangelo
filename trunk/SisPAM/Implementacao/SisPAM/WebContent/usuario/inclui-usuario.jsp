<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../componentes/css/estilo.css"
	type="text/css" />
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
<s:fielderror cssErrorClass="errorMessage" />
<s:actionmessage />
<s:form id="formPerfil" action="usuarioAction!salvarUsuario.action">
	<table class="tabela_moldura">
		<tr>
			<td><s:hidden name="codigoPerfilSelecionado"
				value="%{codigoPerfilSelecionado}" /> <s:if
				test="codigoPerfilSelecionado == 1 || codigoPerfilSelecionado == 2">
				<table border="0" width="100%" class="tabela_moldura"
					cellpadding="3" cellspacing="4">
					<tr>
						<td><label class="label">Nome:</label></td>
						<td><s:textfield theme="simple" name="usuario.nome" size="60"
							maxlength="60" /></td>
						<td><label class="label">CPF:</label></td>
						<td><s:textfield theme="simple" name="usuario.cpf" size="12"
							maxlength="11" /></td>
					</tr>
					<tr>
						<td><label class="label">RG:</label></td>
						<td><s:textfield theme="simple" name="usuario.rg" size="15"
							maxlength="15" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label class="label">Expedidor:&nbsp;&nbsp;</label><s:textfield
							theme="simple" name="usuario.expedidorRg" size="15"
							maxlength="15" />
						<td><label class="label">Sexo:</label></td>
						<td><s:select list="sexos" theme="simple" name="usuario.sexo"
							headerKey="0" headerValue="" listKey="sigla" /></td>
					</tr>
					<tr>
						<td><label class="label">Endereço:</label></td>
						<td><s:textfield theme="simple" name="usuario.endereco"
							size="60" maxlength="60" /></td>
						<td><label class="label">Cidade:</label></td>
						<td><s:textfield theme="simple" name="usuario.cidade"
							size="12" maxlength="11" />
					</tr>
					<tr>
						<td><label class="label">Estado:</label></td>
						<td><select name="usuario.uf">
							<option value="0">Selecione</option>
							<option value="AC">Acre</option>
							<option value="AL">Alagoas</option>
							<option value="AP">Amapá</option>
							<option value="AM">Amazonas</option>
							<option value="BA">Bahia</option>
							<option value="CE">Ceará</option>
							<option value="DF">Distrito Federal</option>
							<option value="ES">Espírito Santo</option>
							<option value="GO">Goiás</option>
							<option value="MA">Maranhão</option>
							<option value="MT">Mato Grosso</option>
							<option value="MS">Mato Grosso do Sul</option>
							<option value="MG">Minas Gerais</option>
							<option value="PA">Pará</option>
							<option value="PB">Paraíba</option>
							<option value="PR">Paraná</option>
							<option value="PE">Pernambuco</option>
							<option value="PI">Piauí</option>
							<option value="RJ">Rio de Janeiro</option>
							<option value="RN">Rio Grande do Norte</option>
							<option value="RS">Rio Grande do Sul</option>
							<option value="RO">Rondônia</option>
							<option value="RR">Roraima</option>
							<option value="SC">Santa Catarina</option>
							<option value="SP">São Paulo</option>
							<option value="SE">Sergipe</option>
							<option value="TO">Tocantins</option>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label class="label">CEP:&nbsp;&nbsp;</label><s:textfield
							theme="simple" name="usuario.cep" size="15" maxlength="8" /></td>

						<td><label class="label">DDD:</label></td>
						<td><s:textfield theme="simple" name="usuario.ddd" size="3"
							maxlength="3" />&nbsp;&nbsp; <label class="label">Tel:&nbsp;&nbsp;</label><s:textfield
							theme="simple" name="usuario.telefone" size="8" maxlength="8" /></td>
					</tr>
					<tr>
						<td><label class="label">E-mail:</label></td>
						<td><s:textfield theme="simple" name="usuario.email"
							size="30" maxlength="30" /></td>
						<td><label class="label">Senha:</label></td>
						<td><s:password theme="simple" name="usuario.senha"
							maxlength="25" size="25" /></td>

					</tr>
					<table border="0" align="center">
						<tr>
							<td><s:submit value="Salvar" cssClass="button" /></td>
						</tr>
					</table>
				</table>
			</s:if> <s:elseif test="codigoPerfilSelecionado == 3">
				<table border="0" width="100%" class="tabela_moldura"
					cellpadding="3" cellspacing="4">
					<tr>
						<td><label class="label">Nome:</label></td>
						<td><s:textfield theme="simple" name="usuario.nome" size="60"
							maxlength="60" /></td>
						<td><label class="label">CPF:</label></td>
						<td><s:textfield theme="simple" name="usuario.cpf" size="12"
							maxlength="11" /></td>
					</tr>
					<tr>
						<td><label class="label">RG:</label></td>
						<td><s:textfield theme="simple" name="usuario.rg" size="15"
							maxlength="15" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label class="label">Expedidor:</label><s:textfield
							theme="simple" name="usuario.expedidorRg" size="15"
							maxlength="15" />
						<td><label class="label">Sexo:</label></td>
						<td><s:select list="sexos" theme="simple" name="usuario.sexo"
							headerKey="0" headerValue="" listKey="sigla" /></td>
					</tr>
					<tr>
						<td><label class="label">Endereço:</label></td>
						<td><s:textfield theme="simple" name="usuario.endereco"
							size="60" maxlength="60" /></td>
						<td><label class="label">Cidade:</label></td>
						<td><s:textfield theme="simple" name="usuario.cidade"
							size="12" maxlength="11" />
					</tr>
					<tr>
						<td><label class="label">Estado:</label></td>
						<td><select name="convenio.estado">
							<option value="0">Selecione</option>
							<option value="AC">Acre</option>
							<option value="AL">Alagoas</option>
							<option value="AP">Amapá</option>
							<option value="AM">Amazonas</option>
							<option value="BA">Bahia</option>
							<option value="CE">Ceará</option>
							<option value="DF">Distrito Federal</option>
							<option value="ES">Espírito Santo</option>
							<option value="GO">Goiás</option>
							<option value="MA">Maranhão</option>
							<option value="MT">Mato Grosso</option>
							<option value="MS">Mato Grosso do Sul</option>
							<option value="MG">Minas Gerais</option>
							<option value="PA">Pará</option>
							<option value="PB">Paraíba</option>
							<option value="PR">Paraná</option>
							<option value="PE">Pernambuco</option>
							<option value="PI">Piauí</option>
							<option value="RJ">Rio de Janeiro</option>
							<option value="RN">Rio Grande do Norte</option>
							<option value="RS">Rio Grande do Sul</option>
							<option value="RO">Rondônia</option>
							<option value="RR">Roraima</option>
							<option value="SC">Santa Catarina</option>
							<option value="SP">São Paulo</option>
							<option value="SE">Sergipe</option>
							<option value="TO">Tocantins</option>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label class="label">CEP:</label><s:textfield theme="simple"
							name="usuario.cep" size="15" maxlength="8" /></td>

						<td><label class="label">DDD:</label></td>
						<td><s:textfield theme="simple" name="usuario.ddd" size="3"
							maxlength="3" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label
							class="label">Tel:</label><s:textfield theme="simple"
							name="usuario.telefone" size="8" maxlength="8" /></td>
					</tr>
					<tr>
						<td><label class="label">E-mail:</label></td>
						<td><s:textfield theme="simple" name="usuario.email"
							size="30" maxlength="30" /></td>
						<td><label class="label">Senha:</label></td>
						<td><s:password theme="simple" name="usuario.senha"
							maxlength="25" size="25" /></td>

					</tr>
					<tr>
					<td><label class="label">CRM:</label></td>
					<td><s:textfield theme="simple" name="medico.crm" size="7"
						maxlength="7" /></td>
					</tr>
					<table border="0" align="center">
						<tr>
							<td><s:submit value="Salvar" cssClass="button" /></td>
						</tr>
					</table>
				</table>
			</s:elseif></td>
		</tr>
	</table>
</s:form>
</body>
</html>