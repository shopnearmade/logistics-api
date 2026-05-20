package NearMade.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Entity
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;

    @Embedded
    @Valid
    @NotNull
    private Coordinate dropoffLocation;

    @Column
    private boolean isDelivered;

    // JPA and Jackson both require an empty default constructor
    public DeliveryOrder() {
    }

    //constructor for NEW orders
    public DeliveryOrder(Coordinate dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    // constructor for existing orders
    public DeliveryOrder(String orderId, Coordinate dropoffLocation) {
        this.orderId = orderId;
        this.dropoffLocation = dropoffLocation;
        this.isDelivered = false;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Coordinate getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(Coordinate dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public boolean getIsDelivered()
    {return isDelivered;}

    public void setIsDelivered(boolean isDelivered)
    {this.isDelivered =isDelivered;}

}