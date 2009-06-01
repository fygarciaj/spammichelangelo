package br.com.sispam.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CodigoDoenca {

	private int id;
	private String categoriaInicial;
	private String categoriaFinal;	
	private String descricao;
	private String abreviatura;
	private List<HistoricoProntuario> historicos;
	
	@Id
	@Column(name="cidcod")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="ciddes")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Column(name="cidctgini")
	public String getCategoriaInicial() {
		return categoriaInicial;
	}
	public void setCategoriaInicial(String categoriaInicial) {
		this.categoriaInicial = categoriaInicial;
	}
	@Column(name="cidctgfim")
	public String getCategoriaFinal() {
		return categoriaFinal;
	}
	public void setCategoriaFinal(String categoriaFinal) {
		this.categoriaFinal = categoriaFinal;
	}
	@Column(name="ciddesabr")
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	@OneToMany(mappedBy = "codigoDoenca")
	public List<HistoricoProntuario> getHistoricos() {
		return historicos;
	}
	public void setHistoricos(List<HistoricoProntuario> historicos) {
		this.historicos = historicos;
	}
}
