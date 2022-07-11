package C04_InterfacesAndAbstraction.Exercise03_BirthdayCelebrations;

public class Robot implements Identifiable {
    private String model;
    String id;

    public Robot(String model, String id) {
        this.model = model;
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String getId() {
        return id;
    }
}
