package org.example;

import java.util.List;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Archivio archivio = new Archivio();
        Archivio archivioDaFile = new Archivio();

        // Carica gli elementi da disco
        archivioDaFile.caricaElementiDaDisco();

        Libro libro1 = new Libro(3541142574541L, "La danza delle ombre", 2020, 280, "Simona Russo", "Romanzo Thriller");
        Libro libro2 = new Libro(4541544544874L, "L'isola dimenticata", 2018, 320, "Giovanni Conti", "Avventura Misteriosa");
        Libro libro3 = new Libro(5541842574541L, "I segreti del passato", 2019, 270, "Francesca Neri", "Drammatico Psicologico");
        Libro libro4 = new Libro(6541842574541L, "L'enigma della cripta", 2015, 310, "Roberto Russo", "Mistero Investigativo");
        Rivista rivista1 = new Rivista(2542644544877L, "Tecnologia Avanzata", 2023, 60, Periodicita.MENSILE);
        Rivista rivista2 = new Rivista(2542644544878L, "Viaggi e Avventure", 2021, 40, Periodicita.SEMESTRALE);
        Rivista rivista3 = new Rivista(2542644544879L, "Cucina del Mondo", 2022, 30, Periodicita.MENSILE);


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
        String autoreDaRicercare = "Alessia Rossi";
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