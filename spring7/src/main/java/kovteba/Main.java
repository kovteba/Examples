package kovteba;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
   public static void main(String[] args) {

      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

      MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

      musicPlayer.playMusic();

      System.out.println(musicPlayer.getVolume());

      context.close();
   }
}
