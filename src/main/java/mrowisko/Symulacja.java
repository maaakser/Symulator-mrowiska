package mrowisko;

/*
Importy potrzbne do zapisu do pliku TXT
Losowych sił zagrozenia
Do wpisywania początkowych parametrów
 */
import mrowisko.model.*;
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


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

        /*
        Dodanie początkowych mrówek
         */
        mrowisko.dodajMrowke(new Krolowa());
        mrowisko.dodajMrowke(new Robotnica());
        mrowisko.dodajMrowke(new Robotnica());
        mrowisko.dodajMrowke(new Zolnierz(15));
        mrowisko.dodajMrowke(new Zolnierz(15));
    }


    public void uruchom()
    {
        System.out.printf("Start symulacji: czas= " + czasTrwania + ", zasoby= " + mrowisko.getZasoby() + ", zagrozenia= " + czyZagrozenia);

    /*
    Każdy krok symulacji
    */
        for (int krok = 1; krok <= czasTrwania; krok++)
        {
            System.out.println();
            System.out.printf("=== Krok " + krok + " ===");
            System.out.println();

            if (czyZagrozenia && random.nextBoolean())
            {
                Zagrozenie z = new Zagrozenie("Pajak", 10 + random.nextInt(10)); //Losowa siła dla zagrozenia
                z.atakuj(mrowisko);
            }
            else
            {
                System.out.println("Brak zagrozen w tym kroku.");
            }
            mrowisko.zarzadzaj();


            if (mrowisko.getMrowki().isEmpty())
            {
                System.out.println("Wszystkie mrowki umarly - symulacja zakonczona w kroku " + krok); //zakonczenia symulacji po smieric wszystkich mrówek
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
                System.out.println("Smierc krolowej -  koniec symulacji w kroku " + krok);
                break;
            }



            //Podsumowanie
            System.out.println("Liczba mrowek: " + mrowisko.getMrowki().size());
            System.out.println("Zasoby: " + mrowisko.getZasoby());

            try
            {
                Thread.sleep(1000);  // Pauza aby można było przeczytać
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        zakonczSymulacje();
    }

    //Podsumowanie całej symulacji i zapis do txt
    public void zakonczSymulacje()
    {
        System.out.println();
        System.out.println("--------------");
        System.out.println("Symulacja zakonczona.");
        System.out.println("Koncowe zasoby: " + mrowisko.getZasoby());
        System.out.println("Koncowa liczba mrowek: " + mrowisko.getMrowki().size());


        try (PrintWriter writer = new PrintWriter(new FileWriter("podsumowanie.txt"))) { // zapis do Txt
            writer.println("=== PODSUMOWANIE SYMULACJI ===");
            writer.println("Czas trwania symulacji: " + czasTrwania);
            writer.println("Zagrozenia: " + (czyZagrozenia ? "tak" : "nie"));
            writer.println("Koncowa liczba mrowek: " + mrowisko.getMrowki().size());
            writer.println("Koncowe zasoby w mrowisku: " + mrowisko.getZasoby());
            writer.println("Zasoby pozostale na zewnatrz (niezebrane): " + mrowisko.getJedzenie().getZasoby());
            writer.println("Symulacja zakonczona.");
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu podsumowania do pliku: " + e.getMessage());
        }
    }

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
