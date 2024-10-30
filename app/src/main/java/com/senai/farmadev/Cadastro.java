package com.senai.farmadev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {
    EditText novoUsuario = findViewById(R.id.NovoUsuario);
    EditText novaSenha = findViewById(R.id.NovaSenha);
    Button buttonCad = findViewById(R.id.buttonCad);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        DatabaseHelper dbHelper = new DatabaseHelper(this);

        buttonCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = novoUsuario.getText().toString();
                String senha = novaSenha.getText().toString();

                // Tente adicionar o usuário ao banco de dados
                if (dbHelper.addUser(usuario, senha)) {
                    Toast.makeText(Cadastro.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish(); // Retornar à MainActivity
                } else {
                    Toast.makeText(Cadastro.this, "Usuário já existe!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }
