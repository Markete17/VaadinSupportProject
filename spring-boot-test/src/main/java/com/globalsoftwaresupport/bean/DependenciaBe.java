package com.globalsoftwaresupport.bean;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.Data;

/**
 *
 * @author 06551256M
 */
@Data
public class DependenciaBe {

    public final static String TIPO_DATOS = "Dependencia de datos";
    public final static String TIPO_MOSTRAR = "Dependencia mostrar";
    public final static String TIPO_CALCULAR = "Dependencia calcular";
    public final static String TIPO_RANGO = "Dependencia calcular";

    private int id;
    private int idComponente;
    private String tipo;
    private ComponenteBe campoDependiente;
    private String condicion;
    private String valores;
    private String dependencias;

    public String getCampoDependienteString() {
        if (campoDependiente != null && campoDependiente.getDescripcion() != null) {
            return campoDependiente.getDescripcion();
        } else {
            return "";
        }
    }

    public ArrayList<String> gerTipos() {
        return new ArrayList<>(Arrays.asList(TIPO_DATOS, TIPO_MOSTRAR, TIPO_CALCULAR, TIPO_RANGO));
    }
}
