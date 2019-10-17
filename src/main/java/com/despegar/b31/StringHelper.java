package com.despegar.b31;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringHelper {

    public static <T> String convertirColeccionAString(Collection<T> coleccion) {
        if(coleccion == null) {
            return "";
        }
        return coleccion
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public static <K, V> String convertirMapaAString(Map<K, V> mapa) {
        if (mapa == null) {
            return "";
        }
        return mapa.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " -> " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    public static String convertirBarriosDeComuna(Integer id, List<Comuna> comunas) {
        if(comunas == null) {
            return "";
        }
        return comunas.stream()
                .filter(comuna -> comuna.getId().equals(id)).findFirst()
                .map(comuna -> String.join(", ", comuna.getBarrios()))
                .orElse("(comuna no encontrada)");
    }
}
