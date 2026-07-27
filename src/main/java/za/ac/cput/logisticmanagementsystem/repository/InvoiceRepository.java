package za.ac.cput.logisticmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.logisticmanagementsystem.domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
}
