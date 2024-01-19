package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Archivio archivio = new Archivio();

        ElementoArchivio libro1 = new Libro(1_125_487_545_874L, "Titolo Libro 1", 2022, 300, "Autore1", "Genere1");
        ElementoArchivio libro2 = new Libro(1_541_142_574_541L, "Titolo Libro 2", 2022, 250, "Autore2", "Genere2");
        ElementoArchivio rivista1 = new Rivista(2_541_544_544_874L, "Titolo Rivista 1", 2022, 50, Periodicita.MENSILE);

        archivio.aggiungiElemento(libro1);
        archivio.aggiungiElemento(libro2);
        archivio.aggiungiElemento(rivista1);
        archivio.stampaDataArchivio();
        archivio.salvaElementiSuDisco();
        archivio.caricaElementiDaDisco();


    }
}