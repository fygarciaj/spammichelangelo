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
	<h3>Consulta de Usuário</h3>
	<s:form action="usuarioAction!consultarUsuario.action" onsubmit="return verificaCamposPesquisa()">
		<table class="tabela" >
			<tr>
				<td><label>CPF</label>&nbsp;<s:textfield name="usuario.cpf" id="cpf" theme="simple" size="13" maxlength="11"/></td>
				<td><label>Nome</label>&nbsp;<s:textfield name="usuario.nome" id="nome" theme="simple" size="30" maxlength="30"/></td>
				<td><label>Perfil</label>&nbsp;<s:select list="perfils" headerKey="0" id="perfil" headerValue="--Selecione--" name="codigoPerfilSelecionado"
						 listKey="codigo" theme="simple"/></td>
				<td><s:submit value="Consultar" cssClass="botao_pesquisar" theme="simple"/></td>
			</tr>
		</table>
	</s:form>
	
	<!-- Lista dos últimos usuários cadastrados -->
	<s:if test="usuariosCadastrados != null && usuariosCadastrados.size() > 0">
	<br>
	<table class="tabela_moldura" width="90%" cellspacing="1" cellpadding="2" align="center">
		<tr>
			<th colspan="6" class="principal style2" scope="col">Ùltimos usuários cadastrados</th>
		</tr>
		<tr>		
			<th width="40%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Nome</span>
			</th>
			<th width="12%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">CPF</span>
			</th>
			<th width="12%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Telefone</span>
			</th>
			<th width="12%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Perfil</span>
			</th>
			<th width="5%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Editar</span>
			</th>
			<th width="5%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Excluir</span>
			</th>
		</tr>
		<s:iterator value="usuariosCadastrados" status="status">
			<tr class="<s:if test="#status.odd == true"></s:if><s:else>zebra</s:else>">
			
				<!-- Monta a url para carregar a edição do usuário -->
				<s:url id="editarUsuario" action="usuarioAction!carregarEdicao.action">
					<s:param name="usuario.id" value="%{id}"/>
				</s:url>
					<!-- Monta a url para carregar a exclusão do usuário -->
				<s:url id="excluirUsuario" action="usuarioAction!carregarEdicao.action">
					<s:param name="usuario.id" value="%{id}"/>
				</s:url>
				<td>
					<s:property value="nome" />
				</td>
				<td>
					<s:property value="cpf"/>
				</td>
				<td>
					<s:property value="telefone"/>
				</td>
				<td>
					<s:property value="perfil"/>
				</td>
				<td align="center">
					<s:a href="%{#editarUsuario}">
						<img src="img/editar.png" alt="Alterar" />
					</s:a>
				</td>
				<td align="center">
					<s:a href="%{#excluirUsuario}">
						<img src="img/excluir.png" alt="Alterar" />
					</s:a>
				</td>
			
			</tr>
		</s:iterator>	
	</table>
	</s:if>
		

</body>
</html>