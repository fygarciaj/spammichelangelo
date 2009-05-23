<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../componentes/css/estilo.css" type="text/css" />
<script type="text/javascript" src="../componentes/js/sispam.js"></script>
<link rel="stylesheet" href="componentes/css/estilo.css" type="text/css" />
<script type="text/javascript" src="componentes/js/sispam.js"></script>
<title>Insert title here</title>
</head>
<body>
	<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
    <td>
    	<br>
		<div>Cadastro<img src="../componentes/img/seta.gif" />    		
    	Convênio<img src="../componentes/img/seta.gif" />
		Alterar/Excluir				    		
    	</div>		
    </td>	
	</tr>	
	</table>
	<h2>Pesquisa de Convênios</h2>
	<s:form action="convenioAction!consultarConvenio.action">
		<table class="tabela_moldura">
			<tr>			
        	<td><label class="label">Forma de Consulta</label>
        	<!-- Forma de Consulta -->        	
			<select onchange="formaConsultaConvenio()" id="cmbFrmCns">
				<option value="0">Selecione</option>
    	        <option value="1">Nome do Convênio</option>			
				<option value="2">CNPJ</option>
			</select>
        	</td>
			</tr>
		</table>	
	</s:form>
	
	<s:form id="formConvenio" action="convenioAction!consultarConvenio.action" theme="simple">
		<div id="paiDivs" style="display:none">
		<table class="tabela_moldura">			
			<tr>
			<td>				
				<div id="nomeConvenio" style="display:none">
				<table>
				<td><label class="label" >Nome do Convênio:</label></td><td><s:textfield theme="simple" name="convenio.nome" size="60" maxlength="60"/></td>				
				</table>				
				</div>						
			</td>
			</tr>
			<tr>
			<td>						
				<div id="cnpj" style="display:none">
				<table align="center">				
				<td><label class="label" >CNPJ:</label></td><td><s:textfield theme="simple" name="convenio.cnpj" size="17" maxlength="14"/></td>
				</table>		
				</div>			
			</td>
			</tr>
			<tr>
			<td>
				<div id="botoes" style="display:none">
				<table border="0" align="center">
				<tr>																								
					<td><br><input type="submit" onclick="confirmaAcao()" tabindex="1" name="pesquisarAction" value="Pesquisar" id="submit" class="button"><br></td>																					
				</tr>
				</table>
				</div>				
			</td>
			</tr>	
		</table>
		</div>
	</s:form>
	<!-- Lista dos últimos convênios cadastrados -->
	<s:if test="conveniosCadastrados != null && conveniosCadastrados.size() > 0">
	<br>
	<table class="tabela_moldura" width="90%" cellspacing="1" cellpadding="2" align="center">
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
					<s:a href="%{#excluirConvenio}" cssClass="linkExcluir" cssStyle="linkExcluir"></s:a>
				</td>
			
			</tr>
		</s:iterator>	
	</table>
	</s:if>		
</body>
</html>