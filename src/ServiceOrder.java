public class ServiceOrder implements Comparable<ServiceOrder>{
    private int id;
    private Vehicle vehicle;
    private String description;
    private boolean completed;

    public ServiceOrder(int id, Vehicle vehicle, String description, boolean completed) {
        this.id = id;
        this.vehicle = vehicle;
        this.description = description;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public int compareTo(ServiceOrder o) {
        if (this.id < o.id){
            return -1;
        } else if (this.id == o.id) {
            return 0;
        }
        return 1;
    }
}
