/******************************************************************************************************************

Nombre:   Luka Bergaretxe
Fecha:    2023/04/24
Modulo:   Programacion
UD:       09
Tarea:    E01

Descripcion de la clase:    Definicion de la clase TweetBD
                            Gestionara la descripcion y todos los tweets almacenados

Enlace autoevaluacion: https://docs.google.com/document/d/164-_sJ7bYya-C-WXvYewalrNMgtPyoEXeU-mayK03ew/edit

*******************************************************************************************************************/

package ud09.te01;

import java.text.*;
import java.util.*;

public class TweetBD {

    // Atributos
    private String descripcion;
    private ArrayList<Tweet> tweets;

    /*
     * Constructor: inicializa los atributos descripcion y tweets
     * Parametros:
     * String descripcion: Descripcion de los tweets que va a almacenar
     */
    public TweetBD(String descripcion) {
        this.descripcion = descripcion;
        this.tweets = new ArrayList<Tweet>();
    }

    /*
     * Constructor: inicializa los atributos descripcion y tweets.
     * Carga los tweets leidos de un Scanner conectado a un fichero.
     * Parametros:
     * String descripcion: Descripcion de los tweets que va a almacenar
     * Scanner leerFichero: Scanner conectado a un fichero
     */
    public void cargarTweets(Scanner leerFichero) {
        while (leerFichero.hasNextLine()) {
            String linea = leerFichero.nextLine();
            Scanner leerLinea = new Scanner(linea);
            while (leerLinea.hasNext()) {
                String usuario = leerLinea.next();
                String fechaHora = leerLinea.next();
                String tweet = leerLinea.nextLine();
                tweet = tweet.substring(1);
                addTweet(usuario, fechaHora, tweet);
            }
            leerLinea.close();
        }
    }

    /**
     * Metodo toString
     * Formatea un TweetBD de la siguiente manera:
     * DESCRIPCION
     * 1. usuario fecha tweet
     * 2. usuario fecha tweet
     * 3. usuario fecha tweet
     * ...
     * Utiliza el metodo toString de la clase Tweet y el bucle for-each
     */
    public String toString() {

        String formato = descripcion.toUpperCase();

        int cont = 1;
        for (Tweet unTweet : tweets) {
            formato += "\n" + cont + ". " + unTweet.toString();
            cont++;
        }
        formato += "\n";
        return formato;
    }

    // Metodos get y set
    // Devuelve el numero de tweet del ArrayList tweets
    public int getNumeroTweets() {
        return tweets.size();
    }

    // Devuelve el Tweet con el indice indicado
    // Se realizara una copia antes de devolver el objeto para protegerlo de accesos
    // externos
    // Parametros:
    // int i: indice del tweet
    // Return:
    // Copia del Tweet buscado
    public Tweet getTweet(int i) {
        Tweet buscado = tweets.get(i);
        Tweet copia = new Tweet(buscado);
        return copia;
    }

    // Creamos una Array con el mismo n�mero de elementos que el ArrayList y
    // copiamos los elementos del ArrayList al Array
    // Devolvemos el Array
    public Tweet[] getArrayTweets() {
        int numTweets = tweets.size();
        Tweet[] arrayTweets = new Tweet[numTweets];
        tweets.toArray(arrayTweets);
        return arrayTweets;
    }

    // Anade un Tweet al ArrayList a partir de los datos indicados
    // Parametros:
    // String usuario: nombre del usuario que ha escrito el tweet
    // String fechaHora: fecha del tweet con formato yyyy-MM-ddThh:mm:ss
    // String tweet: contenido del tweet
    // No devuelve nada
    public void addTweet(String usuario, String fechaHora, String tweet) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        try {
            Date fechaDate = formatoFecha.parse(fechaHora);
            Tweet nuevoTweet = new Tweet(usuario, fechaDate, tweet);
            tweets.add(nuevoTweet);
        } catch (ParseException e) {
            System.out.println("El formato de la fecha es incorrecto");
        }
    }

    // Anade una copia de Tweet indicado al ArrayList
    // Parametros:
    // Tweet unTweet: Tweet original. Se debe copiar antes de anadirlo
    // No devuelve nada
    public void addTweet(Tweet unTweet) {
        Tweet copia = new Tweet(unTweet);
        tweets.add(copia);
    }

    // Busca y devuelve una copia del tweet mas reciente de la BBDD.
    // Si no hay ningun tweet devolvera null
    // Return: copia de Tweet mas reciente del ArrayList
    public Tweet tweetMasReciente() {
        if (getNumeroTweets() == 0) {
            return null;
        }

        Tweet masReciente = tweets.get(0);
        for (int i = 1; i < tweets.size(); i++) {
            Tweet esteTweet = tweets.get(i);
            if (esteTweet.esMasReciente(masReciente)) {
                masReciente = esteTweet;
            }
        }
        return new Tweet(masReciente); // Devolvemos una copia
    }

    // Borra todos los tweets del usuario indicado
    // Devolvera el numero de tweets borrados
    // Parametros:
    // String usuario: usuario cuyos tweets hay que borrar
    // Return: numero de tweets borrados
    public int borrarTweets(String usuario) {
        int cont = 0;
        Iterator<Tweet> it = tweets.iterator();
        while (it.hasNext()) {
            Tweet unTweet = it.next();
            String unUsuario = unTweet.getUsuario();
            if (unUsuario.equals(usuario)) {
                it.remove();
                cont++;
            }
        }
        return cont;
    }

    // Crea y devuelve un TweetBD con todos los tweet anteriores (mas antiguos) al
    // tweet dado
    // La descripcion sera "Tweets escritos antes de + tweet"
    // Parametros:
    // Tweet esteTweet: Tweet cuya fecha hay que mirar para obtener los que son mas
    // antiguos
    // Return: un objeto TweetBD con el resultado obtenido
    public TweetBD tweetsAnteriores(Tweet esteTweet) {
        TweetBD nuevaBD = new TweetBD("Tweets escritos antes de este tweet: " + esteTweet);

        for (Tweet unTweet : tweets) {
            if (esteTweet.esMasReciente(unTweet)) {
                nuevaBD.addTweet(unTweet);
            }
        }
        return nuevaBD;
    }

    // Crea y devuelve un TweetBD con todos los tweet que contienen el texto dado
    // La descripcion sera "Tweets que contienen la palabra + palabra"
    // Parametros:
    // String palabra: Palabra a buscar en el texto del tweet. Puede ser parte de
    // una palabra
    // Return: un objeto TweetBD con el resultado obtenido
    public TweetBD buscarTweets(String palabra) {
        TweetBD nuevaBD = new TweetBD("Tweets que contienen la palabra " + palabra);
        for (Tweet unTweet : tweets) {
            String texto = unTweet.getTweet();
            if (texto.contains(palabra)) {
                nuevaBD.addTweet(unTweet);
            }
        }
        return nuevaBD;
    }
}