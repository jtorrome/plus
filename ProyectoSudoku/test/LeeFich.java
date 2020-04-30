
import java.io.*;

public class LeeFich {

    public static void main(String args[]) {
        File miDir = new File(".");
       
        String linea="";
        String ruta="fich.txt";
        String nomFich="";

        File arch;
        FileReader in;
        BufferedReader buffIn;
        File [] archivos;
        
        try{
            in = new FileReader(ruta);
            buffIn = new BufferedReader(in);
            
            linea = buffIn.readLine();
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        System.out.println(linea);
        
        
    }
}
