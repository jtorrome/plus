
import java.io.*;
import java.util.Scanner;

public class PruebaGuardaMatriz {

    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);

        int[][] tablero = new int[9][9];

        tablero[0][1] = 4;
        tablero[0][5] = 6;
        tablero[1][0] = 5;
        tablero[1][3] = 1;
        tablero[1][6] = 2;
        tablero[2][2] = 1;
        tablero[2][3] = 8;
        tablero[3][4] = 3;
        tablero[3][6] = 4;
        tablero[3][8] = 6;
        tablero[4][1] = 8;
        tablero[4][3] = 4;
        tablero[5][0] = 9;
        tablero[5][8] = 7;
        tablero[6][5] = 1;
        tablero[6][7] = 3;
        tablero[7][6] = 7;
        tablero[8][1] = 6;
        tablero[8][2] = 5;
        tablero[8][7] = 9;

        File fich1;

        FileReader in;
        FileWriter out;
        BufferedReader miBufferIn;
        BufferedWriter miBufferOut;

        boolean cont = false;

        do {
            
            System.out.println("Nombre Fichero");
            fich1 = new File(tec.nextLine());

            //Comprueba si existe un fichero con ese nombre
            if (fich1.isFile()) {

                System.out.println("Existe un archivo con el mismo nombre.\nSobreescribir? (s/n)\n");

                char c = tec.next().charAt(0);
                //Si sobreescribe.

                if (c == 's') {

                    try {
                        out = new FileWriter(fich1);
                        miBufferOut = new BufferedWriter(out);

                        for (int i = 0; i < tablero.length; i++) {
                            for (int j = 0; j < tablero[i].length; j++) {
                                String a = String.valueOf(tablero[i][j]);
                                miBufferOut.write(a);
                                miBufferOut.flush();
                            }
                        }
                        cont=true;

                        out.close();
                        miBufferOut.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Archivo Sobrescrito");

                } else if (c == 'n') {
                    System.out.print("Introduce nuevamente");
                }

            //Si no encuentra fichero con el mismo nombre guarda directamente
            } else {

                try {
                    out = new FileWriter(fich1);
                    miBufferOut = new BufferedWriter(out);

                    for (int i = 0; i < tablero.length; i++) {
                        for (int j = 0; j < tablero[i].length; j++) {
                            String a = String.valueOf(tablero[i][j]);
                            miBufferOut.write(a);
                            miBufferOut.flush();
                        }
                    }

                    out.close();
                    miBufferOut.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Archivo creado");
                
                cont=true;
            }

        } while (cont != true);
        
        try{
            
            String ruta =  fich1.getCanonicalPath();
            
            System.out.println("Partida guardada en la ruta\n"+ruta);
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

}
