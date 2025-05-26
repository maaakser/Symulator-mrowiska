package mrowisko.model;


public abstract class Mrowka
{
    private final int id;
    private boolean czyZyje;
    private int energia;
    private int wiek;

    private static int counter = 0;


    public Mrowka(int energiaStart)
    {
        this.id = ++counter;
        this.energia = energiaStart;
        this.wiek = 0;
        this.czyZyje = true;
    }


    public abstract void ruch();


    public void smierc()
    {
        this.czyZyje = false;
    }


    public int getId()
    {
        return id;
    }

    public boolean isCzyZyje()
    {
        return czyZyje;
    }

    public int getEnergia()
    {
        return energia;
    }

    public void zmniejszEnergie(int ilosc)
    {
        this.energia -= ilosc;
        if (this.energia <= 0)
        {
            smierc();
        }
    }

    public int getWiek()
    {
        return wiek;
    }

    public void zwiekszWiek()
    {
        this.wiek++;
    }
}
