/******************************************************************************************************************

Nombre:   Luka Bergaretxe
Fecha:    2023/04/24
Módulo:   Programacion
UD:       09
Tarea:    E01

Descripcion de la clase:	Definicion de la clase Tweet
                      		Gestionara el usuario, la fecha y el contenido de un tweet

Enlace autoevaluacion: https://docs.google.com/document/d/164-_sJ7bYya-C-WXvYewalrNMgtPyoEXeU-mayK03ew/edit
                              
*******************************************************************************************************************/

package v1;

// Librerias para manejar objetos de la clase Date
import java.text.*;
import java.util.*;

// Definicion de la clase Tweet
public class Tweet {

   // Atributos
    private String usuario;
    private Date fecha;
    private String tweet; 
    
   /* 
      Constructor: crea la Un Tweet.
      Parametros: 
         String usuario: nombre del usuario que ha escrito el tweet
         Date fechaHora: fecha del tweet
         String tweet: contenido del tweet
   */
   public Tweet(String usuario, Date fechaHora, String tweet) {
      this.usuario = usuario;
      this.fecha = fechaHora;
      this.tweet = tweet;
   }
   
   // toString
   // Es necesario utilizar la clase SimpleDateFormat para convertir la fecha Date a un String
   // En este caso el formato sera dd de MMMM d' yyyy (HH:mm:ss)
   // Se usara el metodo format
   public String toString() {
      SimpleDateFormat formatoFecha = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy (HH:mm:ss)");
      String fechaString = formatoFecha.format(fecha);
      return String.format("%-18s%-35s%s", usuario, fechaString, tweet);
   }
   
   // Constructor copia
   // Para usar el otro constructor mediante this(), habria que convertir la fecha Date a String
   public Tweet(Tweet otroTweet) {
      this(otroTweet.usuario, otroTweet.fecha, otroTweet.tweet);
   }
   
   // Metodos get y set
   // Devuelve el usuario del Tweet
   public String getUsuario() {
      return usuario;
   }
   
   // Devuelve la fecha del Tweet
   public Date getFecha() {
      return fecha;
   }
   
   // Devuelve el contenido del Tweet
   public String getTweet() {
      return tweet;
   }
     
   // Compara el atributo fecha con la fecha del Tweet otro 
   // Utilizamos el metodo compareTo
   public boolean esMasReciente(Tweet otro) {
      Date otraFecha = otro.fecha;
      int compararFechas = fecha.compareTo(otraFecha);
      return compararFechas > 0;
   }
}