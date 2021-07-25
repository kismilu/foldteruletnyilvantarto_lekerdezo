
package kotprog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Kotprog {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	int x0, x1, x2, y0, y1 ,y2, value; 
    	String name, line;
    	
        Fold fold = new Fold();
        BufferedReader be = new BufferedReader (new FileReader("be.txt"));
        StringTokenizer s = new StringTokenizer(be.readLine());
		
        while( (line=be.readLine()) != null) {
			System.out.println(line);

        	s = new StringTokenizer(line);
            x0 = Integer.parseInt(s.nextToken());
            y0 = Integer.parseInt(s.nextToken());
            x1 = Integer.parseInt(s.nextToken());
            y1 = Integer.parseInt(s.nextToken());
            x2 = Integer.parseInt(s.nextToken());
            y2 = Integer.parseInt(s.nextToken());
            name = s.nextToken();
            value = Integer.parseInt(s.nextToken());
			            fold.addParcella(new Parcella( x0,y0,x1,y1,x2,y2, name, value));
            	
        }
    	
        Parcella legdragabbParcella = fold.legdragabbParcella();
        Parcella legolcsobbParcella = fold.legolcsobbParcella();
        Foldterulet legdragabbFoldterulet = fold.legdragabbFoldterulet();
        Foldterulet legolcsobbFoldterulet = fold.legolcsobbFoldterulet();
        
        System.out.println("____________________________________");
        fold.printParcellak();
        System.out.println("____________________________________");
        fold.printFoldterulet();
        System.out.println("____________________________________");
        
        System.out.println("Legolcsobb parcella tulaj: " + legolcsobbParcella.getTulajdonos() + " (" + legolcsobbParcella.getErtek()+"/"+legolcsobbParcella.getMeret()+")");
        System.out.println("Legdragabb parcella tulaj: " + legdragabbParcella.getTulajdonos()+ " (" + legdragabbParcella.getErtek()+"/"+legdragabbParcella.getMeret()+")");
        System.out.println("Legolcsobb Foldterulet tulaj: " + legolcsobbFoldterulet.getParcellak().get(0).getTulajdonos()+ " (" + legolcsobbFoldterulet.getOsszertek()+"/"+legolcsobbFoldterulet.getOsszmeret()+")");
        System.out.println("Legdragabb Foldterulet tulaj: " + legdragabbFoldterulet.getParcellak().get(0).getTulajdonos()+ " (" + legdragabbFoldterulet.getOsszertek()+"/"+legdragabbFoldterulet.getOsszmeret()+")");
        
        PrintWriter ki = new PrintWriter ("ki.txt");
        
        ki.println("Legolcsobb parcella tulaj: " + legolcsobbParcella.getTulajdonos() + " (" + legolcsobbParcella.getErtek()+"/"+legolcsobbParcella.getMeret()+")");
        ki.println("Legdragabb parcella tulaj: " + legdragabbParcella.getTulajdonos()+ " (" + legdragabbParcella.getErtek()+"/"+legdragabbParcella.getMeret()+")");
        ki.println("Legolcsobb Foldterulet tulaj: " + legolcsobbFoldterulet.getParcellak().get(0).getTulajdonos()+ " (" + legolcsobbFoldterulet.getOsszertek()+"/"+legolcsobbFoldterulet.getOsszmeret()+")");
        ki.println("Legdragabb Foldterulet tulaj: " + legdragabbFoldterulet.getParcellak().get(0).getTulajdonos()+ " (" + legdragabbFoldterulet.getOsszertek()+"/"+legdragabbFoldterulet.getOsszmeret()+")");
        
        
        ki.close();
        be.close(); 
        	
    }
    
}
