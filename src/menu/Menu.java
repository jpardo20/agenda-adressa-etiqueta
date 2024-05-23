package menu;

import java.util.InputMismatchException;
import classes.Teclat;

public class Menu {
    private static final int MAXIM = 100;
    private static final int ERROR = -1;

    private String titolMenu;

    // Constructor
    public Menu(String _titolMenu) {
        this.titolMenu = _titolMenu;
    }

    public static int obteOpcioMenu(String[] opcions, String titol, String missatge){
        int opcioEscollida;
        opcioEscollida = ERROR;
        boolean esCorrecte;
        do {
            mostraMenu(opcions, titol, missatge);
            try {
                esCorrecte = true;
                opcioEscollida = Teclat.llegirEnter();
                if ((opcioEscollida < 1) || (opcioEscollida > MAXIM)) {
                    System.out.println("Error!! Cal que entris una de les opcions diponibles!");
                    esCorrecte = false;
                }
            } catch (InputMismatchException esUnaLletra) {
                System.out.println("Error!! Cal que entris un nombre enter!");
                esCorrecte = false;
            }
        } while(!esCorrecte);
        return opcioEscollida;
    }

    public static void mostraMenu(String[] opcions, String titol, String missatge){
        int midaTitol = titol.length();
        String cadenaOpcions = "";
        cadenaOpcions += " (";
        System.out.println();
        System.out.println(titol);
        System.out.println("=".repeat(midaTitol));
        for (int i = 0; i < opcions.length; i++) {
            System.out.format("%d - %s\n", (i+1) , opcions[i]);
            cadenaOpcions += (i+1);
            if(i<opcions.length-1){
                cadenaOpcions += ",";
            }
        }
        cadenaOpcions += "): ";
        System.out.println("=".repeat(midaTitol));
        System.out.print(missatge + cadenaOpcions);
    }
}
