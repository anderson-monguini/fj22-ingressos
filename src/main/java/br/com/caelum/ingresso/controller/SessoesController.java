package br.com.caelum.ingresso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;

@Controller


public class SessoesController {

	@Autowired
	private SalaDao salaDao;
	
	@Autowired
	private FilmeDao filmeDao;
	
	@Autowired
	private SessaoDao sessaoDao;
	
	@GetMapping ("admin/sessao")
	
	public ModelAndView form (@RequestParam("salaId") Integer salaId, SessaoForm form){
		
		ModelAndView mav = new ModelAndView ("sessao/sessao");
		mav.addObject("sala", salaDao.findOne(salaId));
		mav.addObject("filmes", filmeDao.findAll());
		mav.addObject("sessaofomr", form);
		return mav;
						
	}
	
	@PostMapping ("admin/sessao") @Transactional
	public ModelAndView grava (@Valid SessaoForm sessaoForm, BindingResult br){
		
		ModelAndView mav = new ModelAndView ("redirect: /admin/sala/" +sessaoForm.getSalaId()+"/sessoes");
		
		if (br.hasErrors()) {
		
	}
	
	Sessao sessao = sessaoForm.toSessao(salaDao, filmeDao);
	sessaoDao.save(sessao);
	return mav;
	
	}
}