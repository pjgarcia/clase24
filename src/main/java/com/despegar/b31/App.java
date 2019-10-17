package com.despegar.b31;

import java.util.*;

public class App {

    public static void main( String[] args ) {
        List<Muestra> muestras = GeneradorDeDatos.obtenerMuestras();
        List<Comuna> comunas =  GeneradorDeDatos.obtenerComunas();

        //Ejercicio 1 - Estadísticas generales
        System.out.println("1) Cantidad de muestras: " + conocerCantidadDeMuestras(muestras));
        System.out.println("1) Cantidad de comunas: " + conocerCantidadDeComunas(comunas));

        // Ejercicio 2 - Depurando la fuente de datos
        System.out.println("2) Barrios antes del arreglo: " + StringHelper.convertirBarriosDeComuna(5, comunas));
        eliminarBarrioDeComuna(comunas, 5, "PATERNAL");
        System.out.println("2) Barrios despues del arreglo: " + StringHelper.convertirBarriosDeComuna(5, comunas));

        // Ejercicio 3 - Depurando la fuente de datos
        reemplazarMuestra(muestras, 60, 2, 62);

        // Ejercicio 4 - Comuna mas ruidosa
        Integer comunaMasRuidosa = obtenerComunaMasRuidosa(muestras);
        System.out.println("4) La comuna mas ruidosa es: " + comunaMasRuidosa);

        // Ejercicio 5 - Ruido promedio
        Double ruidoPromedio = obtenerRuidoPromedio(muestras);
        System.out.println(String.format("5) El ruido promedio de CABA es: %.2f db", ruidoPromedio));

        // Ejercicio 6 - Comunas mas silenciosas
        Set<Integer> comunasMasSilenciosas = obtenerComunasConMuestrasSilenciosas(muestras);
        System.out.println("6) Las comunas mas silenciosas son: " + StringHelper.convertirColeccionAString(comunasMasSilenciosas));

        // Ejercicio 7 - Reporte de mediciones
        Map<Integer, Integer> reporteMuetrasPorComuna = obtenerReporteDeMuestrasPorComuna(muestras);
        System.out.println("7) La cantidad de muestras por comunas es: " + StringHelper.convertirMapaAString(reporteMuetrasPorComuna));

        // Ejercicio 8 - Promedio de ruido por comuna
        Map<Integer, Double> promedioDeRuidoPorComuna = obtenerPromedioDeRuidoPorComuna(muestras);
        System.out.println("8) El promedio de ruido por comuna es: " + StringHelper.convertirMapaAString(promedioDeRuidoPorComuna));
    }

    public static Integer conocerCantidadDeMuestras(List<Muestra> muestras) {
        return muestras.size();
    }

    public static Integer conocerCantidadDeComunas(List<Comuna> comunas) {
        return comunas.size();
    }


    public static void eliminarBarrioDeComuna(List<Comuna> comunas, Integer idComuna, String barrio) {
        for (Comuna comuna : comunas) {
            if (comuna.getId().equals(idComuna)) {
                comuna.getBarrios().remove(barrio);
            }
        }
    }

    public static void reemplazarMuestra(List<Muestra> muestras, Integer viejoRuido, Integer comunaId, Integer nuevoRuido) {
        for (Muestra muestra : muestras) {
            if (muestra.getIdComuna().equals(comunaId) && muestra.getRuido().equals(viejoRuido)) {
                muestra.setRuido(nuevoRuido);
            }
        }
    }

    public static Integer obtenerComunaMasRuidosa(List<Muestra> muestras) {
        Muestra muestraMasRuidosa = muestras.get(0);
        for (Muestra muestra : muestras) {
            if (muestra.getRuido() > muestraMasRuidosa.getRuido()) {
                muestraMasRuidosa = muestra;
            }
        }
        return muestraMasRuidosa.getIdComuna();
    }

    public static Double obtenerRuidoPromedio(List<Muestra> muestras) {
        Double sumatoria = 0D;
        for (Muestra muestra : muestras) {
            sumatoria += muestra.getRuido();
        }
        return sumatoria / muestras.size();
    }


    public static Set<Integer> obtenerComunasConMuestrasSilenciosas(List<Muestra> muestras) {
        Set<Integer> resultado = new HashSet<>();

        for (Muestra muestra : muestras) {
            if (muestra.getRuido() < 50) {
                resultado.add(muestra.getIdComuna());
            }
        }
        return resultado;
    }

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

    public static Map<Integer, Double> obtenerPromedioDeRuidoPorComuna(List<Muestra> muestras) {

        Map<Integer, List<Muestra>> muestrasPorComuna = agruparMuestrasPorComuna(muestras);

        // Calculamos el promedio de ruido por comuna
        Map<Integer, Double> resultado = new HashMap<>();
        for(Integer idComuna : muestrasPorComuna.keySet()) {

            List<Muestra> muestrasDeLaComuna = muestrasPorComuna.get(idComuna);
            Double suma = 0D;
            for (Muestra muestra : muestrasDeLaComuna) {
                suma += muestra.getRuido();
            }

            resultado.put(idComuna, suma / muestrasDeLaComuna.size());
        }
        return resultado;
    }

    private static Map<Integer, List<Muestra>> agruparMuestrasPorComuna(List<Muestra> muestras) {
        Map<Integer, List<Muestra>> resultado = new HashMap<>();

        for (Muestra muestra : muestras) {
            if (!resultado.containsKey(muestra.getIdComuna())) {
                resultado.put(muestra.getIdComuna(), new ArrayList<>());
            }
            resultado.get(muestra.getIdComuna()).add(muestra);
        }
        return resultado;
    }

    // Extra 1
    private static void eliminarMuestraErronea(List<Muestra> muestras, Integer ruido, Integer idComuna) {
        Muestra muestraABorrar = null;
        for (Muestra muestra : muestras) {
            if (muestra.getIdComuna().equals(idComuna) && muestra.getRuido().equals(ruido)) {
                muestraABorrar = muestra;
            }
        }

        if (muestraABorrar != null) {
            muestras.remove(muestraABorrar);
        }
    }

    // Extra 2
    public static Collection<Muestra> obtenerMuestrasDeUnBarrio(List<Muestra> muestras, List<Comuna> comunas, String barrio) {
        Integer comunaDelBarrio = null;
        for (Comuna comuna : comunas) {
            if (comuna.getBarrios().contains(barrio) && comuna.getBarrios().size() > 1) {
                System.out.println("La comuna tiene varios barrios, no podemos saber si las muestras pertenecen a ese barrio");
                return new ArrayList<>();
            }

            if (comuna.getBarrios().contains(barrio)) {
                comunaDelBarrio = comuna.getId();
            }
        }

        Map<Integer, List<Muestra>> muestrasPorComuna = agruparMuestrasPorComuna(muestras);

        return muestrasPorComuna.get(comunaDelBarrio);
    }
}
