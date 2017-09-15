package com.marivr.priuebita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvMensaje;
    private Button botoncito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMensaje = (TextView) findViewById(R.id.tvMensaje);
        botoncito = (Button) findViewById(R.id.botoncito);

        botoncito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMensaje.setText("Holi!");
            }
        });
    }
}
