Projektbeschreibung:
Anwendung, die es ermöglicht, Workouts zu erfassen, anzuzeigen & zu filtern.
Verwaltung von Workouts -> folgende Informationen in GUI gespeichert werden: Name, Dauer (in Minuten), Kalorienverbrauch, Krafttraining (ja/nein)
GUI bietet auch: Anzeige aller Workouts in einer Tabelle, Filterfunktion für Krafttraining, Sorteriung nach Dauer oder Kalorien, Kennzeichnung intensiver Workouts (Kalorien > 250), Berechnung durchschnittlicher Kalorienverbrauch
----
Projektstruktur:
src/
└── org.example/
    ├── FitnessTracker.java   // Hauptprogramm + GUI + Logik
    ├── Workout.java          // Modellklasse für Workouts
    └── WorkoutTest.java      // JUnit-Tests für die Modellklasse
    
Workout.java: repäsentiert einzelnes Workout & enthält: 
- Attribute: Name, Dauer, Kalorien, Krafttraining
- Getter/Setter
- Methode istIntensiv() -> true, wenn Kalorien > 250

FitnessTracker.java: zentrale Klasse des Projekts & enthält:
- GUI-Elemente -> Eingabemaske für neue Workouts, Tabellenansicht, Buttons, Anzeige durchschnittlicher Kalorienverbrauch
- Logik -> Speichern der Workouts in einer ArrayList<Workout>, Validierung der Eingaben, Sortier- & Filterfunktion, Aktualisierung Tabelle, Umschalten zwischen Eingabe- & Anzeigeansicht

WorkoutTest.java: JUnit-Testklasse zur Überprüfung der wichtigsten Methoden der Workout-Klasse:
- getName()
- getDauerMinuten()
- getKalorien()
- isIstKrafttraining()
- istIntensiv()
-> Sicherstellung, dass Datenmodell korrekt funktioniert
---
Nutzung des Programms: 
1. Starrt der Anwendung: beim Start erscheint die Eingabemaske
2. Neues Workout hinzufügen -> Nutzer gibt ein: Name, Dauer, Kalorien, Checkbox: Krafttraining; "Workout hinzufügen" -> Eintrag wird gespeichert
3. Workouts anzeigen: "Zeige Workouts an" -> Tabellenansicht mit Filterung und Sortierung sowie automatischer Berechnung der Kalorien; "Zurück" -> Eingabemaske
