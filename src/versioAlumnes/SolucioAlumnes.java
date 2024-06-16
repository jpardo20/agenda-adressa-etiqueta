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
        // desa el contingut de la col·lecció donada en el primer paràmetre
        // a l'arxiu especificat pel segon paràmetre.
        boolean guardatCorrecte = false;
        if (getLlistaContactes().isEmpty()) {
            System.out.println("Agenda buida");
        } else {
            try {
                FileWriter fileWriter = new FileWriter(nomArxiu);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                for (Contacte contacte : getLlistaContactes()) {
                    bufferedWriter.write(contacte.getNom());
                    bufferedWriter.newLine();
                    bufferedWriter.write(contacte.getCognom());
                    bufferedWriter.newLine();

                    for (Telefon telefon : contacte.getLlistaTelefons()) {
                        bufferedWriter.write(telefon.getNumeroTelefon() + SEPARADOR_ETIQUETA_TELEFON + telefon.getEtiqueta());
                        bufferedWriter.newLine();
                    }
                    bufferedWriter.write(MARCA_FINAL_TELEFONS);
                    bufferedWriter.newLine();

                    if (contacte.getLlistaAdreces() == null) {
                        System.out.println("No hi ha adresses per guardar");
                    } else {
                        for (Adressa adressa : contacte.getLlistaAdreces()) {
                            bufferedWriter.newLine();
                            bufferedWriter.write(adressa.getCarrer());
                            bufferedWriter.newLine();
                            bufferedWriter.write(Integer.toString(adressa.getNumeroCarrer()));
                            bufferedWriter.newLine();
                            bufferedWriter.write(adressa.getCodiPostal());
                            bufferedWriter.newLine();
                            bufferedWriter.write(adressa.getCiutat());
                            bufferedWriter.newLine();
                            bufferedWriter.write(adressa.getPais());
                            bufferedWriter.newLine();
                        }
                    }

                    bufferedWriter.write(MARCA_FINAL_ADRECES);
                    bufferedWriter.newLine();
                }

                bufferedWriter.close();
                fileWriter.close();
                System.out.println("Agenda guardada al fitxer " + nomArxiu + "!");
                guardatCorrecte = true;
            } catch (IOException e) {
                System.out.println("Problemes a l'hora de llegir el fitxer de contactes!");
                e.printStackTrace();
            }
        }
        return guardatCorrecte;
    }
}



