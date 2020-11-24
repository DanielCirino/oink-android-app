package tech.devrocks.oink.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import tech.devrocks.oink.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configurarControles()
    }

    fun configurarControles(){
        configurarToolbar()

        card_menu_categorias.setOnClickListener {
            val intent = Intent(this, CategoriaActivity::class.java)
            startActivity(intent)
        }
        card_menu_conta_bancaria.setOnClickListener {
            val intent = Intent(this, ContasBancariasActivity::class.java)
            startActivity(intent)
        }

        card_menu_forma_pagamento.setOnClickListener {
            val intent = Intent(this, FormasPagamentoActivity::class.java)
            startActivity(intent)
        }

        card_menu_configurar_potes.setOnClickListener {
            val intent = Intent(this, PoteGastoActivity::class.java)
            startActivity(intent)
        }

        card_menu_registro_financeiro.setOnClickListener {
            val intent = Intent(this, RegistroFinanceiroActivity::class.java)
            startActivity(intent)
        }



    }

    private fun configurarToolbar() {
        if (Build.VERSION.SDK_INT >= 21) window.statusBarColor =
            ContextCompat.getColor(this,R.color.background)
    }
}