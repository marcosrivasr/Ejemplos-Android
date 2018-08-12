package com.marivr.pendientes;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by marivr on 17/09/2017.
 */

public class Util {

    public static String now(){
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

        return formateador.format(ahora);
    }

    public static String sumarDia(int dias) {
        Calendar cal = new GregorianCalendar();
        Date ahora = new Date();
        cal.setTimeInMillis(ahora.getTime());
        cal.add(Calendar.DATE, dias);

        Date newDate = new Date(cal.getTimeInMillis());
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

        return formateador.format(newDate);
    }

    public static String getDecimalFormat(float number){
        return new DecimalFormat("$##,##0.00").format(number);
    }
}
