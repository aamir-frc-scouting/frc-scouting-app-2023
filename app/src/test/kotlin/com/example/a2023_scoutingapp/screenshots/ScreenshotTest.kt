package com.example.a2023_scoutingapp.screenshots

import android.view.LayoutInflater
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.resources.ScreenOrientation
import com.example.a2023_scoutingapp.R
import org.junit.Rule
import org.junit.Test

class ScreenshotTest {

    // 10.1" tablet in landscape (2560x1600 px at xhdpi = 1280x800 dp)
    // This matches the fixed dp layouts Team 1730 designed for scouting tablets
    // Layout analysis: 550dp field image + 275dp form fields + margins = ~850dp min width
    private val tabletLandscape = DeviceConfig.NEXUS_10.copy(
        screenWidth = DeviceConfig.NEXUS_10.screenHeight,  // swap for landscape
        screenHeight = DeviceConfig.NEXUS_10.screenWidth,
        orientation = ScreenOrientation.LANDSCAPE
    )

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = tabletLandscape)

    @Test
    fun main_pregame() {
        val view = LayoutInflater.from(paparazzi.context)
            .inflate(R.layout.activity_main, null)
        paparazzi.snapshot(view, "main")
    }

    @Test
    fun auto_screen() {
        val view = LayoutInflater.from(paparazzi.context)
            .inflate(R.layout.activity_auto, null)
        paparazzi.snapshot(view, "auto")
    }

    @Test
    fun tele_screen() {
        val view = LayoutInflater.from(paparazzi.context)
            .inflate(R.layout.activity_tele, null)
        paparazzi.snapshot(view, "tele")
    }

    @Test
    fun endgame_screen() {
        val view = LayoutInflater.from(paparazzi.context)
            .inflate(R.layout.activity_endgame, null)
        paparazzi.snapshot(view, "endgame")
    }

    @Test
    fun qr_screen() {
        val view = LayoutInflater.from(paparazzi.context)
            .inflate(R.layout.activity_qr, null)
        paparazzi.snapshot(view, "qr")
    }
}
