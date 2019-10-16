package com.despegar.b31;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneradorDeDatos {

    private static final String COMUNAS_FILE = "/comunas.csv";
    private static final String MUESTRAS_FILE = "/mapa_de_ruido_diurno.csv";

    public static List<Muestra> obtenerMuestras() {
        try {
            List<List<String>> records = new ArrayList<>();
            String muestrasFile = GeneradorDeDatos.class.getResource(MUESTRAS_FILE).getFile();

            try (Scanner scanner = new Scanner(new File(muestrasFile))) {
                while (scanner.hasNextLine()) {
                    records.add(getRecordFromLine(scanner.nextLine()));
                }
            }

            return records.subList(1, records.size()).stream().map(record -> {
                String minimum = record.get(4).split("-")[0];
                return new Muestra(Integer.valueOf(record.get(2)), Double.parseDouble(minimum));
            }).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public static List<Comuna> obtenerComunas() {
        try {
            List<List<String>> records = new ArrayList<>();
            String muestrasFile = GeneradorDeDatos.class.getResource(COMUNAS_FILE).getFile();

            try (Scanner scanner = new Scanner(new File(muestrasFile))) {
                while (scanner.hasNextLine()) {
                    records.add(getRecordFromLine(scanner.nextLine()));
                }
            }

            return records.subList(1, records.size()).stream().map(record -> {
                Set<String> s = new HashSet<>();
                String[] barrios = record.get(0).split(" - ");
                Collections.addAll(s, barrios);

                Integer id = Integer.valueOf(record.get(3));
                return new Comuna(id, s);
            }).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
