package Creational;

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