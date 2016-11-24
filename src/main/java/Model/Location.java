package Model;

/**
 * Created by white on 2016-11-24.
 */
public class Location {
    private String country,state,city;

    public Location(String country, String state, String city) {
        this.country = country;
        this.state = state;
        this.city = city;
    }
    public Location(){

    }

    public void parsLoacation(String data){

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
