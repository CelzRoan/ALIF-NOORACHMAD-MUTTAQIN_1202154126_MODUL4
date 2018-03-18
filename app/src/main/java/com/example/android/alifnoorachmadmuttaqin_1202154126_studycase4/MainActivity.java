package com.example.android.alifnoorachmadmuttaqin_1202154126_studycase4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button list, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.mahasiswa);
        search = findViewById(R.id.gambar);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu1 = new Intent(MainActivity.this,ListNamaMahasiswa.class);
                startActivity(menu1);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu2 = new Intent(MainActivity.this,PencariGambar.class);
                startActivity(menu2);
            }
        });
    }
}
