package mrowisko.model;


public class Jedzenie
{
    private int zasoby;

    public Jedzenie(int zasobyStart)
    {
        this.zasoby = zasobyStart;
    }

    public int zbierz()
    {
        int pobrane = zasoby;
        zasoby = 0;
        return pobrane;
    }

    public int getZasoby()
    {
        return zasoby;
    }
}
//Narazie niestety robotnica nie zbiera jedzenia ani nie dodaje