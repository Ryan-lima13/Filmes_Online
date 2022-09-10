package com.rlds.filmesonline

import Adapter.FilmesAdapter
import Model.addFilmes
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.rlds.filmesonline.databinding.ActivityDetahesFilmesBinding

class DetahesFilmes : AppCompatActivity() {
    private  lateinit var binding: ActivityDetahesFilmesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetahesFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        val toolbar = binding.toolbarDetalhes
        toolbar.setNavigationIcon(R.drawable.ic_voltar)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, ListaFilmes::class.java)
            startActivity(intent)
            finish()
        }
        val recycler_outros_filmes = binding.recyclerOutrosFilmes
        recycler_outros_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_outros_filmes.layoutManager = GridLayoutManager(applicationContext,3)

    }
}