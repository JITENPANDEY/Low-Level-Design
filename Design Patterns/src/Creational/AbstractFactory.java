package Creational;

/**
 * <h2>Abstract Factory Design Pattern</h2>
 *
 * <p>
 * Abstract Factory provides an interface for creating families of related
 * or dependent objects without specifying their concrete classes.
 * </p>
 *
 * <h3>GoF Intent</h3>
 * <p>
 * Provide an interface for creating families of related or dependent
 * objects without specifying their concrete classes.
 * </p>
 *
 * <h3>When to Use</h3>
 * <ul>
 *     <li>When multiple related objects should always be used together.</li>
 *     <li>When the application supports multiple product families.</li>
 *     <li>When switching between complete product families is required.</li>
 *     <li>When consistency between related objects must be maintained.</li>
 * </ul>
 *
 * <h3>Characteristics</h3>
 * <ul>
 *     <li>Creates a <b>family of related objects</b>.</li>
 *     <li>Ensures compatible objects are created together.</li>
 *     <li>Encapsulates creation of multiple products.</li>
 *     <li>Promotes loose coupling between client and concrete classes.</li>
 * </ul>
 *
 * <h3>Advantages</h3>
 * <ul>
 *     <li>Ensures consistency among related products.</li>
 *     <li>Easy to switch between entire product families.</li>
 *     <li>Supports Open/Closed Principle.</li>
 *     <li>Supports Dependency Inversion Principle.</li>
 * </ul>
 *
 * <h3>Disadvantages</h3>
 * <ul>
 *     <li>Introduces many interfaces and concrete classes.</li>
 *     <li>Adding a new product type requires changes to every concrete factory.</li>
 * </ul>
 *
 * <h3>Examples</h3>
 * <ul>
 *     <li>Car Manufacturing (BMW, Audi, Mercedes)</li>
 *     <li>Database Providers (MySQL, PostgreSQL, Oracle)</li>
 *     <li>Cloud Providers (AWS, Azure, GCP)</li>
 *     <li>Payment Gateway Families (Stripe, Razorpay, PayPal)</li>
 *     <li>UI Component Libraries (Windows, macOS, Linux)</li>
 * </ul>
 *
 * <h3>Interview Tip</h3>
 * <p>
 * Abstract Factory creates an <b>entire family of related objects</b>,
 * whereas Factory Method creates only a <b>single object</b>.
 * </p>
 */
interface Engine {
    void build();
}

interface Tyre {
    void manufacture();
}

interface seat {
    void make();
}

class AudiEngine implements Engine {
    @Override
    public void build() {
        System.out.println("Building Audi Engine");
    }
}

class BMWEngine implements Engine {
    @Override
    public void build() {
        System.out.println("Building BMW Engine");
    }
}

class AudiTyre implements Tyre {
    @Override
    public void manufacture() {
        System.out.println("Manufacturing Audi Tyre");
    }
}

class BMWTyre implements Tyre {
    @Override
    public void manufacture() {
        System.out.println("Manufacturing BMW Tyre");
    }
}

class AudiSeat implements seat {
    @Override
    public void make() {
        System.out.println("Making Audi Seat");
    }
}

class BMWSeat implements seat {
    @Override
    public void make() {
        System.out.println("Making BMW Seat");
    }
}

interface CarFactory {
    Engine createEngine();
    Tyre createTyre();
    seat createSeat();
}

class AudiFactory implements CarFactory {

    @Override
    public Engine createEngine() {
        return new AudiEngine();
    }

    @Override
    public Tyre createTyre() {
        return new AudiTyre();
    }

    @Override
    public seat createSeat() {
        return new AudiSeat();
    }
}

class BMWFactory implements CarFactory {

    @Override
    public Engine createEngine() {
        return new BMWEngine();
    }

    @Override
    public Tyre createTyre() {
        return new BMWTyre();
    }

    @Override
    public seat createSeat() {
        return new BMWSeat();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        CarFactory audiFactory = new AudiFactory();
        Engine audiEngine = audiFactory.createEngine();
        Tyre audiTyre = audiFactory.createTyre();
        seat audiSeat = audiFactory.createSeat();

        audiEngine.build();
        audiTyre.manufacture();
        audiSeat.make();

        System.out.println();

        CarFactory bmwFactory = new BMWFactory();
        Engine bmwEngine = bmwFactory.createEngine();
        Tyre bmwTyre = bmwFactory.createTyre();
        seat bmwSeat = bmwFactory.createSeat();

        bmwEngine.build();
        bmwTyre.manufacture();
        bmwSeat.make();
    }
}

