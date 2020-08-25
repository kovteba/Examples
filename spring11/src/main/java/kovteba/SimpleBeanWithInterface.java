package kovteba;

import org.springframework.beans.factory.InitializingBean;

public class SimpleBeanWithInterface implements InitializingBean {
    private static final String DEFAULT_NAME = "Luke Skywalker";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void myInit() {
        System.out.println("My Init");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing bean");

        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(
                    "You must set the age property of any beans of type " 
                    + SimpleBeanWithInterface.class);
        }
    }

    public String toString() {
        return "Name: " + name + "\nAge: " + age;
    }

}
