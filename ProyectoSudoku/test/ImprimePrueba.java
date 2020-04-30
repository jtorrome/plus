

public class ImprimePrueba {

    public static void main(String[] args) {

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
}
