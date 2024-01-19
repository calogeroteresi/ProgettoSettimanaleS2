package org.example;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Archivio {

    private List<ElementoArchivio> archivio = new ArrayList<>();

    // Altre variabili e costanti necessarie

    public void aggiungiElemento(ElementoArchivio elemento) {
        archivio.add(elemento);
    }

    public void rimuoviElementoPerISBN(long isbn) {
        archivio = archivio.stream()
                .filter(elemento -> elemento.getIsbn() != isbn)
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

            String stringaElementi = archivio.stream()
                    .map(elemento -> {
                        if (elemento instanceof Libro) {
                            return elemento.getIsbn() + "@" + elemento.getTitolo() + "@" +
                                    elemento.getAnnoPubblicazione() + "@" + elemento.getNumeroPagine() + "@" +
                                    ((Libro) elemento).getAutore() + "@" + ((Libro) elemento).getGenere();
                        } else if (elemento instanceof Rivista) {
                            return elemento.getIsbn() + "@" + elemento.getTitolo() + "@" +
                                    elemento.getAnnoPubblicazione() + "@" + elemento.getNumeroPagine() + "@" +
                                    ((Rivista) elemento).getPeriodicita();
                        } else {
                            return elemento.getIsbn() + "@" + elemento.getTitolo() + "@" +
                                    elemento.getAnnoPubblicazione() + "@" + elemento.getNumeroPagine();
                        }
                    })
                    .collect(Collectors.joining("#"));

            FileUtils.writeStringToFile(file, stringaElementi, Charset.defaultCharset(), true);
        } catch (IOException e) {
            e.printStackTrace();
            // Puoi gestire l'eccezione in modo diverso, ad esempio, stampando un messaggio di errore
        }
    }
    public void caricaElementiDaDisco() {
        try {
            File file = new File("dirArchivio/elementi.txt");
            if (file.exists()) {
                String fileContent = FileUtils.readFileToString(file, Charset.defaultCharset());
                String[] elementiArray = fileContent.split("#");

                archivio = Arrays.stream(elementiArray)
                        .map(this::creaElementoDaStringa)
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ElementoArchivio creaElementoDaStringa(String elementoStringa) {
        String[] attributi = elementoStringa.split("@");
        long isbn = Long.parseLong(attributi[0]);
        String titolo = attributi[1];
        int annoPubblicazione = Integer.parseInt(attributi[2]);
        int numeroPagine = Integer.parseInt(attributi[3]);

        return new ElementoArchivio(isbn, titolo, annoPubblicazione, numeroPagine);
    }

    public void stampaDataArchivio() {
        archivio.forEach(System.out::println);
    }
}


