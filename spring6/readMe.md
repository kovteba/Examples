# Annotation configuration

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

    <context:annotation-config/>
    <context:component-scan base-package="kovteba"/>

</beans>
```
```java
package kovteba;
public interface Music {
   String getSong();
}
```
```java
package kovteba;
import org.springframework.stereotype.Component;
@Component
public class ClassicalMusic implements Music {
   @Override
   public String getSong() {
      return "Playing classical music";
   }

}
```
```java
package kovteba;
import org.springframework.stereotype.Component;
@Component
public class RockMusic implements Music {
   @Override
   public String getSong() {
      return "Playing rock music";
   }

}
```
```java
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
```
```java
package kovteba;
import org.springframework.context.support.GenericXmlApplicationContext;
public class Main {
   public static void main(String[] args) {
      GenericXmlApplicationContext context = new GenericXmlApplicationContext();
      context.load("applicationContext.xml");
      context.refresh();
      MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
      musicPlayer.playMusic();
      context.close();
   }
}
```