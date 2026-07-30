package Creational;

/**
 * <h2>Builder Design Pattern</h2>
 *
 * <p>
 * Builder Pattern is a creational design pattern used to construct complex
 * objects step by step. It separates the construction process from the
 * representation, allowing the same construction process to create different
 * representations of an object.
 * </p>
 *
 * <h3>GoF Intent</h3>
 * <p>
 * Separate the construction of a complex object from its representation so
 * that the same construction process can create different representations.
 * </p>
 *
 * <h3>Why Builder?</h3>
 * <ul>
 *     <li>Avoids constructors with too many parameters (Telescoping Constructor Problem).</li>
 *     <li>Improves code readability using method chaining.</li>
 *     <li>Allows optional parameters without creating multiple constructors.</li>
 *     <li>Helps create immutable objects.</li>
 * </ul>
 *
 * <h3>When to Use</h3>
 * <ul>
 *     <li>When an object has many optional fields.</li>
 *     <li>When constructor parameters become difficult to manage.</li>
 *     <li>When object creation requires multiple steps.</li>
 *     <li>When creating immutable objects.</li>
 * </ul>
 *
 * <h3>Characteristics</h3>
 * <ul>
 *     <li>Constructs objects step by step.</li>
 *     <li>Supports method chaining (Fluent API).</li>
 *     <li>Separates object construction from business logic.</li>
 *     <li>Produces clean, readable, and maintainable code.</li>
 * </ul>
 *
 * <h3>Advantages</h3>
 * <ul>
 *     <li>Eliminates telescoping constructors.</li>
 *     <li>Improves readability and maintainability.</li>
 *     <li>Supports immutable object creation.</li>
 *     <li>Makes optional parameters easy to handle.</li>
 *     <li>Provides better control over object construction.</li>
 * </ul>
 *
 * <h3>Disadvantages</h3>
 * <ul>
 *     <li>Requires additional Builder class.</li>
 *     <li>Introduces slightly more boilerplate code.</li>
 *     <li>May be unnecessary for simple objects with few fields.</li>
 * </ul>
 *
 * <h3>Real-World Examples</h3>
 * <ul>
 *     <li>StringBuilder</li>
 *     <li>Lombok @Builder</li>
 *     <li>HttpRequest.Builder (Java 11)</li>
 *     <li>OkHttp Request.Builder</li>
 *     <li>Spring's UriComponentsBuilder</li>
 * </ul>
 *
 * <h3>Interview Questions</h3>
 * <ul>
 *     <li>What problem does Builder Pattern solve?</li>
 *     <li>What is the Telescoping Constructor Problem?</li>
 *     <li>Why is Builder preferred over constructors with many parameters?</li>
 *     <li>How does Builder help create immutable objects?</li>
 *     <li>How is Builder different from Factory Pattern?</li>
 *     <li>What is Fluent Interface in Builder Pattern?</li>
 * </ul>
 *
 * <h3>Interview Tip</h3>
 * <p>
 * Use the Builder Pattern when an object has many optional parameters or
 * requires step-by-step construction. It is commonly used in modern Java APIs
 * and is considered the preferred alternative to constructors with numerous
 * arguments.
 * </p>
 */
public class BuilderPattern {
    public static void main(String[] args) {
        User user = User.builder().setAge(27).build();
        System.out.println(user);
    }
}

class User {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String phone;
    private final String email;

    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.email = builder.email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String firstName; // required
        private String lastName; // required
        private int age; // optional
        private String phone;// optional
        private String email;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}