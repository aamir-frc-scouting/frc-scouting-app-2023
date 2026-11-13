package com.example.a2023_scoutingapp.screenshots;

import android.view.LayoutInflater;
import android.view.View;

import app.cash.paparazzi.Paparazzi;

import com.example.a2023_scoutingapp.R;

import org.junit.Rule;
import org.junit.Test;

public class ScreenshotTest {

    @Rule
    public Paparazzi paparazzi = new Paparazzi();

    @Test
    public void main_pregame() {
        View view = LayoutInflater.from(paparazzi.getContext())
                .inflate(R.layout.activity_main, null);
        paparazzi.snapshot(view, "main");
    }

    @Test
    public void auto_screen() {
        View view = LayoutInflater.from(paparazzi.getContext())
                .inflate(R.layout.activity_auto, null);
        paparazzi.snapshot(view, "auto");
    }

    @Test
    public void tele_screen() {
        View view = LayoutInflater.from(paparazzi.getContext())
                .inflate(R.layout.activity_tele, null);
        paparazzi.snapshot(view, "tele");
    }

    @Test
    public void endgame_screen() {
        View view = LayoutInflater.from(paparazzi.getContext())
                .inflate(R.layout.activity_endgame, null);
        paparazzi.snapshot(view, "endgame");
    }

    @Test
    public void qr_screen() {
        View view = LayoutInflater.from(paparazzi.getContext())
                .inflate(R.layout.activity_qr, null);
        paparazzi.snapshot(view, "qr");
    }
}
