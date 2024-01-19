package org.example;

import java.util.HashSet;
import java.util.Set;

public class ElementoArchivio {

    private long isbn;
    private String titolo;
    private int annoPubblicazione;



    private int numeroPagine;

    private static Set<Long> isbnsPresenti = new HashSet<>();

    public ElementoArchivio(long isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        if (isbnValido(isbn) && !isbnGiaPresente(isbn)) {
            this.isbn = isbn;
            isbnsPresenti.add(isbn);
        } else {
            throw new IllegalArgumentException("ISBN non valido o già presente: " + isbn);
        }
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
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

    private boolean isbnValido(long isbn) {
        String isbnString = String.valueOf(isbn);

        if (isbnString.length() != 13) {
            return false;
        }

        if (isbnString.charAt(0) == '0') {
            return false;
        }

        for (int i = 0; i < isbnString.length(); i++) {
            if (!Character.isDigit(isbnString.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private boolean isbnGiaPresente(long isbn) {
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
