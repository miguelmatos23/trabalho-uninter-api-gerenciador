package com.example.trabalho.consulta.entity;

import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="CONSULTA")
public class Tarefa extends AbstractEntity<Long>{
	
	@Column (name="nome_paciente", nullable =false, length=60)
	private String nomePaciente;
	
	@Column (name="data_consulta", nullable =false, columnDefinition="DATE")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // Ajuda o Spring a entender a data vinda do formulário HTML
    @JsonFormat(pattern = "dd-MM-yyyy") // Garante que a data seja formatada corretamente no JSON da API
	private Date dataConsulta;
	
	@Column (name="nome_medico", nullable =false, length=60)
	private String nomeMedico;
	
	
	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

}
