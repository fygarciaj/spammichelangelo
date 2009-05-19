package br.com.sispam.dominio;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Paciente {

    private int id;
    private Convenio convenio;
    private String plano;
    private String descricaoAcomodacao;
    private Usuario usuario;
    private Date validadePlano;
   
    @Id
    @Column(name="pctidfseg")
    @GeneratedValue (strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @ManyToOne
	@JoinColumn(name="cvncod")
    public Convenio getConvenio() {
        return convenio;
    }
    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }
    @Column(name="pctplnrde")
    public String getPlano() {
        return plano;
    }
    public void setPlano(String plano) {
        this.plano = plano;
    }
    @Column(name="pctacm")
    public String getDescricaoAcomodacao() {
        return descricaoAcomodacao;
    }
    public void setDescricaoAcomodacao(String descricaoAcomodacao) {
        this.descricaoAcomodacao = descricaoAcomodacao;
    }
   
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usrcod")
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    @Column(name="pctplnvld")
    public Date getValidadePlano() {
        return validadePlano;
    }
    public void setValidadePlano(Date validadePlano) {
        this.validadePlano = validadePlano;
    }
}
