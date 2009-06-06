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

<SCRIPT language="javaScript">
	function marcaEspecialidades(){
		var elemento = document.getElementById("idDouble");
		for(var i=0; i < elemento.size; i++){
			elemento.options[i].selected = true;
			}
		return false;
		}
	
</script>
<script type="text/javascript">
		 $(document).ready(function(){
			calendario('data');
			calendario('data1');
	 });
	 function calendario(idCampo){
				var id = '#'+idCampo;
				 $(id).datepicker({
							 showMonthAfterYear: false,
							 showOtherMonths: true,
							 changeMonth: true,
							 changeYear: true,
							 gotoCurrent: true
				 });
			}

		$(document).ready(function(){
				$(".cpf").mask("999.999.999-99");
			});

		$(document).ready(function(){
			$(".cep").mask("99.999.999");
		});
		$(document).ready(function(){
			$(".horario").mask("99:99");
		});

		$(document).ready(function(){
			$(".telefone").mask("9999-9999");
		});
	</script>


</head>
<body>
<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
		<td><br>
		Cadastro <span class="imgSeta"></span> Usuário <span class="imgSeta"></span> 
			<s:if test="paciente.id > 0">
				Alterar
			</s:if>
			<s:elseif test="usuario.id > 0">
				Alterar
			</s:elseif>
			<s:elseif test="medico.id > 0">
				Alterar
			</s:elseif>
			<s:else>
			Incluir
			</s:else>
		</td>
	</tr>
</table>
<h2>Cadastro de Usuários</h2>
<s:if test="pacienteLogado == false">
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
</s:if>


	<s:fielderror theme="simple" cssClass="errorMessage"  cssErrorStyle="errorMessage" cssErrorClass="errorMessage"/>
	<s:actionmessage theme="simple" cssClass="sucessMessage" />


<s:if test="codigoPerfilSelecionado == 1 || codigoPerfilSelecionado == 2">
	<s:form id="formPerfil" action="usuarioAction!salvarUsuario.action" method="post">
	<s:hidden name="codigoPerfilSelecionado" value="%{codigoPerfilSelecionado}" />
	<s:hidden name="usuario.id" value="%{usuario.id}"/>
		<table border="0" class="tabela_moldura" cellpadding="3" cellspacing="4">
			<tr>
				<td><label class="label">Nome:</label></td>
				<td><s:textfield theme="simple" name="usuario.nome" size="60" maxlength="60" /></td>
				<td><label class="label">CPF:</label></td>
				<td><s:textfield cssClass="cpf" theme="simple" name="usuario.cpf" size="12" maxlength="11" /></td>
			</tr>
			<tr>
				<td><label class="label">RG:</label></td>
				<td><s:textfield theme="simple" name="rgAux" size="15"	maxlength="15" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="label">Expedidor:&nbsp;&nbsp;</label>
				<s:textfield theme="simple" name="usuario.expedidorRg" size="15" maxlength="15" /></td>
				<td><label class="label">Sexo:</label></td>
				<td><s:select list="sexos" theme="simple" name="usuario.sexo" headerKey="0" headerValue="" listKey="sigla" /></td>
			</tr>			
			<tr>
				<td><label class="label">Endereço:</label></td>
				<td><s:textfield theme="simple" name="usuario.endereco"	size="60" maxlength="60" /></td>
				<td><label class="label">Cidade:</label></td>
				<td><s:textfield theme="simple" name="usuario.cidade" size="25" maxlength="25" /></td>
			</tr>
			<tr>
				<td><label class="label">Estado:</label></td>
				<td>
				<s:select name="usuario.uf" theme="simple"  list="#{'':'Selecione','AC':'Acre', 'AL':'Alagoas', 'AP': 'Amapá', 
					'AM':'Amazônas', 'BA': 'Bahia', 'CE':'Ceará', 'DF':'Distrito Federal', 'ES':'Espírito Santo', 'GO':'Goiás', 
					'MA': 'Maranhão', 'MT':'Mato Grosso', 'MS':'Mato Grosso do Sul', 'MG':'Minas Gerais', 'PA':'Pará', 
					'PB':'Paraíba', 'PR':'Paraná', 'PE':'Pernambuco', 'PI':'Piauí', 'RJ':'Rio de Janeiro', 'RN':'Rio Grande do Norte', 
					'RS':'Rio Grande do Sul', 'RO':'Rondônia', 'RR':'Roraima', 'SC':'Santa Catariana', 'SP':'São Paulo', 
					'SE':'Sergipe', 'TO': 'Tocantins'}" />	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label class="label">CEP:&nbsp;&nbsp;</label>
					<s:textfield theme="simple" cssClass="cep" name="cepAux" size="15" maxlength="8" />
				</td>
				<td><label class="label">DDD:</label></td>
				<td>
					<s:textfield theme="simple" name="dddAux" size="2"	maxlength="2" />&nbsp;&nbsp; 
					<label class="label">Tel:&nbsp;&nbsp;</label>
					<s:textfield theme="simple" cssClass="telefone" name="telefoneAux" size="8" maxlength="8" />
				</td>
			</tr>
			<tr>
				<td>
				<label class="label">Data de Nascimento:</label></td>
				<td><s:textfield id="data" name="dataNascimentoAux" theme="simple" size="12"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="label">E-mail:</label>&nbsp;
				<s:textfield theme="simple" name="usuario.email" size="30" maxlength="30" /></td>						
			</tr>
			<tr>
				<td><label class="label">Login:</label></td>
				<td colspan="2"><s:textfield theme="simple" name="usuario.acesso" maxlength="25" size="25" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="label">Senha:&nbsp;&nbsp;</label><s:password theme="simple" name="usuario.senha"	maxlength="6" size="6" /></td>
			</tr>
			<tr>
				<s:if test="usuario.id > 0">
					<td><s:submit value="Alterar" cssClass="button" onclick="return confirmaAlteracao()" /></td>
				</s:if>
				<s:else>
					<td><s:submit value="Incluir" cssClass="button" /></td>
				</s:else>
			</tr>
	</table>
</s:form>
</s:if>	
<s:elseif test="codigoPerfilSelecionado == 3">
	<s:form action="medicoAction!salvarMedico.action" name="formMedico" method="post" >
	<s:hidden name="codigoPerfilSelecionado" value="%{codigoPerfilSelecionado}" />
	<s:hidden name="medico.id" value="%{medico.id}"/>
	<s:hidden name="medico.usuario.id" value="%{medico.usuario.id}"/>
		<table border="0" class="tabela_moldura" cellpadding="3" cellspacing="4">
			<tr>
				<td><label class="label">Nome:</label></td>
				<td><s:textfield theme="simple" name="medico.usuario.nome" size="60" maxlength="60" /></td>
				<td><label class="label">CPF:</label></td>
				<td><s:textfield cssClass="cpf" theme="simple" name="medico.usuario.cpf" size="12" maxlength="11" /></td>
			</tr>
			<tr>
				<td><label class="label">RG:</label></td>
				<td><s:textfield theme="simple" name="rgAux" size="15"	maxlength="15" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="label">Expedidor:&nbsp;&nbsp;</label>
				<s:textfield theme="simple" name="medico.usuario.expedidorRg" size="15" maxlength="15" /></td>
				<td><label class="label">Sexo:</label></td>
				<td><s:select list="sexos" theme="simple" name="medico.usuario.sexo" headerKey="0" headerValue="" listKey="sigla" /></td>
			</tr>
			<tr>
				<td><label class="label">Endereço:</label></td>
				<td><s:textfield theme="simple" name="medico.usuario.endereco"	size="60" maxlength="60" /></td>
				<td><label class="label">Cidade:</label></td>
				<td><s:textfield theme="simple" name="medico.usuario.cidade" size="25" maxlength="25" /></td>
			</tr>
			<tr>
				<td><label class="label">Estado:</label></td>
				<td>
				<s:select name="medico.usuario.uf" theme="simple"  list="#{'':'Selecione','AC':'Acre', 'AL':'Alagoas', 'AP': 'Amapá', 
					'AM':'Amazônas', 'BA': 'Bahia', 'CE':'Ceará', 'DF':'Distrito Federal', 'ES':'Espírito Santo', 'GO':'Goiás', 
					'MA': 'Maranhão', 'MT':'Mato Grosso', 'MS':'Mato Grosso do Sul', 'MG':'Minas Gerais', 'PA':'Pará', 
					'PB':'Paraíba', 'PR':'Paraná', 'PE':'Pernambuco', 'PI':'Piauí', 'RJ':'Rio de Janeiro', 'RN':'Rio Grande do Norte', 
					'RS':'Rio Grande do Sul', 'RO':'Rondônia', 'RR':'Roraima', 'SC':'Santa Catariana', 'SP':'São Paulo', 
					'SE':'Sergipe', 'TO': 'Tocantins'}" />	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label class="label">CEP:&nbsp;&nbsp;</label>
					<s:textfield cssClass="cep" theme="simple" name="cepAux" size="15" maxlength="8" />
				</td>
				<td><label class="label">DDD:</label></td>
				<td>
					<s:textfield theme="simple" name="dddAux" size="2"	maxlength="2" />&nbsp;&nbsp; 
					<label class="label">Tel:&nbsp;&nbsp;</label>
					<s:textfield cssClass="telefone" theme="simple" name="telefoneAux" size="8" maxlength="8" />
				</td>
			</tr>
<tr>
				<td>
				<label class="label">Data de Nasc.:</label></td><td>
				<s:textfield id="data" name="dataNascimentoAux" theme="simple" size="10"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="label">CRM:</label>&nbsp;&nbsp;&nbsp;
				<s:textfield theme="simple" name="crmAux" size="6" maxlength="6" />&nbsp;&nbsp; 
				<label class="label">UF:</label>&nbsp;&nbsp;&nbsp;
					<s:select name="medico.crmUf" theme="simple"  list="#{'':'Selecione','AC':'AC', 'AL':'AL', 'AP': 'AP', 
					'AM':'AM', 'BA': 'BA', 'CE':'CE', 'DF':'DF', 'ES':'ES', 'GO':'GO', 
					'MA': 'MA', 'MT':'MT', 'MS':'MS', 'MG':'MG', 'PA':'PA', 
					'PB':'PB', 'PR':'PR', 'PE':'PE', 'PI':'PI', 'RJ':'RJ', 'RN':'RN', 
					'RS':'RS', 'RO':'RO', 'RR':'RR', 'SC':'SC', 'SP':'SP', 
					'SE':'SE', 'TO': 'TO'}" />	
			 </td>
				<td><label class="label">E-mail:</label>&nbsp;</td><td>
				<s:textfield theme="simple" name="medico.usuario.email" size="30" maxlength="30" /></td>						
			</tr>
			<tr>
				<td><label class="label">Consultório:</label></td><td><s:textfield theme="simple" name="consultorioAux" maxlength="4" size="7" />&nbsp;&nbsp;&nbsp;
				<label class="label">Hora início:&nbsp;&nbsp;</label><s:textfield theme="simple" cssClass="horario" name="horaIni" maxlength="4" size="4" />&nbsp;&nbsp;&nbsp;
				<label class="label">Hora Fim:</label>&nbsp;&nbsp;<s:textfield theme="simple" cssClass="horario" name="horaFim" maxlength="4" size="4" /></td>				
			</tr>
			<tr>
				<td><label class="label">Login:</label></td>
				<td><s:textfield theme="simple" name="medico.usuario.acesso" maxlength="25" size="25" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="label">Senha:</label>&nbsp;&nbsp;&nbsp;<s:password theme="simple" name="medico.usuario.senha"	maxlength="6" size="6" /></td>
			</tr>
			<tr>				
			</tr>			
			<tr>
			<td colspan="3">
				 	<s:optiontransferselect cssStyle="height:100px" 
				 	theme="simple" 	leftTitle="Especialidades" allowUpDownOnLeft="false" 
				 	allowUpDownOnRight="false" allowAddAllToLeft="false"  addToLeftLabel="<<" 	
				 	rightTitle="Especialidades do Medico" doubleList="medico.especialidades" 
				 	 addToRightLabel=">>" allowAddAllToRight="false" allowSelectAll="false" 
				 	 listKey="id" listValue="descricao" 
				 	 list="especialidades"  doubleName="especialidadesSelecionadas"  
				  	 doubleCssStyle="height:100px" doubleId="idDouble" doubleListKey="id" doubleListValue="descricao"></s:optiontransferselect>			
			</td>							
				<td ><label class="label">Dias de Atendimento</label>&nbsp;
				<s:iterator value="dias">
					<br>
					<s:if test="diasString.contains(descricao)">					
						<s:checkbox value="true" name="dia-%{codigo}" theme="simple"/><s:property value="descricao" />
					</s:if>
					<s:else>
						<s:checkbox value="false" name="dia-%{codigo}" theme="simple"/><s:property value="descricao" />
					</s:else>					
				</s:iterator>
				</td>
				<s:if test="medico.id > 0">						
					<td><s:submit value="Alterar" onclick="return marcaEspecialidades(); return confirmaAlteracao();" cssClass="button" /></td>
				</s:if>
				<s:else>
					<td><s:submit value="Incluir" onclick="return marcaEspecialidades();" cssClass="button" /></td>
				</s:else>
			</tr>
	</table>
	</s:form>		
</s:elseif>

<s:elseif test="codigoPerfilSelecionado == 4">
	<s:form id="formPerfil" action="pacienteAction!salvarPaciente.action" method="post">
	<s:hidden name="codigoPerfilSelecionado" value="%{codigoPerfilSelecionado}" />
	<s:hidden name="paciente.id" value="%{paciente.id}"/>
	<s:hidden name="paciente.usuario.id" value="%{paciente.usuario.id}"/>
		<table border="0" class="tabela_moldura" cellpadding="3" cellspacing="4">
			<tr>
				<td><label class="label">Nome:</label></td>
				<td><s:textfield theme="simple" name="paciente.usuario.nome" size="60" maxlength="60" /></td>
				<td><label class="label">CPF:</label></td>
				<td><s:textfield cssClass="cpf" theme="simple" name="paciente.usuario.cpf" size="12" maxlength="11" /></td>
			</tr>
			<tr>
				<td><label class="label">RG:</label></td>
				<td><s:textfield theme="simple" name="rgAux" size="15"	maxlength="15" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="label">Expedidor:&nbsp;&nbsp;</label>
				<s:textfield theme="simple" name="paciente.usuario.expedidorRg" size="15" maxlength="15" /></td>
				<td><label class="label">Sexo:</label></td>
				<td><s:select list="sexos" theme="simple" name="paciente.usuario.sexo" headerKey="0" headerValue="" listKey="sigla" /></td>
			</tr>
			<tr>
				<td><label class="label">Endereço:</label></td>
				<td><s:textfield theme="simple" name="paciente.usuario.endereco"	size="60" maxlength="60" /></td>
				<td><label class="label">Cidade:</label></td>
				<td><s:textfield theme="simple" name="paciente.usuario.cidade" size="25" maxlength="25" /></td>
			</tr>
			<tr>
				<td><label class="label">Estado:</label></td>
				<td>
				<s:select name="paciente.usuario.uf" theme="simple"  list="#{'':'Selecione','AC':'Acre', 'AL':'Alagoas', 'AP': 'Amapá', 
					'AM':'Amazônas', 'BA': 'Bahia', 'CE':'Ceará', 'DF':'Distrito Federal', 'ES':'Espírito Santo', 'GO':'Goiás', 
					'MA': 'Maranhão', 'MT':'Mato Grosso', 'MS':'Mato Grosso do Sul', 'MG':'Minas Gerais', 'PA':'Pará', 
					'PB':'Paraíba', 'PR':'Paraná', 'PE':'Pernambuco', 'PI':'Piauí', 'RJ':'Rio de Janeiro', 'RN':'Rio Grande do Norte', 
					'RS':'Rio Grande do Sul', 'RO':'Rondônia', 'RR':'Roraima', 'SC':'Santa Catariana', 'SP':'São Paulo', 
					'SE':'Sergipe', 'TO': 'Tocantins'}" />	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label class="label">CEP:&nbsp;&nbsp;</label>
					<s:textfield cssClass="cep" theme="simple" name="cepAux" size="15" maxlength="8" />
				</td>
				<td><label class="label">DDD:</label></td>
				<td>
					<s:textfield theme="simple" name="dddAux" size="2"	maxlength="2" />&nbsp;&nbsp; 
					<label class="label">Tel:&nbsp;&nbsp;</label>
					<s:textfield cssClass="telefone" theme="simple" name="telefoneAux" size="8" maxlength="8" />
				</td>
				
			</tr>
			<tr>
				<td>
				<label class="label">Data de Nasc.:</label></td>
				<td><s:textfield id="data" name="dataNascimentoAux" theme="simple" size="10"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="label">E-mail:</label>&nbsp;
				<s:textfield theme="simple" name="paciente.usuario.email" size="30" maxlength="30" /></td>
				<td><label class="label">Convênio:</label></td>
				<td >
					<s:select theme="simple" name="paciente.convenio.id" list="convenios" listKey="id" listValue="nome" headerKey="0" headerValue="--Selecione--"/>
				</td>						
			</tr>
			<tr>
			
			</tr>
			<tr>
			<td>
			<label class="label">Plano:&nbsp;&nbsp;</label></td><td><s:textfield name="paciente.plano" size="25" maxlength="25" theme="simple"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					
			<label class="label">Validade:&nbsp;&nbsp;</label><s:textfield name="validaPlanoAux" size="10" id="data1" maxlength="30" theme="simple"/>
			</td>			
			<td>
			<label class="label">Acomodação:&nbsp;&nbsp;</label></td><td><s:textfield name="paciente.descricaoAcomodacao" size="20" maxlength="20" theme="simple"/>&nbsp;&nbsp;
			</td>
			</tr>			
			<tr>
				<td><label class="label">Login:</label></td>
				<td ><s:textfield theme="simple" name="paciente.usuario.acesso" maxlength="25" size="25" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="label">Senha:&nbsp;&nbsp;</label><s:password theme="simple" name="paciente.usuario.senha"	maxlength="6" size="6" /></td>
				
				
			</tr>

			<tr>
				<s:if test="paciente.id > 0">
					<td><s:submit value="Alterar" cssClass="button" onclick="return confirmaAlteracao()" /></td>
				</s:if>
				<s:else>
					<td><s:submit value="Incluir" cssClass="button" /></td>
				</s:else>
			</tr>
	</table>
</s:form>
</s:elseif>	

</body>
</html>