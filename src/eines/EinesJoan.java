package eines;

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
}
