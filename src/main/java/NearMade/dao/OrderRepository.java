package NearMade.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import NearMade.model.DeliveryOrder;
public interface OrderRepository extends JpaRepository<DeliveryOrder, String>
{
 List<DeliveryOrder> findByIsDeliveredFalse();
}