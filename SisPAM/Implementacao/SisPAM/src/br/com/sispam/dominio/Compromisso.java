package br.com.sispam.dominio;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.com.sispam.enums.TipoCompromisso;
import br.com.sispam.util.CampoUtil;

@Entity
public class Compromisso {
	private int id;
	private Medico medico;
	private Date data;
	private String descricao;
	private int horaInicial;
	private int horaFinal;
	private String tipo;
	private TipoCompromisso tipoCompromisso;
	
	
	@Id
	@Column(name="cpmcod")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "cpmdta")
	public Date getData() {
		return data;
	}
	public void setData(Date data){
		this.data = data;
	}
	@Column(name="cpmhorini")
	public int getHoraInicial() {
		return horaInicial;
	}
	public void setHoraInicial(int horaInicial) {
		this.horaInicial = horaInicial;
	}
	@Column(name="cpmhorfim")
	public int getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(int horaFinal) {
		this.horaFinal = horaFinal;
	}
	@Column(name="cpmdes")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Column(name="cpmtip")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@ManyToOne
	@JoinColumn(name="mdccod")
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	@Transient
	public TipoCompromisso getTipoCompromisso() {
		return tipoCompromisso;
	}
	
	public void setTipoCompromisso(TipoCompromisso tipoCompromisso) {
		this.tipoCompromisso = tipoCompromisso;
	}
	
	@Transient
	public String getHoraFinalFormatada(){
		return CampoUtil.formataHora(horaFinal);
	}
	
	@Transient
	public String getHoraInicialFormatada(){
		return CampoUtil.formataHora(horaInicial);
	}
	
	
}