package com.despegar.b31;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StringHelper {

    public static String convertirSetIntegerAString(Set<Integer> set) {
        return set.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }

    public static String convertirListIntegerAString(List<Integer> set) {
        return set.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }

    public static String convertirMapaMuestrasDeRuidoAString(Map<Integer, Integer> mapa) {
        String result = mapa.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " -> " + entry.getValue())
                .collect(Collectors.joining(", "));
        return result;
    }

    public static String convertirMapaDePromedioDeRuidoAString(Map<Integer, Double> mapa) {
        String result = mapa.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " -> " + entry.getValue())
                .collect(Collectors.joining(", "));
        return result;
    }
}
