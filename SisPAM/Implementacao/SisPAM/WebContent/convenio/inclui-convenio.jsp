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
	<script type="text/javascript" src="componentes/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="../componentes/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="js/jquery/ui.core.js"></script>
	<script type="text/javascript" src="componentes/js/jquery/ui.datepicker.js"></script>
	<script type="text/javascript" src="../componentes/js/jquery/ui.datepicker.js"></script>
	<script type="text/javascript" src="componentes/js/jquery/ui.datepicker-pt-BR.js"></script>
	<script type="text/javascript" src="../componentes/js/jquery/ui.datepicker-pt-BR.js"></script>
	<link rel="stylesheet" href="../componentes/js/jquery/css/ui.all.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="componentes/js/jquery/css/ui.all.css" type="text/css" media="screen" />
	<script src="../componentes/js/jquery/jquery.maskedinput-1.2.2.js" type="text/javascript"></script>
	<script src="componentes/js/jquery/jquery.maskedinput-1.2.2.js" type="text/javascript"></script>

<title>Insert title here</title>
	<script type="text/javascript">		
		$(document).ready(function(){
				$(".cnpj").mask("99.999.999/9999-99");
			});
	</script>
</head>
<body>
	<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
    <td>
    	<br>
		<div>Cadastro<img src="../componentes/img/seta.gif" />    		
    	Convênio<img src="../componentes/img/seta.gif" />
    	<s:if test="convenio.id > 0">
    		Alterar
    	</s:if>
    	<s:else>
			Incluir
		</s:else>				    		
    	</div>
    </td>	
	</table>
	<h2>Cadastro de Convênios</h2>
	<table width="89%" id="AreaMsg" class="AreaMsg">			
	<tr>	
	<td>	
		<div id="MensagensErro" >						
			<s:fielderror theme="simple" cssClass="errorMessage" />
			<s:actionmessage theme="simple" cssClass="sucessMessage" />
		</div>
	</td>
	</tr>
	</table>
	<s:form id="formConvenio" action="convenioAction!incluirConvenio.action" method="post">
		<s:hidden name="convenio.id" value="%{convenio.id}"/>
		<table class="tabela_moldura">
			<tr>
				<td>
						<table border="0" width="100%" class="tabela_moldura" cellpadding="3" cellspacing="4">								
							
							<tr>							    
								<td><label class="label" >Nome do Convênio:</label></td><td><s:textfield theme="simple" name="convenio.nome" size="60" maxlength="60"/></td>
								<td><label class="label" >CNPJ:</label></td><td><s:textfield cssClass="cnpj" theme="simple" name="convenio.cnpj" size="17" maxlength="14"/></td>
							</tr>																				
							<tr>
							    <td><label class="label" >Endereço:</label></td><td colspan="1"><s:textfield theme="simple" name="convenio.endereco" size="60" maxlength="60"/></td>
								<td><label class="label" >Código ANS:</label></td><td><s:textfield theme="simple" name="codigoANSAux" size="17" maxlength="14"/></td>
							</tr>							
							<tr>
								<td><label class="label" >Cidade:</label></td><td><s:textfield theme="simple" name="convenio.cidade" size="30" maxlength="30"/>&nbsp;									
								</td>
							</tr>
							<tr>
								<td><label class="label" >Estado:</label></td>
								<td><s:select name="convenio.estado" theme="simple"  list="#{'':'Selecione','AC':'Acre', 'AL':'Alagoas', 'AP': 'Amapá', 
								'AM':'Amazônas', 'BA': 'Bahia', 'CE':'Ceará', 'DF':'Distrito Federal', 'ES':'Espírito Santo', 'GO':'Goiás', 
								'MA': 'Maranhão', 'MT':'Mato Grosso', 'MS':'Mato Grosso do Sul', 'MG':'Minas Gerais', 'PA':'Pará', 
								'PB':'Paraíba', 'PR':'Paraná', 'PE':'Pernambuco', 'PI':'Piauí', 'RJ':'Rio de Janeiro', 'RN':'Rio Grande do Norte', 
								'RS':'Rio Grande do Sul', 'RO':'Rondônia', 'RR':'Roraima', 'SC':'Santa Catariana', 'SP':'São Paulo', 
								'SE':'Sergipe', 'TO': 'Tocantins'}" />	
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
							<tr>
								<td><label class="label" >CEP:</label></td><td><s:textfield theme="simple" name="cepAux" size="15" maxlength="8"/></td>
							</tr>							
							<tr>
								<td><label class="label" >DDD:</label></td><td><s:textfield theme="simple" name="dddAux" size="2" maxlength="2"/>
								<label class="label" >&nbsp;&nbsp;&nbsp;Telefone:&nbsp;&nbsp;</label><s:textfield theme="simple" name="telefoneAux" size="8" maxlength="8"/></td>								
							</tr>
							<tr>
							    <td><label class="label" >Site:</label></td><td><s:textfield theme="simple" name="convenio.site" size="32" maxlength="30"/></td>        	 					        				       		
								<td><label class="label" >E-mail:</label></td><td colspan="2"><s:textfield theme="simple" name="convenio.email" size="30" maxlength="30"/></td>
							</tr>												
							<table border="0" align="center">
							<tr>																								
								<td align="center"><br>
									<s:if test="convenio.id > 0">
										<s:submit name="Alterar" value="Alterar" cssClass="button" onclick="return confirmaAlteracao()"/><br>
									</s:if>
									<s:else>
										<input type="submit" tabindex="1"  name="confirmarAction" value="Incluir" class="button"><br>
									</s:else>
								</td>																								
							</tr>
							</table>						
						</table>						
				</td>
			</tr>
		</table>						
	</s:form>	
</body>
</html>