package com.example.fromapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName;
    EditText etEmail;
    EditText etAge;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etEmail = findViewById(R.id.etEmail);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                handleBtnSave();
                break;
        }
    }

    private void handleBtnSave(){
        String age= etAge.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPeference",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String email = etEmail.getText().toString();
        editor.putString("email",email);
        editor.apply();
        writeToFile();
        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
        intent.putExtra("Age",age);
        startActivity(intent);
    }

    private void writeToFile(){
        File file = new File(getFilesDir(),"nameFile.txt");
        try (FileOutputStream fileOutputStream = openFileOutput("nameFile.txt", Context.MODE_PRIVATE)){

            fileOutputStream.write(etName.getText().toString().getBytes());

        } catch (IOException ioException){
            Toast.makeText(this,"File Not Found", Toast.LENGTH_SHORT).show();
            ioException.printStackTrace();
        }

    }




}
