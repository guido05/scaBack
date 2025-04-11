package com.sca.model.DTO;

import java.sql.Timestamp;

public class AsociadoDTO {

    private int activo;
    private String apellido;
    private String documento;
    private String nombre;
    private String legajo;
    private String telefono;
    private String id_firma;
    private String codigo;
    private Timestamp fecha_hora;

    public AsociadoDTO() {
    }

    public AsociadoDTO(int activo, String apellido, String documento, String nombre, String legajo, String telefono, String id_firma, String codigo, Timestamp fecha_hora) {
        this.activo = activo;
        this.apellido = apellido;
        this.documento = documento;
        this.nombre = nombre;
        this.legajo = legajo;
        this.telefono = telefono;
        this.id_firma = id_firma;
        this.codigo = codigo;
        this.fecha_hora = fecha_hora;
    }

    public Timestamp getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Timestamp fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getId_firma() {
        return id_firma;
    }

    public void setId_firma(String id_firma) {
        this.id_firma = id_firma;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
