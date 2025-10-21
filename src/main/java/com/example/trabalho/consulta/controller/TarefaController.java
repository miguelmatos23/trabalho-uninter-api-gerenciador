package com.example.trabalho.consulta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String cadastro(Tarefa tarefa) {
        return "/tarefa/cadastro";
    }

    @PostMapping(value = "/salvar", consumes = "application/json")
    public String salvarJson(@RequestBody Tarefa tarefa) {
        System.out.println(">>> [SALVAR JSON] Tarefa recebida: " + tarefa);
        if (tarefa.getDataConsulta() == null) {
             System.out.println(">>> [SALVAR JSON] ATENÇÃO: dataConsulta chegou NULA!");
        } else {
             System.out.println(">>> [SALVAR JSON] Data recebida: " + tarefa.getDataConsulta());
        }

        service.salvar(tarefa);
        return "redirect:/tarefas/lista";
    }


    @GetMapping("/lista")
    public ModelAndView lista() {
        ModelAndView mv = new ModelAndView("tarefa/lista");
        mv.addObject("tarefas", service.buscaTodos());
        return mv;
    }

    @PostMapping(value = "/editar", consumes = "application/json")
    public String editarJson(@RequestBody Tarefa tarefa) {
        System.out.println(">>> [EDITAR JSON] Tarefa recebida para edição: " + tarefa);
         if (tarefa.getDataConsulta() == null) {
             System.out.println(">>> [EDITAR JSON] ATENÇÃO: dataConsulta chegou NULA!");
        } else {
             System.out.println(">>> [EDITAR JSON] Data recebida: " + tarefa.getDataConsulta());
        }

        service.editar(tarefa);
        return "redirect:/tarefas/lista";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        System.out.println(">>> [EXCLUIR] Recebido ID para exclusão: " + id);

        service.excluir(id);
        return "redirect:/tarefas/lista";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView preEditar(@PathVariable("id") Long id) {
        System.out.println(">>> [PRE-EDITAR] Recebido ID para buscar: " + id);

        ModelAndView mv = new ModelAndView("tarefa/cadastro");
        Tarefa tarefaEncontrada = service.buscarPorId(id);

        System.out.println(">>> [PRE-EDITAR] Tarefa encontrada: " + tarefaEncontrada);

        mv.addObject("tarefa", tarefaEncontrada);
        return mv;
    }
}