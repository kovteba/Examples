# Dependency Pull

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/util
        http://www.springframework.org/schema/util/spring-util-2.5.xsd">
    
    <bean id="classicMusicBean" class="kovteba.dependencypull.ClassicalMusic"/>

    <bean id="musicPlayer"  class="kovteba.dependencypull.MusicPlayer">
        <constructor-arg index="0" ref="classicMusicBean"/>
        <constructor-arg index="1" value="120"/>
    </bean>    

</beans>
```
```java
package kovteba.dependencypull;
public interface Music {
   String getSong();
}
```
```java
package kovteba.dependencypull;
public class ClassicalMusic implements Music {
   @Override
   public String getSong() {
      return "Playing classical music";
   }
}
```
```java
package kovteba.dependencypull;
public class RockMusic implements Music {
   @Override
   public String getSong() {
      return "Playing rock music";
   }
}
```
```java
package kovteba.dependencypull;
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
package kovteba.dependencypull;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Main {
   public static void main(String[] args) {
      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
      MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
      musicPlayer.playMusic();
      System.out.println(musicPlayer.getVolume());
      context.close();
   }
}
```