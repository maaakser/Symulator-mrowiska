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
        System.out.println("Robotnica #" + getId() + " ruch wykonany. Energia: " + getEnergia());
    }


}
