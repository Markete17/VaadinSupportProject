package com.globalsoftwaresupport.bean;

import java.util.ArrayList;
import java.util.HashMap;

import com.globalsoftwaresupport.dao.CamposFactory;
import com.globalsoftwaresupport.dao.FormularioDao;

import lombok.Data;

/**
 *
 * @author 06551256M
 */
@Data
public class ComponenteBe {

    private static final ComponenteBe FECHA_ATENCION = new ComponenteBe(CamposFactory.getCampo(CampoBe.FECHA, CampoBe.FECHA_ATTR),
             "Fecha de atención ", "LOINC", "88383-1");

    private static final ComponenteBe MOTIVO_ATENCION = new ComponenteBe(CamposFactory.getCampo(CampoBe.TEXTO, CampoBe.TEXTO_ATTR),
            "Motivo de atención ", "LOINC", "348484-1");

    private static final ComponenteBe ORIGEN_SOLICITUD = new ComponenteBe(CamposFactory.getCampo(CampoBe.COMBO, CampoBe.COMBO_ATTR),
            "Origen de atención ", "LOINC", "348484-1",             "Atención primaria,Atención Especilizada, Directa paciente, Otros");

    private static final ComponenteBe ANTECEDENTES = new ComponenteBe(CamposFactory.getCampo(CampoBe.TEXTO, CampoBe.TEXTO_ATTR),
            "Antecedentes ", "LOINC", "22222-1");

    private static final ComponenteBe ALERTAS = new ComponenteBe(CamposFactory.getCampo(CampoBe.TEXTO, CampoBe.TEXTO_ATTR),
            "Alertas  ", "LOINC", "4444-1");

    private static final ComponenteBe TRATAMIENTO_ACTUAL = new ComponenteBe(CamposFactory.getCampo(CampoBe.TEXTO, CampoBe.TEXTO_ATTR),
            "Tratamiento Actual  ", "LOINC", "6666-1");

    private static final ComponenteBe RESULTADOS_LABORATORIO = new ComponenteBe(CamposFactory.getCampo(CampoBe.AREA, CampoBe.AREA_ATTR),
            "Resultados Laboratorio ", "LOINC", "664444-1");

    private static final ComponenteBe TRATAMIENTO_FARMACOLÓGICO = new ComponenteBe(CamposFactory.getCampo(CampoBe.AREA, CampoBe.AREA_ATTR),
            "Tratamiento ", "LOINC", "664433-1");

    private static final ComponenteBe DIETA = new ComponenteBe(CamposFactory.getCampo(CampoBe.AREA, CampoBe.AREA_ATTR),
            "Dieta ", "LOINC", "664433-1");

    private int id;
    private int idFormulario;

    private CampoBe campo;
    private Integer orden;
    private String descripcion;
    private String code;
    private String codesystem;
    private ComponenteBe pertenece;
    private String valores;
    private HashMap<Integer, DependenciaBe> dependencias = new HashMap<>();

    public ComponenteBe(CampoBe campo, String descrip, String code, String codeSystem) {
        this.campo = campo;
        this.descripcion = descrip;
        this.code = code;
        this.codesystem = codeSystem;
    }

    public ComponenteBe(CampoBe campo, String descrip, String code, String codeSystem, String valores) {
        this.campo = campo;
        this.descripcion = descrip;
        this.code = code;
        this.codesystem = codeSystem;
        this.valores = valores;
    }

    public ComponenteBe() {
        this.campo = new CampoBe();
        this.orden = 1;

    }

    /**
     * *
     *
     * @return
     */
    public String getPerteneceString() {
        if (pertenece != null && pertenece.getDescripcion() != null) {
            return pertenece.descripcion;
        } else {
            return "";
        }
    }

    /**
     *
     * @return
     */
    public String getCampoString() {
        if (campo != null && campo.getCampo() != null) {
            return campo.getCampo();
        } else {
            return "";
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<ComponenteBe> getHijos() {
        FormularioBe formularioBe = new FormularioDao().getPorId(this.idFormulario);
        ArrayList<ComponenteBe> hijos = new FormularioDao().getHijos(formularioBe, this);
        return hijos;
    }

    /**
     *
     * @return
     */
    public static ArrayList<ComponenteBe> getPredefinidos() {
        ArrayList<ComponenteBe> lista = new ArrayList<>();
        lista.add(FECHA_ATENCION);
        lista.add(MOTIVO_ATENCION);
        lista.add(ORIGEN_SOLICITUD);
        lista.add(ANTECEDENTES);
        lista.add(ALERTAS);
        lista.add(TRATAMIENTO_ACTUAL);
        return lista;
    }

    public int getSiguienteIdDependencias() {
        int id = 0;
        for (Integer n : this.getDependencias().keySet()) {
            if (n >= id) {
                id = n;
            }
        }
        return id + 1;
    }
}
