
package dado;

import java.awt.Desktop;
import java.io.File;
import java.lang.Runtime;
import javax.swing.*;

public class Dado {
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        int cont=0,tirada=0;
    
        tirada=Integer.parseInt(JOptionPane.showInputDialog(null,"Cantidad"));
       
        
        
        while(cont<10){
            
            tirada=dado(tirada);
        
            Thread.sleep(2000);
        
            cont++;
        
            Runtime.getRuntime().exec("taskkill /F /IM Microsoft.Photos.exe");
        
            Thread.sleep(1000);
        }
    }
    
     public static int dado (int dado)throws Exception{

        dado=(int)(Math.random()*6)+1;
         if(dado==1){
            File uno = new File ("src/Dado/dado_1.jpg");
            Desktop dt = Desktop.getDesktop();
            dt.open(uno);
        }
        if(dado==2){
            File dos = new File ("src/Dado/dado_2.jpg");
            Desktop dt = Desktop.getDesktop();
            dt.open(dos);
        }
        if(dado==3){
            File tres = new File ("src/Dado/dado_3.jpg");
            Desktop dt = Desktop.getDesktop();
            dt.open(tres);
        }
        if(dado==4){
            File cuatro = new File ("src/Dado/dado_4.jpg");
            Desktop dt = Desktop.getDesktop();
            dt.open(cuatro);
        }
        if(dado==5){
            File cinco = new File ("src/Dado/dado_5.jpg");
            Desktop dt = Desktop.getDesktop();
            dt.open(cinco);
        }
        if(dado==6){
            File seis = new File ("src/Dado/dado_6.jpg");
            Desktop dt = Desktop.getDesktop();
            dt.open(seis);
        }
        return dado;
       
    }
}