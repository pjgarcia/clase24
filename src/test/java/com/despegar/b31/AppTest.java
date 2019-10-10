package com.despegar.b31;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static final String COMUNAS_FILE = "/home/pedro/despegar/clase24/src/test/java/com/despegar/b31/comunas.csv";
    private static final String MUESTRAS_FILE = "/home/pedro/despegar/clase24/src/test/java/com/despegar/b31/mapa_de_ruido_diurno.csv";

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void parseOfCSVShouldReturnAList() throws FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(MUESTRAS_FILE))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }

        assertNotNull(records);

        records.subList(1, records.size()).stream().map(record -> {
            String range = record.get(3).split(" ")[0];
            return new Muestra(record.get(1), record.get(2), Double.parseDouble(range));
        }).collect(Collectors.toList());
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
