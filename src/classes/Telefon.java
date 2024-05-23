package classes;

public class Telefon {
    // Atributs
    private int numeroTelefon;
    private String etiqueta;
    // Constructors.
    // Constructor TOTS (2) paràmetres.
    public Telefon(int numeroTelefon, String etiqueta) {
        this.numeroTelefon = numeroTelefon;
        this.etiqueta = etiqueta;
    }
    // Getters i Setters.
    // Getters (2).
    public int getNumeroTelefon() {
        return numeroTelefon;
    }
    public String getEtiqueta() {
        return etiqueta;
    }
    // Setters (2).
    public void setNumeroTelefon(int numeroTelefon) {
        this.numeroTelefon = numeroTelefon;
    }
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    // Mètodes.
    // Mètodes Comúns
    @Override
    public String toString() {
        return  "\t" + this.etiqueta.toLowerCase() + ":\t" + this.numeroTelefon;
    }
}
