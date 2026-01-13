package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FitnessTracker extends JFrame {
    private ArrayList<Workout> workouts;
    private DefaultTableModel tableModel;
    private JTable table;
    private JLabel kalorienLabel;
    private JTextField nameField;
    private JTextField dauerField;
    private JTextField kalorienField;
    private JCheckBox krafttrainingBox;

    // Panels
    private JPanel eingabePanel;
    private JPanel anzeigePanel;


    public FitnessTracker() {
        workouts = new ArrayList<>();

        // Initialisierung der Beispieldaten
        initObjekte();

        setupGUI();
    }

    // Methode zur Initialisierung von mindestens 3 Objekten
    public void initObjekte() {
        workouts.add(new Workout("Bankdrücken", 45, 350, true));
        workouts.add(new Workout("Joggen", 30, 300, false));
        workouts.add(new Workout("Kniebeugen", 40, 400, true));
        workouts.add(new Workout("Radfahren", 60, 500, false));
        workouts.add(new Workout("Kreuzheben", 35, 450, true));
        workouts.add(new Workout("Schwimmen", 45, 480, false));
    }

    public void setupGUI() {
        setTitle("Fitness-Tracker");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        eingabePanel = erstelleEingabePanel();
        anzeigePanel = erstelleAnzeigePanel();

        add(eingabePanel);
    }

    public JPanel erstelleEingabePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Überschrift
        JLabel titel = new JLabel("Neues Workout hinzufügen");
        titel.setFont(new Font("Arial", Font.BOLD, 20));
        titel.setBounds(250, 30, 300, 30);
        panel.add(titel);

        // Name Label und Textfeld
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(200, 100, 150, 25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(350, 100, 250, 25);
        panel.add(nameField);

        // Dauer Label und Textfeld
        JLabel dauerLabel = new JLabel("Dauer (Minuten):");
        dauerLabel.setBounds(200, 150, 150, 25);
        panel.add(dauerLabel);

        dauerField = new JTextField();
        dauerField.setBounds(350, 150, 250, 25);
        panel.add(dauerField);

        // Kalorien Label und Textfeld
        JLabel kalorienLabelInput = new JLabel("Kalorien:");
        kalorienLabelInput.setBounds(200, 200, 150, 25);
        panel.add(kalorienLabelInput);

        kalorienField = new JTextField();
        kalorienField.setBounds(350, 200, 250, 25);
        panel.add(kalorienField);

        // Checkbox für Krafttraining
        krafttrainingBox = new JCheckBox("Ist Krafttraining");
        krafttrainingBox.setBounds(350, 250, 200, 25);
        panel.add(krafttrainingBox);

        // Button: Workout hinzufügen
        JButton hinzufuegenButton = new JButton("Workout hinzufügen");
        hinzufuegenButton.setBounds(250, 320, 150, 30);
        panel.add(hinzufuegenButton);

        hinzufuegenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                workoutHinzufuegen();
            }
        });

        // Button: Zeige Workouts an
        JButton zeigeButton = new JButton("Zeige Workouts an");
        zeigeButton.setBounds(420, 320, 150, 30);
        panel.add(zeigeButton);

        zeigeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zeigeWorkoutsAn();
            }
        });

        return panel;
    }

    public JPanel erstelleAnzeigePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Tabelle erstellen
        String[] spalten = {"Name", "Dauer (Min)", "Kalorien", "Krafttraining", "Intensiv"};
        tableModel = new DefaultTableModel(spalten, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);

        // Button-Panel oben
        JPanel buttonPanel = new JPanel();

        JButton zurueckButton = new JButton("Zurück");
        buttonPanel.add(zurueckButton);

        zurueckButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zeigeEingabeAn();
            }
        });

        JButton filterButton = new JButton("Nur Krafttraining");
        buttonPanel.add(filterButton);

        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterKrafttraining();
            }
        });

        JButton alleButton = new JButton("Alle anzeigen");
        buttonPanel.add(alleButton);

        alleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aktualisiereTabelle(workouts);
                berechneDurchschnitt(workouts);
            }
        });

        JButton sortDauerButton = new JButton("Sort. nach Dauer");
        buttonPanel.add(sortDauerButton);

        sortDauerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortiereNachDauer();
            }
        });

        JButton sortKalorienButton = new JButton("Sort. nach Kalorien");
        buttonPanel.add(sortKalorienButton);

        sortKalorienButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortiereNachKalorien();
            }
        });

        // Label für Durchschnitt
        kalorienLabel = new JLabel();
        kalorienLabel.setFont(new Font("Arial", Font.BOLD, 14));

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(kalorienLabel, BorderLayout.SOUTH);

        return panel;
    }

    public void workoutHinzufuegen() {
        String name = nameField.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name darf nicht leer sein!");
            return;
        }

        try {
            int dauer = Integer.parseInt(dauerField.getText());
            double kalorien = Double.parseDouble(kalorienField.getText());
            boolean istKraft = krafttrainingBox.isSelected();

            Workout neuesWorkout = new Workout(name, dauer, kalorien, istKraft);
            workouts.add(neuesWorkout);

            JOptionPane.showMessageDialog(this, "Workout hinzugefügt!");

            // Felder leeren
            nameField.setText("");
            dauerField.setText("");
            kalorienField.setText("");
            krafttrainingBox.setSelected(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nur Eingabe von Zahlen in den Feldern Kalorien und Dauer erlaubt!");
        }
    }

    public void zeigeWorkoutsAn() {
        getContentPane().removeAll();
        add(anzeigePanel);
        aktualisiereTabelle(workouts);
        berechneDurchschnitt(workouts);
        revalidate();
    }

    public void zeigeEingabeAn() {
        getContentPane().removeAll();
        add(eingabePanel);
        revalidate();
        repaint();
    }

    // Tabelle aktualisieren
    private void aktualisiereTabelle(ArrayList<Workout> liste) {
        tableModel.setRowCount(0); // Tabelle leeren

        for (Workout w : liste) {
            Object[] zeile = {
                    w.getName(),
                    w.getDauerMinuten(),
                    w.getKalorien(),
                    w.isIstKrafttraining() ? "Ja" : "Nein",
                    w.istIntensiv() ? "Ja" : "Nein"
            };
            tableModel.addRow(zeile);
        }
    }

    // Filter: nur Krafttraining
    private void filterKrafttraining() {
        ArrayList<Workout> gefiltert = new ArrayList<>();

        for (Workout w : workouts) {
            if (w.isIstKrafttraining()) {
                gefiltert.add(w);
            }
        }

        aktualisiereTabelle(gefiltert);
        berechneDurchschnitt(gefiltert);
    }

    // Berechnung: durchschnittliche Kalorien
    private void berechneDurchschnitt(ArrayList<Workout> liste) {
        if (liste.isEmpty()) {
            kalorienLabel.setText("Keine Workouts vorhanden");
            return;
        }

        double summe = 0;
        for (Workout w : liste) {
            summe += w.getKalorien();
        }

        double durchschnitt = summe / liste.size();
        kalorienLabel.setText(String.format("Durchschnittliche Kalorien: %.2f kcal (Anzahl Workouts: %d)",
                durchschnitt, liste.size()));
    }

    // Sortierung nach Dauer
    private void sortiereNachDauer() {
        Collections.sort(workouts, new Comparator<Workout>() {
            @Override
            public int compare(Workout w1, Workout w2) {
                return Integer.compare(w1.getDauerMinuten(), w2.getDauerMinuten());
            }
        });

        aktualisiereTabelle(workouts);
        berechneDurchschnitt(workouts);
    }

    // Sortierung nach Kalorien
    private void sortiereNachKalorien() {
        Collections.sort(workouts, new Comparator<Workout>() {
            @Override
            public int compare(Workout w1, Workout w2) {
                return Double.compare(w1.getKalorien(), w2.getKalorien());
            }
        });

        aktualisiereTabelle(workouts);
        berechneDurchschnitt(workouts);
    }

    // Main-Methode
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FitnessTracker tracker = new FitnessTracker();
                tracker.setVisible(true);
            }
        });
    }
}
