# Correcció de la part del codi del 1r parcial d'<u>EDA</u> del curs 2023-2024 

El repositori era el següent:

## [https://github.com/jpardo20/agenda-adressa-etiqueta](https://github.com/jpardo20/agenda-adressa-etiqueta)

### Estructura del projecte original

```bash
.
├── aplicacio
│   └── AppAgenda.java
├── classes
│   ├── Adressa.java
│   ├── Agenda.java
│   ├── Contacte.java
│   ├── GuardarRecuperar.java
│   │   - public static LinkedList<Contacte> recuperarAgenda(String nomArxiu)
│   │   - public static boolean guardarAgenda(String nomArxiu) throws IOException
│   ├── Teclat.java
│   └── Telefon.java
├── eines
│   └── EinesJoan.java
└── menu
    └── Menu.java
```

I el que es va demanar era que a partir del que hi havia al mètode **`recuperarAgenda(String nomArxiu)`**, desenvolupéssiu el mètode **`guardarAgenda(String nomArxiu)`**.

<details>
<summary>Pitja per veure el mètode <code>recuperarAgenda(String nomArxiu)</code></summary>

```java
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
```
</details>

A l'hora de corregir l'exàmen i per tal de poder ser més precís i no suspendre i a provar en funció de si el que teníeu desenvolupat funcionava (aprovat) o no funcionava (suspès). Sigui dit de pas, aquest ha estat el motiu principal pel qual he trigat tant a donar les notes. Vaig decidir modificar l'estructura de la següent manera:    

```bash
.
├── aplicacio
│   └── AppAgenda.java
├── classes
│   ├── Adressa.java
│   ├── Agenda.java
│   ├── Contacte.java
│   ├── GuardarRecuperar.java
│   │   - public static LinkedList<Contacte> recuperarAgenda(String nomArxiu)
│   ├── GuardarRecuperarSol.java
│   ├── Teclat.java
│   └── Telefon.java
├── eines
│   └── EinesJoan.java
├── menu
│   └── Menu.java
├── README.md
└── versioAlumnes
    └── SolucioAlumnes.java
        - public static boolean guardarAgenda(String nomArxiu) throws IOException
```

A on, al paquet **`versioAlumnes`**, he col·locat el mètode **`guardarAgenda`** que m'havíeu d'enviar, i he afegit un seguit de línies de codi del programa **`AppAgenda.java`**, que cal executar, per tal de poder corregir de manera més acorada i ràpida el vostre codi.

En aquest programa, he afegit els següents canvis:

1) He creat el mètode **`preparaFitxerAgenda`** que permet _**reparar**_ el fitxer a on hi ha guardada una **agenda**, per si la darrera correcció no ha estat correcta,
2) he creat el mètode **`afegirContacteFix`**, que afegeix un nou contacte a l'agenda que tenia només el contacte creat amb el mètode **`preparaFitxerAgenda`**. Aquest nou contacte conté tres telèfons amb les seves corresponents etiquetes i dues adreces també amb les seves corresponents etiquetes,
3) he creat amb el mètode el **`mostraContingutFitxer`** que mostra per consola el contingut d'un fitxer i per últim
4) he modificat el codi del programa **`AppAgenda.java`**.

<details>
<summary>Pitja per veure el detall dels canvis que he fet en el codi del programa <code>AppAgenda.java</code></summary>

```java
// INICI afegit per la correcció
// Funció que prepara el fitxer nomFitxerAmbAgenda amb
// les dades d'un contacte amb 3 telèfons i dues adreces.

String nomFitxerCorreccio = NOM_FITXER + EXTENSIO_FITXER;
preparaFitxerAgenda(nomFitxerCorreccio);
mostraContingutFitxer(nomFitxerCorreccio);
pitjaIntroPerContinuar();

// Funció que afegeix un contacte a l'agenda
// actual, amb 2 telèfons i dues adreces.
afegirContacteFix();
System.out.println("S'executa afegirContacteFix");
pitjaIntroPerContinuar();

mostrarContactes();
System.out.println("Mostrar després d'executar afegirContacteFix");
pitjaIntroPerContinuar();

System.out.println("S'executa guardarAgenda de l'alumne!");
guardarAgenda(nomFitxerCorreccio);

System.out.println("S'executa recuperarAgenda després del guardarAgenda de l'alumne!");
recuperarAgenda(nomFitxerCorreccio);

System.out.println("Mostrar després d'executar guardarAgenda de l'alumne!");
mostrarContactes();
System.out.printf("FINAL");
pitjaIntroPerContinuar();

System.out.printf("Mostra fitxer final");

System.exit(0);
// FINAL afegit per la correcció
```

Aquest codi el que fa és:
1) Crea la variable (**`nomFitxerCorreccio`**) amb el nom del fitxer que conté l'agenda que buscarà el programa,
```java
String nomFitxerCorreccio = NOM_FITXER + EXTENSIO_FITXER;
```

2) Crida el mètode **`preparaFitxerAgenda(nomFitxerCorreccio)`** per arreglar el fitxer que conté l'agenda,
```java
preparaFitxerAgenda(nomFitxerCorreccio);
```

3) Crida el mètode **`mostraContingutFitxer(nomFitxerCorreccio)`** per mostrar el contingut del fitxer, per veure que tot és correcte.
```java
mostraContingutFitxer(nomFitxerCorreccio);
```

4) Crida el mètode **`pitjaIntroPerContinuar();`** per aturar l'execució i comprovar que tot és correcte.
```java
pitjaIntroPerContinuar();
```

En aquest punt, ja tenim el programa en execució i el fitxer que buscarà el programa que conté una agenda en un format correcte.

5) Crida el mètode **`afegirContacteFix()`**, que afegirà un nou contacte al fitxer. Això ens servirà per poder testejar el mètode creat per l'alumne.
```java
afegirContacteFix();
```

6) Es mostra per consola el literal **`S'ha executat afegirContacteFix`**, per indicar en quin punt ens trobem, 
```java
System.out.println("S'ha executat afegirContacteFix");
```

7) Es crida el mètode **`pitjaIntroPerContinuar();`** per aturar l'execució i comprovar que tot és correcte.
```java
pitjaIntroPerContinuar();
```

8) Es crida el mètode **`mostrarContactes()`**, per comprovar que els dos contactes es troben correctament afegits al programa.
```java
mostrarContactes();
```

9) Es mostra per consola el literal **`Mostrar després d'executar afegirContacteFix`**, per indicar en quin punt ens trobem,
```java
System.out.println("Mostrar després d'executar afegirContacteFix");
```

10) Es crida el mètode **`pitjaIntroPerContinuar();`** per aturar l'execució i comprovar que tot és correcte.
```java
pitjaIntroPerContinuar();
```

Fins aquí encara no he provat el vostre codi.

11) Es mostra per consola el literal **`S'executa guardarAgenda de l'alumne!`**, per indicar que executarem el codi de l'alumne.
```java
System.out.println("S'executa guardarAgenda de l'alumne!");
```

12) Es crida el mètode **`guardarAgenda(nomFitxerCorreccio);`** que ha proporcionat l'alumne.
```java
guardarAgenda(nomFitxerCorreccio);
```

13) Es mostra per consola el literal **`S'executa recuperarAgenda després del guardarAgenda de l'alumne!`**, per indicar que procedirem a comprovar si el mètode **`guardarAgenda(nomFitxerCorreccio);`** que ha proporcionat l'alumne funciona o no.
```java
System.out.println("S'executa recuperarAgenda després del guardarAgenda de l'alumne!");
```

12) Es crida el mètode **`recuperarAgenda(nomFitxerCorreccio);`** que determinarà si el mètode **`guardarAgenda`** de l'alumne ha funcionat correctament. 

```java
recuperarAgenda(nomFitxerCorreccio);
```

Un cop arribats a aquest punt ja podem comprovar si el mètode **`guardarAgenda`** de l'alumne ha funcionat correctament.
</details>

Un cop feta l'explicació del programa **`AppAgenda.java`**, cal que en el fitxer **`SolucioAlumnes.java`** del paquet **`versioAlumnes`**, en el mètode **`guardarAgenda`**, copieu el vostre codi i executeu el programa **`AppAgenda.java`**.

D'aquesta manera podreu comprovar si el codi que m'heu proporcionat funciona o no. 

Si el programa no ha fallat, no vol dir que hagi funcionat tot correctament, ja que, com s'ha donat el cas, ens hem trobat que alumnes han mostrat el codi per consola, i això no ha fet el que es demanava, però tampoc ha fet que el programa fallès.

Ens hem trobat amb diferents escenaris:

1) **Primer escenari**, _**no s'ha tancat el canal**_, és a dir que l'alumne no ha posat cridat al mètode **`close()`**. Això ha fet que no es guardi res en el fitxer. En aquest cas, per no posar un zero directament, s'ha mirat si afegint el mètode **`close()`**, si que funcionava i si ha estat així s'ha puntuat amb un 4, ja que el programa no ha funcionat, però s'ha fet prou bé.

2) **Segon escenari**, _**no s'ha tancat el canal**_, és a dir que l'alumne no ha posat cridat al mètode **`close()`**. Això ha fet que no es guardi res en el fitxer. En aquest cas, per no posar un zero directament, s'ha mirat si afegint el mètode **`close()`**, si que funcionava i si no ha funcionat, s'ha puntuat amb una nota inferior al 4 depenent del motiu que ha fet que no funcioni.

3) **Tercer escenari**, _**no s'ha guardat algun dels números (el del telèfon, o el del carrer) amb format `String`**_. Això ha fet que no funcionés correctament el mètode **`recuperarAgenda`**, ja que no ha llegit un enter, sinó que ha llegit algun altre valor i no ha pogut afegir el número a la lògica de l'agenda que s'esperava.

4) **Quart escenari**, _**no s'ha guardat alguna de les etiquetes (el del telèfon, o de l'adreça)**_. Això ha fet que no funcionés correctament el mètode **`recuperarAgenda`**, ja que no ha llegit l'etiqueta, sinó que ha llegit algun altre valor i no ha pogut afegir l'etiqueta a la lògica de l'agenda que s'esperava.

5) **Cinquè escenari**, _**no s'ha escrit un salt de línia**_. Això ha fet que no funcionés correctament el mètode **`recuperarAgenda`**, ja que no ha llegit la línia correctament i no ha pogut processar correctament la lògica de l'agenda que s'esperava.

6) **Sisè escenari**, _**no s'ha escrit alguna de les etiquetes (el del telèfon, o de l'adreça)**_. Això ha fet que no funcionés correctament el mètode **`recuperarAgenda`**, ja que no ha llegit correctament quan s'acabaven els telèfons, o les adreces i no s'ha pogut processar correctament la lògica de l'agenda que s'esperava.

Cal comentar que en tots els casos que **no ha funcionat correctament el codi**, s'ha verificat quina era la naturalesa de l'error, i s'ha avaluat en funció d'aquesta.

Però **SEMPRE** s'ha avaluat a partir de **si el mètode ha funcionat o no**. I si **no ha funcionat**, per un petit detall s'ha puntuat amb un **`4`**, ja que **no ha funcionat**, però si el motiu pel qual no ha funcionat **ha estat major**, llavors s'ha reduït la puntuació en funció de quin o quins han estat el motiu o els motius pels quals no ha funcionat. 
