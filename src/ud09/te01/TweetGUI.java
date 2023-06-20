/******************************************************************************************************************

Nombre:   Luka Bergaretxe
Fecha:    2023/04/24
Modulo:   Programacion
UD:       09
Tarea:    E01

Descripcion de la clase:    Definicion de la clase principal TweetGUI
                            Gestionara la ventana y flujo de ejecucion
                            principales de la aplicacion

Enlace autoevaluacion: https://docs.google.com/document/d/164-_sJ7bYya-C-WXvYewalrNMgtPyoEXeU-mayK03ew/edit

*******************************************************************************************************************/

package ud09.te01;

import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class TweetGUI implements ActionListener {

// CONSTANTES

    //Titulos
    public static final String TITULO_FRAME = "Gestion de Tweets";
    public static final String TITULO_MENU_ANADIR = "ANADIR";
    public static final String TITULO_MENU_BORRAR = "BORRAR";
    public static final String TITULO_MENU_MOSTRAR = "MOSTRAR";
    public static final String TITULO_CARGAR_FICHERO = "Cargar fichero";
    public static final String TITULO_ANADIR_TWEET = "Anadir un Tweet";
    public static final String TITULO_BORRAR_TWEETS = "Borrar Tweets de un usuario";
    public static final String TITULO_MOSTRAR_TWEETS = "Mostrar todos los Tweets";
    public static final String TITULO_MOSTRAR_ANTERIORES = "Mostrar los Tweets mas antiguos";
    public static final String TITULO_MOSTRAR_POR_TEMA = "Mostrar los Tweets que contienen una palabra";

    //Mensajes
    public static final String MENSAJE_PRESENTACION = "Este programa permite trabajar con una lista de tweets\n"
            + "Utiliza las clase Tweet y TweetBD\n" + "Lee tweets del fichero tweets.txt\n"
            + "Mediante un menu permite elegir diferentes opciones\n"
            + "Cuando se elige finalizar, muestra \"Fin del programa\"";
    public static final String MENSAJE_DIALOGO_TEMA = "Palabra que quieres buscar:";
    public static final String MENSAJE_TWEETS_CARGADOS = "Se han leido %d tweets\n\nEl tweet mas reciente es:\n%s";

    // Errores
    public static final String ERROR_LECTURA_FICHERO = "Se ha producido un error al leer el fichero";

    // Rutas
    public static final String RUTA_FICHERO_TWEETS = "res/ud09/te01/tweets.txt";

// ATRIBUTOS

    private JFrame frame;
    private JMenuBar barraMenu;
    private JTextArea cuadroTexto;
    private TweetBD tweetBD = new TweetBD("Tweets del fichero");

// METODOS

    /**
     * Metodo principal del programa.
     * @param args argumentos de linea de comandos.
     */
    public static void main(String[] args) {
        new TweetGUI();
    }

    /**
     * Constructor de TweetGUI.
     */
    public TweetGUI() {

        // Establecer la ventana enmarcada (frame) y su comportamiento de cierre
        this.frame = new JFrame(TITULO_FRAME);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear la barra de menus y asignarla al frame
        this.barraMenu = this.crearMenuBar();
        this.frame.setJMenuBar(barraMenu);

        // Crear el cuadro de texto con la presentacion y asignarlo al frame
        this.cuadroTexto = new JTextArea();
        this.cuadroTexto.setText(MENSAJE_PRESENTACION);
        this.frame.add(this.cuadroTexto);

        // Crear la barra de scroll y asignarla al frame
        JScrollPane scrollPane = new JScrollPane(cuadroTexto);
        this.frame.add(scrollPane);

        // Ajustar el tamano del frame segun los componentes presentes en el y hacerlo
        // visible
        this.frame.pack();
        this.frame.setVisible(true);
    }

    /**
     * Metodo que encapsula la funcionalidad de crear una barra de menu.
     * @return JMenuBar a asignar al frame.
     */
    private JMenuBar crearMenuBar() {

        // Crear la barra de menu
        JMenuBar barraMenus = new JMenuBar();

        // Crear el menu Anadir
        JMenu menuAnadir = new JMenu(TITULO_MENU_ANADIR);

        // Crear las opciones de Anadir con sus respectivos listeners
        JMenuItem opcionCargarFichero = new JMenuItem(TITULO_CARGAR_FICHERO);
        menuAnadir.add(opcionCargarFichero);
        opcionCargarFichero.addActionListener(this);

        JMenuItem opcionAnadirTweet = new JMenuItem(TITULO_ANADIR_TWEET);
        menuAnadir.add(opcionAnadirTweet);
        opcionAnadirTweet.addActionListener(this);

        // Crear el menu Borrar
        JMenu menuBorrar = new JMenu(TITULO_MENU_BORRAR);

        // Crear las opciones de Borrar con sus respectivos listeners
        JMenuItem opcionBorrar = new JMenuItem(TITULO_BORRAR_TWEETS);
        menuBorrar.add(opcionBorrar);
        opcionBorrar.addActionListener(this);

        // Crear el menu Mostrar
        JMenu menuMostrar = new JMenu(TITULO_MENU_MOSTRAR);

        // Crear las opciones de Mostrar con sus respectivos listeners
        JMenuItem opcionMostrarTodos = new JMenuItem(TITULO_MOSTRAR_TWEETS);
        menuMostrar.add(opcionMostrarTodos);
        opcionMostrarTodos.addActionListener(this);

        JMenuItem opcionMostrarAnteriores = new JMenuItem(TITULO_MOSTRAR_ANTERIORES);
        menuMostrar.add(opcionMostrarAnteriores);
        opcionMostrarAnteriores.addActionListener(this);

        JMenuItem opcionMostrarConPalabra = new JMenuItem(TITULO_MOSTRAR_POR_TEMA);
        menuMostrar.add(opcionMostrarConPalabra);
        opcionMostrarConPalabra.addActionListener(this);

        // Anadir los menus a la barra
        barraMenus.add(menuAnadir);
        barraMenus.add(menuBorrar);
        barraMenus.add(menuMostrar);

        return barraMenus;
    }

    /**
     * Metodo que encapsula la funcionalidad de cargar una serie de Tweets
     *      desde un fichero sobre la TweetBD de esta instancia de TweetGUI.
     */
    public void cargarFichero() {
        Scanner ficheroEntrada = null;
        try {
            ficheroEntrada = new Scanner(new File(RUTA_FICHERO_TWEETS));
            this.tweetBD.cargarTweets(ficheroEntrada);
        } catch (Exception excepcion) {
            this.cuadroTexto.setText(ERROR_LECTURA_FICHERO);
        }
        if (ficheroEntrada != null) {
            ficheroEntrada.close();
        }
        int size = this.tweetBD.getNumeroTweets();
        this.cuadroTexto.setText(
            String.format(
                MENSAJE_TWEETS_CARGADOS,
                size,
                this.tweetBD.tweetMasReciente()
            )
        );
    }

    /**
     * Metodo que encapsula la funcionalidad de mostrar los Tweets
     *      de la TweetBD que contienen una palabra que se le solicitara
     *      al usuario mediante un cuadro de dialogo.
     */
    public void mostrarPorTema() {
        String palabra = JOptionPane.showInputDialog(null, MENSAJE_DIALOGO_TEMA);
        TweetBD resultados = this.tweetBD.buscarTweets(palabra);
        this.cuadroTexto.setText(resultados.toString());
    }

    /**
     * Metodo que implementa la interfaz ActionListener
     *      para los elementos de la barra de menu.
     * @param evento Evento que dispara la ejecucion de este metodo.
     */
    @Override
    public void actionPerformed(ActionEvent evento) {

        // Identificar la opcion pulsada
        JMenuItem opcion = (JMenuItem) evento.getSource();
        String textoOpcion = opcion.getText();

        // Ramificar el flujo de ejecucion del programa segun la opcion de menu escogida
        switch (textoOpcion) {
            case TITULO_CARGAR_FICHERO:
                this.cargarFichero();
                this.frame.pack();
                break;
            case TITULO_ANADIR_TWEET:
                new DialogoAdd(this.tweetBD);
                break;
            case TITULO_BORRAR_TWEETS:
                new DialogoBorrar(this.tweetBD);
                break;
            case TITULO_MOSTRAR_TWEETS:
                this.cuadroTexto.setText(this.tweetBD.toString());
                this.frame.pack();
                break;
            case TITULO_MOSTRAR_ANTERIORES:
                DialogoAnterioresCombo dialogoAnteriores = new DialogoAnterioresCombo(this.tweetBD);
                this.cuadroTexto.setText(dialogoAnteriores.tweetsAnteriores.toString());
                this.frame.pack();
                break;
            case TITULO_MOSTRAR_POR_TEMA:
                this.mostrarPorTema();
                this.frame.pack();
                break;
        }
    }
}