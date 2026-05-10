package NearMade.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.*;

import NearMade.dao.OrderRepository;
import NearMade.model.DeliveryOrder;
import NearMade.exception.OrderNotFoundException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository order;

    @Autowired
    public OrderController(OrderRepository order) {
        this.order = order;
    }

    // 1. Use PostMapping for saving data!
    // 2. Return the saved object so we can see it in Postman!
    @PostMapping("/add")
    public DeliveryOrder addOrders(@Valid @RequestBody DeliveryOrder deliveryOrder) {
        return order.save(deliveryOrder);
    }

    @GetMapping("/all")
    public List<DeliveryOrder> retrieveAllOrders()
    {
        return order.findAll();
    }
    @GetMapping("/{id}")
    public void retrieveAnOrder(@PathVariable("id") String Id ) {
        order.findById(Id).orElseThrow(()-> new OrderNotFoundException("Order with ID "+Id +" not found!"));
    }


    @PutMapping("/{id}/complete")
    public void markAnOrderAsCompleted(@PathVariable("id") String Id)
    {
          DeliveryOrder orderById = order.findById(Id)
                          .orElseThrow(() -> new OrderNotFoundException("Order with ID " + Id + " not found!" ));
          orderById.setIsDelivered(true);
          order.save(orderById);



    }



}