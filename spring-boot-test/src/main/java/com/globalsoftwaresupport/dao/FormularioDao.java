package com.globalsoftwaresupport.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.globalsoftwaresupport.bean.CampoBe;
import com.globalsoftwaresupport.bean.ComponenteBe;
import com.globalsoftwaresupport.bean.FormularioBe;
import com.globalsoftwaresupport.spring.MainView;
import com.globalsoftwaresupport.util.ConstantesFile;
import com.globalsoftwaresupport.util.Utilidades;
import com.globalsoftwaresupport.util.UtilidadesFicheros;
import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;

/**
 *
 * @author 06551256M
 */
@Log4j2
public class FormularioDao {

    public Boolean haGrabado(FormularioBe formularioBe) {
        Boolean grabado = false;
        try {
            String datos = new Gson().toJson(formularioBe);
            String path = ConstantesFile.PATHABSDIRECTORIO + System.getProperty("file.separator") + formularioBe.getNombre() + ConstantesFile.EXTENSIONJSON;
            UtilidadesFicheros.escribe(path, datos);
            grabado = true;
        } catch (IOException e) {
            log.error(Utilidades.getStackTrace(e));
        }
        return grabado;
    }

    public Boolean haGrabado(Collection<FormularioBe> formularioBes) {
        Boolean grabado = false;
        try {
            String datos = new Gson().toJson(formularioBes);
            String path = ConstantesFile.PATHABSDIRECTORIO + System.getProperty("file.separator") + "Formularios" + ConstantesFile.EXTENSIONJSON;
            UtilidadesFicheros.escribe(path, datos);
            grabado = true;
        } catch (IOException e) {
            log.error(Utilidades.getStackTrace(e));
        }
        return grabado;
    }


    public Boolean haGrabado(List<FormularioBe> formularioBes) {
        Boolean grabado = false;
        try {
            String datos = new Gson().toJson(formularioBes);
            String path = ConstantesFile.PATHABSDIRECTORIO + System.getProperty("file.separator") + "Formularios" + ConstantesFile.EXTENSIONJSON;
            UtilidadesFicheros.escribe(path, datos);
            grabado = true;
        } catch (IOException e) {
            log.error(Utilidades.getStackTrace(e));
        }
        return grabado;
    }

    /**
     *
     * @param formularioBe
     * @return
     */
    public ArrayList< ComponenteBe> getPadres(FormularioBe formularioBe) {
        ArrayList<ComponenteBe> lista = new ArrayList<>();
        for (ComponenteBe co : formularioBe.getComponentes().values()) {
            if (co.getCampo().getTipo().equals(CampoBe.TIPO_CONTENEDOR)) {
                lista.add(co);
            }
        }
        return lista;
    }

    /**
     *
     * @param formularioBe
     * @param componenteBe
     * @return
     */
    public ArrayList< ComponenteBe> getHijos(FormularioBe formularioBe, ComponenteBe componenteBe) {
        ArrayList<ComponenteBe> lista = new ArrayList<>();
        for (ComponenteBe co : formularioBe.getComponentes().values()) {
            if (co != null && co.getPertenece() != null && co.getPertenece().getId() != 0) {
                if (co.getPertenece().getId() == componenteBe.getId()) {
                    lista.add(co);
                }
            }
        }
        return lista;
    }

    /**
     *
     * @param formularioBe
     * @return
     */

    public FormularioBe getPorId(Integer id) {
        return MainView.FORMULARIOS.get(id);
    }

    public FormularioBe getPorNobre(String nombre) {
        FormularioBe formularioBe = null;
        for (FormularioBe f : MainView.FORMULARIOS.values()) {
            if (f.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                formularioBe = f;
            }
        }
        return formularioBe;
    }

    public int getSiguienteIdFormulario() {
        Collection<Integer> numeros = MainView.FORMULARIOS.keySet();

        int mayor = 0;
        for (int n : numeros) {
            if (n >= mayor) {
                mayor = n;
            }
        }
        mayor++;
        return (mayor);
    }

}
