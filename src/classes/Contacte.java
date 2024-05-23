package classes;

import java.util.LinkedList;

public class Contacte {
    // Atributs
    private String nom;
    private String cognom;
    private LinkedList<Telefon> llistaTelefons;
    private LinkedList<Adressa> llistaAdreces;
    // Constructors.
    // Constructor amb 2 dels 4 paràmetres.
    public Contacte(String _nom, String _cognom) {
        this.nom = _nom;
        this.cognom = _cognom;
        this.llistaTelefons = new LinkedList<>();
        this.llistaAdreces = new LinkedList<>();
    }
    // Constructor TOTS els paràmetres.
    public Contacte(String _nom,
                    String _cognom,
                    LinkedList<Telefon> _llistaTelefons,
                    LinkedList<Adressa> _llistaAdreces) {
        this.nom = _nom;
        this.cognom = _cognom;
        this.llistaTelefons = _llistaTelefons;
        this.llistaAdreces = _llistaAdreces;
    }
    // Getters i Setters.
    // Getters (4)
    public String getNom() {
        return this.nom;
    }
    public String getCognom() {
        return this.cognom;
    }
    public LinkedList<Telefon> getLlistaTelefons() {
        return this.llistaTelefons;
    }
    public LinkedList<Adressa> getLlistaAdreces() {
        return this.llistaAdreces;
    }
    // Setters (4)
    public void setNom(String _nom) {
        this.nom = _nom;
    }
    public void setCognom(String _cognom) {
        this.cognom = _cognom;
    }
    public void setLlistaAdreces(LinkedList<Adressa> llistaAdreces) {
        this.llistaAdreces = llistaAdreces;
    }
    public void setLlistaTelefons(LinkedList<Telefon> llistaTelefons) {
        this.llistaTelefons = llistaTelefons;
    }
    // Mètodes.
    // Mètodes Comúns
    @Override
    public String toString() {
        return "\tNom sencer : " + nom + " " + cognom + "\n" +
               "\tTelèfons\n" + llistaTelefons.toString() +
               "\tAdreces\n" + llistaAdreces.toString() + "\n";
    }
    public boolean equals(Contacte _contacte){
        return ( this.getNom().equals(_contacte.getNom()) &&
                 this.getCognom().equals(_contacte.getCognom())
        );
    }
    // Mètodes més específics
    public void afegirTelefon(Telefon _telefon) {
        this.llistaTelefons.add(_telefon);
    }
    public void afegirTelefon(LinkedList<Telefon> _llistaTelefons) {
        for(Telefon telActual: _llistaTelefons){
            this.llistaTelefons.addAll(_llistaTelefons);
        }
    }
    public void afegirAdressa(Adressa _adressa) {
        this.llistaAdreces.add(_adressa);
    }
    public void afegirAdressa(LinkedList<Adressa> _llistaAdreces) {
        for(Adressa adressaActual: _llistaAdreces){
            this.llistaAdreces.addAll(_llistaAdreces);
        }
    }

}
