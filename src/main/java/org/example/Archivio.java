package org.example;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;


import java.util.stream.Collectors;

public class Archivio {

    private List<ElementoArchivio> archivio = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger("Archivio");

    public void aggiungiElemento(ElementoArchivio elemento) {
        archivio.add(elemento);
    }

    public void rimuoviElementoPerISBN(long isbn) {
        archivio = archivio.stream()
                .filter(elemento -> {
                    boolean isToBeRemoved = elemento.getIsbn() == isbn;
                    if (isToBeRemoved) {
                        ElementoArchivio.isbnsPresenti.remove(isbn);
                    }
                    return !isToBeRemoved;
                })
                .collect(Collectors.toList());
    }


    public Optional<ElementoArchivio> ricercaPerISBN(long isbn) {
        return archivio.stream()
                .filter(elemento -> elemento.getIsbn() == isbn)
                .findFirst();
    }

    public List<ElementoArchivio> ricercaPerAnnoPubblicazione(int anno) {
        return archivio.stream()
                .filter(elemento -> elemento.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    public List<ElementoArchivio> ricercaPerAutore(String autore) {
        return archivio.stream()
                .filter(elemento -> elemento instanceof Libro)
                .map(elemento -> (Libro) elemento)
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }


    public void salvaElementiSuDisco() {
        try {
            File file = new File("dirArchivio/elementi.txt");

            List<Long> isbnsInseritiFile = new ArrayList<>();

            String stringaElementi = archivio.stream()
                    .map(elemento -> {
                        long isbn = elemento.getIsbn();

                        if (isbnsInseritiFile.contains(isbn)) {
                            System.out.println("ISBN già presente nel file, ignoro l'elemento: " + isbn);
                            return null;
                        }

                        isbnsInseritiFile.add(isbn);

                        if (elemento instanceof Libro libro) {
                            return libro.getIsbn() + " @ " + libro.getTitolo() + " @ " +
                                    libro.getAnnoPubblicazione() + " @ " + libro.getNumeroPagine() + " @ " +
                                    libro.getAutore() + " @ " + libro.getGenere();
                        } else if (elemento instanceof Rivista rivista) {
                            return rivista.getIsbn() + " @ " + rivista.getTitolo() + " @ " +
                                    rivista.getAnnoPubblicazione() + " @ " + rivista.getNumeroPagine() + " @ " +
                                    rivista.getPeriodicita();
                        } else {
                            return "";
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.joining("#\n"));

            FileUtils.writeStringToFile(file, stringaElementi, Charset.defaultCharset(), true);

            System.out.println("ISBN inseriti nel file: " + isbnsInseritiFile);
            logger.info("ISBN inseriti nel file: {}", isbnsInseritiFile);
        } catch (IOException e) {
            logger.error("Errore durante il salvataggio degli elementi su disco", e);
        }
    }




    public void caricaElementiDaDisco() {
        try {
            File file = new File("dirArchivio/elementi.txt");
            if (file.exists()) {
                String fileContent = FileUtils.readFileToString(file, Charset.defaultCharset());
                String[] elementiArray = fileContent.split("#");

                Arrays.stream(elementiArray)
                        .map(this::creaElementoDaStringa)
                        .filter(Objects::nonNull)
                        .filter(elemento -> !archivio.contains(elemento))
                        .forEach(this::aggiungiElemento);
            }
        } catch (IOException e) {
            logger.error("Errore durante il salvataggio degli elementi dal disco", e);
        }
    }



    private ElementoArchivio creaElementoDaStringa(String elementoStringa) {
        elementoStringa = elementoStringa.trim();
        String[] attributi = elementoStringa.split("@");
        long isbn = Long.parseLong(attributi[0].trim());
        String titolo = attributi[1].trim();
        int annoPubblicazione = Integer.parseInt(attributi[2].trim());
        int numeroPagine = Integer.parseInt(attributi[3].trim());

        if (ElementoArchivio.isbnsPresenti.contains(isbn)) {
            System.out.println("ISBN già presente nell'archivio, ignoro l'elemento: " + isbn + "proveniente dal file");
            return null;
        }

        if (attributi.length == 6) {
            String autore = attributi[4].trim();
            String genere = attributi[5].trim();
            return new Libro(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);
        } else if (attributi.length == 5) {
            Periodicita periodicita = Periodicita.valueOf(attributi[4].trim());
            return new Rivista(isbn, titolo, annoPubblicazione, numeroPagine, periodicita);
        } else {
            return new ElementoArchivio(isbn, titolo, annoPubblicazione, numeroPagine);
        }
    }



    public void stampaDataArchivio() {
        archivio.forEach(System.out::println);
    }
}


