package NearMade.controller;

import NearMade.dao.OrderRepository;
import NearMade.model.Coordinate;
import NearMade.model.DeliveryOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import NearMade.exception.OrderNotFoundException;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderController orderController;


    @Test
    public void getAllOrders()
    {
        // Arrange
        DeliveryOrder closeOrder = new DeliveryOrder("12345", new Coordinate(1, 1));
        DeliveryOrder farOrder = new DeliveryOrder("54321", new Coordinate(2, 2));

        List<DeliveryOrder> orders = new ArrayList<>();
        orders.add(farOrder); 
        orders.add(closeOrder);

        Mockito.when(orderRepository.findAll()).thenReturn(orders);
        List<DeliveryOrder> actualResponse = orderController.retrieveAllOrders();


        // Act
       assertEquals(2,actualResponse.size());


    }
    @Test
    public void postAnOrder()
    {
        DeliveryOrder anOrder = new DeliveryOrder("12345", new Coordinate(1, 1));


        Mockito.when(orderRepository.save(anOrder)).thenReturn(anOrder);

        DeliveryOrder actualResponse = orderController.addOrders(anOrder);

        assertEquals(anOrder.getOrderId(),actualResponse.getOrderId());

    }

    @Test
    public void markAnOrderAsCompleted(){
        DeliveryOrder anOrder = new DeliveryOrder("12345", new Coordinate(1, 1));
        Mockito.when(orderRepository.findById(anOrder.getOrderId())).thenReturn(Optional.of(anOrder));

         orderController.markAnOrderAsCompleted(anOrder.getOrderId());

        assertTrue(anOrder.getIsDelivered());
    }

    @Test
    public void markAnOrderAsCompleted_NotFound() {
        // Arrange
        String fakeId = "999";

        Mockito.when(orderRepository.findById(fakeId)).thenReturn(Optional.empty());

        // Act & Assert
        // We expect the controller to throw an OrderNotFoundException when it opens the empty box!
        assertThrows(OrderNotFoundException.class, () -> {
            orderController.markAnOrderAsCompleted(fakeId);
        });
    }

}
