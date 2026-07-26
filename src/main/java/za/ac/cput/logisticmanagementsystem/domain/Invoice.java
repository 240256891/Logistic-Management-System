package za.ac.cput.logisticmanagementsystem.domain;

import java.util.Date;

public class Invoice{
    public String invoiceId;
    public double total;
    public String paymentStatus;
    public Date dateIssued;

    private Invoice(Builder builder) {
        this.invoiceId = builder.invoiceId;
        this.total = builder.total;
        this.paymentStatus = builder.paymentStatus;
        this.dateIssued = builder.dateIssued;
    }


    public String getInvoiceId() {
        return invoiceId;
    }

    public double getTotal() {
        return total;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public static class Builder {
        private String invoiceId;
        private double total;
        private String paymentStatus;
        private Date dateIssued;

        public Builder invoiceId(String invoiceId) {
            this.invoiceId = invoiceId;
            return this;
        }

        public Builder total(double total) {
            this.total = total;
            return this;
        }

        public Builder paymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Builder dateIssued(Date dateIssued) {
            this.dateIssued = dateIssued;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
        }
    }
}
