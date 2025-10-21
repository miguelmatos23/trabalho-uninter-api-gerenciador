package com.example.trabalho.consulta.entity;

import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tarefas")
public class Tarefa extends AbstractEntity<Long> {

    @Column(name = "nome_paciente", nullable = false, length = 60)
    private String nomePaciente;

    @Column(name = "data_consulta", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private Date dataConsulta;

    @Column(name = "nome_medico", nullable = false, length = 60)
    private String nomeMedico;

    public Tarefa() {
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(dataConsulta, nomeMedico, nomePaciente);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tarefa other = (Tarefa) obj;
        return Objects.equals(dataConsulta, other.dataConsulta)
                && Objects.equals(nomeMedico, other.nomeMedico)
                && Objects.equals(nomePaciente, other.nomePaciente);
    }

    @Override
    public String toString() {
        String dataFormatada = (dataConsulta != null)
                ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(dataConsulta)
                : "null";
        return "Tarefa [id=" + getId() +
                ", nomePaciente=" + nomePaciente +
                ", dataConsulta=" + dataFormatada +
                ", nomeMedico=" + nomeMedico + "]";
    }
}
