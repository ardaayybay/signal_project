// 1. Inheritance (Generalization): A solid line with a hollow arrowhead
// Example: Vehicle <-- Car (Car inherits from Vehicle)
abstract class Vehicle {
    protected String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public abstract void drive();
}

class Car extends Vehicle {
    public Car(String brand) {
        super(brand);
    }

    @Override
    public void drive() {
        System.out.println(brand + " Car is driving on the road.");
    }
}

// 2. Implementation (Realization): A dashed line with a hollow arrowhead
// Example: Drivable <-- Car (Car implements the Drivable interface)
interface Drivable {
    void startEngine();
    void stopEngine();
}

class Car implements Drivable {
    @Override
    public void startEngine() {
        System.out.println("Car engine started.");
    }

    @Override
    public void stopEngine() {
        System.out.println("Car engine stopped.");
    }
}

// 3. Association: A solid line
// Example: Driver -- Car (Driver can drive a Car, a simple relationship)
class Driver {
    private String name;
    private Car car; // Association: Driver has a reference to Car

    public Driver(String name, Car car) {
        this.name = name;
        this.car = car;
    }

    public void driveCar() {
        System.out.println(name + " is driving the car.");
        car.drive();
    }
}

// 4. Aggregation: A solid line with a hollow diamond
// Example: Car ◇-- Wheel (Car has Wheels, but Wheels can exist independently)
class Wheel {
    private String type;

    public Wheel(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class Car {
    private List<Wheel> wheels; // Aggregation: Car has Wheels

    public Car(String brand) {
        super(brand);
        this.wheels = new ArrayList<>();
    }

    public void addWheel(Wheel wheel) {
        wheels.add(wheel);
    }

    public void showWheels() {
        System.out.println("Car has " + wheels.size() + " wheels:");
        for (Wheel wheel : wheels) {
            System.out.println("- " + wheel.getType() + " wheel");
        }
    }
}

// 5. Composition: A solid line with a filled diamond
// Example: Car ◆-- Engine (Car owns an Engine, Engine cannot exist without Car)
class Engine {
    private String type;

    public Engine(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class Car {
    private Engine engine; // Composition: Car owns an Engine

    public Car(String brand, String engineType) {
        super(brand);
        this.engine = new Engine(engineType); // Engine is created within Car
    }

    public void showEngine() {
        System.out.println("Car has a " + engine.getType() + " engine.");
    }
}

// 6. Dependency: A dashed line with an arrow
// Example: Car ..> Fuel (Car depends on Fuel to operate, but doesn't own it)
class Fuel {
    private String type;

    public Fuel(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class Car {
    public void refuel(Fuel fuel) { // Dependency: Car uses Fuel temporarily
        System.out.println("Car is refueled with " + fuel.getType() + ".");
    }
}

// Main class to demonstrate the relationships
public class UMLDemo {
    public static void main(String[] args) {
        // Inheritance and Implementation
        Car car = new Car("Toyota");
        car.drive(); // From Vehicle (Inheritance)
        car.startEngine(); // From Drivable (Implementation)
        car.stopEngine();

        // Association
        Driver driver = new Driver("Alice", car);
        driver.driveCar();

        // Aggregation
        Wheel wheel1 = new Wheel("Front Left");
        Wheel wheel2 = new Wheel("Front Right");
        car.addWheel(wheel1);
        car.addWheel(wheel2);
        car.showWheels();

        // Composition
        Car carWithEngine = new Car("Honda", "V6");
        carWithEngine.showEngine();

        // Dependency
        Fuel fuel = new Fuel("Gasoline");
        car.refuel(fuel);
    }
}