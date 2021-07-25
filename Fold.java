
package kotprog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Fold {
    private List<Parcella> parcellak;

    /**
     * Fold default konstruktora
     */
    public Fold() {
        parcellak = new ArrayList<Parcella>();
    }
     
    /**
     * Uj parcella felvetel a parcellak listahoz
     * @param ujparcella az uj parcella
     */
    public void addParcella(Parcella ujparcella){
       parcellak.add(ujparcella);
    }
    /**
     * legdragabb parcellat adja vissza a parcellak listabol
     * @return a legdragabb parcella, null ha nincsen ilyen (parcellak lista ures)
     */
    public Parcella legdragabbParcella(){
        //Double.MIN_VALUE a legkisebb szam, amit a double tipus felvehet
        double ertek = Double.MIN_VALUE;
        Parcella legdragabb = null;
        for(Parcella parcella : parcellak){
            /*
             *Megvizsgalja, hogy a epp vizsgalt parcella relativ erteke nagyobb-e, 
             *mint az aktulisan tarolt legdragabb
             */
            if(parcella.getErtek()/parcella.getMeret() > ertek){
                ertek = parcella.getErtek()/parcella.getMeret();
                legdragabb = parcella;
            }
        }
        return legdragabb;
    }
    /**
     * legolcsobb parcellat adja vissza a parcellak listabol
     * @return a legolcsobb parcella, null ha nincsen ilyen (parcellak lista ures)
     */
    public Parcella legolcsobbParcella(){
        //A Double.MAX_VALUE a legnagyobb szam, melyet a double tipus felvehet
        double ertek = Double.MAX_VALUE;
        Parcella legolcsobb = null;
        for(Parcella parcella : parcellak){
            /*
             *Megvizsgalja, hogy a epp vizsgalt parcella relativ erteke kisebb-e, 
             *mint az aktulisan tarolt legdragabb
             */
            if(parcella.getErtek()/parcella.getMeret() < ertek){
                ertek = parcella.getErtek()/parcella.getMeret();
                legolcsobb = parcella;
            }
        }
        return legolcsobb;
    }
    /**
     * Legdragabb foldterulet szamitja ki. Ehhez ket segedfuggveny hasznal: csoportositParcellakTulajSzerint,
     * csoportositFoldteruletTulajSzerint. Az elobbi a Foldhoz tartozo parcellakat egy HashMapbe teszi, melynek
     * kulcsa tulajdonos neve, az erteke pedig egy lista, mely az osszes olyan parcellat tartalmazza,
     * ami a tulajdonoshoz tartozik.
     * Az utobbi ez elobbi altal generalt hashmapbol rendszerezi ezeket foldteruletekbe.
     * A fuggveny ezen a hashmapen vegigmegy, mindegyik foldteruletnek az relativ erteket kiszamolja, majd 
     * visszaadja a legdragabb foldteruletet
     * @return  A legdragabb foldterulet, null ha nincsen ilyen
     */
    public Foldterulet legdragabbFoldterulet(){
        //Double.MIN_VALUE a legkisebb szam, amit a double tipus felvehet
        double ertek = Double.MIN_VALUE;
        Foldterulet legdragabb = null;
        //csoportositja  a parcellakat tulaj szerint
        HashMap<String, List<Parcella>> csoportositottParcella = this.csoportositParcellakTulajSzerint();
        //csoportositja a parcellakat tulaj + foldterulet szerint
        HashMap<String, List<Foldterulet>> csoportositottFoldterulet = this.csoportositFoldteruletTulajSzerint(csoportositottParcella);
        //kulcs
        Set keys = csoportositottFoldterulet.keySet();
        //vegigiteralja az osszes lehetseges kulcsot. Addig fut, mig a i.hasNext true erteket ad vissza
        for(Iterator i = keys.iterator(); i.hasNext(); ){
            //az iteratort atalakitja stringge (kulcs string tipusu)
            String key = (String) i.next();
            //a foldteruleteket lekeri tulaj szerint
            List<Foldterulet> foldteruletek = csoportositottFoldterulet.get(key);
            //vegigfut a foldteruletek listan
            for(Foldterulet foldterulet : foldteruletek){
                /*ha az aktulaisan vizsgalt foldterulet erteke nagyobb, mint az eddigi legertekesebbe
                 * akkor atallitja az erteket, es a legdragabb foldteruletet erre
                */
                if(ertek < (double)foldterulet.getOsszertek()/(double)foldterulet.getOsszmeret()){
                    ertek = (double)foldterulet.getOsszertek()/(double)foldterulet.getOsszmeret();
                    legdragabb = foldterulet;
                }
            }
        }
        return legdragabb;
    }
        
    /**
     * Legolcsobb foldterulet szamitja ki. Ehhez ket segedfuggveny hasznal: csoportositParcellakTulajSzerint,
     * csoportositFoldteruletTulajSzerint. Az elobbi a Foldhoz tartozo parcellakat egy HashMapbe teszi, melynek
     * kulcsa tulajdonos neve, az erteke pedig egy lista, mely az osszes olyan parcellat tartalmazza,
     * ami a tulajdonoshoz tartozik.
     * Az utobbi ez elobbi altal generalt hashmapbol rendszerezi ezeket foldteruletekbe.
     * A fuggveny ezen a hashmapen vegigmegy, mindegyik foldteruletnek az relativ erteket kiszamolja, majd 
     * visszaadja a legolcsobb foldteruletet
     * @return  A legolcsobb foldterulet, null ha nincsen ilyen
     */
    public Foldterulet legolcsobbFoldterulet(){
        //A Double.MAX_VALUE a legnagyobb szam, melyet a double tipus felvehet
        double ertek = Double.MAX_VALUE;
        Foldterulet legolcsobb = null;
        //csoportositja  a parcellakat tulaj szerint
        HashMap<String, List<Parcella>> csoportositottParcella = this.csoportositParcellakTulajSzerint();
        //csoportositja a parcellakat tulaj + foldterulet szerint
        HashMap<String, List<Foldterulet>> csoportositottFoldterulet = this.csoportositFoldteruletTulajSzerint(csoportositottParcella);
        //kulcs
        Set keys = csoportositottFoldterulet.keySet();
        //vegigiteralja az osszes lehetseges kulcsot. Addig fut, mig a i.hasNext true erteket ad vissza
        for(Iterator i = keys.iterator(); i.hasNext(); ){
            //az iteratort atalakitja stringge (kulcs string tipusu)
            String key = (String) i.next();
            //a foldteruleteket lekeri tulaj szerint
            List<Foldterulet> foldteruletek = csoportositottFoldterulet.get(key);
            //vegigfut a foldteruletek listan
            for(Foldterulet foldterulet : foldteruletek){
                /*ha az aktulaisan vizsgalt foldterulet erteke kisebb, mint az eddigi legertekesebbe
                 * akkor atallitja az erteket, es a legkisebb foldteruletet erre
                */
                if(ertek > (double)foldterulet.getOsszertek()/(double)foldterulet.getOsszmeret()){
                    ertek = (double)foldterulet.getOsszertek()/(double)foldterulet.getOsszmeret();
                    legolcsobb = foldterulet;
                }
            }
        }
        return legolcsobb;
    }    
    
    /**
     * Csoportositja a parcellak tulaj, es szomszedossag szerint (tulajdonos - foldteruelt)
     * Ehhez melysegi bejarast alkalmazva nezi a mar tulajdonos szerint csoportositott parcellakat, hogy
     * szomszedosak-e.
     * @param map Tulaj szerint csoportositott parcellak
     * @return tulaj szerint csoportosittott foldteruletek
     */
    public HashMap<String, List<Foldterulet>> csoportositFoldteruletTulajSzerint(HashMap<String, List<Parcella>> map){
        HashMap<String, List<Foldterulet>> csoportositott = new HashMap<String, List<Foldterulet>>();
        //kulcs
        Set keys = map.keySet();
        //vegigiteralja az osszes lehetseges kulcsot. Addig fut, mig a i.hasNext true erteket ad vissza
        for(Iterator i = keys.iterator(); i.hasNext(); ){
            //kulcs az epp vizsgalt tulajdonos
            String key = (String) i.next();
            //a tulajdonos parcellai
            List<Parcella> parcellak = map.get(key);
            //a mar vizsgalt parcellak listaja
            List<Parcella> vizsgalt = new ArrayList<Parcella>();
            //a tulajdonoshoz tartozo foldteruletek
            List<Foldterulet> foldteruletek = new ArrayList<Foldterulet>();
            //vegignezi a tulajdonoshoz tartozo parcellakat
            for(Parcella parcella : parcellak){
                //megvizsgalja, hogy mar vizsgalva volt-e (a bejarMelysegi fv is tehet parcellat a vizsgalt-ba
                if(!vizsgalt.contains(parcella)){
                    /*Ha nincsen vizsgalva, akkor letrehoz egy uj foldteruletet, majd a
                     *bejarMelysegi fuggvennyel kikeresi, hogy mely parcellakkal tartozik egy
                     *foldterulethez.
                    */
                    Foldterulet temp = new Foldterulet();
                    temp.addParcellak(bejarMelysegi(parcellak, vizsgalt, parcella));
                    foldteruletek.add(temp);
                }
            }
            //A tulajdonoshoz tartozo foldteruleteket a tulajdonos kulccsal beteszik a csoportositott hashmapbbe
            csoportositott.put(key, foldteruletek);
        }
        return csoportositott;
    }
    
    
    /**
     * A telkeket grafkent kezeljuk. Pontok: telek, Él: ha szomszedos 2 telek.
     * Melysegi bejarassal bejarjuk a grafot. Vizsgalt pontokat nem vizsgaljuk meg megegyszer. 
     * Az elerheto pnotok 1 foldterulethez tartozik, mivel szomszedos
     * @param parcellak szobajoheto szomszedok
     * @param vizsgalt mar vizsgalt parcellak
     * @param parcella a kiindulasi parcella
     * @return 1 foldterulethez tartozo parcellak
     */
    public List<Parcella> bejarMelysegi(List<Parcella> parcellak, List<Parcella> vizsgalt, Parcella parcella){
        //az aktulaisan vizsgalt parcellat a beletesszuk a vizsgalt parcellak koze.
        vizsgalt.add(parcella);
        //A szomszedainak letrehozunk egy listat
        List<Parcella> szomszedok = new ArrayList<Parcella>();
        //Ebbe a listaba beletesszuk az aktualisan vizsgalt parcellat
        szomszedok.add(parcella);
        //a tulajdonoshoz tartozo parcellakon vegigmegyunk
        for(Parcella szomszed : parcellak){
            /*Ha meg nem vizsgalt és szomszedos a parcellaval, amit param-kent kapott, akkor 
             *beletesszuk a szomszedok listaba, és meghivjuk erre a parcellara is a bejarMelysegi (ezt)
             *a fuggvenyt. -> Rekurziv fuggveny
            */
            if( !vizsgalt.contains(szomszed) && parcella.szomszedos(szomszed)){
                szomszedok.add(szomszed);
                List<Parcella> szomszedSzomszedok = bejarMelysegi(parcellak, vizsgalt, szomszed);
                /*Az igy visszakapott szomszedokat beletesszuk a szomszedok listaban, abban az esetben
                 *ha mar nincsen benne
                */
                for(Parcella p: szomszedSzomszedok){
                    if(!szomszedok.contains(p)){
                        szomszedok.add(p);
                    }
                }
            }
        }
        return szomszedok;
    }
    
    /**
     * csoportositja a parcellakat (Fold attributum lista) tulaj szerint
     * @return hashmap, melyben a kulcs a tulaj, erteke a tulajhoz tartozo parcellak listaja
     */
    public HashMap<String, List<Parcella>> csoportositParcellakTulajSzerint(){
        HashMap<String, List<Parcella>> csoportositott = new HashMap<String, List<Parcella>>();
        //vegigfut az osszes parcellan
        for(Parcella parcella : parcellak){
            /*Ha a tulajdonosa mar benne van a csoportotitott hashmapben, akkor
             *kinyeri a hozza tartozo listat, hozzaadja az aktulalisan vizsgalt parcellat
             *a listahoz, amjd kicsereli a hashmapban a listat (kinyeri -> frissiti -> regit kitorli ->
             *-> frissitettet visszateszi
            */
            if(csoportositott.containsKey(parcella.getTulajdonos())){
                List<Parcella> list = csoportositott.get(parcella.getTulajdonos());
                list.add(parcella);
                csoportositott.remove(parcella.getTulajdonos());
                csoportositott.put(parcella.getTulajdonos(), list);
            }
            /*
             * Ha nincsen benne a tulajdonos, akkor letrehoz neki egy uj listat, beleteszi, majd
             * tulajdoons kulccsal beteszi a hashmapba
            */
            else{
                List<Parcella> ujList = new ArrayList<Parcella>();
                ujList.add(parcella);
                csoportositott.put(parcella.getTulajdonos(), ujList);
            }
        }
        return csoportositott;
    }
    
    /**
     *Kiirja a tulajdonosokat, a hozza tartozo parcellak szama, osszeertekuket, es osszmeretuket
     */
    public void printParcellak(){
        HashMap<String, List<Parcella>> map = this.csoportositParcellakTulajSzerint();
        Set keys = map.keySet();
        //vegigfut a tulaj szerint csoportositott parcellakon. 
        for(Iterator i = keys.iterator(); i.hasNext(); ){
            String key = (String) i.next();
            List<Parcella> parcellak = map.get(key);
            //Kiirja a tulajdonost
            System.out.println(key + ":");
            double osszertek = 0;
            double osszmeret = 0;
            for(Parcella parcella : parcellak){
                osszertek += parcella.getErtek();
                osszmeret += parcella.getMeret();
            }
            System.out.println("parcellak szama: " + parcellak.size() + ", osszertekuk: " + osszertek + ", osszmeretuk: " + osszmeret);
        }
    }
    
    /**
     * Kiirja a foldteruleteket tulaj szerint csoportositva
     */
    public void printFoldterulet(){
        HashMap<String, List<Parcella>> csoportositottParcella = this.csoportositParcellakTulajSzerint();
        HashMap<String, List<Foldterulet>> csoportositottFoldterulet = this.csoportositFoldteruletTulajSzerint(csoportositottParcella);
        Set keys = csoportositottFoldterulet.keySet();
        for(Iterator i = keys.iterator(); i.hasNext(); ){
            String key = (String) i.next();
            List<Foldterulet> foldteruletek = csoportositottFoldterulet.get(key);
            System.out.println("Tulaj: " + key + "("+ foldteruletek.size()  + ")");
            int count = 1;
            for(Foldterulet foldterulet : foldteruletek){
                System.out.println("Foldterulet " + count + "("+ foldterulet.getParcellak().size()+ "), osszertek: " + foldterulet.getOsszertek() + ", osszmeret: " + foldterulet.getOsszmeret());
                foldterulet.print();
                count++;
            }
            if(i.hasNext())
               System.out.println("------------");
        }
    }
}
