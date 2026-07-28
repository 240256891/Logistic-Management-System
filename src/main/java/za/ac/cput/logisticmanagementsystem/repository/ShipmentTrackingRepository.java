package za.ac.cput.logisticmanagementsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentTrackingRepository extends JpaRepository<ShipmentTracking, String> {

}
