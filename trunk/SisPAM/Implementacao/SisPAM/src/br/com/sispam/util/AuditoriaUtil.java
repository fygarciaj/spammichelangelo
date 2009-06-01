package br.com.sispam.util;

import java.util.Date;

import br.com.sispam.dominio.Auditoria;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Acao;

public class AuditoriaUtil {
	
	
	public static Auditoria montaAuditoria(Acao acao, Usuario usuario){
		Auditoria auditoria = new Auditoria();
		auditoria.setAcao(acao.getDescricao());
		auditoria.setUsuario(usuario);
		auditoria.setDataReferencia(new Date());
		return auditoria;
	}
}
