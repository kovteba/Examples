package kovteba;

public class MusicPlayer {

   private Music music;

   private int volume;

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