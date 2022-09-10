package com.rlds.filmesonline

import Adapter.FilmesAdapter
import Model.addFilmes
import OnClick.OnItemClickListener
import OnClick.addOnItemClickListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.rlds.filmesonline.databinding.ActivityListaFilmesBinding

class ListaFilmes : AppCompatActivity() {
    private lateinit var binding:ActivityListaFilmesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        val recycler_filmes = binding.recyclerViewFilmes
        recycler_filmes.adapter  = FilmesAdapter(addFilmes())
        recycler_filmes.layoutManager = GridLayoutManager(applicationContext,3)

        recycler_filmes.addOnItemClickListener(object:OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {
                when{
                    position == 0 -> DetalhesFilme()
                }
            }
        })

    }
    fun DetalhesFilme(){
        val intent = Intent(this, DetahesFilmes::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu,menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_sair ->{
                FirebaseAuth.getInstance().signOut()
                voltarTelaTelaLogin()


            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun voltarTelaTelaLogin(){
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }

}