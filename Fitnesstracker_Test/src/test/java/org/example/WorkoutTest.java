package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;


class WorkoutTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        Workout w= new Workout("laufband",30, 100, false);

        assertEquals("laufband", w.getName());

    }

    @Test
    void getDauerMinuten() {
        Workout w= new Workout("laufband",30, 100, false);
        assertEquals(30, w.getDauerMinuten());
    }

    @Test
    void getKalorien() {
        Workout w= new Workout("laufband",30, 100, false);
        assertEquals(100, w.getKalorien());
    }

    @Test
    void isIstKrafttraining() {
        Workout w= new Workout("laufband",30, 100, false);
        assertEquals(false, w.isIstKrafttraining());
    }

    @Test
    void istIntensiv() {
        Workout w= new Workout("laufband",30, 100, false);
        assertEquals(false,w.istIntensiv());
    }
}