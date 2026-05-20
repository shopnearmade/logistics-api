package NearMade.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import NearMade.model.Coordinate;
import NearMade.model.DeliveryOrder;


@Service
public class RouteService {

    private static final int EARTH_RADIUS_MILES = 3959;

    /**
     */
    public List<DeliveryOrder> optimizeRoute(Coordinate startLocation, List<DeliveryOrder> unsortedOrders) {

        List<DeliveryOrder> sortedRoute = new ArrayList<>();
        
        // We need to keep track of where the driver currently is
        // they start at the startLocation.
        Coordinate currentLocation = startLocation;

        while(!unsortedOrders.isEmpty())
        {
            int index = -1;
            double shortestDistance = Double.MAX_VALUE;
            
            for (int i = 0; i < unsortedOrders.size(); i++)
            {
                double distanceBetween = calculateDistance(currentLocation, unsortedOrders.get(i).getDropoffLocation());
                if (distanceBetween < shortestDistance)
                {
                    shortestDistance = distanceBetween;
                    index = i;
                }

            }
            currentLocation = unsortedOrders.get(index).getDropoffLocation();
            sortedRoute.add(unsortedOrders.get(index));
            unsortedOrders.remove(index);

        }

      

        return sortedRoute;
    }

    /**
     *  distance between two coordinates on Earth.
     */
    public double calculateDistance(Coordinate c1, Coordinate c2) {

        double lat1 = Math.toRadians(c1.getLatitude());
        double lat2 = Math.toRadians(c2.getLatitude());
        double lon1 = Math.toRadians(c1.getLongitude());
        double lon2 = Math.toRadians(c2.getLongitude());

        double latDiff = lat2 - lat1;
        double lonDiff = lon2 - lon1;

        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2)
                 + Math.cos(lat1) * Math.cos(lat2)
                 * Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);

         
        double distance = 2 * EARTH_RADIUS_MILES 
                 * Math.asin(Math.sqrt(a));
        
        return distance;
    }


}