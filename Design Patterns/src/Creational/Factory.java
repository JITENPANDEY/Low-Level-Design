package Creational;
/**
 * <h2>Factory Method Design Pattern</h2>
 *
 * <p>
 * Factory Method defines an interface for creating an object, but allows
 * subclasses to decide which concrete implementation to instantiate.
 * The client depends only on the abstraction and is unaware of the
 * concrete class being created.
 * </p>
 *
 * <h3>GoF Intent</h3>
 * <p>
 * Define an interface for creating an object, but let subclasses decide
 * which class to instantiate.
 * </p>
 *
 * <h3>When to Use</h3>
 * <ul>
 *     <li>When object creation logic is complex.</li>
 *     <li>When the client should not know the concrete implementation.</li>
 *     <li>When new implementations may be added in the future.</li>
 *     <li>To reduce coupling between object creation and object usage.</li>
 * </ul>
 *
 * <h3>Characteristics</h3>
 * <ul>
 *     <li>Creates <b>one product</b> at a time.</li>
 *     <li>Encapsulates object creation logic.</li>
 *     <li>Promotes loose coupling.</li>
 *     <li>Follows the Open/Closed Principle.</li>
 * </ul>
 *
 * <h3>Advantages</h3>
 * <ul>
 *     <li>Hides object creation from the client.</li>
 *     <li>Easy to introduce new implementations.</li>
 *     <li>Improves maintainability and extensibility.</li>
 *     <li>Supports Dependency Inversion Principle.</li>
 * </ul>
 *
 * <h3>Disadvantages</h3>
 * <ul>
 *     <li>Requires additional classes.</li>
 *     <li>Can increase code complexity for simple scenarios.</li>
 * </ul>
 *
 * <h3>Examples</h3>
 * <ul>
 *     <li>Payment Factory</li>
 *     <li>Notification Factory</li>
 *     <li>Document Parser Factory</li>
 *     <li>Vehicle Factory</li>
 * </ul>
 *
 * <h3>Interview Tip</h3>
 * <p>
 * Factory Method creates a <b>single product</b>.
 * If multiple related objects need to be created together,
 * prefer the Abstract Factory pattern.
 * </p>
 */
public class Factory {
    public static void main(String[] args) {
        NotificationFactory notificationFactory = new SMSNotificationFactory();
        notificationFactory.notifyUser();

        notificationFactory = new EmailNotificationFactory();
        notificationFactory.notifyUser();

        notificationFactory = new PushNotificationFactory();
        notificationFactory.notifyUser();
    }
}

interface Notification {
    void notifyUser();
}

class SMSNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending an SMS notification");
    }
}

class EmailNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending an Email notification");
    }
}

class PushNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("Sending a Push notification");
    }
}

abstract class NotificationFactory {
    public abstract Notification createNotification();

    public void notifyUser() {
        createNotification().notifyUser();
    }
}

class SMSNotificationFactory extends NotificationFactory {
    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }
}

class EmailNotificationFactory extends NotificationFactory {
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}

class PushNotificationFactory extends NotificationFactory {
    @Override
    public Notification createNotification() {
        return new PushNotification();
    }
}


