package classes;

import java.util.LinkedList;

public class Agenda {
    // Atributs
    private static LinkedList<Contacte> llistaContactes;
    // Constructors.
    // Constructor sense paràmetres.
    public Agenda() {
        this.llistaContactes = new LinkedList<>();
    }
    // Constructor TOTS (1) els paràmetres.
    public Agenda(LinkedList<Contacte> _llistaContactes) {
        this.llistaContactes = _llistaContactes;
    }
    // Getters i Setters.
    // Getters (1).
    public static LinkedList<Contacte> getLlistaContactes() {
        return llistaContactes;
    }
    // Setters (1)
    public static void setLlistaContactes(LinkedList<Contacte> _llistaContactes) {
        Agenda.llistaContactes = _llistaContactes;
    }
    // Mètodes.
    // Mètodes Comúns
    @Override
    public String toString() {
        return "Agenda{" +
                "llistaContactes=" + llistaContactes.toString() +
                '}';
    }
    public void afegirTelefonContacte(Contacte _contacte, Telefon _telefon) {
        for (Contacte contacte : llistaContactes) {
            if (contacte.equals(_contacte)) {
                contacte.afegirTelefon(_telefon);
                return;
            }
        }
        _contacte.afegirTelefon(_telefon);
        llistaContactes.add(_contacte);
    }
    public void afegirLlistaDeTelefonContacte(Contacte _contacte, LinkedList<Telefon> _llistaTelefons) {
        for (Contacte contacte : llistaContactes) {
            if (contacte.equals(_contacte)) {
                for (Telefon telAct : _llistaTelefons)
                    this.afegirTelefonContacte(contacte, telAct);
            }
        }
    }
    public void afegirAdressaContacte(Contacte _contacte, Adressa _adressa) {
        for (Contacte contacte : llistaContactes) {
            if (contacte.equals(_contacte)) {
                contacte.afegirAdressa(_adressa);
                return;
            }
        }
        llistaContactes.add(_contacte);
    }

    public LinkedList<Contacte> buscaContactePerNom(String nomContacteABuscar) {
        LinkedList<Contacte> contactesTrobats = new LinkedList<>();
        for (Contacte contacte : llistaContactes) {
            if (contacte.getNom().equalsIgnoreCase(nomContacteABuscar)) {
                contactesTrobats.add(contacte);
            }
        }
        return contactesTrobats.isEmpty() ? null : contactesTrobats;
    }
    public LinkedList<Contacte> buscaContactePerNomOCognom(String criteri) {
        LinkedList<Contacte> contactesTrobats = new LinkedList<>();
        for (Contacte contacte : llistaContactes) {
            if (contacte.getNom().equalsIgnoreCase(criteri) || contacte.getCognom().equalsIgnoreCase(criteri)) {
                contactesTrobats.add(contacte);
            }
        }
        return contactesTrobats.isEmpty() ? null : contactesTrobats;
    }
    public boolean eliminarContacte(String nom, String cognom) {
        return llistaContactes.removeIf(
                contacte -> contacte.getNom().equals(nom) &&
                        contacte.getCognom().equals(cognom));
    }
    public boolean modificarContacte(String nom, String cognom, Contacte nouContacte) {
        for (int i = 0; i < llistaContactes.size(); i++) {
            Contacte contacte = llistaContactes.get(i);
            if (contacte.getNom().equals(nom) && contacte.getCognom().equals(cognom)) {
                llistaContactes.set(i, nouContacte);
                return true;
            }
        }
        return false;
    }
}