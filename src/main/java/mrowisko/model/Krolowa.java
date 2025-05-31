package mrowisko.model;
import java.util.Random;


public class Krolowa extends Mrowka
{
    private static Random szanse = new Random(); //losowa liczba do wyklucia zolnierz/robotnica

    public Krolowa()
    {
        super(200);  // energia startowa, tu trzeba dodac że złożenia jaja = mniejsza energia. Może nowa klasa "jaja"?
    }

    public void ruch(Mrowisko mrowisko)//referencja do mrowiska, żeby krolowa mogła edytowac/zarzadac mrowiskiem
    {
        if (!isCzyZyje()) return;
        zwiekszWiek();
        zmniejszEnergie(15);

        int kosztZlozeniaJaja=100; //Tu mozemy edytowac ile za złożenie jaja
        int kiedyLeczyc = 50;
        int kosztLeczenia=25;
        int zyskEnergi=50;

        if (getEnergia() < kiedyLeczyc)
        {
            mrowisko.setZasoby(mrowisko.getZasoby() - kosztLeczenia);
            zmniejszEnergie(-zyskEnergi);
            System.out.println("Krolowa wykorzystala zasoby i uleczyla sie o " + zyskEnergi + " energii, aktualna ilosc energii to " + getEnergia() );
        }

        if (mrowisko.getZasoby() >= kosztZlozeniaJaja) //pozniej trzeba dodac "szanse" (mysle ze 30%?) aby z tych mrowek urodził sie zolnierz
        {
            mrowisko.setZasoby(mrowisko.getZasoby() - kosztZlozeniaJaja);

            if (szanse.nextDouble()<0.30)
            {
                mrowisko.dodajMrowke(new Zolnierz(15));
                zmniejszEnergie(20);
                System.out.println("Krolowa zlozyla jajo. Jej aktualna energia to  " + getEnergia());
                System.out.println("Z jaja wyklul sie zolnierz");
            }

            else
            {
                mrowisko.dodajMrowke(new Robotnica());
                zmniejszEnergie(20);
                System.out.println("Krolowa zlozyla jajo. Jej aktualna energia to  " + getEnergia());
                System.out.println("Z jaja wyklula sie robotnica");
            }


        }

        else
        {
            System.out.println("Za malo zasobow na zlozenia jaja");
        }
    }



}
