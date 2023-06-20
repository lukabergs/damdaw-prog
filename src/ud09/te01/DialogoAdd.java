/******************************************************************************************************************

Nombre:   Luka Bergaretxe
Fecha:    2023/04/24
Modulo:   Programacion
UD:       09
Tarea:    E01

Descripcion de la clase:    Definicion de la clase DialogoAdd
                            Gestionara el dialogo correspondiente a la opcion de anadir Tweets

Enlace autoevaluacion: https://docs.google.com/document/d/164-_sJ7bYya-C-WXvYewalrNMgtPyoEXeU-mayK03ew/edit

*******************************************************************************************************************/

package v1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class DialogoAdd implements ActionListener {
    
// CONSTANTES

    // Titulos
    public static final String TITULO_DIALOGO = "Anadir un Tweet";
    public static final String TITULO_BOTON = "ANADIR";

    // Etiquetas
    public static final String[] ETIQUETAS = {
            "Usuario:",
            "Fecha y hora (aaaa-MM-ddThh:mm:ss):",
            "Mensaje:"
            };
    
    // Mensajes de error
    public static final String ERROR_CAMPOS_VACIOS = "Por favor, rellena todos los campos.";
    public static final String ERROR_FORMATO_FECHA_INCORRECTO = "Introduce una fecha y hora en formato (aaaa-mm-ddThh:mm:ss).";

    // Regex
    public static final String REGEX_FORMATO_FECHA = "[0-9]{4}-\\d{2}-\\d{2}T[0-9]{2}:\\d{2}:\\d{2}";

// ATRIBUTOS
    
    private JDialog dialogo;
    private TweetBD tweetBD;
    private JTextField[] campos = new JTextField[ETIQUETAS.length];
    private Verificador verificador;

// METODOS

    /**
     * Constructor de DialogoAdd.
     * @param tweetBD TweetBD sobre la que trabajamos.
     */
    public DialogoAdd(TweetBD tweetBD) {
        
        // Vincular esta instancia de dialogo con la TweetBD sobre la que trabajamos
        this.tweetBD = tweetBD;
        
        // Crear y configurar un JDialog
        this.dialogo = new JDialog();
        this.dialogo.setModal(true);
        this.dialogo.setLayout(new BorderLayout());
        
        // Crear el panel central para alojar las etiquetas y campos a rellenar
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(3, 2));

        // Crear las etiquetas y los campos a rellenar, y anadirlos al dialogo
        this.dialogo.add(BorderLayout.NORTH, new JLabel(TITULO_DIALOGO));
        for (int i = 0; i < ETIQUETAS.length; i++) {
            panelCentral.add(new JLabel(ETIQUETAS[i]));
            this.campos[i] = new JTextField();
            panelCentral.add(this.campos[i]);
        }
        this.dialogo.add(BorderLayout.CENTER, panelCentral);

        // Crear el boton con su respectivo listener y anadirlo al dialogo
        JButton boton = new JButton(TITULO_BOTON);
        this.dialogo.add(BorderLayout.SOUTH, boton);
        boton.addActionListener(this);

        // Anadir verificador
        this.verificador = new Verificador();

        // Seleccionar por defecto el boton para que se active al pulsar ENTER
        this.dialogo.getRootPane().setDefaultButton(boton);

        // Ajustar el tamano del dialogo segun los componentes presentes en el y hacerlo visible
        this.dialogo.pack();
        this.dialogo.setVisible(true);
    }

    /**
     * Metodo que comprueba si los campos del dialogo han sido rellenados.
     * @return boolean que indica si los campos del dialogo han sido rellenados o no
     */
    public boolean tieneCamposRellenados() {
        boolean res = !campos[0].getText().isEmpty();
        int i = 1;
        while (res && i < campos.length) {
            res &= !campos[i].getText().isEmpty();
            i++;
        }
        return res;
    }
    
    /**
     * Metodo que implementa la interfaz ActionListener
     *      para el boton de este dialogo.
     * @param evento Evento que dispara la ejecucion de este metodo.
     */
    @Override
    public void actionPerformed(ActionEvent evento) {

        // Identificar la opcion pulsada
        JButton boton = (JButton) evento.getSource();
        String texto = boton.getText();

        // Comprobar si los campos han sido rellenados y lanzar un aviso en caso contrario
        if (texto.equals(TITULO_BOTON)) {
            if (this.tieneCamposRellenados()) {
                if (verificador.verify(campos[1])) {
                    this.tweetBD.addTweet(campos[0].getText(), campos[1].getText(), campos[2].getText());
                    this.dialogo.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, ERROR_FORMATO_FECHA_INCORRECTO);
                }
            } else {
                JOptionPane.showMessageDialog(null, ERROR_CAMPOS_VACIOS);
            }
        }
    }

    /**
     * Clase interna que hereda de InputVerifier.
     * Se encarga de comprobar si el texto de un campo a rellenar del dialogo
     *      cumple con un formato determinado.
     */
    private class Verificador extends InputVerifier {
        
        /**
         * Metodo que encapsula la funcionalidad de comprobar si el texto de una
         *      campo a rellenar cumple con un formato determinado.
         * @param componente JComponent correspondiente al campo a comprobar.
         */
        @Override
        public boolean verify(JComponent componente) {
            boolean res = false;
            if (componente instanceof JTextField) {
                JTextField cajaTexto = (JTextField) componente;

                // Comprobar formato de la fecha: yyyy-MM-ddTHH:mm:ss
                Pattern formatoFecha = Pattern.compile(REGEX_FORMATO_FECHA);
                String fecha = cajaTexto.getText();
                Matcher comparaFormato = formatoFecha.matcher(fecha);

                // Si el formato no coincide, lanzar un aviso
                if (comparaFormato.matches()) {
                    res = true;
                }
            }
            return res;
        }
    }
}
