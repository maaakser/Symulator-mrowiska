package mrowisko;

import mrowisko.model.*; //poprawa importow zeby wszystkie na raz brał
import java.util.Scanner; // żeby wpisywac do konsoli poczatkowe wartosci
import java.util.Random; //sila zagorozenia


public class Symulacja
{
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
        mrowisko.dodajMrowke(new Zolnierz(15));
        mrowisko.dodajMrowke(new Zolnierz(15));
    }


    public void uruchom()
    {
        System.out.printf("Start symulacji: czas= " + czasTrwania + ", zasoby= " + mrowisko.getZasoby() + ", zagrozenia= " + czyZagrozenia);

        for (int krok = 1; krok <= czasTrwania; krok++)
        {
            System.out.println();
            System.out.printf("=== Krok " + krok + " ===");
            System.out.println();

            if (czyZagrozenia && random.nextBoolean())
            {
                Zagrozenie z = new Zagrozenie(".....", 10 + random.nextInt(10)); //tu mozna zmienic "sile" zagrozenia
                z.atakuj(mrowisko);
            }
            else
            {
                System.out.println("Brak zagrozen w tym kroku.");
            }
            mrowisko.zarzadzaj();


            if (mrowisko.getMrowki().isEmpty())
            {
                System.out.println("Wszystkie mrowki umarly - symulacja zakonczona w kroku " + krok); //Dodanie zakonczenia symulacji po smieric wszystkich mrówek
                break;
            }

            boolean czyZyjeKrolowa = false;
            for(Mrowka m : mrowisko.getMrowki())
            {
                if (m instanceof Krolowa && m.isCzyZyje()) // sprawdzenie czy mrowka jest krolowa i czy zyje
                {
                    czyZyjeKrolowa = true;
                    break;
                }
            }

            if (!czyZyjeKrolowa)
            {
                System.out.println("Krolowa nie zyje – koniec symulacji w kroku " + krok);
                break;
            }



            //takie podsumowanie
            System.out.println("Liczba mrowek: " + mrowisko.getMrowki().size());
            System.out.println("Zasoby: " + mrowisko.getZasoby());

            try
            {
                Thread.sleep(100);  // mała pauza by faktycznie działało jako symulacja a nie wypluwało od razu wszystko
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
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
        Scanner scanner = new Scanner(System.in);

            System.out.print("Podaj liczbe rund: ");
                int czas = scanner.nextInt();

            System.out.print("Podaj poczatkowe zasoby: ");
                int zasobyStart = scanner.nextInt();

            System.out.print("Czy uwzglednia czyZagrozenia (true/false): ");
            boolean czyZagrozenia = scanner.nextBoolean();

        scanner.close();

        Symulacja sym = new Symulacja(czas, zasobyStart, czyZagrozenia);
        sym.uruchom();
    }
}
