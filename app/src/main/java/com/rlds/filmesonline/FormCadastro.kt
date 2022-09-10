package com.rlds.filmesonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.rlds.filmesonline.databinding.ActivityFormCadastroBinding

class FormCadastro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        binding.btCadastrar.setOnClickListener {
            var email = binding.txtEmail.text.toString()
            var senha = binding.txtSenha.text.toString()
            if(email.isEmpty() || senha.isEmpty()){
                binding.erroMensagem.setText("Preencha todos os campos")

            }else{
                binding.erroMensagem.setText("")
                cadastrarUsuario()


            }
        }


    }
    private  fun  cadastrarUsuario(){
        val email = binding.txtEmail.text.toString()
        val senha = binding.txtSenha.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            email, senha
        ).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText( this, "Usuario cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                binding.txtEmail.setText("")
                binding.txtSenha.setText("")
                binding.erroMensagem.setText("")

            }
        }.addOnFailureListener {
            var erro = it
            when{
                erro is FirebaseAuthWeakPasswordException -> binding.erroMensagem.setText("Digite uma senha com minino 6 caracteres")
                erro is FirebaseAuthUserCollisionException -> binding.erroMensagem.setText("Conta já cadastrada!")
                erro is FirebaseNetworkException -> binding.erroMensagem.setText("Sem conecção coma Internet!")
                else -> binding.erroMensagem.setText("Erro ao cadastrar usuario!")
            }


        }


    }
}