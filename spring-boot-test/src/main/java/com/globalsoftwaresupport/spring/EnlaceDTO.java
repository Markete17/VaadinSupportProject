package com.globalsoftwaresupport.spring;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnlaceDTO {
    
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("descripcion")
    private String descripcion;
    
    public EnlaceDTO() {
    }
    
    public EnlaceDTO(Integer id, String nombre, String descripcion) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
