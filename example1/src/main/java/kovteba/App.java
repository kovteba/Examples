package kovteba;

import java.util.*;

public class App {
   public static void main(String[] args) {
      List<User> userList = new ArrayList<>();
      User user1 = new User("ASD");
      User user2 = new User("DFGE");
      User user3 = new User("SDFSD");
      User user4 = new User("SDFSDE");
      User user5 = new User("SDFSDFDS");
      userList.add(user4);
      userList.add(user1);
      userList.add(user5);
      userList.add(user2);
      userList.add(user3);

      userList.stream().map(User::getName).forEach(System.out::println);
      System.out.println();
      Collections.sort(userList, new ComparatorByName());
      userList.stream().map(User::getName).forEach(System.out::println);
   }
}



