package DayOne.Oops;

public class Main {

    public static void main(String[] args) {
        Vehicle car = new Car("Mahindra", 1500000L, 45);
        Vehicle bike = new Bike("Royal Enfield", 350000L,15);

        System.out.println("Car is ready to use.........");
        car.start();
        car.addFuel();
        car.stop();

        System.out.println("Bike is ready to use.............");
        bike.start();
        bike.addFuel();
        bike.stop();
    }
}
