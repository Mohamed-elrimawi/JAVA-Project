public class Location {


    String street ;
    String district ;
    String city ;


    public Location() {
    }

    public Location(String street, String district, String city) {
        this.street = street;
        this.district = district;
        this.city = city;
    }


    public void setStreet(String street) {
        this.street = street;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
