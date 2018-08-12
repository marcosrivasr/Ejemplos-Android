package com.marivr.tiendita.Util;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by marivr on 17/09/2017.
 */

public class Util {

    public static String now(){
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

        Log.d("fecha", formateador.format(ahora));
        return formateador.format(ahora);
    }

    public static String getDecimalFormat(float number){
        return new DecimalFormat("$##,##0.00").format(number);
    }
}
