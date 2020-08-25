package kovteba;

public class Factorial {

   public static long factorial(int n) {
      long result = 1;
      if (n == 1 || n == 0) return result;
      result = n * factorial(n - 1);
      return result;
   }

}
