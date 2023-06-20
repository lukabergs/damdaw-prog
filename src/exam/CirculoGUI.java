// Importamos las librerias necesarias
import java.awt.*;
import javax.swing.*;

public class CirculoGUI {
    
    public static final int TAMANYO_AREA_TEXTO = 10;
    public static final int[] DIMENSIONES = {400, 150};
    public static final String TITULO_VENTANA = "ELEGIR CIRCULO";
    public static final String[] COLORES = {"Blanco", "Negro", "azul", "Verde"};
    public static final String TITULO_BOTON = "BUSCAR";
    

   // Programa principal
   public static void main(String[] args) {
      CirculoGUI gui = new CirculoGUI(COLORES);
   }
   
   public CirculoGUI(String[] colores) {
       JFrame ventana = new JFrame(TITULO_VENTANA);
       ventana.setLayout(new BorderLayout());
       ventana.setSize(new Dimension(DIMENSIONES[0], DIMENSIONES[1]));
       JPanel panel = new JPanel();
       panel.setLayout(new FlowLayout());
       
       JLabel radio = new JLabel("Radio: ");
       JTextField campoTexto = new JTextField(TAMANYO_AREA_TEXTO);
       JLabel color = new JLabel("Color: ");
       JComboBox<String> comboColores = new JComboBox<String>(colores);
       
       panel.add(radio);
       panel.add(campoTexto);
       panel.add(color);
       panel.add(comboColores);
       
       JButton boton = new JButton(TITULO_BOTON);
       
       ventana.add(BorderLayout.CENTER, panel);
       ventana.add(BorderLayout.SOUTH, boton);
       
       ventana.setVisible(true);
   }
}