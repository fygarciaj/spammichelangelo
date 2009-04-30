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
    	Conv�nio<img src="img/seta.gif" />
		Incluir				    		
    	</div>
    </td>	
	</table>
	<h2>Cadastro de Conv�nios</h2>
	<s:form id="formConvenio" action="convenioAction!incluirConvenio.action">
		<table border="0" width="100%" class="tabela_moldura" cellpadding="3"	cellspacing="4">
			<tr>
				<td>
						<table border="0" width="100%" class="tabela_moldura" cellpadding="3" cellspacing="4">								
							
							<tr>							    
								<td><label class="label" >Nome do Conv�nio:</label></td><td><s:textfield theme="simple" name="convenio.nome" size="60" maxlength="60"/></td>
								<td><label class="label" >CNPJ:</label></td><td><s:textfield theme="simple" name="convenio.cpf" size="17" maxlength="14"/></td>
							</tr>																				
							<tr>
							    <td><label class="label" >Endere�o:</label></td><td colspan="3"><s:textfield theme="simple" name="convenio.endereco" size="60" maxlength="60"/></td>
							</tr>							
							<tr>
								<td><label class="label" >Cidade:</label></td><td><s:textfield theme="simple" name="convenio.cidade" size="20" maxlength="20"/>&nbsp;									
								</td>
							</tr>
							<tr>
								<td><label class="label" >Estado:</label></td><td><select name="estado">
											<option value="0">--Selecione--</option>
											<option value="AC">Acre</option>
											<option value="AL">Alagoas</option>
											<option value="AP">Amap�</option>
											<option value="AM">Amazonas</option>
											<option value="BA">Bahia</option>
											<option value="CE">Cear�</option>											
											<option value="ES">Esp�rito Santo</option>
											<option value="GO">Goi�s</option>
											<option value="MA">Maranh�o</option>
											<option value="MT">Mato Grosso</option>
											<option value="MS">Mato Grosso do Sul</option>
											<option value="MG">Minas Gerais</option>
											<option value="PA">Par�</option>
											<option value="PB">Para�ba</option>
											<option value="PR">Paran�</option>
											<option value="PE">Pernambuco</option>
											<option value="PI">Piau�</option>
											<option value="RJ">Rio de Janeiro</option>
											<option value="RN">Rio Grande do Norte</option>
											<option value="RS">Rio Grande do Sul</option>
											<option value="RO">Rond�nia</option>
											<option value="RR">Roraima</option>
											<option value="SC">Santa Catarina</option>
											<option value="SP">S�o Paulo</option>
											<option value="SE">Sergipe</option>
											<option value="TO">Tocantins</option>
									</select>
								</td>
							</tr>
							<tr>
								<td><label class="label" >CEP:</label></td><td><s:textfield theme="simple" name="convenio.cep" size="15" maxlength="8"/></td>
							</tr>							
							<tr>
								<td><label class="label" >DDD:</label></td><td><s:textfield theme="simple" name="convenio.ddd" size="2" maxlength="2"/>
								<label class="label" >&nbsp;&nbsp;&nbsp;Telefone:&nbsp;&nbsp;</label><s:textfield theme="simple" name="convenio.telefone" size="8" maxlength="8"/></td>								
							</tr>
							<tr>
							    <td><label class="label" >Site:</label></td><td><s:textfield theme="simple" name="convenio.site" size="32" maxlength="30"/></td>        	 					        				       		
								<td><label class="label" >E-mail:</label></td><td colspan="2"><s:textfield theme="simple" name="convenio.email" size="30" maxlength="30"/></td>
							</tr>
							<tr>
								<td><s:date var="afd"  name="dataEntrada"/></td>								
							</tr>													
						</table>
						<table border="0" align="center" cellpadding="20" cellspacing="20">
							<tr>
																								
								<td><br><input type="submit" tabindex="1" name="confirmarAction" value="Incluir" class="button"><br></td>								
								<td><br><input type="reset" tabindex="2" name="limparAction" value="Limpar" class="button"><br></td>
								
							</tr>
						</table>					
				</td>
			</tr>
		</table>						
	</s:form>
	
</body>
</html>