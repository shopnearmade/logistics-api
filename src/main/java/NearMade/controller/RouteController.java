package NearMade.controller;

import java.util.List;

import NearMade.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import NearMade.model.Coordinate;
import NearMade.model.DeliveryOrder;
import NearMade.service.RouteService;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;
    private final OrderRepository order;
    // Dependency Injection
    @Autowired
    public RouteController(RouteService routeService, OrderRepository order) {

        this.routeService = routeService;
        this.order = order;

     }

    // @GetMapping
    @GetMapping("/ping")
    public String healthCheck() {

        return "NearMade Routing Engine is LIVE and ready for requests!";
    }



    // @PostMapping listens for incoming data payloads at "/api/routes/generate"
    @PostMapping("/generate")
    public List<DeliveryOrder> generateOptimizedRoute(@RequestBody RouteRequest request) {

         return routeService.optimizeRoute(request.getStartLocation(), request.getOrders());
    }
    @PostMapping("/generate-from-db")
    public List<DeliveryOrder> generateOptimizedRouteForNotDelivered(@RequestBody RouteRequest request)
    {
        return routeService.optimizeRoute(request.getStartLocation(),order.findByIsDeliveredFalse());
    }



    // --- DTO (Data Transfer Object) ---
     public static class RouteRequest {
        private Coordinate startLocation;
        private List<DeliveryOrder> orders;

        public Coordinate getStartLocation() { return startLocation; }
        public void setStartLocation(Coordinate startLocation) { this.startLocation = startLocation; }

        public List<DeliveryOrder> getOrders() { return orders; }
        public void setOrders(List<DeliveryOrder> orders) { this.orders = orders; }
    }

}