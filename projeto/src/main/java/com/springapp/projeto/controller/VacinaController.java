package com.springapp.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springapp.projeto.model.Vacina;
import com.springapp.projeto.repository.VacinaRepository;

@Controller
public class VacinaController {
	
	@Autowired
	private VacinaRepository vr;

	@RequestMapping(value="/cadastrarVacina", method=RequestMethod.GET)
	public String form() {
		return "paginas/formVacina";
	}
	
	@RequestMapping(value="/cadastrarVacina", method=RequestMethod.POST)
	public String form(Vacina vacina) {
		
		// cadastrar 
		vr.save(vacina);
		
		// redirecionar para home
		return "redirect:/";
	}
	
	// lista 
	@GetMapping
	public ModelAndView listaVacinas() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Vacina> vacinas = vr.findAll();
		mv.addObject("vacinas", vacinas);
		return mv;
	}
	@RequestMapping(value="/alterarVacina/{codigoVacina}", method=RequestMethod.GET)
	public ModelAndView alterarVacina(@PathVariable("codigoVacina") long codigoVacina) {
		Vacina vacina = vr.findByCodigoVacina(codigoVacina);
		ModelAndView mv = new ModelAndView("paginas/alterarVacina");
		mv.addObject("vacina", vacina);
		return mv;
	}
	
	@RequestMapping(value="/alterarVacina/{codigoVacina}", method=RequestMethod.POST)
	public String alterarVacinaPost(@PathVariable("codigoVacina") long codigoVacina, Vacina vacina){
		vr.save(vacina);
		return "redirect:/";
		}
	
	@RequestMapping("/deletar")
	public String deletarVacina(long codigoVacina) {
		Vacina vacina = vr.findByCodigoVacina(codigoVacina);
		vr.delete(vacina);
		return "redirect:/";
	}
}