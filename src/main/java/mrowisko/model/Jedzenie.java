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
        int iloscZebrana = Math.min(zasoby, maks);// maks ilosc dla 1 mróweczki
        zasoby -=iloscZebrana;
        return iloscZebrana;
    }
    public void dodaj(int ile) {
        this.zasoby += ile; // dodanie jedzenia poza mrowiskiem do zasobów
    }

    public int getZasoby()
    {
        return zasoby;
    }
}
