package com.globalsoftwaresupport.bean;

import java.util.HashMap;

import com.globalsoftwaresupport.dao.CamposFactory;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author 06551256M
 */
@Data
@AllArgsConstructor
public class FormularioBe {

    public static final String CON_IMPRESO = "Tiene impreso asociado";
    public static final String SIN_IMPRESO = "Sin impreso asociado";

    private Integer id;
    private String nombre;
    private HashMap<Integer, ComponenteBe> componentes = new HashMap<>();
    private String tieneImpreso;

    /**
     *
     * @param id
     * @param nombre
     */
    public FormularioBe(int id, String nombre) {
        this.nombre = nombre;
        this.id = id;
        this.tieneImpreso = SIN_IMPRESO;
        // por defecto añade la fila 1
        ComponenteBe c1 = new ComponenteBe();
        c1.setCampo(CamposFactory.getCampo(CampoBe.FILA, CampoBe.FILA_ATTR));
        c1.setDescripcion("Fila1");
        c1.setIdFormulario(this.getId());
        c1.setIdFormulario(id);
        c1.setId(1);
        this.getComponentes().put(c1.getId(), c1);
    }

    /**
     *
     * @param id
     */
    public FormularioBe(Integer id) {
        this.tieneImpreso = SIN_IMPRESO;
        this.id = id;
        ComponenteBe c1 = new ComponenteBe();
        c1.setCampo(CamposFactory.getCampo(CampoBe.FILA, CampoBe.FILA_ATTR));;
        c1.setDescripcion("Fila1");
        c1.setIdFormulario(id);
        c1.setId(1);
        this.getComponentes().put(c1.getId(), c1);
    }

    /**
     *
     * @return un estring con el nombre para el combo para que no de error null
     * al construir el label
     */
    public String getNombreCombo() {
        if (nombre != null) {
            return nombre;
        } else {
            return "";
        }
    }

    /**
     *
     * @return el id siguiente del componente del formularios
     */
    public int getSiguienteIdComponente() {
        int id = 0;
        for (ComponenteBe co : this.getComponentes().values()) {
            if (co.getId() > id) {
                id = co.getId();
            }
        }
        return id + 1;
    }
}
