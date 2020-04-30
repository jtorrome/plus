
import java.io.*;
import java.util.Scanner;

public class PruebaCargarMatriz {

    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);

        int[][] tablero = new int[9][9];
        int cont;

        String linea = "";
        String ruta = "";
        String nomFich = "";

        File arch;
        File miDir;
        FileReader in;
        BufferedReader buffIn;
        File[] archivos;

        System.out.println("********************\n***CARGAR PARTIDA***\n********************\n");
        System.out.println("Ruta por defecto");

        //Devuelve la ruta por defecto (directorio del proyecto)
        try {
            miDir = new File("");
            ruta = miDir.getCanonicalPath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(ruta);

        File carpeta = new File(ruta);

        archivos = carpeta.listFiles();

        System.out.println("Lista de partidas guardadas\n");
        //Lista los archivos que acaban en .txt
        for (int i = 0; i < archivos.length; i++) {
            if (archivos[i].getName().endsWith(".txt")) {
                System.out.println(archivos[i].getName());

            }
        }
        System.out.println();

        //Pide nombre de fichero.
        System.out.println("Escribe el nombre del archivo a cargar");
        nomFich = tec.nextLine();
        System.out.println();
        //Repite mientras no encuentre fichero
        do {
            //Crea nuevo tipo fichero con el nombre introducido
            arch = new File(nomFich);

            //Comprueba que el fichero existe
            if (arch.isFile()) {
                System.out.println("Archivo encontrado");
                //Si fichero existe, intenta leer linea
                
                try {
                    in = new FileReader(arch);
                    buffIn = new BufferedReader(in);
                    
                    linea = buffIn.readLine();
                    
                    in.close();
                    buffIn.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            } else {

                System.out.println("Archivo no encontrado\n");
                System.out.println("Escribe el nombre del archivo a cargar");
                nomFich = tec.nextLine();
                System.out.println();

            }

        } while (arch.isFile() != true);
        

        System.out.println("linea"+linea);
        
        cont=0;
        
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = Character.getNumericValue(linea.charAt(cont));
                cont++;
            }
        }
        
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print("["+tablero[i][j]+"]");
            }
            System.out.println();
        }
      

    }

}









        
        
       