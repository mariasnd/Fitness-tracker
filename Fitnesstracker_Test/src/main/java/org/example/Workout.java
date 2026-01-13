package org.example;

// Klasse Workout
class Workout {
    private String name;
    private int dauerMinuten;
    private double kalorien;
    private boolean istKrafttraining;

    // Konstruktor
    public Workout(String name, int dauerMinuten, double kalorien, boolean istKrafttraining) {
        this.name = name;
        this.dauerMinuten = dauerMinuten;
        this.kalorien = kalorien;
        this.istKrafttraining = istKrafttraining;
    }

    // Getter-Methoden
    public String getName() {
        return name;
    }

    public int getDauerMinuten() {
        return dauerMinuten;
    }

    public double getKalorien() {
        return kalorien;
    }

    public boolean isIstKrafttraining() {
        return istKrafttraining;
    }

    // Methode: prüft ob Workout intensiv ist (mehr als 400 Kalorien)
    public boolean istIntensiv() {
        return kalorien > 250;
    }
}
