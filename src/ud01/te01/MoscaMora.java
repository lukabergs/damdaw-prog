/**
* @author  Luka Bergaretxe
* <br>
* Date: 2022/09/30
* <br>
* Module: Programación
* Self-assessment: https://docs.google.com/document/d/1XvIgDTQe9heoYVoDdFa5miTK0fqxS5WusCVLXNjK260/edit?usp=sharing
* 
* This class represents a predefined cumulative song ("La mosca y la mora") to be printed to screen.
* It is structured by verses, providing two methods for the two last lines of a verse of the song to be printed,
* plus another method for each added verse.
* 
* Helper methods of this program work in a layered/stacked way, meaning that, as the song progresses, each new method
* to print its corresponding added line will call the method it is stacked upon, then the latter will call the next method
* in the stack, and so on, like so:
* 
* .
*  .
*   . ____________
*    |M3 ________ |
*    |  |M2 ____ ||
*    |  |  |M1  |||
*    |  |  |____|||
*    |  |________||
*    |____________|
* 
* 
*/

package ud01.te01;

public class MoscaMora {
    
    /**
	* Main method of the program, which prints "La mosca y la mora" to console following a versed structure.
	* @param  args command line arguments.
	*/
	public static void main(String[] args) {
	    
	    // First verse
		System.out.println("Estaba la mora en su lugar,\n"
				+ "Vino la mosca y le hizo mal,");
		printMosca();

        // Second verse
		System.out.println("Estaba la mosca en su lugar,\n"
				+ "Vino la araña y le hizo mal,");
		printArana();
        
        // Third verse
		System.out.println("Estaba la araña en su lugar,\n"
				+ "Vino el ratón y le hizo mal,");
		printRaton();

        // Fourth verse
		System.out.println("Estaba el ratón en su lugar,\n"
				+ "Vino el gato y le hizo mal,");
		printGato();

        // Fifth verse
		System.out.println("Estaba el gato en su lugar,\n"
				+ "Vino el perro y le hizo mal,");
		printPerro();
	}
	
	/**
	* Method which prints to console the last line of each verse of "La mosca y la mora".
	*/
	public static void printMora() {
		System.out.println("Y la mora en su moralito sola.\n");
	}
	
	/**
	* Method which prints to console the second last line of each verse of "La mosca y la mora"
	* and then calls printMora().
	*/
	public static void printMosca() {
		System.out.println("La mosca a la mora,");
		printMora();
	}
	
	/**
	* Method which prints to console the third last line of each verse of "La mosca y la mora"
	* from the second verse onwards, then calls printMosca().
	*/
	public static void printArana() {
		System.out.println("La araña a la mosca,");
		printMosca();
	}
	
	/**
	* Method which prints to console the fourth last line of each verse of "La mosca y la mora"
	* from the third verse onwards, then calls printArana().
	*/
	public static void printRaton() {
		System.out.println("El ratón a la araña,");
		printArana();
	}
	
	/**
	* Method which prints to console the fifth last line of each verse of "La mosca y la mora"
	* from the fourth verse onwards, then calls printRaton().
	*/
	public static void printGato() {
		System.out.println("El gato al ratón,");
		printRaton();
	}
	
	/**
	* Method which prints to console the  sixth last line of each verse of "La mosca y la mora"
	* from the fifth verse onwards, then calls printGato().
	*/
	public static void printPerro() {
		System.out.println("El perro al gato,");
		printGato();
	}
}