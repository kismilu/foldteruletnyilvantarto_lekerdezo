
package kotprog;

import java.util.ArrayList;
import java.util.List;

public class Foldterulet {
    private List<Parcella> parcellak;

    /**
     * A foldterulet default konstruktora
     */
    public Foldterulet() {
        parcellak = new ArrayList<Parcella>();
    }    

    /**
     * Kiszamolja a foldterulethez tartozo parcellak osszeerteket
     * @return a parcellak osszerteke
     */
    public double getOsszertek(){
        double osszertek = 0;
        for(Parcella parcella : parcellak){
            osszertek += parcella.getErtek();
        }
        return osszertek;
    }
    
    /**
     * Kiszamolja a fodlterulethez tartozo parcellak osszmeretet
     * @return a percellak osszmerete
     */
    public double getOsszmeret(){
        double osszmeret = 0;
        for(Parcella parcella : parcellak){
            osszmeret += parcella.getMeret();
        }
        return osszmeret;
    }

    /**
     * A percellak gettter fuggvenye
     * @return
     */
    public List<Parcella> getParcellak() {
        return parcellak;
    }
    
    /**
     * Uj parcellat ad hozza a parcellak listahoz
     * @param parcella az uj parcella
     */
    public void addParcella(Parcella parcella){
        parcellak.add(parcella);
    }

    /**
     * Uj parcellakat ad hozza a parcella listahoz
     * @param parcellak az uj parcellak listaja
     */
    public void addParcellak(List<Parcella> parcellak){
        this.parcellak.addAll(parcellak);
    }
    
    /**
     * Kiiratja a consolera a peldany parcellak listajahoz tartozo parcellakat
     */
    public void print(){
        for(Parcella parcella : parcellak){
            System.out.println(parcella);
        }
    }
}
