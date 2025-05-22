package DayOne.Oops;

public abstract class Vehicle implements VehicleBehaviour{

    private String brand;
    private Long price;
    private int fuelCapacity;

    public Vehicle(String brand,Long price, int fuelCapacity){
        this.brand = brand;
        this.price = price;
        this.fuelCapacity = fuelCapacity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        if(fuelCapacity >=0 && fuelCapacity <=100){
            this.fuelCapacity = fuelCapacity;
        }
    }

    public abstract void start();

    public abstract void stop();

    public abstract void addFuel();

}
