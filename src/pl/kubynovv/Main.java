package pl.kubynovv;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char obecnySymbol ='X';

        System.out.println("Witaj w grze, podaj rozmiar planszy");
        int wymiar = scanner.nextInt();

        char[][] plansza = new char[wymiar][wymiar];


        boolean czyWygrana = false;
        int licznikRuchow = 0;
        while(!czyWygrana) {
            drukujPlansze(plansza);
            boolean ruchPoprawny = wykonajRuch(plansza,obecnySymbol, wymiar);
            if(ruchPoprawny) {
                boolean wygranaWiersze = sprawdzWiersze(plansza,obecnySymbol);
                boolean wygranaKolumny = sprawdzKolumny(plansza,obecnySymbol);
                boolean wygranaSkos1 = sprawdzSkos1(plansza,obecnySymbol);
                boolean wygranaSkos2 = sprawdzSkos2(plansza,obecnySymbol);
                if(wygranaWiersze || wygranaKolumny || wygranaSkos1 || wygranaSkos2){
                    System.out.println("Gratulacje gracz " +obecnySymbol+" wygrywa!!!");
                    drukujPlansze(plansza);
                    czyWygrana =true;
                }
                obecnySymbol = obecnySymbol == 'X' ? 'O' : 'X';
            }
            licznikRuchow++;
            if(licznikRuchow==wymiar*wymiar){
                System.out.println("Niestety rozgrywka zakończona remisem :(");
                break;
            }
        }
    }

    public static boolean sprawdzWiersze(char[][] plansza, char obecnySymbol){
        for (int i=0; i< plansza.length;i++)
        {
            boolean wygrana = true;
            for (int j=0; j<plansza[i].length;j++)
            {
                if (plansza[i][j]!=obecnySymbol){
                    wygrana = false;
                    break;
                }
            }
            if(wygrana){return true;}
        }
        return false;
    }

    public static boolean sprawdzKolumny(char[][] plansza, char obecnySymbol){
        for (int i=0; i< plansza.length;i++)
        {
            boolean wygrana = true;
            for (int j=0; j<plansza[i].length;j++)
            {
                if (plansza[j][i]!=obecnySymbol){
                    wygrana = false;
                    break;
                }
            }
            if(wygrana){return true;}
        }
        return false;
    }
    public static boolean sprawdzSkos1(char[][] plansza, char obecnySymbol) {
        for (int i = 0; i < plansza.length; i++) {
            if (plansza[i][i] != obecnySymbol) {
                return false;
            }
        }
        return true;
    }
    public static boolean sprawdzSkos2(char[][] plansza, char obecnySymbol) {
        for (int i = 0; i < plansza.length; i++) {
            if (plansza[plansza.length-1-i][i] != obecnySymbol) {
                return false;
            }
        }
        return true;
    }

    public static boolean wykonajRuch(char[][] plansza, char obecnySymbol, int wymiar){
        Scanner scanner = new Scanner(System.in);
        System.out.println(obecnySymbol + " twój ruch");

        System.out.println("Podaj numer wiersza:");
        int nrWiersza = scanner.nextInt();
        System.out.println("Podaj numer kolumny:");
        int nrKolumny = scanner.nextInt();
        boolean ruchPoprawny = plansza[nrWiersza][nrKolumny] ==0;
        if (!ruchPoprawny){
            System.out.println("Ruch niepoprawny!!!");
            return false;
        }
        plansza[nrWiersza][nrKolumny] = obecnySymbol;
        return true;
    }

    public static void drukujPlansze(char[][] plansza){
        int wymiar = plansza.length;
        //petla do nagłowków kolumn
        for (int i=0;i<wymiar;i++){
            System.out.print("\t"+i+":");
        }
        System.out.println();

        for (int i=0; i< plansza.length;i++) {
            System.out.print(i+":\t");
            for (int j = 0; j < plansza[i].length; j++) {
                System.out.print(plansza[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
