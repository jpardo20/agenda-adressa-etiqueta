package aplicacio;

import classes.Agenda;
import classes.Teclat;
import menu.Menu;
import static eines.EinesJoan.*;

import java.io.BufferedReader;
import java.io.FileReader;

public class AppAgenda {
    public static final String SEP_CSV = ";";
    public static final int NOM_CONTACTE = 0;
    public static final int TELEFON_CONTACTE = 1;

    public static Agenda llegeixDadesFitxer(){
        // Obre el fitxer agenda.csv, llegeix les dades
        // dels contactes que hi ha i les afegeix a una
        // l'agenda que posteriorment retornarà.
        Agenda agenda = new Agenda();
        String liniaLlegida;
        String[] dadesContacteActual;
        String nomContacteActual;
        int telContacteActual;
        BufferedReader canalAgenda;
        String arxiu = "agenda.csv";
        try {
            canalAgenda = new BufferedReader(
                    new FileReader(arxiu));

            liniaLlegida = canalAgenda.readLine();
            while (liniaLlegida != null) {
                dadesContacteActual = liniaLlegida.split(SEP_CSV);
                nomContacteActual = dadesContacteActual[NOM_CONTACTE];
                telContacteActual = Integer.parseInt(dadesContacteActual[TELEFON_CONTACTE]);
                agenda.afegir(nomContacteActual,telContacteActual);
                liniaLlegida = canalAgenda.readLine();
            }
        } catch(Exception ex) {
            System.err.println("Problemes en " +
                    "llegir el fitxer!");
            System.exit(1);
        }
        return agenda;
    }

    public static void main(String[] args) {
        Agenda laMevaAgenda;

//        boolean importar = false;
//        while(!importar){
//            System.out.print("Vols importar les dades de l'agenda des d'un fitxer (S/N)?: ");
//            respImport = Teclat.llegirCadena().toUpperCase();
//            if(respImport.equals(SI)){
//                importar = true;
//                laMevaAgenda = llegeixDadesFitxer();
//            }
//        }

        laMevaAgenda = llegeixDadesFitxer();

        String[] opcionsMenuPrincipal = {"Nou contacte","Buscar contacte",
            "Modificar contacte","Eliminar contacte",
            "Llistar contactes","Buidar agenda","Sortir"};
        String nomContacte;
        String telefonContacte;
        boolean numeroValidat;

        char opcio;
        opcio  = '1';
        while ( (opcio == '1') || (opcio == '2') ||
                (opcio == '3') || (opcio == '4') ||
                (opcio == '5') || (opcio == '6')) {

        Menu.mostraMenu(opcionsMenuPrincipal,
                "Menú principal",
                "Escull una opció");

        opcio = Teclat.llegirCadena().charAt(0);
            switch (opcio) {
                case '1':
                    System.out.print("Entra el nom: ");
                    nomContacte = Teclat.llegirCadena();
                    System.out.print("Entra el telèfon: ");
                    telefonContacte = Teclat.llegirCadena();
                    numeroValidat = esCorrecte(telefonContacte);
                    if (numeroValidat) {
                        int telefonoEnter = Integer.parseInt(telefonContacte);
                        laMevaAgenda.consultar(nomContacte, telefonoEnter);
                        laMevaAgenda.afegir(nomContacte, telefonoEnter);
                    } else {
                        System.out.println("ERROR! El format de telèfon és incorrecte!.");
                        pitjaIntroPerContinuar();
                    }
                    break;

                case '2':
                    System.out.print("Nom a buscar: ");
                    nomContacte = Teclat.llegirCadena().toUpperCase();
                    laMevaAgenda.buscar(nomContacte);
                    break;

                case '3':
                    laMevaAgenda.modificar();
                    break;

                case '4':
                    laMevaAgenda.esborrar();
                    break;

                case '5':
                    laMevaAgenda.mostrar();
                    break;

                case '6':
                    laMevaAgenda.buidar();
                    break;

                case '7':
                    System.out.println("Fins la propera!");
                    break;

                default:
                    System.out.println("\n\tOpció incorrecta ...\n\tTorna a intentar-ho!\n");
                    opcio = '1';
            }
        }
    }

    public static boolean esCorrecte(String telefonCadena) {
        char caracterActual;
        for (int i = 0; i < telefonCadena.length(); i++) {
            caracterActual = telefonCadena.charAt(i);
            if (!Character.isDigit(caracterActual))
                return false;
        }
        return true;
    }
}