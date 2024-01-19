package org.example;

import java.util.List;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Archivio archivio = new Archivio();


        Libro libro1 = new Libro(1125487545874L, "Oltre le stelle", 2022, 300, "Alessia Rossi", "Narrativa Fantascientifica");
        Libro libro2 = new Libro(1541142574541L, "L'ombra del passato", 2022, 250, "Marco Bianchi", "Thriller Psicologico");
        Libro libro3 = new Libro(2541544544874L, "Il segreto della rosa", 2022, 300, "Laura Ferrari", "Romanzo Storico");
        Libro libro4 = new Libro(1541842574541L, "L'ultimo viaggio", 2022, 250, "Luca Martini", "Avventura");

        Rivista rivista1 = new Rivista(2542644544874L, "Mondo Scientifico", 2022, 50, Periodicita.MENSILE);
        Rivista rivista2 = new Rivista(2542644544875L, "Arte e Cultura Oggi", 2022, 50, Periodicita.SEMESTRALE);
        Rivista rivista3 = new Rivista(2542644544876L, "Scienza in Pratica", 2022, 50, Periodicita.MENSILE);


        archivio.aggiungiElemento(libro1);
        archivio.aggiungiElemento(libro2);
        archivio.aggiungiElemento(libro3);
        archivio.aggiungiElemento(libro4);
        archivio.aggiungiElemento(rivista1);
        archivio.aggiungiElemento(rivista2);
        archivio.aggiungiElemento(rivista3);



        // Stampa la data dell'archivio
        System.out.println("Dati dell'archivio:");
        archivio.stampaDataArchivio();

        // Salva gli elementi su disco
        archivio.salvaElementiSuDisco();

        // Rimuovi un elemento dall'archivio
        long isbnDaRimuovere = 1125777545874L;
        archivio.rimuoviElementoPerISBN(isbnDaRimuovere);
        System.out.println("Elemento rimosso con ISBN: " + isbnDaRimuovere);

        // Ricerca per ISBN
        long isbnDaRicercare = 1541142574541L;
        Optional<ElementoArchivio> elementoTrovato = archivio.ricercaPerISBN(isbnDaRicercare);
        if (elementoTrovato.isPresent()) {
            System.out.println("Elemento trovato per ISBN " + isbnDaRicercare + ": " + elementoTrovato.get());
        } else {
            System.out.println("Nessun elemento trovato per ISBN " + isbnDaRicercare);
        }

        // Ricerca per anno di pubblicazione
        int annoDaRicercare = 2022;
        List<ElementoArchivio> elementiPerAnno = archivio.ricercaPerAnnoPubblicazione(annoDaRicercare);
        System.out.println("Elementi trovati per anno " + annoDaRicercare + ":");
        elementiPerAnno.forEach(System.out::println);

        // Ricerca per autore
        String autoreDaRicercare = "Autore2";
        List<ElementoArchivio> elementiPerAutore = archivio.ricercaPerAutore(autoreDaRicercare);
        System.out.println("Elementi trovati per autore " + autoreDaRicercare + ":");
        elementiPerAutore.forEach(System.out::println);

        // Carica gli elementi da disco
        archivio.caricaElementiDaDisco();

        // Stampa la data dell'archivio aggiornata
        System.out.println("Dati dell'archivio dopo il caricamento da disco:");
        archivio.stampaDataArchivio();

    }
}