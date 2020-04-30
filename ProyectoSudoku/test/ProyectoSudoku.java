

import java.util.Scanner;

public class ProyectoSudoku {

    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);

        int[][] tablero = new int[9][9];

        tablero = nuevoTablero();

        String valores; //Valores introducidos por usuario

        int fila=0, columna=0, valor=0;

        boolean reset = false; //Reiniciar
        boolean filaRep = false, colRep = false, cajaRep = false; //Comprobar numeros repetidos

        do {

            imprimeTablero(tablero);

            System.out.println("Introduce Coordenadas Valor");
            valores = tec.nextLine();

            if (valores.equalsIgnoreCase("reiniciar")) {

                tablero = nuevoTablero();
            } else {

                fila = devuelveNumFila(valores);
                columna = Integer.parseInt(valores.substring(1, 2));
                valor = Integer.parseInt(valores.substring(3, 4));

                System.out.println("X=" + fila + "Y=" + columna + "valor=" + valor);

                filaRep = compruebaFila(tablero, valor, fila);
                colRep = compruebaColumna(tablero, valor, columna);

            }
            //Si el valor no esta repetido añade valor al tablero
            if (filaRep==false && colRep==false && cajaRep==false) {
                tablero[fila][columna-1]=valor;
            }
            //Si el valor esta repetido,muestra mensaje
            if (filaRep || colRep) {
                System.out.println("El numero está repetido");
            }

        } while (reset = true);

    }

    public static int[][] nuevoTablero() {
        int[][] tablero = new int[9][9];

        tablero[0][0] = 6;
        tablero[0][4] = 1;
        tablero[1][1] = 2;
        tablero[1][6] = 1;
        tablero[2][0] = 5;
        tablero[2][7] = 3;
        tablero[3][2] = 5;
        tablero[3][5] = 6;
        tablero[4][3] = 5;
        tablero[4][7] = 1;
        tablero[5][1] = 7;
        tablero[5][5] = 1;
        tablero[5][8] = 5;
        tablero[6][1] = 5;
        tablero[6][6] = 3;
        tablero[7][2] = 8;
        tablero[7][7] = 2;
        tablero[8][0] = 9;
        tablero[8][4] = 3;
        tablero[8][8] = 5;

        return tablero;
    }

    public static int devuelveNumFila(String valores) {
        int f = 0;

        char c1 = valores.charAt(0);

        if (c1 == 'a') {
            f = 0;
        } else if (c1 == 'b') {
            f = 1;
        } else if (c1 == 'c') {
            f = 2;
        } else if (c1 == 'd') {
            f = 3;
        } else if (c1 == 'e') {
            f = 4;
        } else if (c1 == 'f') {
            f = 5;
        } else if (c1 == 'g') {
            f = 6;
        } else if (c1 == 'h') {
            f = 7;
        } else if (c1 == 'i') {
            f = 8;
        }

        return f;
    }

    public static void imprimeTablero(int[][] tablero) {

        int fil = 0;
        int col = 1;
        System.out.println("-------------------------------");

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {

                if (fil % 3 == 0) {
                    System.out.print("|");
                }

                fil++;

                if (tablero[i][j] == 0) {
                    System.out.print("   ");
                } else {
                    System.out.print(" " + tablero[i][j] + " ");
                }

            }

            System.out.println("|");

            if (col % 3 == 0) {
                System.out.println("-------------------------------");
            }
            col++;
        }

    }

    public static boolean compruebaFila(int[][] tablero, int valor, int fila) {

        boolean flag = false;

        for (int i = 0; i < tablero.length-1; i++) {

            if (tablero[fila][i] == valor) {
                flag = true;
            }
        }
        return flag;
    }

    public static boolean compruebaColumna(int[][] tablero, int valor, int col) {

        boolean flag = false;

        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i][col - 1] == valor) {
                flag = true;
            }
        }

        return flag;
    }

    public static boolean compruebaSector(int[][] tablero, int fila, int col, int valor) {

        boolean flag = true;

        int[][] sector = new int[3][3];

        int minFila, maxFila;
        int minCol, maxCol;

        //Determina las filas de la caja
        if (fila >= 0 && fila <= 2) {
            minFila = 0;
            maxFila = 2;
        } else if (fila >= 3 && fila <= 5) {
            minFila = 3;
            maxFila = 5;
        } else {
            minFila = 6;
            maxFila = 8;
        }

        //Determina las columnas de la caja
        if (col >= 0 && col <= 2) {
            minCol = 0;
            maxCol = 2;
        } else if (col >= 3 && col <= 5) {
            minCol = 3;
            maxCol = 5;
        } else {
            minCol = 6;
            maxCol = 8;
        }

        //Recorre el rango de la caja y comprueba si encuentra el valor
        for (int i = minFila; i <= maxFila; i++) {
            for (int j = minCol; j <= maxCol; j++) {

                if (tablero[i][j] == valor) {
                    flag = true;
                }
            }
        }

        return flag;

    }
    
    public static void intro()throws Exception{
        
        System.out.println("***SUDOKU***");
        System.out.println();
        Thread.sleep(500);
        
        System.out.print("Please Wait");
        
        for (int i = 1; i <= 20; i++) {
            System.out.print(".");
            Thread.sleep(300);
        }
    }
}
