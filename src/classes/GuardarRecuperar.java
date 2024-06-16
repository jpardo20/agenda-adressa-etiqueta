package classes;

import java.io.*;
import java.util.LinkedList;

// Importem la llista de contactes de l'agenda actual
import static classes.Agenda.getLlistaContactes;

public class GuardarRecuperar {
    private static final String MARCA_FINAL_TELEFONS = "M_F_T";
    private static final String MARCA_FINAL_ADRECES = "M_F_A";
    private static final String SEPARADOR_ETIQUETA_TELEFON = "#";

    public static LinkedList<Contacte> recuperarAgenda(String nomArxiu) throws IOException {
        BufferedReader canalDeLectura;
        boolean hiHaTelefons = false;
        boolean hiHaAdreces = false;

        canalDeLectura = new BufferedReader(new FileReader(nomArxiu));
        LinkedList<Contacte> llistaContactes = new LinkedList<>();

        String linia;   // Variable que conté la línia llegida
        String[] dadesTelefon;

        linia = canalDeLectura.readLine();
        while (linia != null) { // Mentre hi hagi línies llegides
            // Inicialitzem les llistes
            LinkedList<Telefon> llistaTelefons = new LinkedList<>();
            LinkedList<Adressa> llistaAdreces = new LinkedList<>();
            // Llegir nom i cognom del contacte
            String nom = linia;
            String cognom = canalDeLectura.readLine();
            // llistaTelefons per afegir TOTS els telèfons llegits del contacte
            // Llegir l'etiqueta del telèfon del contacte
            linia = canalDeLectura.readLine();
            hiHaTelefons = (linia != null && !linia.equals(MARCA_FINAL_TELEFONS));
//            hiHaContactes = !llistaContactes.isEmpty();
            while (hiHaTelefons) {
                dadesTelefon = null;
                // Hi ha telefons amb el format int telefon#String etiqueta
                dadesTelefon = linia.split(SEPARADOR_ETIQUETA_TELEFON);
                int numeroTelefon = Integer.parseInt(dadesTelefon[0]);
                String etiqueta = dadesTelefon[1];
                llistaTelefons.add(new Telefon(numeroTelefon, etiqueta));
                linia = canalDeLectura.readLine();
                hiHaTelefons = (linia != null && !linia.equals(MARCA_FINAL_TELEFONS));
            }
            if(linia.equals(MARCA_FINAL_TELEFONS)){
                linia = canalDeLectura.readLine();
            }
            hiHaAdreces = (linia != null && !linia.equals(MARCA_FINAL_ADRECES));
            // Pot ser MARCA_FINAL_ADRESSES, o etiqueta 1a Adreça
            while (hiHaAdreces) {
                String etiqueta = linia;
                String carrer = canalDeLectura.readLine();
                int numeroCarrer = Integer.parseInt(canalDeLectura.readLine());
                String codiPostal = canalDeLectura.readLine();
                String ciutat = canalDeLectura.readLine();
                String pais = canalDeLectura.readLine();
                llistaAdreces.add(new Adressa(etiqueta, carrer, numeroCarrer, ciutat, codiPostal, pais));
                linia = canalDeLectura.readLine();
                hiHaAdreces = (linia != null && !linia.equals(MARCA_FINAL_ADRECES));
            }
            Contacte contacte = new Contacte(nom, cognom, llistaTelefons, llistaAdreces);
            llistaContactes.add(contacte);
            linia = canalDeLectura.readLine();
        }
        canalDeLectura.close();
        return llistaContactes;
    }

    public static boolean guardarAgenda(String nomArxiu) throws IOException {
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
