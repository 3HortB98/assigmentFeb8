package com.example.fromapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        TextView tvDetails = findViewById(R.id.tvDetails);


        String age = getIntent().getStringExtra("Age");
        String name = readDataFromFile();
        String email = readFromSharedPreference();
        String combo = "Name: " + name +"\n" +
                    "Email: " + email  + "\n" +
                    "Age: " + age;
        tvDetails.setText(combo);
    }
    private String readDataFromFile(){
        File file = new File(getFilesDir(), "nameFile.txt");
        int size = (int) file.length();
        byte[] contents = new byte[size];
        try(FileInputStream fileInputStream = new FileInputStream(file)){
            fileInputStream.read(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(contents);
    }

    private String readFromSharedPreference(){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPeference", Context.MODE_PRIVATE);
        String email =  sharedPreferences.getString("email", "");
        return email;
    }
}

