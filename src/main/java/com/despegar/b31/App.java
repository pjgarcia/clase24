package com.despegar.b31;

import java.util.*;

public class App {

    public static void main( String[] args ) {
        List<Muestra> muestras = GeneradorDeDatos.obtenerMuestras();
        List<Comuna> comunas =  GeneradorDeDatos.obtenerComunas();

        //Ejercicio 1 - Estadísticas generales
        System.out.println("Cantidad de muestras: " + conocerCantidadDeMuestras(muestras));
        System.out.println("Cantidad de comunas: " + conocerCantidadDeComunas(comunas));

        // Ejercicio 2 - Depurando la fuente de datos
        eliminarBarrioDeComuna(comunas, 5, "PATERNAL");
        System.out.println("Cantidad de muestras: " + conocerCantidadDeMuestras(muestras));
        System.out.println("Cantidad de comunas: " + conocerCantidadDeComunas(comunas));

        System.out.println("Cantidad de muestras: " + conocerCantidadDeMuestras(muestras));
        System.out.println("Cantidad de comunas: " + conocerCantidadDeComunas(comunas));

        // Ejercicio 3 - Depurando la fuente de datos
        reemplazarMuestra(muestras, 60.0, 2, 62.0);

        // Ejercicio 4 - Comuna mas ruidosa
        Integer comunaMasRuidosa = obtenerComunaMasRuidosa(muestras);
        System.out.println("La comuna mas ruidosa es: " + comunaMasRuidosa);

        // Ejercicio 5 - Ruido promedio
        Double ruidoPromedio = obtenerRuidoPromedio(muestras);
        System.out.println("El ruido promedio de CABA es: " + ruidoPromedio);

        // Ejercicio 6 - Comunas mas silenciosas
        Set<Integer> comunasMasSilenciosas = obtenerComunasConMuestrasSilenciosas(muestras);
        System.out.println("Las comunas mas silenciosas son: " + StringHelper.convertirSetIntegerAString(comunasMasSilenciosas));

        // Ejercicio 7 - Reporte de mediciones
        Map<Integer, Integer> reporteMuetrasPorComuna = obtenerReporteDeMuestrasPorComuna(muestras);
        System.out.println("La cantidad de muestras por comunas es: " + StringHelper.convertirMapaMuestrasDeRuidoAString(reporteMuetrasPorComuna));

        // Ejercicio 8 - Promedio de ruido por comuna
        Map<Integer, Double> promedioDeRuidoPorComuna = obtenerPromedioDeRuidoPorComuna(muestras);
        System.out.println("El promedio de ruido por comuna es: " + StringHelper.convertirMapaDePromedioDeRuidoAString(promedioDeRuidoPorComuna));
    }

    // Ejercicio 1
    public static Integer conocerCantidadDeMuestras(List<Muestra> muestras) {
        // Implementame !!
        return muestras.size();
        // return -1;
    }

    public static Integer conocerCantidadDeComunas(List<Comuna> comunas) {
        // Implementame !!
        return comunas.size();
        // return -1;
    }


    // Ejercicio 2
    public static void eliminarBarrioDeComuna(List<Comuna> comunas, Integer idComuna, String barrio) {
        // Implementame !!!
        //return;
        for (Comuna comuna : comunas) {
            if (comuna.getId().equals(idComuna)) {
                comuna.getBarrios().remove(barrio);
            }
        }
    }

    // Ejercicio 3
    public static void reemplazarMuestra(List<Muestra> muestras, Double viejoRuido, Integer comunaId, Double nuevoRuido) {
        // Implementame !!!
        // return;
        for (Muestra muestra : muestras) {
            if (muestra.getIdComuna().equals(comunaId) && muestra.getMediaDeRuido().equals(viejoRuido)) {
                muestra.setMediaDeRuido(nuevoRuido);
            }
        }
    }

    // Ejercicio 4
    public static Integer obtenerComunaMasRuidosa(List<Muestra> muestras) {
        // return -1;
        Muestra muestraMasRuidosa = muestras.stream().findFirst().get();
        for (Muestra muestra : muestras) {
            if (muestra.getMediaDeRuido() > muestraMasRuidosa.getMediaDeRuido()) {
                muestraMasRuidosa = muestra;
            }
        }
        return muestraMasRuidosa.getIdComuna();
    }

    // Ejercicio 5
    public static Double obtenerRuidoPromedio(List<Muestra> muestras) {
        // return -1D;
        Double sumatoria = 0D;
        for (Muestra muestra : muestras) {
            sumatoria += muestra.getMediaDeRuido();
        }
        return sumatoria / muestras.size();
    }

    // Ejercicio 6
    public static Set<Integer> obtenerComunasConMuestrasSilenciosas(List<Muestra> muestras) {
        Set<Integer> resultado = new HashSet<>();

        for (Muestra muestra : muestras) {
            if (muestra.getMediaDeRuido() < 50) {
                resultado.add(muestra.getIdComuna());
            }
        }
        return resultado;
    }

    // Ejercicio 7
    public static Map<Integer, Integer> obtenerReporteDeMuestrasPorComuna(List<Muestra> muestras) {
        Map<Integer, Integer> resultado = new HashMap<>();

        for (Muestra muestra : muestras) {
            Integer contadorDeUnaComuna;
            if (resultado.get(muestra.getIdComuna()) == null) {
                contadorDeUnaComuna = 0;
            } else {
                contadorDeUnaComuna = resultado.get(muestra.getIdComuna());
            }
            resultado.put(muestra.getIdComuna(), contadorDeUnaComuna + 1);

        }

        return resultado;
    }

    // Ejercicio 8
    public static Map<Integer, Double> obtenerPromedioDeRuidoPorComuna(List<Muestra> muestras) {
        Map<Integer, List<Double>> muestrasPorComuna = new HashMap<>();

        // Agrupamos las muestras por comunas
        for (Muestra muestra : muestras) {
            if (!muestrasPorComuna.containsKey(muestra.getIdComuna())) {
                muestrasPorComuna.put(muestra.getIdComuna(), new ArrayList<>());
            }
            muestrasPorComuna.get(muestra.getIdComuna()).add(muestra.getMediaDeRuido());
        }

        // Calculamos el promedio de ruido por comuna
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
