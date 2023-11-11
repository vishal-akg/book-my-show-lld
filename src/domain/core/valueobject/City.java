package domain.core.valueobject;

public class City implements Comparable<City>{
    private String name;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(City o) {
        return name.compareTo(o.getName());
    }
}
