package mrowisko;

import mrowisko.model.Krolowa;
import mrowisko.model.Mrowisko;
import mrowisko.model.Robotnica;
import mrowisko.model.Zolnierz;
import mrowisko.model.Zagrozenie;

import java.util.Random;


public class Symulacja {
    private int czasTrwania;
    private boolean czyZagrozenia;
    private Mrowisko mrowisko;
    private Random random = new Random();


    public Symulacja(int czasTrwania, int zasobyStart, boolean czyZagrozenia)
    {
        this.czasTrwania = czasTrwania;
        this.czyZagrozenia = czyZagrozenia;
        this.mrowisko = new Mrowisko(zasobyStart);
        // dodajemy mrówki początkowe, moze potem zrobimy żeby to w konsoli fajnie działało
        mrowisko.dodajMrowke(new Krolowa());
        mrowisko.dodajMrowke(new Robotnica());
        mrowisko.dodajMrowke(new Robotnica());
        mrowisko.dodajMrowke(new Zolnierz(2));
        mrowisko.dodajMrowke(new Zolnierz(1));
    }


    public void uruchom()
    {
        System.out.printf("Start symulacji: czas= " + czasTrwania + ", zasoby= " + mrowisko.getZasoby()+ ", zagrozenia= " + czyZagrozenia);

        for (int krok = 1; krok <= czasTrwania; krok++)
        {
            System.out.println();
            System.out.printf("=== Krok "+ krok +  " ===");
            System.out.println();

            if (czyZagrozenia && random.nextBoolean())
            {
                Zagrozenie z = new Zagrozenie(".....", 10 + random.nextInt(20)); //tu mozna zmienic "sile" zagrozenia
                z.atakuj(mrowisko);
            }

            else
            {
                System.out.println("Brak zagrozen w tym kroku.");
            }
            mrowisko.zarzadzaj();


            //takie podsumowanie
            System.out.println("Liczba mrowek: " + mrowisko.getMrowki().size());
            System.out.println("Zasoby: " + mrowisko.getZasoby());
        }
        zakonczSymulacje();
    }

    //Tu podsumowanie całej symulacji, dodamy pózniej żeby też ilosc złożonych jaj była i żeby do excela szło
    public void zakonczSymulacje()
    {
        System.out.println();
        System.out.println("--------------");
        System.out.println("Symulacja zakonczona.");
        System.out.println("Koncowe zasoby: " + mrowisko.getZasoby());
        System.out.println("Koncowa liczba mrowek: " + mrowisko.getMrowki().size());
    }

    //Tu musimy znalesc "złoty" środek żeby to wszystko fajnie grało, znaczy żeby te mrówki miały zrównoważone siły z zagrozeniem ale i tak narazie zolnierz nie moze bic zagrozenia :((
    public static void main(String[] args)
    {
        Symulacja sym = new Symulacja(20, 100, true);
        sym.uruchom();
    }
}
