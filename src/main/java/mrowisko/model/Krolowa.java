package mrowisko.model;


public class Krolowa extends Mrowka
{
    public Krolowa()
    {
        super(200);  // energia startowa, tu trzeba dodac że złożenia jaja = mniejsza energia. Może nowa klasa "jaja"?
    }

    public void ruch()
    {
        if (!isCzyZyje()) return;
        zwiekszWiek();
        zmniejszEnergie(15);
        System.out.println("Krolowa #" + getId() + " zlozyla jajo. Energia: " + getEnergia());
    }
}
