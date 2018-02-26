package com.heneli.copia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {

    protected String firstName;
    protected String lastName;
    protected String street;
    protected String city;
    protected String state;
    protected int postal;
    protected String country;
    protected String email;
    protected String phone;
    protected double latitude;
    protected double longitude;

    public double distance(User user) {
        double lat1 = this.getLatitude(), lng1 = this.getLongitude();
        double lat2 = user.getLatitude(), lng2 = user.getLongitude();

        return distance(lat1, lat2, lng1, lng2);
    }

    private double distance(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        distance /= 1000; // m to Km
        distance *= 0.62137; // Km to miles

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}
