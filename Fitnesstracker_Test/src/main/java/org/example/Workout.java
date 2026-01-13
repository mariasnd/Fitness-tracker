package org.example;

// Klasse Workout
class Workout {
    private String name;
    private int dauerMinuten;
    private int kalorien;
    private boolean istKrafttraining;

    // Konstruktor
    public Workout(String name, int dauerMinuten, int kalorien, boolean istKrafttraining) {
        this.name = name;
        this.dauerMinuten = dauerMinuten;
        this.kalorien = kalorien;
        this.istKrafttraining = istKrafttraining;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDauerMinuten(int dauerMinuten) {
        this.dauerMinuten = dauerMinuten;
    }

    public void setKalorien(int kalorien) {
        this.kalorien = kalorien;
    }
    public void setistKrafttraining(boolean istKrafttraining) {
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

    // Methode: prüft ob Workout intensiv ist (mehr als 250 Kalorien)
    public boolean istIntensiv() {

        return kalorien > 250;
    }
}
