package com.rlds.filmesonline

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.rlds.filmesonline.databinding.ActivityListaFilmesBinding
import com.rlds.filmesonline.databinding.ActivityVideoBinding

class Video : AppCompatActivity() {
    private lateinit var binding:ActivityVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        val videoUrl = Uri.parse("https://firebasestorage.googleapis.com/v0/b/filmes-online-ec0fb.appspot.com/o/THE%20WITCHER%20_%20TRAILER%20FINAL%20_%20NETFLIX.mp4?alt=media&token=1a998aa4-b199-46d9-aa20-c9af18af04b9")
        val video = binding.video
        video.setMediaController(MediaController(this))
        video.setVideoURI(videoUrl)
        video.requestFocus()
        video.start()

    }
    
}