package mrowisko.model;

public class Robotnica extends Mrowka
{


    public Robotnica()
    {
        super(100);  //energia startowa robotnicy
    }


    public void ruch(Mrowisko mrowisko) //referencja do mrowiska
    {

        if (!isCzyZyje())
        {
            return;
        }

        zmniejszEnergie(5);

        /*
        Zbieranie jedzenia do mrowiska,
        ustawiamy maksymalna ilosc jedzenia która moze zostac zebrana przez jedna mrówke
         */
        Jedzenie j =mrowisko.getJedzenie();
        int zebrane = j.zbierz(15);
        mrowisko.setZasoby(mrowisko.getZasoby() + zebrane );
        if (zebrane>0)
            System.out.println("Robotnica #" + getId() + " zebrala " + zebrane + " jednostek jedzenia.");
        else
            System.out.println("Robotnica #" + getId() + " nie znalazla jedzenia.");
    }


}
