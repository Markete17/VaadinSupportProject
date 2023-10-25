package com.globalsoftwaresupport.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 *
 * @author 06551256M
 */
public class Utilidades {

    public static String getStackTrace(Exception e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }

    public static String getStackTrace(Throwable e) {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        e.printStackTrace(pWriter);
        return sWriter.toString();
    }

    public String getFicheroRutaAbsoluta(String nombre) {

        return ConstantesFile.PATHABSDIRECTORIO + nombre;
    }

    public static ArrayList<String> getLista(String cadena) {

        ArrayList<String> lista = new ArrayList<>();
        if (cadena.contains(",")) {
            String[] valores = cadena.split(",");
            for (String valor : valores) {
                lista.add(valor);
            }

        }
        return lista;
    }

    public static boolean esPar(int numero) {
        if (numero % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
