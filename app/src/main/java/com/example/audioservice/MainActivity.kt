package com.example.audioservice

import android.content.ContentResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI


class MainActivity : AppCompatActivity() {
    val songList: MutableList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var songUri:Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        var contentResolver:ContentResolver = getContentResolver();
        var songCursor: Cursor? = contentResolver.query(songUri,null,null,null,null)







        if (songCursor != null && songCursor.moveToFirst()) {
            val songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val songData = songCursor.getColumnIndex(MediaStore.Audio.Media.RELATIVE_PATH)

            do {
                val currentTitle = songCursor.getString(songTitle)
                val currentArtist = songCursor.getString(songArtist)
                songList.add(currentTitle + "\n" + currentArtist)
            } while (songCursor.moveToNext())
        }
        textView.setText(songList.toString())
    }

    public fun startServiceOnClick(view: View) {

        val intent = Intent(this, AudioService::class.java)
        startService(intent)

    }

     public fun stopServiceOnClick(view: View) {

        val intent = Intent(this, AudioService::class.java)
        stopService(intent)

     }
}
