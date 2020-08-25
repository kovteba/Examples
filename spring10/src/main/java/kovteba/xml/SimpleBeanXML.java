package kovteba.xml;

public class SimpleBeanXML {
    private static final String DEFAULT_NAME = "DEFAULT_NAME";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void init() {
        System.out.println("Initializing bean");

       if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(
                    "You must set the age property of any beans of type " + SimpleBeanXML.class);
        }
    }

    public String toString() {
        return "Name: " + name + "\nAge: " + age;
    }


}
