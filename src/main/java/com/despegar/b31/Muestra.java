package com.despegar.b31;

public class Muestra {

    private String id;
    private String idComuna;
    private Double mediaDeRuido;

    public Muestra(String id, String idComuna, Double mediaDeRuido) {
        this.id = id;
        this.idComuna = idComuna;
        this.mediaDeRuido = mediaDeRuido;
    }

    public String getId() {
        return id;
    }

    public String getIdComuna() {
        return idComuna;
    }

    public Double getMediaDeRuido() {
        return mediaDeRuido;
    }
}
