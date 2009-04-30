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
        	<td><label class="label">Forma de Consulta&nbsp;</label>
        	<!-- Forma de Consulta -->
        	
			<select name="cmbFrmCns">
				<option value="Selecione">Selecione</option>
    	        <option value="0">Nome do Convênio</option>			
				<option value="1">CNPJ</option>
			</select>
        	</td>
			</tr>			
		</table>
	</s:form>	
</body>
</html>