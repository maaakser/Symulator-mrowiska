package mrowisko.model;

public class Robotnica extends Mrowka
{


    public Robotnica()
    {
        super(100);  // przykładowa, narazie wpływa tylko na to ile "przeżyje", jedzenie nie dodaje nic
    }


    public void ruch(Mrowisko mrowisko) //referencja do mrowiska
    {

        if (!isCzyZyje())
        {
            return;
        }

        zwiekszWiek();
        zmniejszEnergie(5);

        Jedzenie j =mrowisko.getJedzenie();
        int zebrane = j.zbierz(15); // maksymalna ilosc jedzenia dla 1 robotnicy
        mrowisko.setZasoby(mrowisko.getZasoby() + zebrane );
        if (zebrane>0)
            System.out.println("Robotnica #" + getId() + " zebrala " + zebrane + " jednostek jedzenia.");
        else
            System.out.println("Robotnica #" + getId() + " nie znalazla jedzenia.");
    }


}
