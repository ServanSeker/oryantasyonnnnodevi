import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Şervan  {

    static Scanner inp = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<String> myNumbers = readMapFromFile("harita.txt");
        String[][] liste = initializeList(myNumbers);
        while (hepsiXMi(liste)) {
            haritayiYazdir(liste);
            programaBasla(liste);
        }
        haritayiYazdir(liste);
    }

    private static ArrayList<String> readMapFromFile(String fileName) {
        ArrayList<String> myNumbers = new ArrayList<>();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                myNumbers.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı.");
            e.printStackTrace();
        }
        return myNumbers;
    }

    private static String[][] initializeList(ArrayList<String> myNumbers) {
        String[][] liste = new String[myNumbers.size()][];

        for (int i = 0; i < myNumbers.size(); i++) {
            String[] parts = myNumbers.get(i).split(",");
            liste[i] = new String[parts.length];
            System.arraycopy(parts, 0, liste[i], 0, parts.length);
        }
        return liste;
    }

    private static boolean isValidIndex(int sutun, int satir, String[][] liste) {
        return sutun >= 0 && sutun < liste.length && satir >= 0 && satir < liste[0].length;
    }

    private static void programaBasla(String[][] liste) {
        System.out.println("Sutun giriniz: ");
        int sutun = inp.nextInt();
        System.out.println("Satir giriniz: ");
        int satir = inp.nextInt();

        if (sutun == 0 && satir == 0) {
            System.out.println("Program sonlandı");
            System.exit(0);
        }
        String hedef = liste[sutun - 1][satir - 1];
        if (!(hedef == "X")) {
            System.out.println("Hedef sayımız : " + hedef);
            indexleriKontrolEt(sutun - 1, satir - 1, liste, hedef, 1);
        }
    }

    private static void indexleriKontrolEt(int sutun, int satir, String[][] liste, String hedef, int depth) {
        if (liste[sutun][satir].equals(hedef) && depth <= 100) {
            liste[sutun][satir] = "X";
            if (isValidIndex(sutun - 1, satir, liste) && liste[sutun - 1][satir].equals(hedef)) {
                indexleriKontrolEt(sutun - 1, satir, liste, hedef, depth + 1);
            }
            if (isValidIndex(sutun, satir - 1, liste) && liste[sutun][satir - 1].equals(hedef)) {
                indexleriKontrolEt(sutun, satir - 1, liste, hedef, depth + 1);
            }
            if (isValidIndex(sutun + 1, satir, liste) && liste[sutun + 1][satir].equals(hedef)) {
                indexleriKontrolEt(sutun + 1, satir, liste, hedef, depth + 1);
            }
            if (isValidIndex(sutun, satir + 1, liste) && liste[sutun][satir + 1].equals(hedef)) {
                indexleriKontrolEt(sutun, satir + 1, liste, hedef, depth + 1);
            }
        }
    }

    private static boolean hepsiXMi(String[][] liste) {
        int x_sayisi = 0;

        for (int i = 0; i < liste.length; i++) {
            for (int j = 0; j < liste[0].length; j++) {
                if (liste[i][j] == "X") {
                    x_sayisi += 1;
                }
            }
        }
        return !(x_sayisi == 100);
    }

    private static void haritayiYazdir(String[][] liste) {
        for (int i = 0; i < liste.length; i++) {
            for (int j = 0; j < liste[0].length; j++) {
                System.out.print(liste[i][j] + " ");
            }
            System.out.println();
        }
    }
}