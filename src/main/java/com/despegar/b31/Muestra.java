package com.despegar.b31;

public class Muestra {

    private Integer idComuna;
    private Double mediaDeRuido;

    public Muestra(Integer idComuna, Double mediaDeRuido) {
        this.idComuna = idComuna;
        this.mediaDeRuido = mediaDeRuido;
    }

    public Integer getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Integer idComuna) {
        this.idComuna = idComuna;
    }

    public Double getMediaDeRuido() {
        return mediaDeRuido;
    }

    public void setMediaDeRuido(Double mediaDeRuido) {
        this.mediaDeRuido = mediaDeRuido;
    }
}
