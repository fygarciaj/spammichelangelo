
function definirPerfil(){
	var selecionado = document.getElementById("perfil").value;
	var divUsuario = document.getElementById("usuario");
	var divPaciente = document.getElementById("paciente");
	var divMedico = document.getElementById("medico");
	if(selecionado == 1 || selecionado == 2){
		divUsuario.style.display = 'block';
		divPaciente.style.display = 'none';
		divMedico.style.display = 'none';
		document.getElementById("perfil").disabled = true;
	}
	else if(selecionado == 3){
		divUsuario.style.display = 'none';
		divPaciente.style.display = 'none';
		divMedico.style.display = 'block';
		document.getElementById("perfil").disabled = true;
	}
	else if(selecionado == 4){
		divUsuario.style.display = 'none';
		divPaciente.style.display = 'block';
		divMedico.style.display = 'none';
		document.getElementById("perfil").disabled = true;
	}
	else{
	}
}