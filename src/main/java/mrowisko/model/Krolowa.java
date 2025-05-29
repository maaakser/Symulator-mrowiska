package mrowisko.model;


public class Krolowa extends Mrowka
{
    public Krolowa()
    {
        super(200);  // energia startowa, tu trzeba dodac że złożenia jaja = mniejsza energia. Może nowa klasa "jaja"?
    }

    public void ruch(Mrowisko mrowisko)//referencja do mrowiska, żeby krolowa mogła edytowac/zarzadac mrowiskiem
    {
        if (!isCzyZyje()) return;
        zwiekszWiek();
        zmniejszEnergie(15);
        System.out.println("Krolowa #" + getId() + " zlozyla jajo. Energia: " + getEnergia());

        int kosztZlozeniaJaja=100; //Tu mozemy edytowac ile za złożenie jaja

        if (mrowisko.getZasoby() >= kosztZlozeniaJaja) //pozniej trzeba dodac "szanse" (mysle ze 30%?) aby z tych mrowek urodził sie zolnierz
        {
            mrowisko.setZasoby(mrowisko.getZasoby() - kosztZlozeniaJaja);
            mrowisko.dodajMrowke(new Robotnica());
            System.out.println("Nowa robotnica urodzona. Zuzyto "+kosztZlozeniaJaja+" zasobow. Pozostalo: "+mrowisko.getZasoby());
        }

        else
        {
            System.out.println("Za malo zasobow na zlozenia jaja");
        }
    }



}
