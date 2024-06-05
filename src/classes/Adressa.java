package classes;

public class Adressa {
    // Atributs
    // Atributs
    private String carrer;
    private int numeroCarrer;
    private String ciutat;
    private String codiPostal;
    private String pais;
    private String etiqueta;
    // Constructors.
    // Constructor TOTS els paràmetres.
    public Adressa(String _etiqueta,
                   String _carrer,
                   int _numeroCarrer,
                   String _ciutat,
                   String _codiPostal,
                   String _pais) {
        this.carrer = _carrer;
        this.numeroCarrer = _numeroCarrer;
        this.codiPostal = _codiPostal;
        this.pais = _pais;
        this.ciutat = _ciutat;
        this.etiqueta = _etiqueta;
    }
    // Getters i Setters.
    public int getNumeroCarrer() {
        return this.numeroCarrer;
    }
    public String getCarrer() {
        return this.carrer;
    }
    public String getCiutat() {
        return this.ciutat;
    }
    public String getCodiPostal() {
        return this.codiPostal;
    }
    public String getPais() {
        return this.pais;
    }
    public String getEtiqueta() {
        return this.etiqueta;
    }

    // Setters
    public void setNumeroCarrer(int _numeroCarrer) {
        this.numeroCarrer = _numeroCarrer;
    }
    public void setCarrer(String _carrer) {
        this.carrer = _carrer;
    }
    public void setCiutat(String _ciutat) {
        this.ciutat = _ciutat;
    }
    public void setCodiPostal(String _codiPostal) {
        this.codiPostal = _codiPostal;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public void setEtiqueta(String _etiqueta) {
        this.etiqueta = _etiqueta;
    }

    // Mètodes.
    // Mètodes Comúns
    @Override
    public String toString() {
        return "\t" + this.etiqueta.toLowerCase() + ":\t" +
                this.getCarrer() + ", " + this.getNumeroCarrer() + " | " +
                getCodiPostal() + " - " + getCiutat() + " | " +
                getPais();
    }

//    @Override
//    public String toString() {
//        return  "\t" + this.etiqueta.toLowerCase() + ":\t" + this.numeroTelefon;
//    }
}
