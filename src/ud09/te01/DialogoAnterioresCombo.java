/******************************************************************************************************************

Nombre:   Luka Bergaretxe
Fecha:    2023/04/24
Modulo:   Programacion
UD:       09
Tarea:    E01

Descripcion de la clase:    Definicion de la clase DialogoAnterioresCombo
                            Gestionara el dialogo correspondiente a la opcion de mostrar Tweets anteriores
                                a un determinado Tweet.

Enlace autoevaluacion: https://docs.google.com/document/d/164-_sJ7bYya-C-WXvYewalrNMgtPyoEXeU-mayK03ew/edit

*******************************************************************************************************************/

package v1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogoAnterioresCombo implements ActionListener {
    
// CONSTANTES

    // Titulos
    public static final String TITULO_DIALOGO = "Mostrar todos los tweets mas antiguos al elegido";
    public static final String TITULO_BOTON = "BUSCAR";

    // Etiquetas
    public static final String ETIQUETA = "Elige un tweet:";

// ATRIBUTOS
    
    private JDialog dialogo;
    private TweetBD tweetBD;
    private JComboBox<Tweet> tweets;
    protected TweetBD tweetsAnteriores;

// METODOS

    /**
     * Constructor de DialogoAnterioresCombo.
     * @param tweetBD TweetBD sobre la que trabajamos.
     */
    public DialogoAnterioresCombo(TweetBD tweetBD) {
        
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
        this.dialogo.add(BorderLayout.NORTH, new JLabel(TITULO_DIALOGO));
        panelCentral.add(new JLabel(ETIQUETA));
        this.tweets = new JComboBox<Tweet>(this.tweetBD.getArrayTweets());
        panelCentral.add(this.tweets);
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
     * Metodo que implementa la interfaz ActionListener
     *      para el boton de este dialogo.
     * @param evento Evento que dispara la ejecucion de este metodo.
     */
    @Override
    public void actionPerformed(ActionEvent evento) {

        // Identificar la opcion pulsada
        JButton boton = (JButton) evento.getSource(); // Realmente hace falta comprobar?
        String texto = boton.getText();

        // Mostrar los Tweets anteriores al seleccionado en el cuadro de texto del frame principal
        if (texto.equals(TITULO_BOTON)) {
            Tweet tweetSeleccionado = (Tweet) this.tweets.getSelectedItem();
            this.tweetsAnteriores = this.tweetBD.tweetsAnteriores(tweetSeleccionado);
            this.dialogo.dispose();
        }
    }
}