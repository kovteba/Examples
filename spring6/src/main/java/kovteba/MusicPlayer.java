package kovteba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {

   private Music music;

   @Autowired
   public MusicPlayer(@Qualifier("rockMusic") Music music) {
      this.music = music;
   }

   public void playMusic(){
      System.out.println("Playing: " + music.getSong());
   }

}
