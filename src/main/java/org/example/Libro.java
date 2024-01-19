package org.example;

public class Libro extends ElementoArchivio {
    private String autore;
    private String genere;



    public Libro(long isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "ISBN=" + getIsbn() +
                ", Titolo='" + getTitolo() + '\'' +
                ", Anno di Pubblicazione=" + getAnnoPubblicazione() +
                ", Numero Pagine=" + getNumeroPagine() +
                ", Autore='" + autore + '\'' +
                ", Genere='" + genere + '\'' +
                '}';
    }
}
