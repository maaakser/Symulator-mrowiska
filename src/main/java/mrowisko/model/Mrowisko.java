package mrowisko.model;

import java.util.ArrayList;
import java.util.List;


public class Mrowisko
{
    private int zasoby;
    private List<Mrowka> mrowki;

    public Mrowisko(int zasobyStart)
    {
        this.zasoby = zasobyStart;
        this.mrowki = new ArrayList<>();
    }

    public void zarzadzaj()
    {
        List<Mrowka> snapshot = new ArrayList<>(mrowki);   //kopię listy bo inaczej wywalalo błąd
        for (Mrowka m : snapshot)
        {
            m.ruch(this); //referencja do mrowiska aby krolowa mogla "edytowac" mrowisko

            if (!m.isCzyZyje())
            {
                mrowki.remove(m);  // jeśli któraś mrówka padła, usuwamy
                System.out.println("Mrowka #" + m.getId() + " umarla.");
            }
        }
    }


    public void dodajMrowke(Mrowka mrowka)
    {
        mrowki.add(mrowka);
    }


    public void usunMrowke()
    {
        if (!mrowki.isEmpty())
        {
            mrowki.remove(0);
        }
    }

    //getter
    public int getZasoby()
    {
        return zasoby;
    }

    public List<Mrowka> getMrowki()
    {
        return mrowki;
    }

    //setter
    public void setZasoby(int zasoby)
    {
        this.zasoby = zasoby;
    }

    public void setMrowki(List<Mrowka> mrowki)
    {
        this.mrowki = mrowki;
    }
}
