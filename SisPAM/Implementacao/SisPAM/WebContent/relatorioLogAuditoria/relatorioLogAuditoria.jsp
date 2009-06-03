<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../componentes/css/estilo.css"	type="text/css" />
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
<script type="text/javascript">
		 $(document).ready(function(){
			calendario('data');
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
	
		function emiteRelatorio(){			
			var data = document.getElementById("data");
			document.getElementById("dataForm").value = data.value;
			document.forms[1].submit();
			
		}		
	</script>
</head>
<body>
<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
		<td><br>
		<div>Relatórios<img src="../componentes/img/seta.gif" />Log de Auditoria<img
			src="../componentes/img/seta.gif" />Emitir</div>
		</td>
</table>
<h2>Emissão de Log de Auditoria</h2>
<h3>Atenção! Informe uma data válida para pesquisa</h3>
<form action="../relatorioLog.sispam" method="post" name="relatorio">
		<table class="tabela_relatorio"  >
						
			<tr>
				<td><label>Data de pesquisa:</label></td>				
				<td><input type="text" name="data" id="data" size="10" maxlength="10"/></td>
				<td></td><td><input type="button" onclick="emiteRelatorio()" value="Emitir" class="button"/></td>
			</tr>
			
		</table>
</form>
			
	<form action="../relatorioLog.sispam" method="post" name="relatorio">
		<input type="hidden" name="data" id="dataForm">
		<input type="hidden" name="relatorioChamado" value="relatorioLog">
	</form>	
</body>
</html>