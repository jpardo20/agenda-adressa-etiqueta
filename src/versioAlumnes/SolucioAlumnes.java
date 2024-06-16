package versioAlumnes;

import classes.Adressa;
import classes.Agenda;
import classes.Contacte;
import classes.Telefon;

import java.io.*;
import java.util.LinkedList;

import static classes.Agenda.getLlistaContactes;

public class SolucioAlumnes {
    private static final String MARCA_FINAL_TELEFONS = "M_F_T";
    private static final String MARCA_FINAL_ADRECES = "M_F_A";
    private static final String SEPARADOR_ETIQUETA_TELEFON = "#";

    public static boolean guardarAgenda(String nomArxiu)
            throws IOException {
        // Desa el contingut de la classes.Agenda.getLlistaContactes
        // importada a l'arxiu especificat pel paràmetre.
        boolean guardatCorrecte = false;
        if (getLlistaContactes().isEmpty()) {
            System.out.println("Agenda buida, no hi ha res a guardar!");
        } else {
            BufferedWriter buw;
            try {
                buw = new BufferedWriter(new FileWriter(nomArxiu));
                for (Contacte contacte : getLlistaContactes()) {
                    // Escriure el nom del contacte més el salt de línia
                    buw.write(contacte.getNom()); buw.newLine();
                    // Escriure el cognom del contacte més el salt de línia
                    buw.write(contacte.getCognom()); buw.newLine();
                    // Obtenir TOTS els telèfons del contacte per poder
                    // iterar sobre TOTS els telèfons del contacte.
                    for (Telefon telefon : contacte.getLlistaTelefons()) {
                        // Escriure el número del telèfon del contacte
                        buw.write(String.valueOf(telefon.getNumeroTelefon()));
                        // seguit del separador de l'etiqueta del telèfon i el número de teléfon.
                        buw.write(SEPARADOR_ETIQUETA_TELEFON);
                        // més escriure l'etiqueta del telèfon del contacte més el salt de línia
                        buw.write(telefon.getEtiqueta()); buw.newLine();
                    }
                    buw.write(MARCA_FINAL_TELEFONS); buw.newLine();
                    // desar marca de MARCA_FINAL_TELEFONS per indicar
                    // que ja no hi ha més telèfons del contacte

                    if (contacte.getLlistaAdreces() == null) {
                        System.out.printf("No hi ha adreces per guardar!");
                    } else {
                        // Obtenir TOTES les adreces del contacte per poder
                        for (Adressa adressa : contacte.getLlistaAdreces()) {
                            buw.write(adressa.getEtiqueta()); buw.newLine();
                            // iterar sobre TOTES les adreces del contacte.
                            buw.write(adressa.getCarrer()); buw.newLine();
                            // Escriure el nom del carrer de l'adreça del contacte més el salt de línia
                            buw.write(String.valueOf(adressa.getNumeroCarrer())); buw.newLine();
                            // Escriure el número del carrer de l'adreça del contacte més el salt de línia
                            buw.write(adressa.getCodiPostal()); buw.newLine();
                            // Escriure el codi postal del carrer de l'adreça del contacte més el salt de línia
                            buw.write(adressa.getCiutat()); buw.newLine();
                            // Escriure el nom de la ciutat de l'adreça del contacte més el salt de línia
                            buw.write(adressa.getPais()); buw.newLine();
                            // Escriure el nom del país de l'adreça del contacte més el salt de línia
                        }
                        buw.write(MARCA_FINAL_ADRECES); buw.newLine();
                        // desar marca de MARCA_FINAL_ADRESSES de la seqüència de telèfons  més el salt de línia
                    }
                }
                System.out.println("Agenda guardada al fitxer " +
                        nomArxiu + "!");
                guardatCorrecte = true;
                buw.close();
            } catch (IOException e) {
                System.out.println("Problemes a l'hora de llegir el fitxer de contactes!");
                // O millor, mostra un missatge d'error a l'usuari
                e.printStackTrace();
            }
        }
        return guardatCorrecte;
    }
}



