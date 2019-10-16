package com.despegar.b31;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {

    public static void main( String[] args ) {

        Collection<Muestra> muestras = GeneradorDeDatos.obtenerMuestras();
        Collection<Comuna> comunas =  GeneradorDeDatos.obtenerComunas();

        // Ejercicio 1
        Integer cantidadDeMuestras = conocerCantidadDeMuestras(muestras);
        assert cantidadDeMuestras ==  167 : "obtenemos la cantidad correcta de muestras";
        assert comunas.size() == 15 : "obtenemos la cantidad de comunas";

        // Punto 2
        // eliminarMuestra
    }

    // Ejercicio 1
    public static Integer conocerCantidadDeMuestras(Collection<Muestra> muestras) {
       return muestras.size();
    }

    // Ejercicio 2
    public static void eliminarBarrioDeComuna(Collection<Comuna> comunas, Integer idComuna, String barrio) {
        for (Comuna comuna : comunas) {
            if (comuna.getId().equals(idComuna)) {
                comuna.getBarrios().remove(barrio);
            }
        }
    }

    // Ejercicio 3
    // esta solucion supone que las muestras son inmutables
    public static void reemplazarMuestra(Collection<Muestra> muestras, Integer viejoId, Double viejoRuido,
                                         Integer nuevoId, Double nuevoRuido) {
        Muestra muestraABorrar = null;
        for (Muestra muestra : muestras) {
            if (muestra.getIdComuna().equals(viejoId) && muestra.getMediaDeRuido().equals(viejoRuido)) {
                muestraABorrar = muestra;
            }
        }

        // opcion B
        // muestras.removeIf(muestra -> muestra.getIdComuna().equals(viejoId) && muestra.getMediaDeRuido().equals(viejoRuido));

        if (muestraABorrar != null) {
            muestras.remove(muestraABorrar);
            muestras.add(new Muestra(nuevoId, nuevoRuido));
        }
    }

    // Ejercicio 4
    public static Integer obtenerComunaMasRuidosa(Collection<Muestra> muestras) {
        Muestra muestraMasRuidosa = muestras.stream().findFirst().get();
        for (Muestra muestra : muestras) {
            if (muestra.getMediaDeRuido() > muestraMasRuidosa.getMediaDeRuido()) {
                muestraMasRuidosa = muestra;
            }
        }
        return muestraMasRuidosa.getIdComuna();
    }

    // Ejerciio 5
    public static Double obtenerMedia(Collection<Muestra> muestras) {
        Double sumatoria = 0D;
        for (Muestra muestra : muestras) {
            sumatoria += muestra.getMediaDeRuido();
        }
        return sumatoria / muestras.size();
    }

    // Ejercicio 6
    public static Set<Integer> obtenerComunasConMuestrasSilencioas(Collection<Muestra> muestras) {
        Set<Integer> resultado = new HashSet<>();

        for (Muestra muestra : muestras) {
            if (muestra.getMediaDeRuido() < 50) {
                resultado.add(muestra.getIdComuna());
            }
        }
        return resultado;
    }

    // Ejercicio 7
    public static Map<Integer, Integer> obtenerReporteDeMuestrasPorComuna(Collection<Muestra> muestras) {
        Map<Integer, Integer> resultado = new HashMap<>();

        for (Muestra muestra : muestras) {
            if (resultado.containsKey(muestra.getIdComuna())) {
                Integer contadorDeUnaComuna = resultado.get(muestra.getIdComuna());
                resultado.replace(muestra.getIdComuna(), contadorDeUnaComuna + 1);
            }
        }

        return resultado;
    }

    // Ejercicio 8
    public static Map<Integer, Double> obtenerMediaPorComuna(Collection<Muestra> muestras) {
        Map<Integer, List<Double>> muestrasPorComuna = new HashMap<>();

        for (Muestra muestra : muestras) {
            if (!muestrasPorComuna.containsKey(muestra.getIdComuna())) {
                muestrasPorComuna.put(muestra.getIdComuna(), new ArrayList<>());
            }
            muestrasPorComuna.get(muestra.getIdComuna()).add(muestra.getMediaDeRuido());
        }

        Map<Integer, Double> resultado = new HashMap<>();

        for(Map.Entry<Integer, List<Double>> entry : muestrasPorComuna.entrySet()) {

            Double suma = 0D;
            for (Double ruido : entry.getValue()) {
                suma += ruido;
            }

            resultado.put(entry.getKey(), suma / entry.getValue().size());
        }
        return resultado;
    }
}
