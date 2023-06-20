/******************************************************************************************************************

Nombre:   Luka Bergaretxe
Fecha:    2023/04/24
Modulo:   Programacion
UD:       09
Tarea:    E01

Descripcion de la clase:    Definicion de la clase DialogoBorrar
                            Gestionara el dialogo correspondiente a la opcion de borrar Tweets

Enlace autoevaluacion: https://docs.google.com/document/d/164-_sJ7bYya-C-WXvYewalrNMgtPyoEXeU-mayK03ew/edit

*******************************************************************************************************************/

package v1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogoBorrar implements ActionListener {

// CONSTANTES

    // Titulos
    public static final String TITULO_DIALOGO_PRINCIPAL = "Borrar todos los tweets de un usuario";
    public static final String TITULO_DIALOGO_CONFIRMACION = "Confirmacion";
    public static final String TITULO_BOTON = "BORRAR";

    // Mensajes
    public static final String MENSAJE_DIALOGO_CONFIRMACION = "¿Realmente quieres borrar los tweets de %s?";
    public static final String MENSAJE_FINAL = "<html>Se han borrado <i>%d</i> mensajes</html>";

    // Etiquetas
    public static final String ETIQUETA = "Usuario:";

// ATRIBUTOS
    
    private JDialog dialogo;
    private TweetBD tweetBD;
    private JTextField campoUsuario;

// METODOS

    /**
     * Constructor de DialogoBorrar.
     * @param tweetBD TweetBD sobre la que trabajamos.
     */
    public DialogoBorrar(TweetBD tweetBD) {
        
        // Vincular esta instancia de dialogo con la TweetBD sobre la que trabajamos
        this.tweetBD = tweetBD;
        
        // Crear y configurar un JDialog
        this.dialogo = new JDialog();
        this.dialogo.setModal(true);
        this.dialogo.setLayout(new BorderLayout());
        
        // Crear el panel central para alojar las etiquetas y campos a rellenar
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(1, 2));

        // Crear las etiquetas y los campos a rellenar, y anadirlos al dialogo
        this.dialogo.add(BorderLayout.NORTH, new JLabel(TITULO_DIALOGO_PRINCIPAL));
        panelCentral.add(new JLabel(ETIQUETA));
        this.campoUsuario = new JTextField();
        panelCentral.add(this.campoUsuario);
        this.dialogo.add(BorderLayout.CENTER, panelCentral);

        // Crear el boton con su respectivo listener y anadirlo al dialogo
        JButton boton = new JButton(TITULO_BOTON);
        this.dialogo.add(BorderLayout.SOUTH, boton);
        boton.addActionListener(this);

        // Seleccionar por defecto el boton para que se active al pulsar ENTER
        this.dialogo.getRootPane().setDefaultButton(boton);

        // Ajustar el tamano del dialogo segun los componentes presentes en el y hacerlo visible
        this.dialogo.pack();
        this.dialogo.setVisible(true);
    }
    
    /**
     * Metodo que encapsula la funcionalidad de invocar un dialogo de confirmacion
     *      antes de borrar los Tweets de un determinado usuario.
     * @return int correspondiente a la opcion escogida.
     */
    public int dialogoConfirmacion() {
        return JOptionPane.showConfirmDialog(
            null,
            String.format(
                MENSAJE_DIALOGO_CONFIRMACION,
                this.campoUsuario.getText()
            ),
            TITULO_DIALOGO_CONFIRMACION,
            JOptionPane.YES_NO_OPTION
        );
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

        // Solicitar al usuario confirmacion antes de proceder al borrado de Tweets
        if (texto.equals(TITULO_BOTON)) {
            if (this.dialogoConfirmacion() == JOptionPane.YES_OPTION) {

                // Borrar los mensajes
                int tweetsBorrados = this.tweetBD.borrarTweets(this.campoUsuario.getText());

                // Informar al usuario de que los mensajes se han borrado
                Font font = new Font("Comic Sans MS", Font.PLAIN, 64);
                Color color = Color.MAGENTA;
                JLabel mensajeBorrados = new JLabel(String.format(MENSAJE_FINAL, tweetsBorrados));
                mensajeBorrados.setFont(font);
                mensajeBorrados.setForeground(color);
                JOptionPane.showMessageDialog(
                    null,
                    mensajeBorrados
                );
                this.dialogo.dispose();
            }
        }
    }
}