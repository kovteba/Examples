package kovteba;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {

   @Autowired
   private Music music;

   private int volume;

   // IoC
   public MusicPlayer(Music music, int volume) {
      this.music = music;
      this.volume = volume;
   }

   public void playMusic(){
      System.out.println("Playing: " + music.getSong());
   }

   public int getVolume() {
      return volume;
   }

   public void setVolume(int volume) {
      this.volume = volume;
   }
}
