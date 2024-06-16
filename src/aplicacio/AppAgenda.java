package aplicacio;

import classes.*;
import eines.EinesJoan;
import menu.Menu;

import java.io.*;

import static eines.EinesJoan.pitjaIntroPerContinuar;
import static versioAlumnes.SolucioAlumnes.guardarAgenda;

import static classes.GuardarRecuperar.recuperarAgenda;
import static eines.EinesJoan.fitxerTrobat;

public class AppAgenda {
    private static final String NOM_FITXER = "agenda";
    private static final String EXTENSIO_FITXER = ".ajp";
    private static Agenda agenda = new Agenda();

    private static void afegirContacte() {
        EinesJoan.pintaComSubTitol("Afegir Dades del Contacte");
        String nom = Teclat.llegirCadena("Nom: ");
        String cognom = Teclat.llegirCadena("Cognom: ");

        Contacte contacte = new Contacte(nom, cognom);

        EinesJoan.pintaComSubTitol("Afegir Telefon del Contacte");
        int numeroTelefon = Teclat.llegirEnter("Número de telèfon: ");
        System.out.print("Etiqueta (Mòbil, Casa, Treball, etc.): ");
        String etiqueta = Teclat.llegirCadena();
        Telefon telefon = new Telefon(numeroTelefon, etiqueta);
        agenda.afegirTelefonContacte(contacte, telefon);
        System.out.println("Telèfon de contacte afegit amb èxit!");
        EinesJoan.pintaComSubTitol("Afegir Adreça del Contacte");
        String etiquetaAdressaContacte = Teclat.llegirCadena("Etiqueta de l'adreça: ");
        String carrerContacte = Teclat.llegirCadena("Carrer: ");
        int numeroCarrerContacte = Teclat.llegirEnter("Número del carrer: ");
        String ciutatContacte = Teclat.llegirCadena("Ciutat: ");
        String codiPostalContacte = Teclat.llegirCadena("Codi postal: ");
        String paisContacte = Teclat.llegirCadena("País: ");
        Adressa adresssaAdressaContacte = new Adressa(etiquetaAdressaContacte,
                carrerContacte, numeroCarrerContacte, ciutatContacte,
                codiPostalContacte, paisContacte);
        agenda.afegirAdressaContacte(contacte, adresssaAdressaContacte);
        System.out.println("Adreça contacte afegida amb èxit!!");
        System.out.println("Contacte afegit amb èxit!");
    }

    private static void buscarContacte() {
        EinesJoan.pintaComSubTitol("Buscar Contacte");

        System.out.print("Nom a buscar: ");
        String nom = Teclat.llegirCadena();

        var contactes = agenda.buscaContactePerNom(nom);

        if (contactes == null) {
            System.out.println("No s'han trobat contactes amb aquest nom.");
        } else {
            for (Contacte contacte : contactes) {
                System.out.println(contacte);
            }
        }
    }

    private static void modificarContacte() {
        EinesJoan.pintaComSubTitol("Modificar Contacte");
        System.out.print("Nom o Cognom del contacte a modificar: ");
        String criteri = Teclat.llegirCadena();
        var contactes = agenda.buscaContactePerNomOCognom(criteri);

        if (contactes == null) {
            System.out.println("No s'ha trobat cap contacte amb aquest criteri.");
            return;
        }

        if (contactes.size() == 1) {
            modificarContacte(contactes.get(0));
        } else {
            System.out.println("S'han trobat múltiples contactes:");
            for (int i = 0; i < contactes.size(); i++) {
                System.out.println((i + 1) + ". " + contactes.get(i));
            }

            System.out.print("Escull el número del contacte a modificar: ");
            int index = Teclat.llegirEnter() - 1;

            if (index >= 0 && index < contactes.size()) {
                modificarContacte(contactes.get(index));
            } else {
                System.out.println("Opció no vàlida.");
            }
        }
    }

    private static void modificarContacte(Contacte contacte) {
        System.out.print("Nou nom: ");
        String nouNom = Teclat.llegirCadena();
        System.out.print("Nou cognom: ");
        String nouCognom = Teclat.llegirCadena();

        contacte.setNom(nouNom);
        contacte.setCognom(nouCognom);

        System.out.println("Contacte modificat amb èxit!");
    }
    public static void mostrarContactes() {
        for (Contacte contacte : agenda.getLlistaContactes()) {
            System.out.format("Contacte: %s %s\n",
                        contacte.getNom(), contacte.getCognom());
            System.out.format("Telèfons (%d): \n", contacte.getLlistaTelefons().size());
            for (Telefon telefon : contacte.getLlistaTelefons()) {
                System.out.format("\t%s\n", telefon.toString());
            }
            System.out.format("Adreces (%d): \n", contacte.getLlistaAdreces().size());
            for (Adressa adressa : contacte.getLlistaAdreces()) {
                System.out.format("\t%s", adressa.toString());
                if(adressa != null){
                    System.out.println();
                }
            }
            System.out.println();
        }
    }
    private static void eliminarContacte() {
        EinesJoan.pintaComSubTitol("Eliminar Contacte");

        System.out.print("Nom del contacte a eliminar: ");
        String nom = Teclat.llegirCadena();
        System.out.print("Cognom del contacte a eliminar: ");
        String cognom = Teclat.llegirCadena();

        boolean eliminat = agenda.eliminarContacte(nom, cognom);

        if (eliminat) {
            System.out.println("Contacte eliminat amb èxit!");
        } else {
            System.out.println("No s'ha trobat el contacte a eliminar.");
        }
    }

    // preparaFitxerAgenda afegit per la correcció
    public static void preparaFitxerAgenda(String nomFitxerAmbAgenda) throws IOException {
        BufferedWriter fitxer = new BufferedWriter(new FileWriter(nomFitxerAmbAgenda));
        fitxer.write("Joan" + "\n");
        fitxer.write("Pardo"); fitxer.newLine();
        fitxer.write("629364567#Mòbil" + "\n");
        fitxer.write("933236456#Casa"); fitxer.newLine();
        fitxer.write("931696500#Feina" + "\n");
        fitxer.write("M_F_T"); fitxer.newLine();
        fitxer.write("Casa" + "\n");
        fitxer.write("carrer Muntaner"); fitxer.newLine();
        fitxer.write("54" + "\n");
        fitxer.write("08456"); fitxer.newLine();
        fitxer.write("Barcelona" + "\n");
        fitxer.write("Espanya"); fitxer.newLine();
        fitxer.write("Feina" + "\n");
        fitxer.write("Av. Ernest Lluch"); fitxer.newLine();
        fitxer.write("32" + "\n");
        fitxer.write("08302"); fitxer.newLine();
        fitxer.write("Mataró" + "\n");
        fitxer.write("Espanya"); fitxer.newLine();
        fitxer.write("M_F_A");
        fitxer.close();
        System.out.format("Fitxer %s prepara't!\n",nomFitxerAmbAgenda);
    }

    // afegirContacteFix afegit per la correcció
    private static void afegirContacteFix() {
        Contacte contacte = new Contacte("Alfons", "Palacios");

        int[] numeroTelefon = {621233456,934561232,931696500 };
        String[] etiqueta = {"mòbil", "casa", "feina"};

        for (int i = 0; i < numeroTelefon.length; i++) {
            Telefon telefon = new Telefon(numeroTelefon[i], etiqueta[i]);
            agenda.afegirTelefonContacte(contacte, telefon);
        }

        String[] etiquetaAdressaContacte = {"casa","feina"};
        String[] carrerContacte = {"Avinguda de Puig i Cadafalch","Av. Ernest Lluch"};
        int[] numeroCarrerContacte = {154, 32};
        String[] ciutatContacte = {"Mataró","Mataró"};
        String[] codiPostalContacte = {"08302","08302"};
        String[] paisContacte = {"Espanya", "Espanya"};
        for (int i = 0; i < etiquetaAdressaContacte.length; i++) {
            Adressa adresssaAdressaContacte = new Adressa(etiquetaAdressaContacte[i],
                    carrerContacte[i], numeroCarrerContacte[i], ciutatContacte[i],
                    codiPostalContacte[i], paisContacte[i]);
            agenda.afegirAdressaContacte(contacte, adresssaAdressaContacte);
        }
        System.out.println("Adreces FIXES del contacte afegides amb èxit!!");
    }

    // afegirContacteFix afegit per la correcció
    private static void mostraContingutFitxer(String nomFitxerAmbAgenda) throws IOException {
        System.out.println("Es mostra el fitxer " + nomFitxerAmbAgenda);
        BufferedReader fitxerFinal = new BufferedReader(
                new FileReader(nomFitxerAmbAgenda));
        String linia;
        linia = fitxerFinal.readLine();
            while (linia != null) {
            System.out.format("%s\n", linia);
            linia = fitxerFinal.readLine();
        }
        System.out.println("S'ha mostrat el fitxer " + nomFitxerAmbAgenda);
    }

    public static void main(String[] args) throws IOException {
        // INICI afegit per la correcció
        // Funció que prepara el fitxer nomFitxerAmbAgenda amb
        // les dades d'un contacte amb 3 telèfons i dues adreces.

        String nomFitxerCorreccio = NOM_FITXER + EXTENSIO_FITXER;
        preparaFitxerAgenda(nomFitxerCorreccio);
        mostraContingutFitxer(nomFitxerCorreccio);
        pitjaIntroPerContinuar();

        // Funció que afegeix un contacte a l'agenda
        // actual, amb 2 telèfons i dues adreces.
        afegirContacteFix();
        System.out.println("S'executa afegirContacteFix");
        pitjaIntroPerContinuar();

        mostrarContactes();
        System.out.println("Mostrar després d'executar afegirContacteFix");
        pitjaIntroPerContinuar();

        System.out.println("S'executa guardarAgenda de l'alumne!");
        guardarAgenda(nomFitxerCorreccio);

        System.out.println("S'executa recuperarAgenda després del guardarAgenda de l'alumne!");
        recuperarAgenda(nomFitxerCorreccio);

        System.out.println("Mostrar després d'executar guardarAgenda de l'alumne!");
        mostrarContactes();
        System.out.printf("FINAL");
        pitjaIntroPerContinuar();

        System.out.printf("Mostra fitxer final");

        System.exit(0);
        // FINAL afegit per la correcció

        String nomFitxerAmbAgenda = NOM_FITXER + EXTENSIO_FITXER;

        // Mirem si hi ha un arxiu amb el nom del fitxer amb l'Agenda
        if(fitxerTrobat(nomFitxerAmbAgenda) != null){
            // S'ha trobat un arxiu
            agenda = new Agenda(recuperarAgenda(nomFitxerAmbAgenda));
        }
        String[] opcions = {
                "Afegir Contacte",
                "Buscar Contacte",
                "Modificar Contacte",
                "Eliminar Contacte",
                "Mostrar Contactes",
                "Afegir Contacte FIX",
                "Sortir"
        };

        boolean sortir = false;

        while (!sortir) {
            int opcio = Menu.obteOpcioMenu(opcions,
                    "Menu Principal",
                    "Escull una opció");
            switch (opcio) {
                case 1:
                    afegirContacte();
                    break;
                case 2:
                    buscarContacte();
                    break;
                case 3:
                    modificarContacte();
                    break;
                case 4:
                    eliminarContacte();
                    break;
                case 5:
                    mostrarContactes();
                    break;
                case 6:
                    mostrarContactes();
                    afegirContacteFix();
                    mostrarContactes();
                    break;
                case 7:
                    guardarAgenda(nomFitxerAmbAgenda);
                    sortir = true;
                    EinesJoan.pintaComiat();
                    break;
                default:
                    System.out.println("Opció no vàlida!");
            }
        }
    }
}