package NearMade.service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import NearMade.model.Coordinate;
import NearMade.model.DeliveryOrder;



public class RouteServiceTest {



    @Test
    public void calculateDistanceTest() {
        // Arrange (Setup your data)
        Coordinate location1 = new Coordinate(0, 0);
        Coordinate location2 = new Coordinate(1, 1);
        RouteService routeService = new RouteService();

        // Act (Call the method you want to test)
        double actualDistance = routeService.calculateDistance(location1, location2);

        // Assert (Verify the results)
        // When comparing doubles, we use a "delta" (0.5) to allow for slight math precision differences
        assertEquals(97.7, actualDistance, 0.5, "Distance should be approximately 97.7 miles");
    }

    @Test
    public void optimizeRouteTest() {
        // Arrange
        Coordinate startLocation = new Coordinate(0, 0);
        DeliveryOrder closeOrder = new DeliveryOrder("12345", new Coordinate(1, 1));
        DeliveryOrder farOrder = new DeliveryOrder("54321", new Coordinate(2, 2));

        List<DeliveryOrder> orders = new ArrayList<>();
        orders.add(farOrder); // Add the far one first so it is unsorted!
        orders.add(closeOrder);

        RouteService routeService = new RouteService();

        // Act
        List<DeliveryOrder> optimizedRoute = routeService.optimizeRoute(startLocation, orders);

        // Assert
        assertEquals(2, optimizedRoute.size(), "Route should contain exactly 2 orders");
        assertEquals("12345", optimizedRoute.get(0).getOrderId(), "The closest order should be first");
        assertEquals("54321", optimizedRoute.get(1).getOrderId(), "The farthest order should be last");
        assertEquals(0, orders.size());
    }

}
