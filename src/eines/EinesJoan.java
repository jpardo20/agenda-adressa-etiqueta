package eines;

import java.io.FilenameFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class EinesJoan {
    static String liniaDecoracio = "-".repeat(40);

    public static void pintaComiat(){
        System.out.println("\tFins la propera !!!!!");
        System.out.println("\t"+ liniaDecoracio);
    }

    public static void pintaLinia(String titol, String caracter) {
        System.out.println(caracter.repeat(titol.length()));
    }

    public static void pintaComSubTitolSenseSubratllar(String subTitol){
        System.out.println(subTitol);
    }

    public static void pintaComSubTitol(String subTitol){
        System.out.println(subTitol);
        pintaLinia(subTitol,"-");
    }
    
    public static void pintaComTitol(String titol){
        System.out.println(titol);
        pintaLinia(titol,"=");
    }

    public static void saltDeLinia(){
        System.out.println();
    }

    public static void saltaLinies(int quantitatDeSalts){
        for (int i = 0; i < quantitatDeSalts; i++)
            saltDeLinia();
    }

    public static void pitjaIntroPerContinuar(){
        System.out.print("\tPitja INTRO per continuar...");
        try {
            System.in.read();
        } catch(Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public static List<File> cercaFitxerPerNomIExtensio(String nomFitxerABuscar) {
        FilenameFilter filtreJpc;
        File directoriActual = new File(".");
        filtreJpc = new FilenameFilter() {
            public boolean accept(File carpetaActual, String nomFitxer) {
                return nomFitxer.equals(nomFitxerABuscar);
            }
        };
        File[] fitxers = directoriActual.listFiles(filtreJpc);

        List<File> llistaFitxers = new ArrayList<>();
        if (fitxers != null) {
            for (File fitxer : fitxers) {
                llistaFitxers.add(fitxer);
            }
        }
        return llistaFitxers;
    }

    public static File fitxerTrobat(String nomFitxerABuscar){
        File fitxerQueSHaTrobat = null;
        List<File> fitxersJpc = cercaFitxerPerNomIExtensio(nomFitxerABuscar);
        if (fitxersJpc.isEmpty()) {
            fitxerQueSHaTrobat = null;
        } else {
            for (File fitxer : fitxersJpc) {
                System.out.println(fitxer.getName());
                fitxerQueSHaTrobat = fitxer;
            }
        }
        return fitxerQueSHaTrobat;
    }
}
