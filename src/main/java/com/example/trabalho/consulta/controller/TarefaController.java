package com.example.trabalho.consulta.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.trabalho.consulta.entity.Tarefa;
import com.example.trabalho.consulta.service.TarefaService;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {
	
	@Autowired
	private TarefaService service;
	
	@GetMapping("/cadastro")
	public String cadastro (Tarefa tarefa) {
		return "/tarefa/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(Tarefa tarefa) {
		service.salvar(tarefa);
		
		return "redirect:/tarefas/lista";
	
	}
	
	@GetMapping("/lista")
	public String lista(ModelMap model) {
		model.addAttribute("tarefas", service.buscaTodos());
		return "tarefa/lista";
	}
	
	@PostMapping("/editar")
	public String editar(Tarefa tarefa) {
		service.editar(tarefa);
		return "redirect:/tarefas/lista";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		
		service.excluir(id);
		return lista(model);
		
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		
		model.addAttribute("tarefa", service.buscarPorId(id));
		return "/tarefa/cadastro";
			
		
	}
	
}
