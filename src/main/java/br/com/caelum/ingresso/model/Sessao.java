package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity

public class Sessao {
	
	
	@OneToMany(mappedBy	= "sessao", fetch = FetchType.EAGER)
	private	Set<Ingresso> ingressos = new HashSet<>();
		
	public Set<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(Set<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	private BigDecimal preco;
	
		
	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Sessao () {
		
				
	}
	
	public	boolean	isDisponivel(Lugar	lugarSelecionado) {
		
		return ingressos.stream().map(Ingresso::getLugar).noneMatch(lugar -> lugar.equals(lugarSelecionado));
		
		}
		
	public Sessao (Sala sala, Filme filme, LocalTime horario){
		this.filme = filme;
		this.sala = sala;
		this.horario = horario;
		this.preco = sala.getPreco().add(filme.getPreco());
    }
	
	@Id 
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Filme filme;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	@ManyToOne
	private Sala sala;

	private LocalTime horario;
	
	public Map<String, List<Lugar>>	getMapaDeLugares(){
		return	sala.getMapaDeLugares();
	}
	
	
}
