package com.globalsoftwaresupport.util;

/**
 *
 * @author 06551256M
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author 06551256M
 */
@Log4j2
public class UtilidadesFicheros {

    /**
     *
     * @param fichero nombre del fichero con path absoluto
     * @param contenido texto a escribir
     */
    public static void escribe(String nombrePdfAbsoluto, String contenido) throws IOException {

        FileWriter fichero = null;

        fichero = new FileWriter(nombrePdfAbsoluto);

        fichero.write(contenido);

        fichero.close();

        log.info(nombrePdfAbsoluto);

    }

    /**
     *
     * @param pathAbsoluto el path absoluto con la ubicación del fichero
     * @return Lee el fichero que será ascci con un objeto json y los retorna
     * como valor del método
     */
    public static String leeFichero(String pathAbsoluto) throws FileNotFoundException, IOException {
        String contenidoFichero = "";

        BufferedReader br = new BufferedReader(new FileReader(pathAbsoluto));

        String linea;
        while ((linea = br.readLine()) != null) {
            contenidoFichero += linea;
        }

        return contenidoFichero;
    }

    /**
     *
     * @param ruta
     * @return comprueba que exista el direcctorio en la ruta indicada. Si no
     * existe lo crea
     */
    public static boolean doValidaDiectorio(String ruta) {
        Boolean existe = false;
        try {
            File directorio = new File(ruta);
            if (!directorio.exists()) {
                if (directorio.mkdirs()) {
                    existe = true;
                    log.debug("Directorio creado " + ruta);
                } else {
                    existe = false;
                }
            } else {
                existe = true;
            }
        } catch (Exception ex) {
            log.error(Utilidades.getStackTrace(ex));
        }
        return existe;
    }

    public static boolean existeFichero(String ruta) {
        Boolean existe = false;
        File fichero = new File(ruta);
        if (fichero.exists()) {
            existe = true;
        }
        return existe;
    }
}
