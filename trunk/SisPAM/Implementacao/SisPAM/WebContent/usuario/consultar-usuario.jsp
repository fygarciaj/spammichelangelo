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
	<title>Insert title here</title>
</head>
<body>
	<table width="89%" id="cmnUsr" class="caminhoUsuario">
		<tr>
			<td>
				<br>
				<div>Cadastro<img src="../componentes/img/seta.gif" /> Usuário
				<img src="../componentes/img/seta.gif" /> Consultar</div>
			</td>
		</tr>
	</table>
	<s:if test="codigoPerfilSelecionado == 1 || codigoPerfilSelecionado == 2">
	<h2>Consulta de Usuários</h2>
	</s:if>
	<s:elseif test="codigoPerfilSelecionado == 3">
	<h2>Consulta de Médicos</h2>
	</s:elseif>
	<s:elseif test="codigoPerfilSelecionado == 4">
	<h2>Consulta de Pacientes</h2>
	</s:elseif>
	
	<table>
		<tr>
			<s:form action="usuarioAction!definirTelaConsulta.action">
				<td><label>Selecione o Perfil&nbsp;</label> <s:select
					list="perfils" headerKey="0" id="perfil" headerValue="--Selecione--"
					name="codigoPerfilSelecionado" onchange="return definirPerfilConsulta()"
					listKey="codigo" theme="simple" /></td>
			</s:form>
		</tr>
	</table>


<s:if test="codigoPerfilSelecionado == 1 || codigoPerfilSelecionado == 2">
	<br>
	<s:form action="usuarioAction!consultarUsuario.action" onsubmit="return verificaCamposPesquisa()" theme="simple">
	<s:hidden name="codigoPerfilSelecionado" value="%{codigoPerfilSelecionado}"/>
		<table class="tabela" >
			<tr>
				<td><label>CPF</label>&nbsp;<s:textfield name="usuario.cpf" id="cpf" theme="simple" size="13" maxlength="11"/></td>
				<td><label>Nome</label>&nbsp;<s:textfield name="usuario.nome" id="nome" theme="simple" size="30" maxlength="30"/></td>
				<td><s:submit value="Consultar" cssClass="botao_pesquisar" theme="simple"/></td>
			</tr>
		</table>
	</s:form>
</s:if>

<s:elseif test="codigoPerfilSelecionado == 3">
	<br>
	<s:form action="usuarioAction!consultarUsuario.action" onsubmit="return verificaCamposPesquisa()" theme="simple">
		<table class="tabela" >
			<tr>
				<td><label>CRM</label>&nbsp;<s:textfield name="usuario.cpf" id="cpf" theme="simple" size="13" maxlength="11"/></td>
				<td><label>Nome</label>&nbsp;<s:textfield name="usuario.nome" id="nome" theme="simple" size="30" maxlength="30"/></td>
				<td><s:submit value="Consultar" cssClass="botao_pesquisar" theme="simple"/></td>
			</tr>
		</table>
	</s:form>
</s:elseif>

<s:elseif test="codigoPerfilSelecionado == 4">
	<br>
	<s:form action="usuarioAction!consultarUsuario.action" onsubmit="return verificaCamposPesquisa()">
		<table class="tabela" >
			<tr>
				<td><label>Prontuário</label>&nbsp;<s:textfield name="usuario.cpf" id="cpf" theme="simple" size="13" maxlength="11"/></td>
				<td><label>Nome</label>&nbsp;<s:textfield name="usuario.nome" id="nome" theme="simple" size="30" maxlength="30"/></td>
				<td><s:submit value="Consultar" cssClass="botao_pesquisar" theme="simple"/></td>
			</tr>
		</table>
	</s:form>
</s:elseif>


<!-- Lista dos últimos usuários cadastrados -->
<s:if test="usuariosCadastrados != null && usuariosCadastrados.size() > 0 ">
	<br>
	<table class="tabela_moldura" width="90%" cellspacing="1"
		cellpadding="2" align="center">
		<tr>
				<th colspan="6" class="principal style2" scope="col">
					<s:if test="codigoPerfilSelecionado == 1">
						Ùltimos	Administradores cadastrados
					</s:if>
					<s:elseif test="codigoPerfilSelecionado == 2">
						Ùltimos	Atendentes cadastrados
					</s:elseif>
					<s:elseif test="codigoPerfilSelecionado == 3">
						Ùltimos	Médicos cadastrados
					</s:elseif>
					<s:elseif test="codigoPerfilSelecionado == 4">
						Ùltimos	Pacientes cadastrados
					</s:elseif>
				</th>
		</tr>
		<tr>
			<th width="40%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Nome</span></th>
			<th width="12%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">CPF</span></th>
			<th width="12%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Telefone</span></th>
			<th width="12%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">RG</span></th>
			<th width="5%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Editar</span></th>
			<th width="5%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Excluir</span></th>
		</tr>
		<s:iterator value="usuariosCadastrados" status="status">
			<s:if test="codigoPerfilSelecionado == 1 || codigoPerfilSelecionado == 2">
				<s:url id="excluirUsuario" action="usuarioAction!excluirUsuario.action">
					<s:param name="usuario.id" value="id"/>
					<s:param name="codigoPerfilSelecionado" value="codigoPerfilSelecionado"/>
				</s:url>
				<s:url id="editarUsuario" action="usuarioAction!carregarEdicao.action">
					<s:param name="usuario.id" value="id"/>
					<s:param name="codigoPerfilSelecionado" value="codigoPerfilSelecionado"/>
				</s:url>
			</s:if>
			<tr	class="<s:if test="#status.odd == true"></s:if><s:else>zebra</s:else>">
				<td><s:property value="nome" /></td>
				<td align="center"><s:property value="cpf" /></td>
				<td align="center"><s:property value="telefone" /></td>
				<td align="center"><s:property value="rg" /></td>
				<td align="center"><s:a href="%{#editarUsuario}" cssClass="linkEditar" ></s:a></td>
				<td align="center"><s:a href="%{#excluirUsuario}" onclick="return confirmaExclusao()">
					<img src="img/excluir.png" alt="Excluir" />
				</s:a></td>

			</tr>
		</s:iterator>
	</table>
</s:if>

</body>
</html>