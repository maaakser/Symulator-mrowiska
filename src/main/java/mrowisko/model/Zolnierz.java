package mrowisko.model;

public class Zolnierz extends Mrowka
{
    private int sila;


    public Zolnierz(int sila)
    {
        super(120);
        this.sila = sila;
    }


    public void ruch(Mrowisko mrowisko) //referencja do mrowiska
    {
        if (!isCzyZyje()) return;

        zwiekszWiek();
        zmniejszEnergie(8);
        System.out.println("zolnierz #" + getId() + " patroluje. Energia: " + getEnergia() + " Jego aktualna sila to: "+ sila);
    }
}
