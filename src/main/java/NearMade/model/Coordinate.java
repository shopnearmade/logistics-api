package NearMade.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;


@Embeddable
public class Coordinate {
    @Min(value = -90, message = "The latitude cannot be lower than -90")
    @Max(value = 90, message = "The latitude cannot exceed 90")
    private double latitude;
    @Min(value = -180, message = "The longitude cannot be lower than -180")
    @Max(value = 180, message = "The longitude cannot exceed 180")
    private double longitude;

    // JPA and Jackson both require an empty default constructor!
    public Coordinate() {
    }

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}