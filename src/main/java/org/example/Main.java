package org.example;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;


public class Main {
    static void main() {

        List<Integer> list = new ArrayList<>();

        int max = 100;
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < max; i++) {
            list.add(random.nextInt(max));
        }


        List<Integer> collectedList = list.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * 2)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println();
        collectedList.forEach(System.out::println);

        int sum = collectedList.stream().reduce(0, Integer::sum);
        System.out.println();
        System.out.println(sum);


        // LISTE AUS EINER DATEI ERSTELLEN
        Path csvPath = Path.of("src", "main", "resources", "students.csv");
        try (Stream<String> lines = Files.lines(csvPath)) {
            List<Student> students = lines
                    .skip(1) // Header entfernen
                    .map(line -> { //
                        String[] parts = line.split(","); // splittet den String entsprechend dem Zeichen
                        try {
                            if (parts.length != 4) return null; // falls parts nicht vier Einträge hat, sondern mehr oder weniger gib mir null
                            String id = parts[0].trim();
                            String name = parts[1].trim();
                            String postalCode = parts[2].trim();
                            int age = Integer.parseInt(parts[3].trim());
                            return new Student(Integer.parseInt(id), name, Integer.parseInt(postalCode), age); // erstelle einen neuen Studenten
                        } catch (Exception e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull) // nimm nur die nicht nuller
                    .distinct()
                    .toList(); // packe bzw. gebe die neuen Studenten in eine Liste (zurück)


            students.forEach(System.out::println); // printe die neuen Studenten in die Konsole

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
