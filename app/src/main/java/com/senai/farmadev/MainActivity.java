package com.senai.farmadev;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

     TextInputEditText usuarioInput, senhaInput;
     Button buttonCad, buttonLog;
     DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioInput = findViewById(R.id.Usuario);
        senhaInput = findViewById(R.id.Senha);
        buttonCad = findViewById(R.id.ButtonCad);
        buttonLog = findViewById(R.id.ButtonLog);

        dbHelper = new DatabaseHelper(this);


        buttonCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro.class);
                startActivity(intent);
            }
        });


        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = usuarioInput.getText().toString().trim();
                String senha = senhaInput.getText().toString().trim();


                if (dbHelper.addUser(usuario, senha)) {
                    Toast.makeText(MainActivity.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, Produtos.class); //
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}