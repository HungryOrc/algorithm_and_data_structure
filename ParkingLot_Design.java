public enum VehicleSize {
    Compact(1);
    Large(2);
    
    private final int size;
    
    VehicleSize(int size) {
        this.size = size;
    }
    
    public int getSize() {
        return size;
    }
}


public abstract class Vehicle {
    public abstract VehicleSize getSize();
}


public class Car extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Compact;
    }
}


public class Truck extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Large;
    }
}


public class ParkingSpot {
    private final VehicleSize size;
    private Vehicle currentVahicle;
    
    ParkingSpot(VehicleSize size) {
        this.size = size;
    }

    boolean fit(Vehicle v) {
        return currentVehicle == null && size.getSize() >= v.getSize().getSize();
    }

    void park(Vehicle v) {
        currentVehicle = v;
    }
    
    void leave() {
        currentVehicle = null;
    }

    Vehicle getVehicle() {
        return currentVehicle;
    }
}


public class Level {
    private final List<ParkingSpot> spots;

    Level(int numOfSpots) {
        List<ParkingSpots> list = new ArrayList<>(numOfSpots);
        
        int i = 0;
        for (; i < numOfSpots / 2; i++) {
            list.add(new ParkingSpot(VehicleSize.Compact));
        }
        for (; i < numOfSpots; i++) {
            list.add(new ParkingSpot(VehicleSize.Large));
        }
        
        spots = Collections.unmodifiableList(list); 
    }

    boolean hasSpot(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.fit(v)) {
                return true;
            }
        }
        return false;
    }
    
    boolean park(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.fit(v)) {
                s.park(v);
                return true;
            }
        }
        return false;
    }
    
    boolean leave(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.getVehicle() == v) {
                s.leave();
                return true;
            }
        }
        return false;
    }
}



