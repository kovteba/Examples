package kovteba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

   @GetMapping(value = "/before")
   public ModelAndView before(ModelAndView modelAndView){
      modelAndView.setViewName("before.jsp");
      return modelAndView;
   }

   @GetMapping(value = "/afterReturn")
   public ModelAndView afterReturn(ModelAndView modelAndView){
      modelAndView.setViewName("afterReturn.jsp");
      return modelAndView;
   }

   @GetMapping(value = "/afterThrow")
   public ModelAndView afterThrow(ModelAndView modelAndView) throws IllegalAccessException {
      modelAndView.setViewName("afterThrow.jsp");
      throw new IllegalAccessException();
   }

}
