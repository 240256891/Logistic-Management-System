package za.ac.cput.logisticmanagementsystem.factory;

import za.ac.cput.logisticmanagementsystem.domain.Invoice;

import java.util.Date;

public class InvoiceFactory {

    public static Invoice buildInvoice(String invoiceId, double total, String paymentStatus, Date dateIssued) {

        if (invoiceId == null || invoiceId.trim().isEmpty() ||
                paymentStatus == null || paymentStatus.trim().isEmpty() ||
                dateIssued == null || total < 0) {
            return null;
        }

        return new Invoice.Builder()
                .invoiceId(invoiceId)
                .total(total)
                .paymentStatus(paymentStatus)
                .dateIssued(dateIssued)
                .build();
    }
}
