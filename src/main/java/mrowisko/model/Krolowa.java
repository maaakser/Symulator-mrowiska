package mrowisko.model;
import java.util.Random;


public class Krolowa extends Mrowka
{
    private static Random szanse = new Random(); //losowa liczba do wyklucia zolnierz/robotnica

    public Krolowa()
    {
        super(200);  // energia startowa
    }

    public void ruch(Mrowisko mrowisko)//referencja do mrowiska, żeby krolowa mogła edytowac/zarzadac mrowiskiem
    {
        if (!isCzyZyje()) return;
        zmniejszEnergie(15);


        /*
        Koszty życia Królowej
         */
        int kosztZlozeniaJaja=100;
        int kiedyLeczyc = 50;
        int kosztLeczenia=25;
        int zyskEnergi=50;

        /*
        Leczenie królowej
         */
        if (getEnergia() < kiedyLeczyc && mrowisko.getZasoby()>=kosztLeczenia)
        {
            mrowisko.setZasoby(mrowisko.getZasoby() - kosztLeczenia);
            zmniejszEnergie(-zyskEnergi);
            System.out.println("Krolowa wykorzystala zasoby i uleczyla sie o " + zyskEnergi + " energii, aktualna ilosc energii to " + getEnergia() );
        }


        /*
        35% szans na urodzenie zolnierza a reszta na urodzenie robotnicy
         */
        if (mrowisko.getZasoby() >= kosztZlozeniaJaja)
        {
            mrowisko.setZasoby(mrowisko.getZasoby() - kosztZlozeniaJaja);

            if (szanse.nextDouble()<0.35)
            {
                mrowisko.dodajMrowke(new Zolnierz(15));
                zmniejszEnergie(20);
                System.out.println("Krolowa zlozyla jajo. Jej aktualna energia to  " + getEnergia());
                System.out.println("Z jaja wyklul sie zolnierz");
            }

            else
            {
                mrowisko.dodajMrowke(new Robotnica());
                zmniejszEnergie(10);
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
