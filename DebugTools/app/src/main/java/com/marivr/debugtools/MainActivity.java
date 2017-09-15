package com.marivr.debugtools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcularPuestos();
        int n = getTacosDesperdiciados();
        calcularTacosTotales();
    }

    private void calcularPuestos(){
        int x = 2;
        int y = 5;
        int z = x * y;
    }

    private int getTacosDesperdiciados(){
        return 0;
    }


    private void calcularTacosTotales(){
        int a = 2;
        int b = 4;
        int c = a + b;
    }
}
