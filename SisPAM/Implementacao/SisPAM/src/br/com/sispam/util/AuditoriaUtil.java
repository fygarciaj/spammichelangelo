package br.com.sispam.util;

import java.util.Date;

import br.com.sispam.dominio.Auditoria;
import br.com.sispam.dominio.Usuario;
import br.com.sispam.enums.Acao;
import br.com.sispam.enums.Funcionalidade;

public class AuditoriaUtil {
	
	
	public static Auditoria montaAuditoria(Funcionalidade funcionalidade, Acao acao, Usuario usuario){
		Auditoria auditoria = new Auditoria();
		auditoria.setTipo(funcionalidade.getDescricao());
		auditoria.setAcao(acao.getDescricao());
		auditoria.setUsuario(usuario);
		auditoria.setDataReferencia(new Date());
		return auditoria;
	}
}
