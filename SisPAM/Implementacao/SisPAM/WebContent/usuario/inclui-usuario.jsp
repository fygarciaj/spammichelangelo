<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/estilo.css" type="text/css" />
<script type="text/javascript" src="js/sispam.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h3>Cadastro de Usuário</h3>
	<s:form id="formPerfil" action="usuarioAction!salvarUsuario.action">
		<table border="0" width="100%" class="tabela_moldura" cellpadding="3"	cellspacing="4">
			<tr>
				<td><label>Selecione o Perfil&nbsp;</label>
					<s:select list="perfils" headerKey="0" id="perfil" headerValue="--Selecione--" name="codigoPerfilSelecionado"
					onchange = "definirPerfil()" listKey="codigo" theme="simple"/></td>
			</tr>
			<tr>
				<td>
					<div id="usuario" style="display:none">
						<table border="0" width="80%" class="tabela_moldura" cellpadding="3"	cellspacing="4">
							<tr>
								<td>Nome:&nbsp;<s:textfield theme="simple" name="usuario.nome" size="60" maxlength="60"/></td>
								<td>Sexo:&nbsp;<s:select list="sexos" theme="simple" headerKey="0" headerValue="" listKey="sigla"/></td>
								<td align="left">CPF:&nbsp;<s:textfield theme="simple" name="usuario.cpf" size="12" maxlength="11"/></td>
							</tr>
							<tr>
								<td>RG:&nbsp;<s:textfield theme="simple" name="usuario.rg" size="15" maxlength="15"/>&nbsp;
									Expedidor:&nbsp;<s:textfield theme="simple" name="usuario.expedidorRg" size="15" maxlength="15"/>
								</td>
								<td colspan="2">E-mail:&nbsp;<s:textfield theme="simple" name="usuario.email" size="30" maxlength="30"/></td>
							</tr>
							<tr>
								<td>Endereço:&nbsp;<s:textfield theme="simple" name="usuario.endereco" size="60" maxlength="60"/></td>
								<td align="left" colspan="2">Cidade:&nbsp;<s:textfield theme="simple" name="usuario.cidade" size="12" maxlength="11"/>&nbsp;
									UF:&nbsp;<select name="estado">
											<option>Distrito Federal</option>
											<option>Goiás</option>
											<option>São Paulo</option>
									   </select>
								</td>
							</tr>
							<tr>
								<td colspan="1" width="40%">Cep:&nbsp;<s:textfield theme="simple" name="usuario.cep" size="15" maxlength="8"/>&nbsp;
								DDD:&nbsp;<s:textfield theme="simple" name="usuario.ddd" size="3" maxlength="3"/>&nbsp;
								Telefone:&nbsp;<s:textfield theme="simple" name="usuario.telefone" size="8" maxlength="8"/>
								</td>
								<td colspan="2">
									Senha:&nbsp;<s:password theme="simple" name="usuario.senha" maxlength="25" size="25"/>
								</td>
							</tr>
							<tr>
								<td><s:date var="afd"  name="dataEntrada"/></td>
							</tr>
						</table>
					</div>
					<div id="paciente" style="display:none">
						<table border="0" width="100%" class="tabela_moldura" cellpadding="3"	cellspacing="4">
							<tr>
							paciente
							</tr>
						</table>
					</div>
					<div id="medico" style="display:none">
						<table border="0" width="100%" class="tabela_moldura" cellpadding="3"	cellspacing="4">
							<tr>
								medico
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		
	</s:form>
</body>
</html>