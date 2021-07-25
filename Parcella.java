
package kotprog;

public class Parcella {

    private int[] coor1;
    private int[] coor2;
    private int[] coor3;
    private String tulajdonos;
    private double ertek;

    /**
     * A parcella konstruktora
     * @param coor1 a parcella elso koordinata (2d-s tomb)
     * @param coor2 a parcella masodik koordinata (2d-s tomb)
     * @param coor3 a parcella harmadik koordinata (2d-s tomb)
     * @param tulajdonos a parcella tulajdonosa
     * @param ertek a parcella erteke
     */
    public Parcella(int[] coor1, int[] coor2, int[] coor3, String tulajdonos, int ertek) {
        this.coor1 = coor1;
        this.coor2 = coor2;
        this.coor3 = coor3;
        this.tulajdonos = tulajdonos;
        this.ertek = ertek;
    }
    
    /**
     * A parcella konstruktora
     * @param coor11 a parcella elso koordinatanak elso erteke
     * @param coor12 a parcella elso koordinatanak masodik erteke
     * @param coor21 a parcella masodik koordinatanak elso erteke
     * @param coor22 a parcella masodik koordinatanak masodik erteke
     * @param coor31 a parcella harmadik koordinatanak elso erteke
     * @param coor32 a parcella harmadik koordinatanak masodik erteke
     * @param tulajdonos a parcella tulajdonosa
     * @param ertek a parcella erteke
     */
    public Parcella(int coor11, int coor12, int coor21, int coor22, int coor31, int coor32, String tulajdonos, int ertek) {
        this.coor1 = new int[2];
        this.coor1[0] = coor11;
        this.coor1[1] = coor12;
        this.coor2 = new int[2];
        this.coor2[0] = coor21;
        this.coor2[1] = coor22;
        this.coor3 = new int[2];
        this.coor3[0] = coor31;
        this.coor3[1] = coor32;
        this.tulajdonos = tulajdonos;
        this.ertek = ertek;
    }

    /**
     * Szomszedosak-e? Ha van 2 kozos csucsuk akkor igen, kulonben nem (Ha 2nel tobb kozos csucsuk van, az azt jelenti, hogy vagy egyezik a 2 telek, vagy
     * 2 csucsa kulon-kulon is egyezik, azaz nem haromszogrol beszelunk, DE ilyeneket a feladat kiirasa szerint nem kaphat bemenetkent)
     * @param szomszed parcella, mellyel vizsgaljuk a szomszedossagot a peldannyal
     * @return true - szomszedos, false - nem szomszedos
     */
    public boolean szomszedos(Parcella szomszed){
        
        int[][] aCoors = this.getCoordinatak();
        int[][] bCoors = szomszed.getCoordinatak();
        int count = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(egyezikCoor(aCoors[i], bCoors[j])){
                    count++;
                }
            }
        }
        if(count == 2){
            return true;
        }
        return false;
    }
  
    
   
     /**
     * Ketdimenzios tombbe alakitja a coordinatakat
     * @return 2 dimenzios tomb, mely tarolja a coordinatakat
     */
    public int[][] getCoordinatak(){
        int[][] coordinatak = new int[3][2];
        coordinatak[0][0] = this.getCoor1()[0];
        coordinatak[0][1] = this.getCoor1()[1];
        coordinatak[1][0] = this.getCoor2()[0];
        coordinatak[1][1] = this.getCoor2()[1];
        coordinatak[2][0] = this.getCoor3()[0];
        coordinatak[2][1] = this.getCoor3()[1];
        return coordinatak;
    }
    
    /**
     * Kapott coordinatak egyeznek-e
     * @param a koordinata1
     * @param b koordinata2 - ezzel vetjuk ossze az a koordinatat
     * @return true - egyezik a ket koordinata, false - nem egyezik a ket koordinata
     */
    public boolean egyezikCoor(int[] a, int[] b){
        if(a[0] == b[0] && a[1] == b[1]){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Kiszamolja a parcella (haromszog) teruletet
     * @return a parcella terulete 
     */
    double getMeret(){
        return Math.abs((double)coor1[0]*(coor2[1]-coor3[1]) + (double)coor2[0]*(coor3[1]-coor1[1])+(double)coor3[0]*(coor1[1]-coor2[1]))/(double)2;
    }
    
    //GETTER SETTER

    /**
     * elso koordinata getter fuggvenye
     * @return elso koordinata
     */
    public int[] getCoor1() {
        return coor1;
    }

    /**
     * elso koordinata setter fuggvenye
     * @param coor1 az uj koordinata
     */
    public void setCoor1(int[] coor1) {
        this.coor1 = coor1;
    }

    /**
     * masodik koordinata getter fuggvenye
     * @return masodik koordinata
     */
    public int[] getCoor2() {
        return coor2;
    }

    /**
     * masodik koordinata setter fuggvenye
     * @param coor2 az uj masodik koordinata
     */
    public void setCoor2(int[] coor2) {
        this.coor2 = coor2;
    }

    /**
     * harmadik koordinata getter fuggvenye
     * @return a harmadik koordinata
     */
    public int[] getCoor3() {
        return coor3;
    }

    /**
     * harmadik koordinata setter fuggvenye
     * @param coor3 az uj harmadik koordinata
     */
    public void setCoor3(int[] coor3) {
        this.coor3 = coor3;
    }

    /**
     * tulajdonos getter fuggvenye
     * @return a tulajdonos
     */
    public String getTulajdonos() {
        return tulajdonos;
    }

    /**
     * a tulajdonos setter fuggvenye
     * @param tulajdonos az uj tulajdonos
     */
    public void setTulajdonos(String tulajdonos) {
        this.tulajdonos = tulajdonos;
    }

    /**
     * az ertek getter fuggvenye
     * @return az ertek
     */
    public double getErtek() {
        return ertek;
    }

    /**
     * az ertek setter fuggvenye
     * @param ertek az uj ertek
     */
    public void setErtek(double ertek) {
        this.ertek = ertek;
    }

    /**
     * A parcella toString metodusa. Akkor hivodik meg, ha stringge alakitjuk a parcella peldanyt (pl.: kiiratas)
     * @return A parcella stringge alakitva
     */
    @Override
    public String toString() {
        return "Parcella{" + "coor1=(" + coor1[0] + "," + coor1[1] + "), coor2=(" + coor2[0] + "," + coor2[1]  + "), coor3=(" + coor3[0] + "," + coor3[1]  + "), tulajdonos=" + tulajdonos + ", ertek=" + ertek + '}';
    }

    
}
