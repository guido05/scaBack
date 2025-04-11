package com.sca.model.DTO;

import java.sql.Timestamp;

public class AsistenciaDTO {
    private int activo;
    private String apellido;
    private String documento;
    private String nombre;
    private String legajo;
    private String telefono;
    private String id_firma;
    private String codigo;
    private String fecha;
    private String hora_entrada;
    private String hora_salida;
    private String fecha_hora;
    private String condicion;

    public AsistenciaDTO() {
    }

    public AsistenciaDTO(int activo, String apellido, String documento, String nombre, String legajo, String telefono, String id_firma, String codigo, String fecha, String hora_entrada, String hora_salida, String fecha_hora,String condicion) {
        this.activo = activo;
        this.apellido = apellido;
        this.documento = documento;
        this.nombre = nombre;
        this.legajo = legajo;
        this.telefono = telefono;
        this.id_firma = id_firma;
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.fecha_hora = fecha_hora;
        this.condicion = condicion;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId_firma() {
        return id_firma;
    }

    public void setId_firma(String id_firma) {
        this.id_firma = id_firma;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
}
