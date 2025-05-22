package DayOne.Oops;

public class Car extends Vehicle{

    String model;

    public Car(String brand, Long price, int fuelCapacity){
        super(brand, price, fuelCapacity);
    }

    @Override
    public void start() {
        System.out.println(getBrand() + " car is starting with key ignition.");
    }

    @Override
    public void stop() {
        System.out.println(getBrand() + " car is stopping using hydraulic brakes.");
    }

    @Override
    public void  addFuel(){
        System.out.println("Refueling the car with CNG.............. ");
    }
}
