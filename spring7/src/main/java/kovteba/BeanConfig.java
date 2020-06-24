package kovteba;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
