package com.sca.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reg")
public class Reg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "fechaHora")
    private Date fechaHora;

    public Reg(Long id, String codigo, Date fechaHora) {
        this.id = id;
        this.codigo = codigo;
        this.fechaHora = fechaHora;
    }

    public Reg() {

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
}
