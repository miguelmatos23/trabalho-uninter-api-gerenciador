package com.example.trabalho.consulta.dao;

import java.util.List;
import com.example.trabalho.consulta.entity.Tarefa; 
public interface TarefaDao {

    void save(Tarefa tarefa);

    void update(Tarefa tarefa);

    void delete(Long id);

    Tarefa findById(Long id);

    List<Tarefa> findAll();
}