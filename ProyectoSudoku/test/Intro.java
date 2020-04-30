

public class Intro {
    public static void main(String[] args)throws Exception {
        
        String neo="Wake up Neo";
        String matrix = "The Matrix has you";
        String rabbit = "Follow the white rabbit.";
        String knock = "Knock, knock, Neo.";
        
        for (int i = 0; i < neo.length(); i++) {
            System.out.print(neo.charAt(i));
            Thread.sleep(300);
        }
        Thread.sleep(500);
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            Thread.sleep(500);
        }
        System.out.println();
        Thread.sleep(1000);
        for (int i = 0; i < matrix.length(); i++) {
            System.out.print(matrix.charAt(i));
            Thread.sleep(300);
        }
        Thread.sleep(500);
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            Thread.sleep(500);
        }
        System.out.println();
        Thread.sleep(2000);
        for (int i = 0; i < rabbit.length(); i++) {
            System.out.print(rabbit.charAt(i));
            Thread.sleep(300);
        }
        System.out.println();
        Thread.sleep(2000);
        
        System.out.println(knock);
        Thread.sleep(3000);  
    }
}
