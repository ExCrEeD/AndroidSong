package com.example.audioservice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast


class AudioService : Service() {

    private var player: MediaPlayer? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        //TODO write your own code

        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        player?.setLooping(true)
        // start the player
        player?.start()
        Toast.makeText(this, "Service Started :) ", Toast.LENGTH_LONG).show()

        return Service.START_STICKY

    }


    override fun onBind(intent: Intent): IBinder? {

        throw UnsupportedOperationException("Not yet implemented")
    }


    override fun onDestroy() {
        super.onDestroy()
        // Stopping the player when service is destroyed
        player!!.stop()
        Toast.makeText(this, " :( Service Stopped!!!", Toast.LENGTH_LONG).show()
    }

}