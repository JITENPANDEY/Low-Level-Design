package Creational;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Singleton Pattern
 *
 * <h2>Intent (GoF)</h2>
 * Ensure a class has only one instance and provide a global point of access
 * to that instance.
 *
 * <h2>Why Singleton?</h2>
 * A Singleton is used when creating multiple objects of a class is either
 * unnecessary, expensive, or may lead to inconsistent state across the
 * application.
 *
 * <h2>Common Use Cases</h2>
 * <ul>
 *     <li>Logging frameworks (shared logger instance)</li>
 *     <li>Configuration managers</li>
 *     <li>Thread pools (ExecutorService)</li>
 *     <li>Cache managers</li>
 *     <li>Database connection pools (e.g. HikariCP)</li>
 *     <li>Driver/Runtime objects</li>
 * </ul>
 *
 * <h2>Core Requirements</h2>
 * <ol>
 *     <li>
 *         <b>Private Constructor</b><br>
 *         Prevents external classes from creating objects using
 *         <code>new</code>.
 *     </li>
 *
 *     <li>
 *         <b>Static Instance Variable</b><br>
 *         Holds the single instance of the class.
 *     </li>
 *
 *     <li>
 *         <b>Static getInstance() Method</b><br>
 *         Provides global access to the single instance and creates it
 *         when required.
 *     </li>
 * </ol>
 *
 * <h2>Benefits</h2>
 * <ul>
 *     <li>Controlled object creation</li>
 *     <li>Global access point</li>
 *     <li>Reduced memory usage</li>
 *     <li>Consistent shared state</li>
 * </ul>
 *
 * <h2>Potential Drawbacks</h2>
 * <ul>
 *     <li>Acts as global state if overused</li>
 *     <li>Can make unit testing difficult</li>
 *     <li>Requires proper synchronization in multithreaded applications</li>
 * </ul>
 *

 * <h2>Singleton Implementations Comparison</h2>
 *
 * <table border="1">
 *     <tr>
 *         <th>Approach</th>
 *         <th>Lazy Initialization</th>
 *         <th>Thread-Safe</th>
 *         <th>Performance</th>
 *         <th>Notes</th>
 *     </tr>
 *
 *     <tr>
 *         <td><b>Eager Initialization</b></td>
 *         <td>No</td>
 *         <td>Yes</td>
 *         <td>Good</td>
 *         <td>Creates the instance during class loading, even if it is never used.</td>
 *     </tr>
 *
 *     <tr>
 *         <td><b>Lazy Initialization (Naive)</b></td>
 *         <td>Yes</td>
 *         <td>No</td>
 *         <td>Good</td>
 *         <td>Not suitable for multi-threaded applications due to race conditions.</td>
 *     </tr>
 *
 *     <tr>
 *         <td><b>Synchronized Method</b></td>
 *         <td>Yes</td>
 *         <td>Yes</td>
 *         <td>Poor</td>
 *         <td>Every call acquires a lock, causing unnecessary synchronization overhead.</td>
 *     </tr>
 *
 *     <tr>
 *         <td><b>Double-Checked Locking (DCL)</b></td>
 *         <td>Yes</td>
 *         <td>Yes</td>
 *         <td>Good</td>
 *         <td>Requires the <code>volatile</code> keyword to prevent instruction reordering.</td>
 *     </tr>
 *
 *     <tr>
 *         <td><b>Bill Pugh Singleton</b></td>
 *         <td>Yes</td>
 *         <td>Yes</td>
 *         <td>Best</td>
 *         <td>Uses JVM class-loading guarantees. Recommended for most Java applications.</td>
 *     </tr>
 *
 *     <tr>
 *         <td><b>Enum Singleton</b></td>
 *         <td>No (Eager)</td>
 *         <td>Yes</td>
 *         <td>Best</td>
 *         <td>Safest implementation. Automatically protects against serialization and reflection attacks.</td>
 *     </tr>
 * </table>
 *
 * <h3>Recommendation</h3>
 * <ul>
 *     <li><b>Bill Pugh Singleton</b> → Best choice when lazy initialization is required.</li>
 *     <li><b>Enum Singleton</b> → Safest implementation against reflection and serialization attacks.</li>
 *     <li><b>Double-Checked Locking</b> → Useful when you specifically need lazy initialization with explicit synchronization control.</li>
 * </ul>
 */
public class Singleton {

    public void doSomething() {
        // EagerSingleton instance = EagerSingleton.getInstance();
        // LazySingleton instance = LazySingleton.getInstance();
        // ThreadSafeSingleton instance = ThreadSafeSingleton.getInstance();
        // DoubleCheckedLockingSingleton instance = DoubleCheckedLockingSingleton.getInstance();
        // BillPughSingleton instance = BillPughSingleton.getInstance();
        // EnumSingleton.INSTANCE.doSomething();

        // Reflection Breaks Singleton
        try {
            Constructor<BillPughSingleton> constructor = BillPughSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            BillPughSingleton instance1 = constructor.newInstance();
            BillPughSingleton instance2 = BillPughSingleton.getInstance();

            System.out.println("Are both instances equal? " + (instance1 == instance2)); // false
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}

/**
 * <h2>1. Eager Initialisation</h2>
 * <p>Simple and thread-safe because the JVM guarantees that class loading
 * and static initialization are performed only once in a thread-safe manner.</p>
 *
 * <p>However, the Singleton instance is created eagerly during class loading,
 * regardless of whether it is ever used.</p>
 *
 * <p>This approach can waste memory and resources if object creation is expensive,
 * such as establishing a database connection, reading configuration files,
 * or performing other costly initialization.</p>
 */
class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }
}

/**
 * <h2>2. Lazy Initialization (NOT thread-safe — common bug)</h2>
 * <b>Disadvantages:</b>
 * <ul>
 *     <li>Works correctly only in a single-threaded environment.</li>
 *     <li>Not thread-safe.</li>
 *     <li>Multiple threads can simultaneously evaluate
 *         <code>if (instance == null)</code> as <code>true</code>.</li>
 *     <li>This creates a race condition during instance creation.</li>
 *     <li>As a result, multiple Singleton objects may be created,
 *         violating the Singleton principle.</li>
 * </ul>
 */
class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

/**
 * <h1>3. Thread-Safe (synchronized method) — but slow</h1>
 * <ul>
 *     <li>Fixes the race condition by synchronizing the <code>getInstance()</code> method.</li>
 *     <li>Every call to <code>getInstance()</code> acquires the lock, even after the Singleton instance has already been created.</li>
 *     <li>Unnecessary synchronization introduces additional overhead.</li>
 *     <li>Can become a performance bottleneck in high-concurrency applications.</li>
 *     <li>Not the preferred implementation for performance-critical systems.</li>
 * </ul>
 */
class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {}

    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}

/**
 * <h2>4. Double-Checked Locking (DCL) — the interview favorite</h2>
 *
 * <p>
 * Double-Checked Locking is a lazy initialization technique that creates the
 * Singleton instance only when it is first requested. It minimizes the
 * synchronization overhead by acquiring the lock only during the first
 * instance creation.
 * </p>
 *
 * <h3>How it Works</h3>
 * <ol>
 *     <li>
 *         <b>First Null Check (Without Lock)</b><br>
 *         Checks whether the instance already exists. If it does, the method
 *         returns immediately without acquiring any lock.
 *     </li>
 *
 *     <li>
 *         <b>Synchronized Block</b><br>
 *         If the instance is null, only one thread is allowed to enter the
 *         synchronized block and create the object.
 *     </li>
 *
 *     <li>
 *         <b>Second Null Check (With Lock)</b><br>
 *         Another thread might have already created the instance while the
 *         current thread was waiting for the lock. Therefore, the instance
 *         must be checked again before creating a new object.
 *     </li>
 * </ol>
 *
 * <h3>Why Two Null Checks?</h3>
 * <ul>
 *     <li>The first check avoids unnecessary synchronization after the instance has been created.</li>
 *     <li>The second check ensures that only one thread creates the Singleton instance.</li>
 *     <li>Without the second check, multiple threads could create multiple instances.</li>
 * </ul>
 *
 * <h3>Why <code>volatile</code>?</h3>
 * <ul>
 *     <li>Prevents instruction reordering during object creation.</li>
 *     <li>Guarantees visibility of the latest instance across all threads.</li>
 *     <li>Prevents another thread from accessing a partially initialized object.</li>
 * </ul>
 *
 * <h3>Object Creation Steps</h3>
 *
 * <pre>
 * Normal Order
 * 1. Allocate Memory
 * 2. Initialize Object
 * 3. Assign Reference
 *
 * Without volatile, JVM may reorder:
 * 1. Allocate Memory
 * 2. Assign Reference
 * 3. Initialize Object
 *
 * Another thread may observe a non-null reference
 * before the object is fully initialized.
 * </pre>
 *
 * <h3>Advantages</h3>
 * <ul>
 *     <li>Lazy initialization.</li>
 *     <li>Thread-safe.</li>
 *     <li>Synchronization occurs only during the first object creation.</li>
 *     <li>Better performance than synchronizing the entire method.</li>
 * </ul>
 *
 * <h3>Disadvantages</h3>
 * <ul>
 *     <li>More complex implementation compared to other Singleton approaches.</li>
 *     <li>Requires the <code>volatile</code> keyword for correctness.</li>
 *     <li>Easy to implement incorrectly if either null check or <code>volatile</code> is omitted.</li>
 * </ul>
 *
 * <h3>Interview Questions</h3>
 * <ul>
 *     <li>Why are there two null checks?</li>
 *     <li>Why is the instance declared as <code>volatile</code>?</li>
 *     <li>What happens if <code>volatile</code> is removed?</li>
 *     <li>Why is DCL faster than a synchronized method?</li>
 *     <li>Is Double-Checked Locking the best Singleton implementation?</li>
 * </ul>
 */
class DoubleCheckedLockingSingleton {
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {}

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) { // Second check (with locking)
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}

/**
 * <h2>5. Bill Pugh Singleton (Initialization-on-Demand Holder Idiom)</h2>
 *
 * <p>
 * Bill Pugh Singleton is the recommended way to implement a Singleton in Java.
 * It leverages the JVM's class loading mechanism to achieve lazy initialization
 * and thread safety without using synchronized blocks or explicit locking.
 * </p>
 *
 * <h3>How it Works</h3>
 * <ol>
 *     <li>The outer Singleton class is loaded, but the instance is <b>not</b> created.</li>
 *     <li>The static inner <code>Holder</code> class is not loaded immediately.</li>
 *     <li>When <code>getInstance()</code> is called for the first time, the JVM loads the
 *         <code>Holder</code> class.</li>
 *     <li>During class initialization, the JVM creates the
 *         <code>INSTANCE</code> exactly once.</li>
 *     <li>Subsequent calls simply return the already created instance.</li>
 * </ol>
 *
 * <h3>Why is it Thread-Safe?</h3>
 * <ul>
 *     <li>JVM guarantees that class initialization happens only once.</li>
 *     <li>Class loading is synchronized internally by the JVM.</li>
 *     <li>No explicit synchronization or locking is required.</li>
 * </ul>
 *
 * <h3>Why is it Lazy?</h3>
 * <ul>
 *     <li>The Singleton object is not created when the outer class is loaded.</li>
 *     <li>The object is created only when the <code>Holder</code> class is loaded.</li>
 *     <li>The <code>Holder</code> class is loaded only when
 *         <code>getInstance()</code> is invoked for the first time.</li>
 * </ul>
 *
 * <h3>Execution Flow</h3>
 *
 * <pre>
 * Application Starts
 *          │
 *          ▼
 * BillPughSingleton Class Loaded
 *          │
 *          ▼
 * Holder Class NOT Loaded
 *          │
 *          ▼
 * First getInstance() Call
 *          │
 *          ▼
 * Holder Class Loaded
 *          │
 *          ▼
 * INSTANCE Created
 *          │
 *          ▼
 * Same Instance Returned Forever
 * </pre>
 *
 * <h3>Advantages</h3>
 * <ul>
 *     <li>Lazy initialization.</li>
 *     <li>Thread-safe.</li>
 *     <li>No synchronization overhead.</li>
 *     <li>Better performance than synchronized or Double-Checked Locking implementations.</li>
 *     <li>Simple and easy to implement.</li>
 *     <li>Recommended Singleton implementation for most Java applications.</li>
 * </ul>
 *
 * <h3>Disadvantages</h3>
 * <ul>
 *     <li>Does not protect against reflection attacks.</li>
 *     <li>Requires additional handling for serialization (e.g., <code>readResolve()</code>).</li>
 *     <li>Less familiar to beginners because it relies on JVM class-loading behavior.</li>
 * </ul>
 *
 * <h3>Interview Questions</h3>
 * <ul>
 *     <li>Why is Bill Pugh Singleton thread-safe?</li>
 *     <li>Why is the Holder class declared as static?</li>
 *     <li>When is the Holder class loaded?</li>
 *     <li>How does Bill Pugh achieve lazy initialization without synchronized?</li>
 *     <li>How is it different from Double-Checked Locking?</li>
 *     <li>Can reflection or serialization break this Singleton?</li>
 * </ul>
 *
 * <h3>Interview Tip</h3>
 * <p>
 * Bill Pugh Singleton is generally preferred over Double-Checked Locking because
 * it is simpler, lazy, thread-safe, and avoids synchronization overhead by
 * relying on the JVM's class-loading guarantees.
 * </p>
 */
class BillPughSingleton {
    private BillPughSingleton() {}

    private static class Holder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    public static BillPughSingleton getInstance() {
        return Holder.INSTANCE;
    }
}

/**
 * <h2>6. Enum Singleton — Joshua Bloch's recommendation</h2>
 *
 * <p>
 * Enum Singleton is the simplest and most robust way to implement the
 * Singleton pattern in Java. It is recommended by Joshua Bloch in
 * <i>Effective Java</i> because it is inherently thread-safe and provides
 * built-in protection against reflection and serialization attacks.
 * </p>
 *
 * <h3>How it Works</h3>
 * <ol>
 *     <li>The JVM creates the enum instance when the enum class is loaded.</li>
 *     <li>Each enum constant is instantiated exactly once.</li>
 *     <li>The JVM guarantees that no additional instances can be created.</li>
 *     <li>Clients access the Singleton using <code>EnumSingleton.INSTANCE</code>.</li>
 * </ol>
 *
 * <h3>Why is it Thread-Safe?</h3>
 * <ul>
 *     <li>Enum instances are created during class initialization.</li>
 *     <li>JVM guarantees that class initialization is thread-safe.</li>
 *     <li>Only one instance of each enum constant exists per ClassLoader.</li>
 * </ul>
 *
 * <h3>Why is it Safe Against Reflection?</h3>
 * <ul>
 *     <li>Reflection cannot invoke an enum constructor.</li>
 *     <li>Attempting to create an enum instance via reflection throws an exception.</li>
 * </ul>
 *
 * <h3>Why is it Safe Against Serialization?</h3>
 * <ul>
 *     <li>Java provides special handling for enum serialization.</li>
 *     <li>During deserialization, the JVM returns the existing enum constant instead of creating a new object.</li>
 *     <li>No <code>readResolve()</code> method is required.</li>
 * </ul>
 *
 * <h3>Execution Flow</h3>
 *
 * <pre>
 * Application Starts
 *          │
 *          ▼
 * Enum Class Loaded
 *          │
 *          ▼
 * JVM Creates INSTANCE
 *          │
 *          ▼
 * EnumSingleton.INSTANCE
 *          │
 *          ▼
 * Same Instance Returned Forever
 * </pre>
 *
 * <h3>Advantages</h3>
 * <ul>
 *     <li>Simple and concise implementation.</li>
 *     <li>Thread-safe by default.</li>
 *     <li>Safe against reflection attacks.</li>
 *     <li>Safe against serialization attacks.</li>
 *     <li>No synchronization required.</li>
 *     <li>No <code>volatile</code>, <code>readResolve()</code>, or locking required.</li>
 * </ul>
 *
 * <h3>Disadvantages</h3>
 * <ul>
 *     <li>Eager initialization—the instance is created when the enum class is loaded.</li>
 *     <li>Cannot extend another class because enums already extend <code>java.lang.Enum</code>.</li>
 *     <li>Not suitable if lazy initialization is a strict requirement.</li>
 * </ul>
 *
 * <h3>Interview Questions</h3>
 * <ul>
 *     <li>Why is Enum Singleton considered the safest Singleton implementation?</li>
 *     <li>How does it prevent reflection attacks?</li>
 *     <li>Why doesn't it require <code>readResolve()</code>?</li>
 *     <li>Is Enum Singleton lazily initialized?</li>
 *     <li>Can an enum extend another class?</li>
 *     <li>When would you prefer Bill Pugh Singleton over Enum Singleton?</li>
 * </ul>
 *
 * <h3>Interview Tip</h3>
 * <p>
 * If the interviewer asks for the safest Singleton implementation,
 * <b>Enum Singleton</b> is usually the best answer because it is concise,
 * thread-safe, serialization-safe, and reflection-safe. However, if lazy
 * initialization is required, <b>Bill Pugh Singleton</b> is generally preferred.
 * </p>
 */
enum EnumSingleton {
    INSTANCE;

    public void doSomething() {
        System.out.println("Doing work...");
    }
}



