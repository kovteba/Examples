package kovteba;

import java.util.Scanner;

public class App {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      Scanner scanner1 = new Scanner(System.in);


      int[] line = {1, 2, 3, 4, 5, 6, 7, 8};
      String[] collom = {"a", "b", "c", "d", "e", "f", "g", "h"};

      for (int i = 0; i < collom.length; i++) {
         for (int j = 0; j < line.length; j++) {
            System.out.print(collom[j] + line[i] + " ");
         }
         System.out.println();
      }

      System.out.print("Enter start letter position : ");
      String startLetter = scanner.nextLine();

      System.out.print("Enter start number position : ");
      int startNumber = scanner.nextInt();


      System.out.print("Enter end letter position : ");
      String endLetter = scanner1.nextLine();

      System.out.print("Enter end number position : ");
      int endNumber = scanner1.nextInt();

      System.out.println(ChessHorse.logicHorse(startLetter, startNumber, endLetter, endNumber));

   }
}
