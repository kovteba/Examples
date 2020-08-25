package kovteba;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConstructorInjection {
   public static void main(String[] args) {

      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContextConstructor.xml");

      MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

      musicPlayer.playMusic();

      System.out.println(musicPlayer.getVolume());

      context.close();
   }
}
