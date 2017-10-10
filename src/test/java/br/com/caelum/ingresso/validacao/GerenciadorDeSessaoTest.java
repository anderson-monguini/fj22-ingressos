package br.com.caelum.ingresso.validacao;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;


public class GerenciadorDeSessaoTest {
	
	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario(){
		
		Filme filme = new Filme ("Rogue One", Duration.ofMinutes(120),"SCI-FI");
		LocalTime horario = LocalTime.parse("10:00:00");
		
		Sala sala = new Sala ("");
		Sessao sessao = new Sessao (sala, filme, horario);
		
		List<Sessao> sessoes = Arrays.asList(sessao);
		
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao (sessoes);
		
		Assert.assertFalse(gerenciador.cabe(sessao));	
	}

	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente()
	{
		
		Filme filme = new Filme ("Rogue One", Duration.ofMinutes(120),"SCI-FI");
		LocalTime horario = LocalTime.parse("10:00:00");
		
		Sala sala = new Sala ("");
		List<Sessao> sessoes = Arrays.asList(new Sessao(sala, filme, horario));
		
		Sessao sessao = new Sessao(sala, filme, horario.minusHours(1));
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao (sessoes);
		
		Assert.assertFalse(gerenciador.cabe (sessao));
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente()
	{
		
		Filme filme = new Filme ("Rogue One", Duration.ofMinutes(120),"SCI-FI");
		LocalTime horario = LocalTime.parse("10:00:00");
		
		Sala sala = new Sala ("");
		List<Sessao> sessoes = Arrays.asList(new Sessao(sala, filme, horario));
		
		Sessao sessao = new Sessao(sala, filme, horario.plusHours(1));
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao (sessoes);
		
		Assert.assertFalse(gerenciador.cabe (sessao));
	}
	
	@Test 
	public	void garanteQueDevePermitirUmaInsercaoEntreDoisFilmes() {
		
		Sala	sala	=	new	Sala("");
		
		Filme	filme1	=	new	Filme("Rogue	One",	Duration.ofMinutes(90),	"SCI-FI");
		
		LocalTime	dezHoras	=	LocalTime.parse("10:00:00");
		
		Sessao	sessaoDasDez	=	new	Sessao(sala, filme1, dezHoras);
		
		Filme	filme2	=	new	Filme("Rogue One", Duration.ofMinutes(120),"SCI-FI");
		
		LocalTime	dezoitoHoras	=	LocalTime.parse("18:00:00");

		Sessao	sessaoDasDezoito	=	new	Sessao(sala, filme2, dezoitoHoras);
		
		List<Sessao>	sessoes	=	Arrays.asList(sessaoDasDez,	sessaoDasDezoito);
		
		GerenciadorDeSessao	gerenciador	=	new	GerenciadorDeSessao(sessoes);
		
		Sessao sessao = new	Sessao(sala, new Filme("Spider Man", Duration.ofMinutes(140),"SCI-FI"),LocalTime.parse("13:00:00"));
		
		Assert.assertTrue(gerenciador.cabe(sessao));
	}

}