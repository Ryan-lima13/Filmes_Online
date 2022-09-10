package com.rlds.filmesonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.rlds.filmesonline.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {
    private lateinit var binding:ActivityFormLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        // abrir tela para fazer cadastro susuario
        binding.txtTelaCadastro.setOnClickListener {
            val intent = Intent(this,FormCadastro::class.java)
            startActivity(intent)
        }
        // fazer login usuario
        binding.btEntrar.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val senha = binding.txtSenha.text.toString()
            val mensagem = binding.erroMensagem
            if(email.isEmpty() || senha.isEmpty()){
                mensagem.setText("Preencha todos os campos")

            }else{
                mensagem.setText("")
                // fazer login
                autenticarUsuarios()
            }
        }

    }
    private fun autenticarUsuarios(){
        val email = binding.txtEmail.text.toString()
        val senha = binding.txtSenha.text.toString()
        val mensagem = binding.erroMensagem
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            email, senha
        ).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                abrirTelaFilme()
            }
        }.addOnFailureListener {
            mensagem.setText("Erro ao fazer Login!")
        }

    }
    private  fun abrirTelaFilme(){
        val intent = Intent(this, ListaFilmes::class.java)
        startActivity(intent)
        finish()
    }
}