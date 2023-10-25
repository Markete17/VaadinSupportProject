/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.globalsoftwaresupport.dao;

import java.util.ArrayList;

import com.globalsoftwaresupport.bean.AtributoBe;
import com.globalsoftwaresupport.bean.CampoBe;

/**
 *
 * @author 06551256M
 */
public class CamposFactory {

    public static CampoBe getCampo(String tipo, ArrayList<String> atributoNombre) {
        CampoBe campoBe = new CampoBe();
        campoBe.setCampo(tipo);
        for (String s : atributoNombre) {
            campoBe.getAtributos().add(new AtributoBe(s, ""));
        }
        switch (tipo) {
            case CampoBe.AREA:
                campoBe.setTipo(CampoBe.TIPO_INPUT);
                break;
            case CampoBe.CHECK:
                campoBe.setTipo(CampoBe.TIPO_INPUT);
                break;
            case CampoBe.COLUMNA:
                campoBe.setTipo(CampoBe.TIPO_CONTENEDOR);
                break;
            case CampoBe.COMBO:
                campoBe.setTipo(CampoBe.TIPO_INPUT);
                break;
            case CampoBe.FECHA:
                campoBe.setTipo(CampoBe.TIPO_INPUT);
                break;
            case CampoBe.FILA:
                campoBe.setTipo(CampoBe.TIPO_CONTENEDOR);
                break;
            case CampoBe.TEXTO:
                campoBe.setTipo(CampoBe.TIPO_INPUT);
                break;
            case CampoBe.RADIO:
                campoBe.setTipo(CampoBe.TIPO_INPUT);
                break;
            default:
                campoBe.setTipo(CampoBe.TIPO_INPUT);
        }
        return campoBe;
    }



}
