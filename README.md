Einleitung Die Java Stream API wurde in Java 8 eingeführt und bietet eine leistungsstarke, funktionale Methode zur Verarbeitung von Datenströmen. 
  Streams ermöglichen eine vereinfachte, lesbare und performante Datenverarbeitung ohne explizite Schleifen.

Ein Stream besteht aus drei Hauptbestandteilen:
  Quelle: Die Datenquelle, z. B. eine Liste oder ein Array.
  Operationen: Transformationen oder Filterungen der Daten.
  Terminal-Operationen: Beenden den Stream und liefern das Endergebnis.
  
Erklärung der Stream-Methoden:
  .stream() – Erstellt einen Stream aus der Liste.
  .filter(...) – Filtert Elemente anhand einer Bedingung.
  .collect(Collectors.toList()) – Sammelt die gefilterten Elemente in einer neuen Liste.

Vorteile von Streams:
  Bessere Lesbarkeit: Stream-Operationen sind vertikal organisiert und leichter verständlich.
  Flexibilität: Neue Operationen können leicht hinzugefügt oder umgeordnet werden.
  Effizienz: Streams bieten die Möglichkeit der Parallelverarbeitung.

Lambda-Ausdrücke und Methodenreferenzen Die Stream API verwendet oft Lambda-Ausdrücke:
  .filter(fruit -> fruit.length() >= 5)
Alternativ kann eine Methodenreferenz verwendet werden:
  .forEach(System.out::println);
  
Zusätzliche Operationen:
  map(...) – Verändert Elemente im Stream:
  fruits.stream().map(String::toUpperCase).forEach(System.out::println);
  distinct() – Entfernt Duplikate:
  fruits.stream().distinct().forEach(System.out::println);
  sorted() – Sortiert die Elemente:
  fruits.stream().sorted().forEach(System.out::println);

Fazit:
Die Stream API bietet eine elegante und effiziente Alternative zu herkömmlichen Schleifen.
  Sie verbessert die Lesbarkeit und Wartbarkeit von Code und ermöglicht eine leistungsstarke Datenverarbeitung.
  Es lohnt sich, Streams aktiv in den Entwicklungsalltag zu integrieren.

Lambda = Kurzform für eine anonyme Implementierung einer funktionalen Schnittstelle.
Syntax: (parameter) -> body
Du verwendest Lambdas überall dort, wo Methoden wie map, filter, forEach, sort, new Thread(...), etc. eine „Funktion“ brauchen.
Methoden-Referenzen (Type::method) sind nur eine Abkürzung für einfache Lambdas.
 
