package mrowisko.model;


public class Jedzenie
{
    private int zasoby;

    public Jedzenie(int zasobyStart)
    {
        this.zasoby = zasobyStart;
    }

    public int zbierz(int maks)
    {
        int iloscZebrana = Math.min(zasoby, maks);// Maksymalna ilość jedzenia która może nieść mrówka
        zasoby -=iloscZebrana;
        return iloscZebrana;
    }

    /*
    dodanie jedzenia poza mrowiskiem do zasobów
     */

    public void dodaj(int ile) {
        this.zasoby += ile;
    }

    public int getZasoby()
    {
        return zasoby;
    }
}
