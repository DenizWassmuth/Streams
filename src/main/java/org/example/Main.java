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

/**
 *
 * Einleitung Die Java Stream API wurde in Java 8 eingeführt und bietet eine leistungsstarke,
 *  funktionale Methode zur Verarbeitung von Datenströmen. Streams ermöglichen eine vereinfachte, lesbare und performante Datenverarbeitung ohne explizite Schleifen.
 *
 * Grundlegender Aufbau eines Streams Ein Stream besteht aus drei Hauptbestandteilen:
 *
 * Quelle: Die Datenquelle, z. B. eine Liste oder ein Array.
 * Operationen: Transformationen oder Filterungen der Daten.
 * Terminal-Operationen: Beenden den Stream und liefern das Endergebnis.
 *
 * Erklärung der Stream-Methoden:
 *
 * .stream() – Erstellt einen Stream aus der Liste.
 * .filter(...) – Filtert Elemente anhand einer Bedingung.
 * .collect(Collectors.toList()) – Sammelt die gefilterten Elemente in einer neuen Liste.
 * Vorteile von Streams
 *
 * Bessere Lesbarkeit: Stream-Operationen sind vertikal organisiert und leichter verständlich.
 * Flexibilität: Neue Operationen können leicht hinzugefügt oder umgeordnet werden.
 * Effizienz: Streams bieten die Möglichkeit der Parallelverarbeitung.
 * Lambda-Ausdrücke und Methodenreferenzen Die Stream API verwendet oft Lambda-Ausdrücke:
 *
 * .filter(fruit -> fruit.length() >= 5)
 *
 * Alternativ kann eine Methodenreferenz verwendet werden:
 * .forEach(System.out::println);
 *
 * Zusätzliche Operationen
 * map(...) – Verändert Elemente im Stream:
 * fruits.stream().map(String::toUpperCase).forEach(System.out::println);
 * distinct() – Entfernt Duplikate:
 * fruits.stream().distinct().forEach(System.out::println);
 * sorted() – Sortiert die Elemente:
 * fruits.stream().sorted().forEach(System.out::println);
 * Fazit Die Stream API bietet eine elegante und effiziente Alternative zu herkömmlichen Schleifen.
 *  Sie verbessert die Lesbarkeit und Wartbarkeit von Code und ermöglicht eine leistungsstarke Datenverarbeitung.
 *  Es lohnt sich, Streams aktiv in den Entwicklungsalltag zu integrieren.
 *
 * Lambda = Kurzform für eine anonyme Implementierung einer funktionalen Schnittstelle.
 * Syntax: (parameter) -> body
 * Du verwendest Lambdas überall dort, wo Methoden wie map, filter, forEach, sort, new Thread(...), etc. eine „Funktion“ brauchen.
 * Methoden-Referenzen (Type::method) sind nur eine Abkürzung für einfache Lambdas.
 *
 *  */

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
        //.forEach(System.out::println);
        collectedList.forEach(System.out::println);

        System.out.println();

        int sum = collectedList.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        // Liste aus einer Datei erstellen

        Path csvPath = Path.of("src", "main", "resources", "students.csv");

        try (Stream<String> lines = Files.lines(csvPath)) {

            List<Student> students = lines
                    .skip(1) // Header entfernen
                    .map(line -> { //
                        String[] parts = line.split(","); // splittet den String entsprechend dem Zeichen
                        try {
                            if (parts.length != 4) return null; // falls parts nicht vier Einträge hat sondern mehr oder weniger gib mir null
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
