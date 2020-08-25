# Contextualized Dependency

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
</beans>
```
```java
package kovteba.contextualizeddependency;
public interface Container {
    Object getDependency(String key);
}
```
```java
package kovteba.contextualizeddependency;
public interface ManagedComponent {
    void performLookup(Container container);  
}
```
```java
package kovteba.contextualizeddependency;
public class ContextualizedDependencyLookup implements ManagedComponent {
    private Dependency dependency;
    @Override
    public void performLookup(Container container) {
        this.dependency = (Dependency) container.getDependency("myDependency"); 
    }
    @Override
    public String toString() {
    	return dependency.toString();
    }
}
```
```java
package kovteba.contextualizeddependency;
public class DefaultContainer implements Container {
	@Override
	public Object getDependency(String key) {
        if("myDependency".equals(key)) {
        	return new Dependency();
        }
        throw new RuntimeException("Unknown dependency: " + key);
	}
}
```
```java
package kovteba.contextualizeddependency;
public class Dependency {
	@Override
	public String toString() {
		return "Hello from " + getClass();
	}
}
```
```java
package kovteba.contextualizeddependency;
public class CDLExample {
    public static void main(String[] args) {
    	Container container = new DefaultContainer();
    	ManagedComponent managedComponent = new ContextualizedDependencyLookup();
    	managedComponent.performLookup(container);
    	System.out.println(managedComponent);
    }
}
```