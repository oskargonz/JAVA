import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class s22220 {
    public static void main(String[] args) {
        //READ_FIRST!!! - z jakiegos powodu przy wgrywaniu pliku na Teamsy zmienia sie zazwa pliku. Moze to powodowac problemy przy uruchomieniu programu. Prosze zmienic nazwe pliku na taka jak nazwa klasy tj. s22220

        //Utworzenie tablicy 15.000 losowych kontenerów
        Kontener[] konteners = new Kontener[15000];
        for (int i = 0; i < 15000; i++) {
            int los = (int)(Math.random() * 12) + 1;
            int ID = i;
            int masa;

            switch(los){
                case 1:
                    konteners[i] = new HermetycznyBanany(ID);
                    masa = (int)(konteners[i].getMasaMax() * Math.random());
                    konteners[i].setMasa(masa);
                    break;

                case 2:
                    konteners[i] = new HermetycznyPomarancze(ID);
                    masa = (int)(konteners[i].getMasaMax() * Math.random());
                    konteners[i].setMasa(masa);
                    break;
                case 3:
                    konteners[i] = new HermetycznyMandarynki(ID);
                    masa = (int)(konteners[i].getMasaMax() * Math.random());
                    konteners[i].setMasa(masa);
                    break;
                case 4:
                    konteners[i] = new CysternaChemikalia(ID);
                    masa = (int)(konteners[i].getMasaMax() * Math.random());
                    konteners[i].setMasa(masa);
                    break;
                case 5:
                    konteners[i] = new CysternaPaliwo(ID);
                    masa = (int)(konteners[i].getMasaMax() * Math.random());
                    konteners[i].setMasa(masa);
                    break;
                case 6:
                    konteners[i] = new CysternaSoki(ID);
                    masa = (int)(konteners[i].getMasaMax() * Math.random());
                    konteners[i].setMasa(masa);
                    break;
                case 7:
                    konteners[i] = new DV40_elektronika(ID);
                    masa = (int)(konteners[i].getMasaMax() * Math.random());
                    konteners[i].setMasa(masa);
                    break;
                case 8:
                    konteners[i] = new DV40_materialy_budowlane(ID);
                    masa = (int)(konteners[i].getMasaMax() * Math.random());
                    konteners[i].setMasa(masa);
                    break;
                case 9:
                    konteners[i] = new DV40_meble(ID);
                    masa = (int)(konteners[i].getMasaMax() * Math.random());
                    konteners[i].setMasa(masa);
                    break;
                case 10:
                    konteners[i] = new SypkieKwarc(ID);
                    masa = (int)(konteners[i].getMasaMax() * Math.random());
                    konteners[i].setMasa(masa);
                    break;
                case 11:
                    konteners[i] = new SypkieZiemia(ID);
                    masa = (int)(konteners[i].getMasaMax() * Math.random());
                    konteners[i].setMasa(masa);
                    break;
                case 12:
                    konteners[i] = new SypkieZwir(ID);
                    masa = (int)(konteners[i].getMasaMax() * Math.random());
                    konteners[i].setMasa(masa);
                    break;
            }
        }

        //Zapisanie losowo ułożonych kontenerów do pliku kontenery_los.txt
        try {
            FileWriter fw = new FileWriter("D:\\PJATK\\PPJ - Java\\kontenery_los.txt");

            for (int i = 0; i < konteners.length; i++) {
                fw.write("#" + konteners[i].getID() + "\t" + konteners[i].getMasa() + "\t" + konteners[i].getTowar() + "\t" + konteners[i].getCecha() + "\t" + konteners[i].getType() + '%' + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ABC abc = new ABC();
        //Odczytanie z pliku .txt
        Kontener[] kontenersNew = new Kontener[15000];
        try {
            FileReader fr = new FileReader("D:\\PJATK\\PPJ - Java\\kontenery_los.txt");
            kontenersNew = abc.wczytajKonteneryZtxt(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       /* //Zapisanie do pliku kontenery_los_wczytane.txt kontenerów, które wczytuje z pliku kontenery_los.txt w celu porównania czy dobrze odczytuje
        try {
            FileWriter fw = new FileWriter("D:\\PJATK\\PPJ - Java\\kontenery_los_wczytane.txt");

            for (int i = 0; i < kontenersNew.length; i++) {
                fw.write("#" + kontenersNew[i].getID() + "\t" + kontenersNew[i].getMasa() + "\t" + kontenersNew[i].getTowar() + "\t" + kontenersNew[i].getCecha() + "\t" + kontenersNew[i].getType() + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //Sortowanie wczytanych kontenerow
        abc.sortowanie(kontenersNew);

/*        //Zapisanie do pliku kontenery_los_sort.txt kontenerów, które posortowalem w celu sprawdzenia czy dobrze sortuje
        try {
            FileWriter fw = new FileWriter("D:\\PJATK\\PPJ - Java\\kontenery_los_sort.txt");
            for (int i = 0; i < kontenersNew.length; i++) {
                fw.write("#" + kontenersNew[i].getID() + "\t" + kontenersNew[i].getMasa() + "\t" + kontenersNew[i].getTowar() + "\t" + kontenersNew[i].getCecha() + "\t" + kontenersNew[i].getType() + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //Układanie kontenerow na statku
        Kontener[][][] statek = abc.ukladanie(kontenersNew);

/*       //Wyświetlenie wybranego poziomu kontenerów
        int poziom = 0;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 50; j++) {
                System.out.print(statek[j][i][poziom].getMasa() + " ");
            }
            System.out.println();
        }*/

        //Ułożenie kontenerów w tablicy w kolejności ładowania
        Kontener[] ladowanie = abc.kolejnoscLadowania(statek);

        try {
            FileWriter fw = new FileWriter("D:\\PJATK\\PPJ - Java\\Manifest.txt");
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 30; j++) {
                    for (int k = 0; k < 50; k++) {
                        fw.write("#" + statek[k][j][i].getID() + "\t" + "Rząd:" + (k + 1) + "\tKolumna: "
                                + (j + 1) + "\tPoziom: " + (i + 1) + "\tMasa: " + statek[k][j][i].getMasa()
                                + "kg\tTowar: " + statek[k][j][i].getTowar() + "\n");
                        fw.flush();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

class ABC{
    public void sortowanie(Kontener[] kontener){
        int[] masaKontenerow = new int[15000];

        for (int i = 0; i < kontener.length; i++) {
            masaKontenerow[i] = kontener[i].getMasa();
        }
        for (int i = 0; i < kontener.length - 1; i++) {
            int min_index = i;
            for (int j = i + 1; j < kontener.length; j++)
                if (masaKontenerow[j] < masaKontenerow[min_index])
                    min_index = j;

            int temp = masaKontenerow[min_index];
            masaKontenerow[min_index] = masaKontenerow[i];
            masaKontenerow[i] = temp;

            Kontener temp1 = kontener[min_index];
            kontener[min_index] = kontener[i];
            kontener[i] = temp1;

        }
    }

    public Kontener[] wczytajKonteneryZtxt(FileReader fr){
        Kontener[] kontenersFromTxt = new Kontener[15000];
        //FileReader fr = new FileReader("c:\\2021Z\\myText.txt");
        int newID;
        int newMasa;
        String newTowar;
        String newCecha;
        String newType;
        int data = 0;
        try {
            data = fr.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 15000; i++) {
            try {
                data = fr.read();

                //Wczytanie ID
                String str = new String();

                while(data != '\t'){
                    str += (char) data;
                    data = fr.read();
                }
                newID = i;
                data = fr.read();

                //Wczytanie masy
                //sb.delete(0, sb.length());
                str = new String();
                while(data != '\t'){
                    str += (char) data;
                    data = fr.read();
                }
                newMasa = Integer.parseInt(str);
                data = fr.read();

                //Wczytanie towaru
                //sb.delete(0, sb.length());
                str = new String();
                while(data != '\t'){
                    str += (char) data;
                    data = fr.read();
                }
                newTowar = str;
                data = fr.read();


                //Wczytanie cechy
                //sb.delete(0, sb.length());
                str = new String();
                while(data != '\t'){
                    str += (char) data;
                    data = fr.read();
                }
                newCecha = str;
                data = fr.read();

                //Wczytanie typu
                //sb.delete(0, sb.length());
                str = new String();
                while(data != '%'){
                    str += (char) data;
                    data = fr.read();
                }
                newType = str;
                data = fr.read();

                switch(newTowar){
                    case "Banany":
                        kontenersFromTxt[i] = new HermetycznyBanany(newID);
                        break;

                    case "Pomarańcze":
                        kontenersFromTxt[i] = new HermetycznyPomarancze(newID);
                        break;
                    case "Mandarynki":
                        kontenersFromTxt[i] = new HermetycznyMandarynki(newID);
                        break;
                    case "Chemikalia":
                        kontenersFromTxt[i] = new CysternaChemikalia(newID);
                        break;
                    case "Paliwo":
                        kontenersFromTxt[i] = new CysternaPaliwo(newID);
                        break;
                    case "Soki":
                        kontenersFromTxt[i] = new CysternaSoki(newID);
                        break;
                    case "Elektronika":
                        kontenersFromTxt[i] = new DV40_elektronika(newID);
                        break;
                    case "Materiały_budowlane":
                        kontenersFromTxt[i] = new DV40_materialy_budowlane(newID);
                        break;
                    case "Meble":
                        kontenersFromTxt[i] = new DV40_meble(newID);
                        break;
                    case "Kwarc":
                        kontenersFromTxt[i] = new SypkieKwarc(newID);
                        break;
                    case "Ziemia":
                        kontenersFromTxt[i] = new SypkieZiemia(newID);
                        break;
                    case "Żwir":
                        kontenersFromTxt[i] = new SypkieZwir(newID);
                        break;
                }


                kontenersFromTxt[i].setMasa(newMasa);
                kontenersFromTxt[i].setTowar(newTowar);
                kontenersFromTxt[i].setCecha(newCecha);
                kontenersFromTxt[i].setType(newType);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return kontenersFromTxt;
    }

    public Kontener[][][] ukladanie(Kontener[] konteners){
        Kontener[][][] statek = new Kontener[50][30][10];
        int counter = 14999;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                for (int k = 0; k < 25; k++) {
                    statek[24-k][14-j][i] = konteners[counter];
                    counter--;

                    statek[25+k][14-j][i] = konteners[counter];
                    counter--;

                    statek[24-k][15+j][i] = konteners[counter];
                    counter--;

                    statek[25+k][15+j][i] = konteners[counter];
                    counter--;
                }
            }
        }
        return statek;
    }

    public Kontener[] kolejnoscLadowania(Kontener[][][] statek){
        Kontener[] ladowanie = new Kontener[15000];
        int counter = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 30; j++) {
                for (int k = 0; k < 50; k++) {
                    ladowanie[counter] = statek[k][j][i];
                    counter++;
                }
            }
        }
        return ladowanie;
    }

}

class Kontener {
    private int ID;
    int masa;


    public Kontener(int ID) {
        this.ID = ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMasaMax() {
        return 0;
    }

    public String getCecha() {
        return "";
    }

    public String getType() {
        return "";
    }

    public String getTowar() {
        return "";
    }
    public void setMasa(int masa) {
        this.masa = masa;
    }

    public int getMasa() {
        return masa;
    }

    public int getID() {
        return ID;
    }

    public void setTowar(String towar) {
    }
    public void setCecha(String cecha) {
    }
    public void setType(String type) {
    }
}

class KontenerCysterna extends Kontener {
    private int masaMax = 36000;
    private String cecha = "Cysterna";
    private String type = "L4BN";


    public KontenerCysterna(int ID) {
        super(ID);
    }

    public int getMasaMax() {
        return masaMax;
    }

    public String getCecha() {
        return cecha;
    }

    public String getType() {
        return type;
    }

    public String getTowar() {
        return "";
    }
    public void setTowar(String towar) {
    }
    public void setCecha(String cecha) {
        this.cecha = cecha;
    }
    public void setType(String type) {
        this.type = type;
    }

}

class KontenerHermetyczny extends Kontener {
    private int masaMax = 26580;
    private String cecha = "Hermetycznie zamknięty";
    private String type = "40 High Cube";



    public KontenerHermetyczny(int ID) {
        super(ID);
    }

    public int getMasaMax() {
        return masaMax;
    }

    public String getCecha() {
        return cecha;
    }

    public String getType() {
        return type;
    }

    public String getTowar() {
        return "";
    }

    public void setTowar(String towar) {
    }
    public void setCecha(String cecha) {
        this.cecha = cecha;
    }
    public void setType(String type) {
        this.type = type;
    }


}

class KontenerSypkie extends Kontener {
    private int masaMax = 24890;
    private String cecha = "Towary sypkie";
    private String type = "BULK";

    public KontenerSypkie(int ID) {
        super(ID);
    }

    public int getMasaMax() {
        return masaMax;
    }

    public String getCecha() {
        return cecha;
    }

    public String getType() {
        return type;
    }

    public String getTowar() {
        return "";
    }
    public void setTowar(String towar) {
    }
    public void setCecha(String cecha) {
        this.cecha = cecha;
    }
    public void setType(String type) {
        this.type = type;
    }
}

class DV40 extends Kontener {
    private int masaMax = 28500;
    private String cecha = "Ogólne_przeznaczenie";
    private String type = "DV40";

    public DV40(int ID) {
        super(ID);
    }

    public int getMasaMax() {
        return masaMax;
    }

    public String getCecha() {
        return cecha;
    }

    public String getType() {
        return type;
    }

    public String getTowar() {
        return "";
    }

    public void setCecha(String cecha) {
        this.cecha = cecha;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class CysternaChemikalia extends KontenerCysterna {
    private String towar = "Chemikalia";


    public CysternaChemikalia(int ID) {
        super(ID);
    }

    public String getTowar() {

        return towar;
    }

    public void setTowar(String towar) {
        this.towar = towar;
    }
}

class CysternaPaliwo extends KontenerCysterna {
    private String towar = "Paliwo";


    public CysternaPaliwo(int ID) {
        super(ID);
    }

    public String getTowar() {

        return towar;
    }
    public void setTowar(String towar) {
        this.towar = towar;
    }

}

class CysternaSoki extends KontenerCysterna {
    private String towar = "Soki";


    public CysternaSoki(int ID) {
        super(ID);
    }

    public String getTowar() {

        return towar;
    }
    public void setTowar(String towar) {
        this.towar = towar;
    }

}

class DV40_elektronika extends DV40 {
    private String towar = "Elektronika";


    public DV40_elektronika(int ID) {
        super(ID);
    }

    public String getTowar() {
        return towar;
    }
    public void setTowar(String towar) {
        this.towar = towar;
    }

}
class DV40_materialy_budowlane extends DV40 {
    private String towar = "Materiały_budowlane";


    public DV40_materialy_budowlane(int ID) {
        super(ID);
    }

    public String getTowar() {

        return towar;
    }
    public void setTowar(String towar) {
        this.towar = towar;
    }

}

class DV40_meble extends DV40 {
    private String towar = "Meble";


    public DV40_meble(int ID) {
        super(ID);
    }

    public String getTowar() {

        return towar;
    }
    public void setTowar(String towar) {
        this.towar = towar;
    }



}

class HermetycznyBanany extends KontenerHermetyczny {
    private String towar = "Banany";


    public HermetycznyBanany(int ID) {
        super(ID);
    }

    public String getTowar() {

        return towar;
    }
    public void setTowar(String towar) {
        this.towar = towar;
    }


}

class HermetycznyMandarynki extends KontenerHermetyczny {
    private String towar = "Mandarynki";


    public HermetycznyMandarynki(int ID) {
        super(ID);
    }

    public String getTowar() {

        return towar;
    }
    public void setTowar(String towar) {
        this.towar = towar;
    }

}

class HermetycznyPomarancze extends KontenerHermetyczny {
    private String towar = "Pomarańcze";

    public HermetycznyPomarancze(int ID) {
        super(ID);
    }

    public String getTowar() {

        return towar;
    }
    public void setTowar(String towar) {
        this.towar = towar;
    }

}

class SypkieKwarc extends KontenerSypkie {
    private String towar = "Kwarc";


    public SypkieKwarc(int ID) {
        super(ID);
    }

    public String getTowar() {

        return towar;
    }
    public void setTowar(String towar) {
        this.towar = towar;
    }


}

class SypkieZiemia extends KontenerSypkie {
    private String towar = "Ziemia";



    public SypkieZiemia(int ID) {
        super(ID);
    }

    public String getTowar() {

        return towar;
    }
    public void setTowar(String towar) {
        this.towar = towar;
    }

}

class SypkieZwir extends KontenerSypkie {
    private String towar = "Żwir";


    public SypkieZwir(int ID) {
        super(ID);
    }

    public String getTowar() {

        return towar;
    }
    public void setTowar(String towar) {
        this.towar = towar;
    }

}
