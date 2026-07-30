package Creational;

/**
 * <h2>Prototype Design Pattern</h2>
 *
 * <p>
 * Prototype Pattern is a creational design pattern that creates new objects
 * by cloning existing objects instead of instantiating them from scratch.
 * It is useful when object creation is expensive or complex.
 * </p>
 *
 * <h3>GoF Intent</h3>
 * <p>
 * Specify the kinds of objects to create using a prototypical instance,
 * and create new objects by copying (cloning) this prototype.
 * </p>
 *
 * <h3>Why Prototype?</h3>
 * <ul>
 *     <li>Avoids expensive object creation.</li>
 *     <li>Improves performance by cloning existing objects.</li>
 *     <li>Reduces repetitive initialization logic.</li>
 *     <li>Useful when object construction involves costly operations.</li>
 * </ul>
 *
 * <h3>When to Use</h3>
 * <ul>
 *     <li>When object creation is expensive (database calls, file loading, network operations).</li>
 *     <li>When many similar objects need to be created.</li>
 *     <li>When object initialization is complex.</li>
 *     <li>When creating objects dynamically at runtime.</li>
 * </ul>
 *
 * <h3>Characteristics</h3>
 * <ul>
 *     <li>Creates objects by cloning an existing instance.</li>
 *     <li>Avoids repeated construction logic.</li>
 *     <li>Supports both shallow and deep copying.</li>
 *     <li>Improves performance for expensive object creation.</li>
 * </ul>
 *
 * <h3>Advantages</h3>
 * <ul>
 *     <li>Faster than creating objects from scratch.</li>
 *     <li>Reduces expensive initialization.</li>
 *     <li>Simplifies creation of complex objects.</li>
 *     <li>Allows runtime addition of new object types.</li>
 * </ul>
 *
 * <h3>Disadvantages</h3>
 * <ul>
 *     <li>Implementing cloning can be complex.</li>
 *     <li>Deep copying nested objects requires additional effort.</li>
 *     <li>Incorrect cloning may lead to shared mutable state.</li>
 * </ul>
 *
 * <h3>Shallow Copy vs Deep Copy</h3>
 * <ul>
 *     <li><b>Shallow Copy</b> → Copies primitive fields, but object references are shared.</li>
 *     <li><b>Deep Copy</b> → Creates copies of both primitive fields and referenced objects.</li>
 * </ul>
 *
 * <h3>Real-World Examples</h3>
 * <ul>
 *     <li>Java Object.clone()</li>
 *     <li>Game object cloning (characters, enemies, weapons)</li>
 *     <li>Document or template duplication</li>
 *     <li>Graphics editors (copy/paste of shapes)</li>
 *     <li>Spring Bean Prototype Scope</li>
 * </ul>
 *
 * <h3>Interview Questions</h3>
 * <ul>
 *     <li>What problem does the Prototype Pattern solve?</li>
 *     <li>When should Prototype be preferred over Factory?</li>
 *     <li>What is the difference between shallow copy and deep copy?</li>
 *     <li>How does Java's <code>Cloneable</code> interface work?</li>
 *     <li>Why is <code>Object.clone()</code> considered problematic?</li>
 *     <li>How would you implement deep cloning?</li>
 * </ul>
 *
 * <h3>Interview Tip</h3>
 * <p>
 * Prototype Pattern is preferred when object creation is expensive and multiple
 * similar objects are required. In Java interviews, be prepared to explain the
 * difference between shallow copy and deep copy, as it is one of the most
 * frequently asked follow-up questions.
 * </p>
 */
public class Prototype {
    public static void main(String[] args) throws CloneNotSupportedException {
//        Employee original = new Employee("Rahul", new Address("Delhi"));
//        Employee shallowCopy = original.clone();
//        Employee shallowCopy2 = original.clone();
//
//        shallowCopy.getAddress().setCity("Mumbai");
//        System.out.println(original); // Output: Mumbai
//        System.out.println(shallowCopy);
//        System.out.println(shallowCopy2);
//        System.out.println(original.equals(original.clone()));// false

//        Person person =  new Person("Rahul", new Address("Delhi"));
//        Person copy = new Person(person);
//        copy.getAddress().setCity("Mumbai");
//        System.out.println(person);
//        System.out.println(copy);

        Human human = Human.of("Rahul", new Address("Delhi"));
        Human copy = Human.copyOf(human);
        copy.getAddress().setCity("Mumbai");
        System.out.println(human);
        System.out.println(copy);

    }
}

/**
 * <h2>Why Cloneable/Object.clone() is Discouraged?</h2>
 *
 * <ul>
 *     <li><b>Performs Shallow Copy by Default</b><br>
 *         Object.clone() copies object references instead of creating new
 *         objects, which can lead to shared mutable state.
 *     </li>
 *
 *     <li><b>Deep Copy is Manual and Error-Prone</b><br>
 *         Every mutable nested object must be cloned explicitly, making
 *         implementation complex for large object graphs.
 *     </li>
 *
 *     <li><b>Constructors are Not Invoked</b><br>
 *         The clone() method bypasses constructors, so important
 *         initialization logic may not execute.
 *     </li>
 *
 *     <li><b>Requires the Cloneable Marker Interface</b><br>
 *         If a class does not implement Cloneable, calling clone()
 *         throws CloneNotSupportedException.
 *     </li>
 *
 *     <li><b>Throws Checked Exception</b><br>
 *         The clone() method requires handling or declaring
 *         CloneNotSupportedException, adding unnecessary boilerplate.
 *     </li>
 *
 *     <li><b>Protected clone() Method</b><br>
 *         Object.clone() is protected, so every class must override
 *         it and increase its visibility.
 *     </li>
 *
 *     <li><b>Breaks Encapsulation</b><br>
 *         Since cloning bypasses constructors, object invariants may
 *         not be preserved.
 *     </li>
 *
 *     <li><b>Difficult to Maintain</b><br>
 *         Whenever new mutable fields are added, the clone() method
 *         must also be updated to perform deep copying.
 *     </li>
 *
 *     <li><b>Not Recommended by Effective Java</b><br>
 *         Joshua Bloch recommends using Copy Constructors,
 *         Copy Factory Methods, or the Builder Pattern instead
 *         of Cloneable/Object.clone().
 *     </li>
 * </ul>
 *
 * <h3>Recommended Alternatives</h3>
 * <ul>
 *     <li>Copy Constructor</li>
 *     <li>Copy Factory Method</li>
 *     <li>Builder Pattern</li>
 * </ul>
 */
//1. using Inbuilt Cloneable interface and Object.clone() method
class Address implements Cloneable {
    private String city;

    public Address(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                '}';
    }
}

class Employee implements Cloneable {
    private String name;
    private Address address;

    public Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Employee clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone();
        cloned.address = this.address.clone();
        return cloned;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

/**
 * <h2>Copy Constructor</h2>
 *
 * <p>
 * A Copy Constructor creates a new object by copying the state of an
 * existing object. Unlike <code>Object.clone()</code>, it explicitly
 * initializes the new object through a constructor, making the copying
 * process safer, clearer, and easier to maintain.
 * </p>
 *
 * <h3>Why Use a Copy Constructor?</h3>
 * <ul>
 *     <li>Avoids the limitations of <code>Cloneable</code> and <code>Object.clone()</code>.</li>
 *     <li>Supports both shallow and deep copying as needed.</li>
 *     <li>Invokes constructors, ensuring proper object initialization.</li>
 *     <li>Provides full control over which fields should be copied.</li>
 *     <li>Simple to implement, read, and maintain.</li>
 * </ul>
 *
 * <h3>When to Use</h3>
 * <ul>
 *     <li>When creating a copy of an existing object.</li>
 *     <li>When deep copying mutable objects.</li>
 *     <li>When object construction logic should not be bypassed.</li>
 *     <li>When following the recommendations from <i>Effective Java</i>.</li>
 * </ul>
 *
 * <h3>Advantages</h3>
 * <ul>
 *     <li>Constructors are invoked during object creation.</li>
 *     <li>No dependency on the <code>Cloneable</code> interface.</li>
 *     <li>No <code>CloneNotSupportedException</code>.</li>
 *     <li>Easy to implement deep copies.</li>
 *     <li>Preserves encapsulation and object invariants.</li>
 *     <li>Easy to extend when new fields are added.</li>
 * </ul>
 *
 * <h3>Disadvantages</h3>
 * <ul>
 *     <li>Requires manually copying every field.</li>
 *     <li>Nested mutable objects must also be copied explicitly.</li>
 * </ul>
 *
 * <h3>Real-World Examples</h3>
 * <ul>
 *     <li>Creating a copy of an Employee or User object.</li>
 *     <li>Copying DTOs before modification.</li>
 *     <li>Creating immutable snapshots of objects.</li>
 * </ul>
 *
 * <h3>Interview Questions</h3>
 * <ul>
 *     <li>What is a Copy Constructor?</li>
 *     <li>How is a Copy Constructor different from clone()?</li>
 *     <li>Why does Effective Java recommend Copy Constructors over Cloneable?</li>
 *     <li>How do you implement deep copying using a Copy Constructor?</li>
 * </ul>
 *
 * <h3>Interview Tip</h3>
 * <p>
 * Joshua Bloch recommends using Copy Constructors (or Copy Factory Methods)
 * instead of <code>Cloneable</code> because they are safer, more explicit,
 * easier to maintain, and support deep copying without the pitfalls of
 * <code>Object.clone()</code>.
 * </p>
 */
//2. Using Copy Constructor
class Person {
    private String name;
    private Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Copy constructor
    public Person(Person other) {
        this.name = other.name;
        this.address = new Address(other.address.getCity());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * <h2>Static Factory Method</h2>
 *
 * <p>
 * A Static Factory Method is a static method that returns an instance of a
 * class instead of creating objects using a public constructor. It provides
 * meaningful names, better control over object creation, and flexibility in
 * deciding whether to create a new object or return an existing one.
 * </p>
 *
 * <h3>Effective Java Recommendation</h3>
 * <p>
 * Joshua Bloch recommends considering Static Factory Methods instead of
 * constructors whenever they provide a clearer or more flexible API.
 * (Effective Java - Item 1)
 * </p>
 *
 * <h3>Why Use Static Factory Methods?</h3>
 * <ul>
 *     <li>Can have meaningful names that describe the object being created.</li>
 *     <li>May return an existing cached instance instead of creating a new one.</li>
 *     <li>Can return objects of any subtype of the declared return type.</li>
 *     <li>Encapsulates object creation logic.</li>
 *     <li>Provides flexibility to change the implementation without affecting clients.</li>
 * </ul>
 *
 * <h3>When to Use</h3>
 * <ul>
 *     <li>When object creation is complex.</li>
 *     <li>When caching or reusing objects improves performance.</li>
 *     <li>When the exact implementation should be hidden from the client.</li>
 *     <li>When different object creation strategies are required.</li>
 * </ul>
 *
 * <h3>Advantages</h3>
 * <ul>
 *     <li>Meaningful method names improve readability.</li>
 *     <li>Can reuse existing instances (Singleton, Flyweight, Object Pool).</li>
 *     <li>Can return different implementations based on runtime conditions.</li>
 *     <li>Reduces coupling between clients and concrete classes.</li>
 *     <li>Supports encapsulation and abstraction.</li>
 * </ul>
 *
 * <h3>Disadvantages</h3>
 * <ul>
 *     <li>Classes without public constructors cannot be subclassed.</li>
 *     <li>Developers may not immediately recognize static methods as object creators.</li>
 * </ul>
 *
 * <h3>Common Naming Conventions</h3>
 * <ul>
 *     <li>of()</li>
 *     <li>from()</li>
 *     <li>valueOf()</li>
 *     <li>getInstance()</li>
 *     <li>newInstance()</li>
 *     <li>instance()</li>
 *     <li>create()</li>
 * </ul>
 *
 * <h3>JDK Examples</h3>
 * <ul>
 *     <li>List.of()</li>
 *     <li>Map.of()</li>
 *     <li>Set.of()</li>
 *     <li>Integer.valueOf()</li>
 *     <li>Boolean.valueOf()</li>
 *     <li>Optional.of()</li>
 *     <li>LocalDate.now()</li>
 *     <li>Executors.newFixedThreadPool()</li>
 * </ul>
 *
 * <h3>Interview Questions</h3>
 * <ul>
 *     <li>What is a Static Factory Method?</li>
 *     <li>How is it different from a constructor?</li>
 *     <li>Why does Effective Java recommend Static Factory Methods?</li>
 *     <li>Can a Static Factory Method return the same object multiple times?</li>
 *     <li>Can it return different implementations of an interface?</li>
 * </ul>
 *
 * <h3>Interview Tip</h3>
 * <p>
 * A constructor always creates an instance of its own class, whereas a
 * Static Factory Method can return cached objects, different implementations,
 * or even the same singleton instance. This flexibility is the primary reason
 * Effective Java recommends preferring Static Factory Methods over constructors
 * when appropriate.
 * </p>
 */
//3. Static Factory Method for Copying
class Human {
    private String name;
    private Address address;

    // make constructor private
    private Human(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    public static Human of(String name, Address address) {
        return new Human(name, address);
    }

    public static Human copyOf(Human other) {
        return new Human(other.name, new Address(other.address.getCity()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
