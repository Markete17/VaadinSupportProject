package com.globalsoftwaresupport.bean;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.Data;

/**
 *
 * @author 06551256M
 */
@Data
public class CampoBe {

    public static final String TIPO_CONTENEDOR = "Contenedor";
    public static final String TIPO_INPUT = "Input";

    public final static String FECHA = "FECHA";
    public final static String TEXTO = "TEXTO";
    public final static String AREA = "AREA";
    public final static String COMBO = "COMBO";
    public final static String CHECK = "CHECK";
    public final static String RADIO = "RADIO";
    public final static String FILA = "FILA";
    public final static String COLUMNA = "COLUMNA";
    public final static String IMAGEN = "IMAGEN";

    public final static String ANCHO = "ANCHO";
    public final static String ALTO = "ALTO";
    public final static String VALOR = "VALOR";
    public final static String AYUDA = "AYUDA";
    public final static String ALINEACION = "ALINEACION";
    public final static String COLORFONDO = "COLORFONDO";

    public final static String ALINEACION_VERTICAL = "ALINEACION_VERTICAL";

    public static ArrayList<String> getListaNombre() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add(FECHA);
        lista.add(TEXTO);
        lista.add(AREA);
        lista.add(COMBO);
        lista.add(CHECK);
        lista.add(RADIO);
        lista.add(FILA);
        lista.add(COLUMNA);
        lista.add(IMAGEN);
        return lista;
    }
    public final static ArrayList<String> FECHA_ATTR = new ArrayList<>(Arrays.asList(ANCHO, VALOR));
    public final static ArrayList<String> TEXTO_ATTR = new ArrayList<>(Arrays.asList(ANCHO, VALOR, AYUDA));
    public final static ArrayList<String> AREA_ATTR = new ArrayList<>(Arrays.asList(ANCHO, ALTO, VALOR, AYUDA));
    public final static ArrayList<String> COMBO_ATTR = new ArrayList<>(Arrays.asList(ANCHO, VALOR, AYUDA));
    public final static ArrayList<String> CHECK_ATTR = new ArrayList<>(Arrays.asList(ANCHO, VALOR, AYUDA, ALINEACION));
    public final static ArrayList<String> RADIO_ATTR = new ArrayList<>(Arrays.asList(ANCHO, VALOR, AYUDA, ALINEACION));
    public final static ArrayList<String> FILA_ATTR = new ArrayList<>(Arrays.asList(ANCHO, ALTO, COLORFONDO));
    public final static ArrayList<String> COLUMNA_ATTR = new ArrayList<>(Arrays.asList(ANCHO, ALTO, COLORFONDO));
    public final static ArrayList<String> IMAGEN_ATTR = new ArrayList<>(Arrays.asList(ANCHO, ALTO));

    private String campo;
    private String tipo;
    private ArrayList<AtributoBe> atributos = new ArrayList<>();

    public CampoBe() {
    }
    public CampoBe(String campo, String tipo, ArrayList<String> atributoNombres) {
        this.campo = campo;
        this.tipo = tipo;
        for (String s : atributoNombres) {
            atributos.add(new AtributoBe(s, ""));
        }
    }


    public String getCampoString() {
        if (campo != null) {
            return campo;
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        if (campo != null) {
            return campo;
        } else {
            return "";
        }
    }

    /**
     *
     * @param nombre
     * @return
     */
    public static Boolean isImput(String nombre) {
        if (nombre.equals(FILA) || nombre.equals(COLUMNA)) {
            return true;
        } else {
            return false;
        }
    }
}
