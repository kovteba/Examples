# Java configuration

```java
package kovteba;
public interface Music {
   String getSong();
}
```
```java
package kovteba;
public class ClassicalMusic implements Music {
   @Override
   public String getSong() {
      return "Playing classical music";
   }
}
```
```java
package kovteba;
public class RockMusic implements Music {
   @Override
   public String getSong() {
      return "Playing rock music";
   }
}
```
```java
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
```
```java
package kovteba;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
//@ComponentScan("kovteba")
public class BeanConfig {
   @Bean
   public RockMusic rockBean() {
      return new RockMusic();
   }
   @Bean(name = "musicPlayer")
   public MusicPlayer musicPlayer() {
      return new MusicPlayer(rockBean(), 12);
   }
}
```
```java
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
```