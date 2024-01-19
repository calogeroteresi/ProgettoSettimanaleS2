package org.example;

import java.util.HashSet;
import java.util.Set;

public class ElementoArchivio {

    private long isbn;
    private String titolo;
    private int annoPubblicazione;



    private int numeroPagine;

    public static Set<Long> isbnsPresenti = new HashSet<>();

    public ElementoArchivio(long isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        validateIsbn(isbn);

        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;

        isbnsPresenti.add(isbn);
    }


    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    private void validateIsbn(long isbn) {
        if (isbn <= 0) {
            throw new IllegalArgumentException("ISBN deve essere un valore positivo.");
        }

        String isbnString = String.valueOf(isbn);

        if (isbnString.length() != 13 || isbnString.charAt(0) == '0' || !isbnString.matches("\\d+")) {
            throw new IllegalArgumentException("ISBN non valido: " + isbn);
        }

        if (isbnGiaPresente(isbn)) {
            throw new DuplicateIsbnException("ISBN già presente: " + isbn);
        }
    }

    boolean isbnGiaPresente(long isbn) {
        return isbnsPresenti.contains(isbn);
    }

    @Override
    public String toString() {
        return "ElementoArchivio{" +
                "isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
