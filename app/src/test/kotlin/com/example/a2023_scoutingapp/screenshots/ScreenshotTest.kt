package com.example.a2023_scoutingapp.screenshots

import android.view.LayoutInflater
import android.widget.ImageView
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.resources.ScreenOrientation
import com.example.a2023_scoutingapp.R
import com.example.qrgenearator.QRGContents
import com.example.qrgenearator.QRGEncoder
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
        
        // Generate a realistic QR code using the same method as the app
        // Sample payload: fake team 99901, scout "Demo Scout", realistic match data
        val sampleJson = buildSamplePayload()
        
        // Use the app's QRGEncoder with the same settings as QrActivity
        val qrgEncoder = QRGEncoder(sampleJson, null, QRGContents.Type.TEXT, 200)
        
        // Match the app's color scheme: backgroundGray for "black", testYellow for "white"
        val backgroundGray = 0xFF2b2b2b.toInt()
        val testYellow = 0xFFe8bf6a.toInt()
        qrgEncoder.setColorBlack(backgroundGray)
        qrgEncoder.setColorWhite(testYellow)
        
        val qrBitmap = qrgEncoder.bitmap
        val qrImageView = view.findViewById<ImageView>(R.id.qrImageView)
        qrImageView.setImageBitmap(qrBitmap)
        
        paparazzi.snapshot(view, "qr")
    }
    
    /**
     * Builds a sample JSON payload in the exact format RecordsActivity.Info.CreateJSON() produces.
     * Uses fake team 99901 and scout "Demo Scout" with realistic match data.
     */
    private fun buildSamplePayload(): String {
        return buildString {
            append("{\"ScoName\": \"Demo Scout\",")
            append("\"MNum\": \"42\",")
            append("\"TNum\": \"99901\",")
            append("\"RPos\": \"1\",")           // ChargeStation position
            append("\"PLoad\": \"1\",")          // Cone preloaded
            append("\"Mob\": \"1\",")            // Mobility achieved
            append("\"AGrid\": \"1\",")          // Coop grid
            append("\"ATop\": \"2\",")           // 2 top scores in auto
            append("\"AMid\": \"1\",")           // 1 mid score in auto
            append("\"ABot\": \"0\",")           // 0 bottom scores
            append("\"ACom\": \"Clean auto path\",")
            append("\"ACharStat\": \"0\",")      // Engaged in auto
            append("\"Died\": \"0\",")
            append("\"RStart\": \"0\",")
            append("\"Broke\": \"0\",")
            append("\"DefPlay\": \"1\",")        // Played defense
            append("\"DefFace\": \"0\",")
            append("\"Fell\": \"0\",")
            append("\"Righted\": \"0\",")
            append("\"Cones\": \"1\",")          // Scored cones
            append("\"Cubes\": \"1\",")          // Scored cubes
            append("\"GroGrab\": \"1\",")        // Ground pickup
            append("\"SubGrab\": \"1\",")        // Substation pickup
            append("\"ShGrab\": \"0\",")
            append("\"TTop\": \"5\",")           // 5 top scores in tele
            append("\"TMid\": \"3\",")           // 3 mid scores
            append("\"TBot\": \"2\",")           // 2 bottom scores
            append("\"GPiece\": \"10\",")        // 10 game pieces picked
            append("\"TCom\": \"Fast cycles\",")
            append("\"ECharSta\": \"0\",")       // Engaged in endgame
            append("\"ECom\": \"Balanced with alliance\",}")
        }
    }
}
