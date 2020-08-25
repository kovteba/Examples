package kovteba;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EagleServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp){
      try {
         java.io.PrintWriter out = resp.getWriter();
         out.print("<html>");
         out.print("<head><title></title></head>");
         out.print("<body>");
         out.print("<h1>" + eagle() + "</h1>");
         out.print("</body>");
         out.print("</html>");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private String eagle(){
      int a = (int) ( Math.random() * 3 );
      if (a == 0){
         return "eagle";
      } else {
         return "tails";
      }
   }

}
