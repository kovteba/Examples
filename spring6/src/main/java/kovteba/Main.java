package kovteba;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
   public static void main(String[] args) {

      GenericXmlApplicationContext context = new GenericXmlApplicationContext();
      context.load("applicationContext.xml");
      context.refresh();

      MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

      musicPlayer.playMusic();

      System.out.println(musicPlayer.getVolume());

      context.close();
   }
}
