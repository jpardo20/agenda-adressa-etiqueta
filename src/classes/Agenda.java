package classes;

import static aplicacio.AppAgenda.SEP_CSV;

public class Agenda {
    // Atributs
    private int quantitat;  // Comptador dels contactes creats.
    private final Contacte[] llistat; // Llista de contactes

    // Constructor sense paràmetres
    public Agenda() {
        this.quantitat = 0;
        this.llistat = new Contacte[99];
    }

    // Mètodes
    public void consultar(String _nom, int _telefon) {
        for (int i = 0; i < this.quantitat; i++) {
            if (_nom.equals(this.llistat[i].getNom())) {
                System.out.println("Ja existeix un contacte amb aquest nom!");
            }
        }
    }

    public void afegir(String _nom, int _telefon) {
        if (this.quantitat < 99) {
            this.llistat[this.quantitat] = new Contacte();
            this.llistat[this.quantitat].setNom(_nom);
            this.llistat[this.quantitat].setTelefon(_telefon);
            this.quantitat++;
            this.ordenar();
        } else {
            System.out.println("L'agenda està plena!");
        }
    }

    public void buscar(String nomContacte) {
        // booleà per trobar un contacte
        boolean contacteTrobat = false;
        if (this.quantitat == 0) {
            System.out.println("No hi ha contactes");
        } else {
            for (int i = 0; i < this.quantitat; i++) {
                System.out.println((i+1) + ". " +
                        this.llistat[i].getNom() + SEP_CSV +
                        this.llistat[i].getTelefon());
                    contacteTrobat = true;
            }
        }
        if (!contacteTrobat) {
            System.out.println("El contacte no existeix!");
        }
    }

    public void ordenar() {
        // Aquest mètode ordenarà el vector de contactes
        // en funció del nom fent servir el Mètode Bombolla
        int midaVector = this.quantitat;
        String nom1;
        String nom2;
        // Optimitzat per a quan es tingui
        // més de dos elements com a mínim.
        if (this.quantitat >= 2) {
            for (int i = 1; i <= midaVector - 1; i++) {
                for (int j = 1; j <= midaVector - i; j++) {
                    nom1 = this.llistat[j - 1].getNom();
                    nom2 = this.llistat[j].getNom();
                    if (nom1.charAt(0) > nom2.charAt(0)) {
                        Contacte tmp = this.llistat[j - 1];
                        this.llistat[j - 1] = this.llistat[j];
                        this.llistat[j] = tmp;
                    }
                }
            }
        }
    }

    public void mostrar() {
        if (this.quantitat == 0) {
            System.out.println("No hi ha contactes!");
        } else {
            for (int i = 0; i < this.quantitat; i++) {
                System.out.println((i+1) + ". " +
                        this.llistat[i].getNom() + " - Tel.: " +
                        this.llistat[i].getTelefon());
            }
        }
    }

    public void buidar() {
        System.out.println("S'eliminaran TOTS els contactes");
        System.out.print("Estàs segur (S/N)?: ");
        String opcioSioNo;

        opcioSioNo = Teclat.llegirCadena().toUpperCase();
        if (opcioSioNo.equals("S")) {
            // Fet per mera formalitat perquè el java s'encarrega d'alliberar
            // els objectes no referenciats creats. El garbage collector
            for (int i = 0; i < this.quantitat; i++) {
                this.llistat[i].setNom("");
                this.llistat[i].setTelefon(0);
            }
            this.quantitat = 0;
            System.out.println("Agenda buidada correctament!");
        } else {
            System.out.println("Acció cancel·lada!");
        }
    }

    public void esborrar() {
        String nomContacte;
        System.out.print("Nom de contacte a eliminar: ");
        nomContacte = Teclat.llegirCadena().toUpperCase();
        // booleà per trobar un contacte
        boolean contacteTrobat = false;
        if (this.quantitat == 0) {
            System.out.println("No hi ha contactes");
        } else {
            for (int i = 0; i < this.quantitat; i++) {
                if (nomContacte.equals(this.llistat[i].getNom())) {
                    System.out.println(
                            (i + 1) + ". " +
                                    this.llistat[i].getNom() + " - Tel.: " +
                                    this.llistat[i].getTelefon());
                    contacteTrobat = true;
                }
                if (contacteTrobat) {
                    System.out.println("Quin contacte vols eliminar, introdueix el número associat?");
                    int eliminarNumero = Integer.parseInt(Teclat.llegirCadena());
                    eliminarNumero--;
                    System.out.println("Estàs segur (S/N)?");
                    String resposta;
                    resposta = Teclat.llegirCadena();
                    resposta = resposta.toUpperCase();
                    if (resposta.equals("S")) {
                        Contacte[] temporal = new Contacte[99];
                        int i1 = 0;
                        boolean trobat2 = false;
                        for (int i2 = 0; i2 < this.quantitat; i2++) {
                            if (i2 != eliminarNumero) {
                                // Es crea l'objecte temporal per esborrar-lo
                                if (!trobat2) {
                                    temporal[i1] = this.llistat[i1];
                                    i1++;
                                } else {
                                    if (i1 < this.quantitat) {
                                        temporal[i1] = this.llistat[i1 + 1];
                                        i1++;
                                    }
                                }
                            } else {
                                temporal[i1] = this.llistat[i1 + 1];
                                i1++;
                                trobat2 = true;
                            }
                        }
                        this.quantitat--;
                        System.out.println("Contacte eliminat correctament");
                        for (int j = 0; j < this.quantitat; j++) {
                            this.llistat[j] = temporal[j];
                        }

                    } else
                        System.out.println("Em sap greu, no s'ha trobat el nom!");
                }
            }
        }
    }

    public void modificar() {
        String nomContacte;
        System.out.print("Nom de contacte a modificar:");
        nomContacte = Teclat.llegirCadena().toUpperCase();
        // booleà per trobar un contacte
        boolean contacteTrobat = false;
        if (this.quantitat == 0) {
            System.out.print("No hi ha contactes!");
        } else {
            for (int i = 0; i < this.quantitat; i++) {
                if (nomContacte.equals(this.llistat[i].getNom())) {
                    System.out.println(
                            (i + 1) + ". " +
                                    this.llistat[i].getNom() + " - Tel.: " +
                                    this.llistat[i].getTelefon());
                    contacteTrobat = true;
                }
            }
        }
        if (contacteTrobat) {
            System.out.print("Quin contacte vols modificar?, entra el número: ");
            int posicioAModificar = Integer.parseInt(Teclat.llegirCadena());
            System.out.print("Entra el nom: ");
            String nouNom = Teclat.llegirCadena();
            System.out.print("Introdueix telèfon, format numèric: ");
            int nouTelefon = Integer.parseInt(Teclat.llegirCadena());
            this.llistat[posicioAModificar - 1].setNom(nouNom);
            this.llistat[posicioAModificar - 1].setTelefon(nouTelefon);
            ordenar();
        } else {
            System.out.println("No hi ha contactes amb aquest nom");
        }
    }
}