/* ---------------------------------------------------------------------------
	Descricao : Coloca mascara de cpf enquanto o usuario digita a 
				informa??o.
	Parametros: campo - campo a ser formatado.
				mascara - mascara.
				evento- evento
	Retorno   : 
	Globais   : -
	Utilizado no evento: onKeyDown
*/

function formataCampo(campo, Mascara, evento) {
	var boleanoMascara;

	var Digitato = evento.keyCode;
	exp = /\-|\.|\/|\(|\)| /g
	campoSoNumeros = campo.value.toString().replace( exp, "" );

	var posicaoCampo = 0;
	var NovoValorCampo="";
	var TamanhoMascara = campoSoNumeros.length;;

	if (Digitato != 8) { // backspace
		for(i=0; i<= TamanhoMascara; i++) {
			boleanoMascara = ((Mascara.charAt(i) == "-") || (Mascara.charAt(i) == ".")
					|| (Mascara.charAt(i) == "/"))
					boleanoMascara = boleanoMascara || ((Mascara.charAt(i) == "(")
							|| (Mascara.charAt(i) == ")") || (Mascara.charAt(i) == " "))
							if (boleanoMascara) {
								NovoValorCampo += Mascara.charAt(i);
								TamanhoMascara++;
							}else {
								NovoValorCampo += campoSoNumeros.charAt(posicaoCampo);
								posicaoCampo++;
							}

//			adiciona mascara ao CPF
			function MascaraCPF(cpf){
				if(mascaraInteiro(cpf)==false){
					event.returnValue = false;
				}
				return formataCampo(cpf, '000.000.000-00', event);
			}
		}
		campo.value = NovoValorCampo;
		return true;
	}else {
		return true;
	}
}