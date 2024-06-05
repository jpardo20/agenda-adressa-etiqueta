package versioAlumnes;

import classes.Adressa;
import classes.Contacte;
import classes.Telefon;

import java.io.*;

import static classes.Agenda.getLlistaContactes;

public class SolucioAlumnes {
    private static final String MARCA_FINAL_TELEFONS = "M_F_T";
    private static final String MARCA_FINAL_ADRECES = "M_F_A";
    private static final String SEPARADOR_ETIQUETA_TELEFON = "#";

    public static boolean guardarAgenda(String nomArxiu) throws IOException {
// desa el contingut de la col·lecció donada en el primer paràmetre
// a l'arxiu especificat pel segon paràmetre.
        BufferedWriter bur = new BufferedWriter(new FileWriter(nomArxiu));
        String nom;
        String cognom;

        boolean guardatCorrecte = false;
        if (getLlistaContactes().isEmpty()) {
            System.out.println("Agenda buida, no hi ha res a guardar!");
        } else {
// Tipus de fitxer
            try {
// Línia a esborrar afegida perquè no aparegui cap error
                int prova = Character.getNumericValue(nomArxiu.charAt(0));
// Tipus de canal
                for (Contacte contacte : getLlistaContactes()) {
                    nom = contacte.getNom();
                    bur.write(nom);
                    cognom = contacte.getCognom();
                    bur.write(cognom);
// Obtenir TOTS els telèfons del contacte per poder
// iterar sobre TOTS els telèfons del contacte.
                    contacte.getLlistaTelefons();

                    for (Telefon telefon : contacte.getLlistaTelefons()) {
                        int numTelefon = telefon.getNumeroTelefon();
                        bur.write(numTelefon);
                        bur.write(SEPARADOR_ETIQUETA_TELEFON + numTelefon);
                        bur.write(SEPARADOR_ETIQUETA_TELEFON);
                    }

// desar marca de MARCA_FINAL_TELEFONS per indicar
// que ja no hi ha més telèfons del contacte
                    bur.write(MARCA_FINAL_TELEFONS);

                    if (contacte.getLlistaAdreces() == null) {
                        System.out.printf("No hi ha adreces per guardar!");
                    } else {
// Obtenir TOTES les adreces del contacte per poder
                        for (Adressa adressa : contacte.getLlistaAdreces()) {

// iterar sobre TOTES les adreces del contacte.
                            contacte.getLlistaAdreces();
                            String nomCarrer = adressa.getCarrer();
                            bur.write(nomCarrer);
                            int numCarrer = adressa.getNumeroCarrer();
                            bur.write(numCarrer);
                            String codiPostal = adressa.getCodiPostal();
                            bur.write(codiPostal);
                            String ciutat = adressa.getCiutat();
                            bur.write(ciutat);
                            String pais = adressa.getPais();
                            bur.write(pais);
                        }
                        bur.write(MARCA_FINAL_ADRECES);
                    }
                }
                System.out.println("Agenda guardada al fitxer " +
                        nomArxiu + "!");
                guardatCorrecte = true;
                bur.close();
            } catch (Exception e) {
                System.out.println("Problemes a l'hora de llegir el fitxer de contactes!");
                e.printStackTrace();
                System.err.print(e);
            }
        }
        return guardatCorrecte;
    }


}

