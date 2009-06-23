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
	<SCRIPT type="text/javascript">

		function mostrarUF(){
			var variavel = document.getElementById("crm");
			if(variavel.value.length > 1){
				document.getElementById("ufCrm").style.display = 'block';
			}else{
				document.getElementById("ufCrm").style.display = 'none';
			}
		}
		$(document).ready(function(){
			$(".cpf").mask("999.999.999-99");
		});
	</SCRIPT>
</head>
<body>
	<table width="89%" id="cmnUsr" class="caminhoUsuario">
		<tr>
			<td>
				<br>
				<div>Cadastro<img src="/SisPAM/componentes/img/seta.gif" /> Usuário
				<img src="/SisPAM/componentes/img/seta.gif" />Alterar/Excluir</div>
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
	<s:else>
	<h2>Consulta</h2>
	</s:else>
	
	<table>
		<tr>
			<s:form action="usuarioAction!definirTelaConsulta.action">
			<table class="tabela_moldura">
				<tr>
				<td><label class="label">Selecione o Perfil&nbsp;</label> <s:select
					list="perfils" headerKey="0" id="perfil" headerValue="Selecione"
					name="codigoPerfilSelecionado" onchange="return definirPerfilConsulta()"
					listKey="codigo" theme="simple" /></td>
				</tr>
			</table>
			</s:form>
		</tr>
	</table>
<s:if test="codigoPerfilSelecionado == 1 || codigoPerfilSelecionado == 2">
	<br>
	<s:form action="usuarioAction!consultarUsuario.action" onsubmit="return verificaCamposPesquisa()" theme="simple">
	<s:hidden name="codigoPerfilSelecionado" value="%{codigoPerfilSelecionado}"/>
		<table class="tabela_moldura" >
			<tr>
				<td><label>CPF</label></td><td><s:textfield cssClass="cpf" name="usuario.cpf" id="cpf" theme="simple" size="13" maxlength="11"/></td>
				<td align="right" width="60px"><label>Nome</label></td><td><s:textfield name="usuario.nome" id="nome" theme="simple" size="60" maxlength="60"/></td>
				<td><s:submit value="Consultar" cssClass="button" theme="simple"/></td>
			</tr>
		</table>
	</s:form>
</s:if>

<s:elseif test="codigoPerfilSelecionado == 3">
	<br>
	<s:form action="medicoAction!consultarMedico.action" onsubmit="return verificaCamposPesquisa()" theme="simple">
		<s:hidden name="codigoPerfilSelecionado" value="%{codigoPerfilSelecionado}"/>
		<table class="tabela_moldura" >
			<tr>
				<td width="50%"><label>CRM</label>&nbsp;<s:textfield name="crmAux" id="crm" theme="simple" size="10" maxlength="11" onkeyup="return mostrarUF()"/>
				<div id="ufCrm" style="display:none">
				&nbsp;<label>UF</label>&nbsp;
				<s:select name="crmUf" theme="simple"  list="#{'':'Selecione','AC':'Acre', 'AL':'Alagoas', 'AP': 'Amapá', 
					'AM':'Amazônas', 'BA': 'Bahia', 'CE':'Ceará', 'DF':'Distrito Federal', 'ES':'Espírito Santo', 'GO':'Goiás', 
					'MA': 'Maranhão', 'MT':'Mato Grosso', 'MS':'Mato Grosso do Sul', 'MG':'Minas Gerais', 'PA':'Pará', 
					'PB':'Paraíba', 'PR':'Paraná', 'PE':'Pernambuco', 'PI':'Piauí', 'RJ':'Rio de Janeiro', 'RN':'Rio Grande do Norte', 
					'RS':'Rio Grande do Sul', 'RO':'Rondônia', 'RR':'Roraima', 'SC':'Santa Catariana', 'SP':'São Paulo', 
					'SE':'Sergipe', 'TO': 'Tocantins'}" />	
				</div>
				</td>
				<td align="right" width="30%"><label>Nome</label></td><td><s:textfield name="medico.usuario.nome" id="nome" theme="simple" size="40" maxlength="60"/></td>
				<td><s:submit value="Consultar" cssClass="button"  theme="simple"/></td>
			</tr>
		</table>
	</s:form>
</s:elseif>

<s:elseif test="codigoPerfilSelecionado == 4">
	<br>
	<s:form action="pacienteAction!consultarPaciente.action" onsubmit="return verificaCamposPesquisa()">
		<s:hidden name="codigoPerfilSelecionado" value="%{codigoPerfilSelecionado}"/>
		<table class="tabela_moldura" >
			<tr>
				<td><label>CPF:</label>&nbsp;<s:textfield cssClass="cpf" name="paciente.usuario.cpf" id="cpf" theme="simple" size="13" maxlength="11"/></td>
				<td ><label>Nome</label>&nbsp;<s:textfield name="paciente.usuario.nome" id="nome" theme="simple" size="30" maxlength="30"/></td>
				<td><s:submit value="Consultar" cssClass="button"  theme="simple"/></td>
			</tr>
		</table>
	</s:form>
</s:elseif>
<br>
<s:fielderror theme="simple" cssClass="errorMessage"  cssErrorStyle="errorMessage" cssErrorClass="errorMessage"/>
<s:actionmessage theme="simple" cssClass="sucessMessage" />
<s:if test="(codigoPerfilSelecionado == 1 || codigoPerfilSelecionado == 2) && usuariosCadastrados != null && usuariosCadastrados.size() > 0">
	<!-- Lista dos últimos usuários cadastrados -->
	<table class="tabela_listagem" width="90%" cellspacing="1" cellpadding="2" align="left">
			<tr>
				<th colspan="6" class="principal style2" scope="col">
					<s:if test="codigoPerfilSelecionado == 1">
						Últimos	Administradores cadastrados
					</s:if>
					<s:elseif test="codigoPerfilSelecionado == 2">
						Últimos	Atendentes cadastrados
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
			
				<s:url id="excluirUsuario" action="usuarioAction!excluirUsuario.action">
					<s:param name="usuario.id" value="id"/>
					<s:param name="codigoPerfilSelecionado" value="codigoPerfilSelecionado"/>
				</s:url>
				<s:url id="editarUsuario" action="usuarioAction!carregarEdicao.action">
					<s:param name="usuario.id" value="id"/>
					<s:param name="codigoPerfilSelecionado" value="codigoPerfilSelecionado"/>
				</s:url>
			
			<tr	class="<s:if test="#status.odd == true"></s:if><s:else>zebra</s:else>">
				<td><s:property value="nome" /></td>
				<td align="center"><s:property value="cpf" /></td>
				<td align="center"><s:property value="telefone" /></td>
				<td align="center"><s:property value="rg" /></td>
				<td align="center"><s:a href="%{#editarUsuario}" cssClass="linkEditar" cssStyle="linkEditar"></s:a></td>
				<td align="center"><s:a href="%{#excluirUsuario}" onclick="return confirmaExclusao()" cssClass="linkExcluir" cssStyle="linkExcluir"></s:a></td>

			</tr>
		</s:iterator>
		</table>
</s:if>

		
		
<s:if test="codigoPerfilSelecionado == 3 && medicosCadastrados != null && medicosCadastrados.size() > 0">
	<table class="tabela_moldura" width="90%" cellspacing="1" cellpadding="2" align="left">
			<tr>
				<th colspan="8" class="principal style2" scope="col">
					Últimos	Médicos cadastrados
				</th>
		</tr>		
		<tr>
			<th width="30%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Nome</span></th>
			<th width="10%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">CRM/UF</span></th>
			<th width="20%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Dias de Atendimento</span></th>
			<th width="10%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Horário</span></th>
			<th width="10%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Consultório</span></th>
			<th width="10%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Telefone</span></th>
			<th width="5%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Editar</span></th>
			<th width="5%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Excluir</span></th>
		</tr>
		
		<s:iterator value="medicosCadastrados" status="status">
			
				<s:url id="excluirMedico" action="medicoAction!excluirMedico.action">
					<s:param name="medico.id" value="id"/>
					<s:param name="codigoPerfilSelecionado" value="codigoPerfilSelecionado"/>
				</s:url>
				<s:url id="editarMedico" action="medicoAction!carregarEdicao.action">
					<s:param name="medico.id" value="id"/>
					<s:param name="codigoPerfilSelecionado" value="codigoPerfilSelecionado"/>
				</s:url>
			
			<tr	class="<s:if test="#status.odd == true"></s:if><s:else>zebra</s:else>">
				<td><s:property value="usuario.nome" /></td>
				<td align="center"><s:property value="crm+'/'+crmUf" /></td>
				<td align="center">
					<s:if test="dias != null && dias.size > 0">
						<s:iterator value="dias">
							<s:property value="sigla"/>
						</s:iterator>			
					</s:if>
				</td>
				<td align="center"><s:property value="horaInicioFormatada"/>&nbsp;às&nbsp;<s:property value="horaFimFormatada"/></td>
				<td align="center"><s:property value="consultorio" /></td>
				<td align="center"><s:property value="usuario.telefone"/></td>
				<td align="center"><s:a href="%{#editarMedico}" cssClass="linkEditar" cssStyle="linkEditar" ></s:a></td>
				<td align="center"><s:a href="%{#excluirMedico}" onclick="return confirmaExclusao()" cssClass="linkExcluir" cssStyle="linkExcluir"></s:a></td>

			</tr>
		</s:iterator>
	</table>
</s:if>


<s:if test="codigoPerfilSelecionado == 4 && pacientesCadastrados != null && pacientesCadastrados.size() > 0">
	<table class="tabela_listagem" width="90%" cellspacing="1" cellpadding="2" align="left">
			<tr>
				<th colspan="7" class="principal style2" scope="col">
					Últimos	Pacientes cadastrados
				</th>
		</tr>		
		<tr>
			<th width="6%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Prontuário</span></th>
			<th width="34%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Nome</span></th>
			<th width="12%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">CPF</span></th>
			<th width="12%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Telefone</span></th>
			<th width="12%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Convênio</span></th>
			<th width="5%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Editar</span></th>
			<th width="5%" bgcolor="#A7C2DA" scope="col"><span
				class="style5">Excluir</span></th>
		</tr>
		
		<s:iterator value="pacientesCadastrados" status="status">
			
				<s:url id="excluirPaciente" action="pacienteAction!excluirPaciente.action">
					<s:param name="paciente.id" value="id"/>
					<s:param name="codigoPerfilSelecionado" value="codigoPerfilSelecionado"/>
				</s:url>
				<s:url id="editarPaciente" action="pacienteAction!carregarEdicao.action">
					<s:param name="paciente.id" value="id"/>
					<s:param name="codigoPerfilSelecionado" value="codigoPerfilSelecionado"/>
				</s:url>
			
			<tr	class="<s:if test="#status.odd == true"></s:if><s:else>zebra</s:else>">
				<td align="center"><s:property value="id" /></td>
				<td><s:property value="usuario.nome" /></td>
				<td align="center"><s:property value="usuario.cpf" /></td>
				<td align="center"><s:property value="usuario.telefone" /></td>
				<td align="center">
					<s:if test="convenio != null">
						<s:property value="convenio.nome"/>
					</s:if>
					<s:else>
						Não possui
					</s:else>
				
				</td>
				<td align="center"><s:a href="%{#editarPaciente}" cssClass="linkEditar" cssStyle="linkEditar"></s:a></td>
				<td align="center"><s:a href="%{#excluirPaciente}" onclick="return confirmaExclusao()" cssClass="linkExcluir" cssStyle="linkExcluir"></s:a></td>

			</tr>
		</s:iterator>
	</table>
</s:if>


</body>
</html>