# Creational Design Patterns

Creational patterns deal with object creation mechanisms, trying to create objects in a manner suitable to the
situation. The basic form of object creation could result in design problems or added complexity to the design.
Creational design patterns solve this problem by controlling this object creation.

---

## 1. Singleton Pattern

### Definition

Ensures a class has **only one instance** and provides a **global point of access** to it.

### Characteristics

- Private constructor — prevents external instantiation via `new`.
- A static field that holds the single instance.
- A static method (`getInstance()`) that returns the instance.
- Instance is created either eagerly (at class load) or lazily (on first request).
- No inheritance-friendly design by default — Singleton classes are typically `final` or have private constructors,
  which blocks subclassing.

### Important Points to Remember

- **Lazy initialization without synchronization is NOT thread-safe** — two threads can both pass the `null` check and
  create two instances.
- **Double-Checked Locking (DCL) requires the `volatile` keyword** on the instance field. Without it, instruction
  reordering can expose a partially-constructed object to another thread.
- **Bill Pugh Singleton (static inner Holder class)** is the most recommended lazy + thread-safe approach — no explicit
  synchronization needed, because JVM class-loading is inherently thread-safe.
- **Enum-based Singleton** (recommended by Joshua Bloch in *Effective Java*) is the safest implementation — it is
  inherently thread-safe, serialization-safe, and reflection-attack-proof.
- Singleton can be broken via:
    - **Reflection** (`constructor.setAccessible(true)`)
    - **Serialization/Deserialization** (creates a new object unless `readResolve()` is implemented)
    - **Cloning** (if `Cloneable` is implemented and `clone()` is not overridden to prevent it)
    - **Multiple classloaders** (each classloader can load the class independently)
- Spring's default `"singleton"` bean scope is **one instance per Spring container (ApplicationContext)**, not one
  instance per JVM like the classic GoF Singleton.

### Advantages

- Guarantees a single, consistent instance across the application (useful for config, logging, caching, connection
  pools).
- Saves memory/resources by avoiding repeated object creation.
- Provides a controlled, global access point.

### Disadvantages

- Introduces global mutable state, which can make code harder to reason about.
- Makes unit testing difficult — hard to mock/replace a hardcoded singleton dependency.
- Creates hidden dependencies between classes (a class silently depends on a singleton instead of receiving it via
  constructor/parameter).
- Often considered an anti-pattern in modern codebases that favor Dependency Injection.

### Interview Questions

1. Why is `volatile` necessary in double-checked locking?
2. What's wrong with lazy initialization in a multi-threaded environment?
3. Why does Joshua Bloch recommend enum-based Singleton?
4. How can Singleton be broken, and how do you prevent each method (reflection, serialization, cloning)?
5. Difference between eager and lazy initialization — when would you choose each?
6. Is Singleton pattern an anti-pattern? Why do some developers argue against it?
7. How does Spring implement Singleton scope differently from the classic GoF Singleton?
8. Can you implement a lazy Singleton without using the `synchronized` keyword at all? (Bill Pugh Holder pattern)

---

## 2. Factory Method Pattern

### Definition

Defines an interface for creating an object, but lets **subclasses decide which class to instantiate**. Factory Method
lets a class defer instantiation to subclasses.

*(Note: "Simple Factory" — a single class with a branching method — is a common idiom but not an official GoF pattern.)*

### Characteristics

- A `Creator` (often abstract) class declares an abstract `factory method` that returns a `Product`.
- Concrete `Creator` subclasses override the factory method to return specific `Product` subtypes.
- Client code depends only on the abstract `Creator`/`Product`, never on concrete classes.
- Often paired with a "template method" that uses the factory method internally.

### Important Points to Remember

- Factory Method is a **specialized case of the Template Method pattern** — the creator's non-abstract method (
  e.g. `notify()`) is the template, and the factory method (e.g. `createNotification()`) is the hook subclasses
  override.
- Adding a new product type means adding a **new Creator subclass** — no existing code is modified (true Open/Closed
  compliance), unlike Simple Factory where you'd edit an `if/switch`.
- A **static factory method** (e.g. `Point.of(x, y)`, from Effective Java Item 1) is **not** the GoF Factory Method
  pattern — it just wraps a constructor for naming/immutability/caching benefits; there's no polymorphic subclass
  decision involved.
- In modern Java, `Supplier<T>` + a `Map<Key, Supplier<T>>` registry can replace a full Factory Method class hierarchy
  with far less boilerplate.
- JDK
  examples: `Calendar.getInstance()`, `NumberFormat.getInstance()`, `Executors.newFixedThreadPool()`, `Collections.unmodifiableList()`.

### Advantages

- Decouples client code from concrete product classes.
- New product types can be added without touching existing client or creator code.
- Centralizes and can encapsulate complex construction logic.
- Makes unit testing easier (swap in a test-specific Creator returning mocks).

### Disadvantages

- Requires creating a new subclass for every new product — can lead to a large number of small classes (parallel class
  hierarchies).
- Adds indirection; for a handful of simple, stable types, plain constructors are often clearer.
- Can be overkill when there's no genuine need for extensibility.

### Interview Questions

1. Difference between Simple Factory and Factory Method?
2. Why does Simple Factory violate the Open/Closed Principle, and how does Factory Method fix it?
3. Is `Point.of(x, y)` an example of the Factory Method pattern? Why or why not?
4. How is Factory Method related to the Template Method pattern?
5. How would you test code that depends on a Factory Method-based Creator?
6. What's the real difference between the Factory pattern and Dependency Injection?
7. Give a JDK example of Factory Method.

---

## 3. Abstract Factory Pattern

### Definition

Provides an interface for creating **families of related or dependent objects** without specifying their concrete
classes.

### Characteristics

- An `AbstractFactory` interface declares multiple creation methods (one per product type in the family),
  e.g. `createButton()`, `createCheckbox()`.
- Concrete factories (e.g. `WindowsFactory`, `MacFactory`) implement all these methods, each returning products from the
  **same consistent family**.
- Client code depends only on the `AbstractFactory` interface and abstract product interfaces — never on concrete
  classes.
- Internally, each creation method in a concrete factory is itself a Factory Method.

### Important Points to Remember

- **Factory Method vs Abstract Factory** is a very common interview distinction:
    - Factory Method → single product, via **inheritance** (subclass overrides one method).
    - Abstract Factory → family of related products, via **composition** (factory object exposes multiple creation
      methods).
- Abstract Factory guarantees **consistency** — it prevents mismatched combinations (e.g. a `WindowsButton` paired with
  a `MacCheckbox`).
- Adding a **new family** (e.g. `LinuxFactory`) is easy — just implement the interface.
- Adding a **new product type** to the family (e.g. `Slider`) is hard — every existing concrete factory must be updated.
  This is a known limitation and a favorite interview trap.
- JDK example: `DocumentBuilderFactory.newInstance()` in `javax.xml.parsers` — resolves the concrete JAXP implementation
  on the classpath at runtime.
- Spring's `BeanFactory` / `ApplicationContext` is a large-scale, config-driven implementation of this idea.

### Advantages

- Guarantees that products from a factory are compatible with each other (family consistency).
- Isolates client code completely from concrete classes.
- Easy to introduce a new complete product family.
- Supports the Open/Closed Principle at the family level.

### Disadvantages

- Adding a new product type to the family requires modifying every concrete factory — violates Open/Closed at the
  product level.
- Increases the number of interfaces/classes considerably.
- Can feel like over-engineering when there's only ever going to be one product family.

### Interview Questions

1. Difference between Factory Method and Abstract Factory?
2. Why does Abstract Factory guarantee family consistency, and why does that matter?
3. What is the main limitation of Abstract Factory when adding a new product type?
4. When would you choose Abstract Factory over Factory Method?
5. Give a JDK example of Abstract Factory.
6. How does an Abstract Factory internally use Factory Methods?

---

## 4. Builder Pattern

### Definition

Separates the **construction of a complex object from its representation**, so that the same construction process can
create different representations. Used when an object needs many optional parameters or a multi-step construction
process.

### Characteristics

- A `Builder` interface/class exposes step-by-step methods to set parts of the object (`setPartA()`, `addPartB()`, ...).
- A `build()` method finalizes and returns the fully-constructed, immutable `Product`.
- An optional `Director` encapsulates a known construction "recipe," reusable across builders (classic GoF form).
- Modern Java typically skips the Director and uses a static nested `Builder` class with fluent, chained method calls.

### Important Points to Remember

- Solves the **telescoping constructor** problem (too many overloaded constructors) and the **JavaBean setter**
  problem (object exists in a partially-constructed, inconsistent state).
- The Product's constructor should be **private**, accepting only the `Builder` — this enforces that objects are always
  fully formed before use.
- Fields should be `final` — Builder is a key technique for building **immutable objects**.
- **Validation belongs inside `build()`**, right before the object is created — not in individual setter-like methods,
  since some validations depend on combinations of fields.
- **Builder vs Factory**: Factory decides *which class* to instantiate; Builder decides *how to assemble* one complex
  object step-by-step.
- JDK examples: `StringBuilder`, `ProcessBuilder`, `Stream.Builder`.
- **Lombok's `@Builder`** auto-generates this pattern in Spring Boot projects — but:
    - On JPA `@Entity` classes, also add `@NoArgsConstructor` (Hibernate needs it for proxies)
      and `@AllArgsConstructor` (which `@Builder` relies on).
    - For inheritance hierarchies, use `@SuperBuilder` instead of `@Builder`.
    - For direct JSON deserialization into a `@Builder`-only class, add `@Jacksonized`.

### Advantages

- Produces immutable, always-consistent objects (no partial state).
- Highly readable client code — named methods instead of positional constructor arguments.
- Centralizes validation logic in one place (`build()`).
- The same builder can produce different representations (classic GoF benefit, with a Director).

### Disadvantages

- More boilerplate — a builder method per field (mitigated by Lombok `@Builder` in Java).
- Overkill for simple objects with very few fields.
- The Builder object itself is mutable, so sharing a single builder instance across threads is unsafe.

### Interview Questions

1. Why prefer Builder over telescoping constructors or JavaBean setters?
2. Is the Director mandatory in the Builder pattern?
3. How does Builder help achieve immutability?
4. What's the difference between Builder and Abstract Factory?
5. Where have you seen Builder pattern used in the JDK?
6. Where should validation logic go in a Builder, and why?
7. Is Lombok's `@Builder` part of Spring Boot? What extra annotations are needed when using it on a JPA entity?

---

## 5. Prototype Pattern

### Definition

Specifies the kinds of objects to create using a **prototypical instance**, and creates new objects by **copying** (
cloning) this prototype — instead of instantiating via a constructor.

### Characteristics

- A `Prototype` interface/abstract class declares a `clone()` method.
- Concrete prototypes implement `clone()` to return a copy of themselves.
- A `Client` requests a copy from an existing prototype instance rather than calling `new`.
- Optionally implemented via a **Prototype Registry** — a map of pre-configured template objects, keyed by name/type,
  that returns fresh clones on demand.

### Important Points to Remember

- **Shallow copy vs deep copy** is the single most important distinction here:
    - Shallow copy: primitive fields are copied; reference-type fields are only **reference-copied** — original and
      clone share the same nested object.
    - Deep copy: nested reference-type fields are also explicitly cloned/recreated — original and clone become fully
      independent.
- **`Object.clone()` / `super.clone()` performs a shallow copy by default.** Any mutable reference field must be
  manually deep-copied inside `clone()`, or the shared-state bug appears silently.
- **Joshua Bloch (Effective Java, Item 13) recommends avoiding Java's `Cloneable`** because:
    - It's an empty marker interface with a confusing contract (`Object.clone()` is `protected`, and throws a
      checked `CloneNotSupportedException` that's often never actually thrown in practice).
    - Deep-copy responsibility is entirely on the developer — the compiler doesn't enforce it, so a missed mutable field
      silently causes shared-state bugs.
    - It's fragile with inheritance — every subclass that adds a new mutable field must remember to update `clone()`.
    - It's awkward with immutable/`final` fields.
- **Recommended alternatives**: a **copy constructor** or a **static copy factory method** — explicit, type-safe, no
  checked exceptions, and each field's copy strategy is visible in the code.
- **Rule of thumb**: mutable fields need a deep copy; immutable fields (`String`, wrapper types, immutable custom
  classes) can safely share a reference.
- Spring's `"prototype"` bean scope is **not** the same as the GoF Prototype pattern — it means a new instance is
  constructed (via the constructor) on every `getBean()` call, not that an existing instance is cloned.

### Advantages

- Avoids the cost of expensive object creation (heavy computation, DB/network round-trips) by copying an existing
  instance instead.
- New object types can be registered/added at runtime without touching client code (Prototype Registry).
- Can copy complex object graphs without knowing their exact concrete class.

### Disadvantages

- Implementing correct deep copy is tricky, especially for complex or circular object graphs.
- Java's `Cloneable` mechanism is widely considered poorly designed; safer alternatives require extra care to implement
  consistently.
- Easy to introduce subtle shared-state bugs if a mutable field's copy step is forgotten.

### Interview Questions

1. Difference between shallow copy and deep copy?
2. Why does Joshua Bloch recommend against implementing `Cloneable`?
3. How would you implement deep copy for an object containing a `List<Address>` field?
4. What is a Prototype Registry, and when would you use it?
5. Is Spring's `"prototype"` bean scope the same as the GoF Prototype pattern?
6. When would you prefer Prototype over Factory Method?
7. When is it safe to just share a reference instead of deep-copying a field?

---

## Quick Comparison Table

| Pattern          | Solves                                              | Key Mechanism                            |
|------------------|-----------------------------------------------------|------------------------------------------|
| Singleton        | Ensuring only one instance exists                   | Private constructor + static accessor    |
| Factory Method   | Deciding *which* class to instantiate               | Subclass overrides a creation method     |
| Abstract Factory | Creating a *family* of related objects consistently | Interface with multiple creation methods |
| Builder          | Constructing a complex object step-by-step          | Fluent methods + a final `build()`       |
| Prototype        | Avoiding expensive re-creation of objects           | Cloning an existing instance             |