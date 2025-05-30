package mrowisko.model;

import java.util.List;

public class Zagrozenie
{
    private String typ;
    private int sila;


    public Zagrozenie(String typ, int sila) {
        this.typ = typ;
        this.sila = sila;
    }


    public void atakuj(Mrowisko mrowisko)
    {

        System.out.println("Zagrozenie '" + typ + "' atakuje z sila " + sila);
        List<Mrowka> lista = mrowisko.getMrowki();

        for (int i = 0; i < lista.size(); i++)
        {
            Mrowka m = lista.get(i);
            if (m instanceof Zolnierz && m.isCzyZyje()) //sprawdzanie czy jest zolnierzem i czy zyje, bo zagrozenie priorytetuje aby atakowac zolnierza
            {
                Zolnierz z = (Zolnierz) m;//rzutowanie aby przejac metody

                if (sila> z.getSila()) //jezeli zolnierz ma mniejsza sile to zagroznie atakuje za tyle hp co ma sile
              {
                  z.zmniejszEnergie(sila);
                  System.out.println("zolnierz #" + z.getId() + " stracil " + sila + " energii. Pozostalo: " + z.getEnergia());
              }

              else //zagrozenie za slabe
              {
                  System.out.println("Zagrozenie ma za mala sile zeby pokonac zolnierza #" + z.getId());
              }
                return;
            }
        }

        if (!lista.isEmpty())
        {
            Mrowka removed = lista.remove(0);
            System.out.println("Brak zolnierzy. Usunieto mrowke #" + removed.getId()); //jak nie ma zolnierza to "zabijamy" mrówke, bo jest słabsza więc od razu umiera
        }
    }


    public String getTyp()
    {
        return typ;
    }

    public void setTyp(String typ)
    {
        this.typ = typ;
    }

    public int getSila()
    {
        return sila;
    }

    public void setSila(int sila)
    {
        this.sila = sila;
    }
}