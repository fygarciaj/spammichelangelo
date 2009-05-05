function definirPerfil(){
	
	var selecionado = document.getElementById("perfil").value;
	if(selecionado == 0){
		alert("Selecione um perfil para o novo cadastro!");
		return false;
	}else{
		document.forms[0].action = "usuarioAction!definirTelaUsuario.action?codigoPerfilString="+selecionado;
		document.forms[0].submit();
	}
	
}


function verificaCamposPesquisa(){
	var cpf = document.getElementById("cpf").value;
	var nome = document.getElementById("nome").value;
	var perfil = document.getElementById("perfil").value;
	
	if(cpf.value == null && nome.value == null && perfil == 0){
		alert("Preencha pelo menos um campo para fazer a pesquisa!");
		return false;
	}else{
		return true;
	}
}

function formaConsultaConvenio(){
	var selecionado = document.getElementById("cmbFrmCns").value;
	var divCnpj = document.getElementById("cnpj");
	var divNome = document.getElementById("nomeConvenio");
	var divBotoes = document.getElementById("botoes");
	
	if (selecionado == 1){
		divNome.style.display = 'block';	
		document.getElementById("cmbFrmCns").disabled = true;
		divBotoes.style.display = 'block';
		
	}
	else if (selecionado == 2){
		divCnpj.style.display = 'block';		
		document.getElementById("cmbFrmCns").disabled = true;
		divBotoes.style.display = 'block';
		
	}else{
		alert("Fironfas");
	}
}
	
function atualizaPaginaCentral(){
	//TODO:
}

 