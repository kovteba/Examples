package kovteba;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IndexServlet extends HttpServlet {

   private String responseTemplate;

   @Override
   public void init() throws ServletException {
      try {
         URI templateURI = getClass().getResource("/template/index.html").toURI();
         byte[] bytes = Files.readAllBytes(Paths.get(templateURI));
         responseTemplate = new String(bytes, StandardCharsets.UTF_8);
      } catch (IOException | URISyntaxException e) {
         throw new ServletException(e);
      }
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.getWriter().print(responseTemplate.replace("{message}", "Hello from IndexServlet"));
      resp.setContentType("text/html");
      resp.setStatus(HttpServletResponse.SC_OK);
   }

}
