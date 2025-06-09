package mrowisko.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mrowisko
{
    private int zasoby;
    private List<Mrowka> mrowki;
    private Jedzenie poleJedzenia;//jedzenie poza mrowiskiem

    public Mrowisko(int zasobyStart)
    {
        this.zasoby = zasobyStart;
        this.mrowki = new ArrayList<>();
        this.poleJedzenia= new Jedzenie(0);
    }


    /*
    co cykl pojawia się losowa ilosc zasobów w obrebie mrowiska,
    kopiujemy liste aby Java wywalała błędu,
    odnosimy się do mrowiska aby krolowa mogla "edytowac" mrowisko
    usuwamy mrówke jeżeli jej hp spadło ponizej 0
     */
    public void zarzadzaj()
    {
        poleJedzenia.dodaj(new Random().nextInt(100) + 5 );  //

        List<Mrowka> snapshot = new ArrayList<>(mrowki);
        for (Mrowka m : snapshot)
        {
            m.ruch(this);

            if (!m.isCzyZyje())
            {
                mrowki.remove(m);
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


    public int getZasoby()
    {
        return zasoby;
    }

    public Jedzenie getJedzenie() {return poleJedzenia;}

    public List<Mrowka> getMrowki()
    {
        return mrowki;
    }

    public void setZasoby(int zasoby)
    {
        this.zasoby = zasoby;
    }

}
