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
    <td>
    	<br>
		<div>Cadastro<img src="/SisPAM/componentes/img/seta.gif" />		
    	Convênio<img src="/SisPAM/componentes/img/seta.gif" />
		Alterar/Excluir				    		
    	</div>		
    </td>	
	</tr>	
	</table>
	<h2>Consulta de Convênios</h2>
	<s:form action="convenioAction!consultarConvenio.action" theme="simple">	
		<table class="tabela_moldura" >
			<tr>
				<td><label>CNPJ</label></td><td><s:textfield name="convenio.cnpj" id="cnpj" theme="simple" size="17" maxlength="18"/></td>
				<td align="right" width="60px"><label>Nome</label></td><td><s:textfield name="convenio.nome" id="nome" theme="simple" size="30" maxlength="30"/></td>
				<td><s:submit value="Consultar" cssClass="button" theme="simple"/></td>
			</tr>
		</table>
	</s:form>

	<!-- Lista dos últimos convênios cadastrados -->
	<s:if test="conveniosCadastrados != null && conveniosCadastrados.size() > 0">
	<br>
	<table class="tabela_listagem" width="90%" cellspacing="1" cellpadding="2" align="left">
		<tr>
			<th colspan="6" class="principal style2" scope="col">Ùltimos Convênios Cadastrados</th>
		</tr>
		<tr>		
			<th width="40%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Nome</span>
			</th>
			<th width="12%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">CNPJ</span>
			</th>
			<th width="12%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Telefone</span>
			</th>
			<th width="12%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Codigo ANS</span>
			</th>
			<th width="5%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Editar</span>
			</th>
			<th width="5%" bgcolor="#A7C2DA" scope="col">
				<span class="style5">Excluir</span>
			</th>
		</tr>
		<s:iterator value="conveniosCadastrados" status="status">
			<tr class="<s:if test="#status.odd == true"></s:if><s:else>zebra</s:else>">
			
				<!-- Monta a url para carregar a edição do convênio -->
				<s:url id="editarConvenio" action="convenioAction!carregaEdicaoConvenio.action">
					<s:param name="convenio.id" value="%{id}"/>
				</s:url>
					<!-- Monta a url para carregar a exclusão do convênio -->
				<s:url id="excluirConvenio" action="convenioAction!excluirConvenio.action">
					<s:param name="convenio.cnpj" value="%{cnpj}"/>
				</s:url>
				<td>
					<s:property value="nome" />
				</td>
				<td>
					<s:property value="cnpj"/>
				</td>
				<td>
					<s:property value="telefone"/>
				</td>
				<td>
					<s:property value="codigoANS"/>
				</td>
				<td align="center">
					<s:a href="%{#editarConvenio}" cssClass="linkEditar" cssStyle="linkEditar"></s:a>
				</td>
				<td align="center">
					<s:a href="%{#excluirConvenio}" cssClass="linkExcluir" onclick="return confirmaExclusao()" cssStyle="linkExcluir"></s:a>
				</td>
			
			</tr>
		</s:iterator>	
	</table>
	</s:if>		
</body>
</html>