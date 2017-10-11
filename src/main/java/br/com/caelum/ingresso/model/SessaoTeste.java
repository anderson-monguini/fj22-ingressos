package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;


public class SessaoTeste {
	
	@Test
	public void oPrecoDaSessapDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFilme(){
		
		Sala sala = new Sala("Eldorado - Imax", new BigDecimal ("22.5"));
		Filme filme = new Filme ("Rogue One", Duration.ofMinutes(120),"SCI-FI", new BigDecimal ("12.0"));
		
		BigDecimal somaDosPrecosDaSalaEFilme = sala.getPreco().add(filme.getPreco());
		
		Sessao sessao = new Sessao(sala, filme, LocalTime.parse("10:00:00"));
		
		Assert.assertEquals(somaDosPrecosDaSalaEFilme, sessao.getPreco());
		
	}

}