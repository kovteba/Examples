package kovteba;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterInjection {
   public static void main(String[] args) {

      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContextSetter.xml");

      MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

      musicPlayer.playMusic();

      System.out.println(musicPlayer.getVolume());

      context.close();
   }
}
