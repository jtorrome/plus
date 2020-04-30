package proyectosudoku;

import java.io.*;
import java.util.Scanner;

public class Sudoku {

    public static void main(String[] args) throws Exception {
        Scanner tec = new Scanner(System.in);

        int opcion = 0;
        int selecTablero = 1;
        boolean compruebaCero;

        String listaJugadas = "";

        int[][] tablero = new int[9][9];

        //Tablero por defecto
        tablero = nuevoTablero(selecTablero);

        intro();
        System.out.println("\n");

        System.out.println("************\n***SUDOKU***\n************\n");

        do {
            System.out.println("Selecciona una  opcion\n"
                    + "1. Instrucciones\n"
                    + "2. Jugar\n"
                    + "3. Seleccionar tablero\n"
                    + "4. Cargar juego guardado\n"
                    + "0. Salir\n");

            //Pide la opcion del menu principal
            opcion = Integer.parseInt(tec.nextLine());

            switch (opcion) {
                case 1:
                    //<editor-fold defaultstate="collapsed" desc="INSTRUCCIONES">
                    System.out.println("*******************\n***INSTRUCCIONES***\n*******************\n");
                    System.out.println("REGLAS DE JUEGO");
                    System.out.println("Este juego está compuesto por una cuadrícula de 9x9 casillas, dividida en regiones de 3x3 casillas.\n"
                            + "Partiendo de algunos números ya dispuestos en algunas de las casillas, hay que completar las casillas vacías\n"
                            + "con dígitos del 1 al 9 sin que se repitan por fila, columna o región.\n");
                    System.out.println("TABLEROS");
                    System.out.println("El juego consta de 3 tableros con valores predefinidos.\n"
                            + "El tablero se selecciona en la opcion 3. Seleccionar tablero. Una vez seleccionado uno de los tableros\n"
                            + "este se muestra en pantalla. Puedes cambiar entre tableros tantas veces como quieras hasta confirmar.\n");
                    System.out.println("EN LA PARTIDA");
                    System.out.println("En caso de no seleccionar tablero antes de jugar, se aplicaz por defecto el tablero numero 1\n"
                            + "El programa pide corrdenadas (x,y) y un valor en la misma linea.\n"
                            + "Las coordenadas se escriben (a-i) para filas (1-9) para columnas seguido de un valor entero\n"
                            + "separado por un espacio\n"
                            + "Puedes retroceder jugadas."
                            + "Durante la partida, puedes escribir las siguientes ordenes:\n"
                            + "(a-i)(1-9) (valor) <- Introducir un valor en el tablero\n"
                            + "guardar <- Guarda el progreso actual de la partida\n"
                            + "reiniciar <- reinicia el tablero con los valores por defecto\n"
                            + "nuevo <- Genera un nuevo tablero\n");
                    System.out.println("GUARDAR Y CARGAR");
                    System.out.println("El progreso de la partida puede ser guardado y retomado posteriormente.(ver punto anterior)\n"
                            + "Dicho progreso se almacena en un fichero de texto localizado en la carpeta del proyecto.\n"
                            + "Tras introducir el nombre del archivo de guardado, comprueba si ya existe un fichero con el mismo nombre,\n"
                            + "dando la opcion de sobreescribir o crear uno nuevo. El progreso se guarda al crear un archivo nuevo o\n"
                            + "sobreescribir un archivo existente.\n"
                            + "Una vez guardado, muestra la ubicacion del archivo.\n"
                            + "Se puede cargar el progreso de una partida desde la opcion 4. Cargar juego guardado del menu principal.\n"
                            + "El programa lista los archivos .txt (partidas guardadas) que se encuentran en el directorio\n."
                            + "Tras escribir el nombre del archivo a cargar, la secuencia de numeros almacenados pasan a ser\n"
                            + "el tablero actual."
                            + "NOTA: Si se selecciona otro tablero desde la opcion 3. el tablero cargado cambiará al nuevo. ");
                    System.out.println("\n");

                    //</editor-fold>
                    break;
                case 2:
                    //<editor-fold defaultstate="collapsed" desc="Jugar">
                    String valores; //Valores introducidos por usuario

                    int fila = 0;
                    int columna = 0;
                    int valor = 0;

                    boolean terminar = false; //Reiniciar
                    boolean filaRep = false,
                     colRep = false,
                     cajaRep = false; //Comprobar numeros repetidos

                    do {

                        imprimeTablero(tablero);

                        System.out.println("Introduce Orden");

                        valores = tec.nextLine();

                        if (valores.equalsIgnoreCase("reiniciar")) {

                            //Devuelve el tablero seleccionado con valores por defecto
                            tablero = nuevoTablero(selecTablero);

                        } else if (valores.equalsIgnoreCase("guardar")) {

                            //Guarda una lista de jugadas para poder retroceder
                            guardaPartida(tablero);

                        } else if (valores.equalsIgnoreCase("atras")) {
                            //Se le pasa una lista de jugadas y pone en la ultima posicion indicada
                            //el valor de la jugada anterior. 
                            //Si este contenia anteriormente un cero, el espacio queda vacio en pantalla. 
                            retrocede(listaJugadas, tablero);

                        } else if (valores.equalsIgnoreCase("terminar")) {

                            terminar = true;
                            System.out.println();

                        } else {
                            //Convierte los valores introducidos a enteros
                            fila = devuelveNumFila(valores);
                            columna = Integer.parseInt(valores.substring(1, 2));
                            valor = Integer.parseInt(valores.substring(3, 4));

                            //Almacena las jugadas
                            listaJugadas = listaJugadas.concat(guardaJugadas(fila, columna, valor));

                            System.out.println("X=" + fila + "Y=" + (columna - 1) + "valor=" + valor);

                            //Lista de jugadas se guarda OK
                            //System.out.println(listaJugadas);
                            //Comprueba si esta repetido
                            filaRep = compruebaFila(tablero, valor, fila);
                            colRep = compruebaColumna(tablero, valor, columna);
                            compruebaSector(tablero, fila, columna, valor);
                        }
                        //Si el valor no esta repetido añade valor al tablero
                        if (filaRep == false && colRep == false && cajaRep == false) {
                            tablero[fila][columna - 1] = valor;
                        }
                        //Si el valor esta repetido,muestra mensaje
                        if (filaRep || colRep) {
                            System.out.println("El numero está repetido");
                        }
                        //Comprueba si todavia existe algun valor cero
                        compruebaCero = compruebaCompleto(tablero);

                        //Si no encuentra ningun cero, el juego se da por completo
                        if (compruebaCero == false) {
                            terminar = true;
                            System.out.println("Has ganado");
                        }

                    } while (terminar == false);

                    //</editor-fold>
                    break;
                case 3:
                    //<editor-fold defaultstate="collapsed" desc="Tablero">
                    System.out.println("*************************\n"
                            + "***SELECCIONAR TABLERO***\n"
                            + "*************************\n");
                    System.out.println("Selecciona un tablero (1-3)");

                    selecTablero = Integer.parseInt(tec.nextLine());

                    do {
                        tablero = nuevoTablero(selecTablero);
                        imprimeTablero(tablero);

                        System.out.println();
                        System.out.println("1. Seleccionar tablero (1-3)\n0. Confirmar y salir");
                        selecTablero = Integer.parseInt(tec.nextLine());

                    } while (selecTablero != 0);

                    //</editor-fold>
                    break;
                case 4:

                    System.out.println("******************\n***CARGAR JUEGO***\n******************\n");
                    tablero = cargaPartida();
                    break;
                default:
                    System.out.println("Numero introducido no valido");
            }

        } while (opcion != 0);

    }

    public static int[][] nuevoTablero(int seleccion) {

        int[][] tablero = new int[9][9];

        switch (seleccion) {
            case 1:
                //<editor-fold defaultstate="collapsed" desc="Tablero 1">
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
                //</editor-fold>       
                break;

            case 2:
                //<editor-fold defaultstate="collapsed" desc="Tablero 2">
                tablero[0][4] = 4;
                tablero[0][6] = 3;
                tablero[1][1] = 5;
                tablero[1][8] = 1;
                tablero[2][4] = 1;
                tablero[3][0] = 9;
                tablero[3][2] = 4;
                tablero[3][4] = 6;
                tablero[3][7] = 2;
                tablero[4][0] = 7;
                tablero[4][5] = 8;
                tablero[5][2] = 8;
                tablero[5][4] = 2;
                tablero[6][0] = 5;
                tablero[6][4] = 3;
                tablero[7][1] = 8;
                tablero[7][5] = 4;
                tablero[8][0] = 6;
                tablero[8][4] = 5;
                tablero[8][6] = 4;
                //</editor-fold>
                break;

            case 3:
                //<editor-fold defaultstate="collapsed" desc="Tablero 3">
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

                //</editor-fold>
                break;

        }

        return tablero;
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

    public static int devuelveNumFila(String valores) {
        int f = 0;

        char c = valores.charAt(0);

        if (c == 'a') {
            f = 0;
        } else if (c == 'b') {
            f = 1;
        } else if (c == 'c') {
            f = 2;
        } else if (c == 'd') {
            f = 3;
        } else if (c == 'e') {
            f = 4;
        } else if (c == 'f') {
            f = 5;
        } else if (c == 'g') {
            f = 6;
        } else if (c == 'h') {
            f = 7;
        } else if (c == 'i') {
            f = 8;
        }

        return f;
    }

    public static boolean compruebaFila(int[][] tablero, int valor, int fila) {

        boolean flag = false;

        for (int i = 0; i < tablero.length; i++) {

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

    public static boolean compruebaCompleto(int[][] tablero) {
        boolean existeCero = false;

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {

                if (tablero[i][j] == 0) {
                    existeCero = true;
                }
            }

        }

        return existeCero;
    }

    public static void guardaPartida(int[][] tablero) {

        Scanner tec = new Scanner(System.in);

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

                //Si sobreescribe, escribe en el fichero.
                if (c == 's') {

                    try {
                        out = new FileWriter(fich1);
                        miBufferOut = new BufferedWriter(out);

                        //Recorre tablero y guarda los valores en un string
                        for (int i = 0; i < tablero.length; i++) {
                            for (int j = 0; j < tablero[i].length; j++) {
                                //Pasa de entero a String
                                String a = String.valueOf(tablero[i][j]);
                                miBufferOut.write(a);
                                //Guarda los cambios
                                miBufferOut.flush();
                            }
                        }
                        cont = true;
                        //Cierra writers
                        out.close();
                        miBufferOut.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Archivo Sobrescrito\n");

                } else if (c == 'n') {
                    System.out.print("Introduce nuevamente");
                }

                //Si no encuentra fichero con el mismo nombre guarda directamente.
                //Mismo proceso que arriba
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
                System.out.println("Archivo creado\n");

                cont = true;
            }

        } while (cont != true);

        //Muestra la ruta donde se guarda el archivo
        try {

            String ruta = fich1.getCanonicalPath();

            System.out.println("Partida guardada en la ruta\n" + ruta);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[][] cargaPartida() {

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

        cont = 0;

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = Character.getNumericValue(linea.charAt(cont));
                cont++;
            }
        }

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print("[" + tablero[i][j] + "]");
            }
            System.out.println();
        }

        return tablero;
    }

    public static String guardaJugadas(int f, int c, int v) {

        String jugada = "";

        //Almacena la lista de jugadas. Cada 3 caracteres es una jugada
        jugada = jugada.concat(Integer.toString(f)).concat(Integer.toString(c).concat(Integer.toString(v)));

        return jugada;
    }

    public static int[][] retrocede(String listaJugadas, int[][] tablero) {

        String tiradaStr = "";
        String tirada;

        int fila, columna, valor;
        char f, c, v;

        System.out.println("Retrocede recibe lista\n" + listaJugadas);

        //Extrae n-4 (valor jugada anterior) a n-1 (2 ultimas posiciones)
        tiradaStr = listaJugadas.substring(listaJugadas.length() - 4, listaJugadas.length() - 1);
        System.out.println("TiradaStr\n" + tiradaStr);

        f = tiradaStr.charAt(1);
        c = tiradaStr.charAt(2);
        v = tiradaStr.charAt(0);

        System.out.println("Fila " + f);
        System.out.println("Columna " + c);
        System.out.println("Valor " + v);

        tirada = Character.toString(f).concat(Character.toString(c).concat(Character.toString(v)));

        System.out.println("Tirada concatenada " + tirada);

        //Pasa tirada a valores enteros
        fila = Integer.parseInt(tiradaStr.substring(1, 2));

        columna = Integer.parseInt(tiradaStr.substring(2, 3));

        valor = Integer.parseInt(tiradaStr.substring(0, 1));

        System.out.println("Fila " + fila + " Columna " + columna + "Valor " + valor);

        //Sustituye la posicion con el valor de la jugada anterior
        tablero[fila][columna] = valor;

        return tablero;
    }

    public static void intro() throws Exception {

        String[] tituloSudoku = new String[9];
        String[] instalando = new String[4];

        String terminal = "JJ.System OS [Versión x0.ffffaa]\n(c) 2020 JJ.System Corporation. Todos los derechos reservados.\n";
        String l1 = "root@machine~$ ";
        String comando = "sudo apt-get install sudoku";
        String inicia = "sudo sudoku\n..............................\n";

        //<editor-fold defaultstate="collapsed" desc="Titulo Sudoku">
        tituloSudoku[0] = "     /\\\\\\\\\\\\\\\\\\\\\\                  /\\\\\\\\\\\\\\\\\\\\\\\\                   /\\\\\\        /\\\\\\";
        tituloSudoku[1] = "    /\\\\\\/////////\\\\\\               \\/\\\\\\////////\\\\\\                \\/\\\\\\     /\\\\\\//";
        tituloSudoku[2] = "    \\//\\\\\\      \\///                \\/\\\\\\      \\//\\\\\\               \\/\\\\\\  /\\\\\\//";
        tituloSudoku[3] = "      \\////\\\\\\          /\\\\\\    /\\\\\\ \\/\\\\\\       \\/\\\\\\     /\\\\\\\\\\    \\/\\\\\\\\\\\\//\\\\\\      /\\\\\\    /\\\\\\";
        tituloSudoku[4] = "          \\////\\\\\\      \\/\\\\\\   \\/\\\\\\ \\/\\\\\\       \\/\\\\\\   /\\\\\\///\\\\\\  \\/\\\\\\// \\//\\\\\\    \\/\\\\\\   \\/\\\\\\     ";
        tituloSudoku[5] = "              \\////\\\\\\   \\/\\\\\\   \\/\\\\\\ \\/\\\\\\       \\/\\\\\\  /\\\\\\  \\//\\\\\\ \\/\\\\\\    \\//\\\\\\   \\/\\\\\\   \\/\\\\\\    ";
        tituloSudoku[6] = "        /\\\\\\      \\//\\\\\\  \\/\\\\\\   \\/\\\\\\ \\/\\\\\\       /\\\\\\  \\//\\\\\\  /\\\\\\  \\/\\\\\\     \\//\\\\\\  \\/\\\\\\   \\/\\\\\\   ";
        tituloSudoku[7] = "        \\///\\\\\\\\\\\\\\\\\\\\\\/   \\//\\\\\\\\\\\\\\\\\\  \\/\\\\\\\\\\\\\\\\\\\\\\\\/    \\///\\\\\\\\\\/   \\/\\\\\\      \\//\\\\\\ \\//\\\\\\\\\\\\\\\\\\";
        tituloSudoku[8] = "           \\///////////      \\/////////   \\////////////        \\/////     \\///        \\///   \\/////////";
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Texto Instalando">
        instalando[0] = "Leyendo lista de paquetes.";
        instalando[1] = "Creando arbol de dependencias.";
        instalando[2] = "Leyendo la informacion de estado.";
        instalando[3] = "Se instalarán varios paquetes nuevos.";
        //</editor-fold>

        System.out.println(terminal);
        Thread.sleep(1000);
        System.out.print(l1);
        Thread.sleep(1000);

        for (int i = 0; i < comando.length(); i++) {
            System.out.print(comando.charAt(i));
            Thread.sleep(255);
        }
        System.out.println("\n");

        for (int i = 0; i < instalando.length; i++) {

            System.out.print(instalando[i]);

            for (int j = 0; j < 3; j++) {
                Thread.sleep(300);
                System.out.print(".");
            }
            System.out.println(" Hecho");
        }
        System.out.println();

        System.out.print(l1);
        Thread.sleep(1000);

        for (int i = 0; i < inicia.length(); i++) {
            System.out.print(inicia.charAt(i));
            Thread.sleep(255);
        }

        System.out.println("\n");

        for (int i = 0; i < tituloSudoku.length; i++) {
            System.out.println(tituloSudoku[i]);
            Thread.sleep(500);
        }

        Thread.sleep(2000);

    }

}
