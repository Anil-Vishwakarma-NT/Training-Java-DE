package DayOne.Oops;

public class Bike extends Vehicle{
    public Bike(String brand, Long price, int fuelCapacity){
        super(brand, price, fuelCapacity);
    }

    @Override
    public void start() {
        System.out.println(getBrand() + " bike is kick-starting.");
    }

    @Override
    public void stop() {
        System.out.println(getBrand() + " bike is stopping with disc brakes.");
    }

    @Override
    public void  addFuel(){
        System.out.println("Refueling the bike with petrol.............. ");
    }
}
