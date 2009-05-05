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
    <td>
    	<br>
		<div>Cadastro<img src="img/seta.gif" />    		
    	Convênio<img src="img/seta.gif" />
		Alterar/Excluir				    		
    	</div>		
    </td>	
	</tr>	
	</table>
	<h2>Pesquisa de Convênios</h2>
	<s:form id="formConvenio" action="convenioAction!consultarConvenio.action">
		<table border="0" width="100%" class="tabela_moldura" cellpadding="3"	cellspacing="4">
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
					<td><br><input type="submit" tabindex="1" name="confirmarAction" value="Pesquisar" class="button"><br></td>																					
				</tr>
				</table>
				</div>				
			</td>
			</tr>	
		</table>
	</s:form>
	<s:if test="isExisteConvenio == 2">
		<!-- faksdfjslkfjkslfjsdjfkds -->
	</s:if>	
	<s:elseif test="isExisteConvenio == 1">
		Nenhum registro encontrado com esses dados!
	</s:elseif>
</body>
</html>