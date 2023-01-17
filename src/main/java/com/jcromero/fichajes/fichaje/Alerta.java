package com.jcromero.fichajes.fichaje;

class Alerta {
    private String codigo;
    private String descripcion;
    
    Alerta(String codigo, String descripcion) {
        super();
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    } 
}
