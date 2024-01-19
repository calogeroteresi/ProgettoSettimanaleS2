package org.example;

public class Rivista extends ElementoArchivio {
    private Periodicita periodicita;

    public Rivista(long isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "ISBN=" + getIsbn() +
                ", Titolo='" + getTitolo() + '\'' +
                ", Anno di Pubblicazione=" + getAnnoPubblicazione() +
                ", Numero Pagine=" + getNumeroPagine() +
                ", Periodicita=" + getPeriodicita() +'\'' +
                '}';
    }
}
