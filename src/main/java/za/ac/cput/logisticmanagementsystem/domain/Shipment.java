package za.ac.cput.logisticmanagementsystem.domain;

import java.time.LocalDate;

/**
 * Shipment.java
 * Author: Inam Ngqokomashe
 * 222660155
 */
public class Shipment {

    private String shipmentId;
    private double weight;
    private String origin;
    private String destination;
    private LocalDate dispatchDate;
    private LocalDate estimatedDeliveryDate;

    private ShippingContract shippingContract;
    private Inventory inventory;
    private ShipmentTracking shipmentTracking;
    private Invoice invoice;



    public Shipment(Builder builder) {
        this.shipmentId = builder.shipmentId;
        this.weight = builder.weight;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.dispatchDate = builder.dispatchDate;
        this.estimatedDeliveryDate = builder.estimatedDeliveryDate;

        this.shippingContract = builder.shippingContract;
        this.inventory = builder.inventory;
        this.shipmentTracking = builder.shipmentTracking;
        this.invoice = builder.invoice;

    }

    public String getShipmentId() {
        return shipmentId;
    }

    public double getWeight() {
        return weight;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public ShippingContract getShippingContract() {
        return shippingContract;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ShipmentTracking getShipmentTracking() {
        return shipmentTracking;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "shipmentId='" + shipmentId + '\'' +
                ", weight=" + weight + "Kg" +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", dispatchDate=" + dispatchDate +
                ", estimatedDeliveryDate=" + estimatedDeliveryDate +
                '}';
    }

    public static class Builder {
        private String shipmentId;
        private double weight;
        private String origin;
        private String destination;
        private LocalDate dispatchDate;
        private LocalDate estimatedDeliveryDate;
        private ShippingContract shippingContract;
        private Inventory inventory;
        private ShipmentTracking shipmentTracking;
        private Invoice invoice;


        public Builder shipmentId(String shipmentId) {
            this.shipmentId = shipmentId;
            return this;
        }
        public Builder weight(double weight) {
            this.weight = weight;
            return this;
        }
        public Builder origin(String origin) {
            this.origin = origin;
            return this;
        }
        public Builder destination(String destination) {
            this.destination = destination;
            return this;
        }
        public Builder dispatchDate(LocalDate dispatchDate) {
            this.dispatchDate = dispatchDate;
            return this;
        }
        public Builder estimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
            this.estimatedDeliveryDate = estimatedDeliveryDate;
            return this;
        }

        public Builder shippingContract(ShippingContract shippingContract) {
            this.shippingContract = shippingContract;
            return this;
        }

        public Builder inventory(Inventory inventory){
            this.inventory = inventory;
            return this;
        }
        public Builder shipmentTracking(ShipmentTracking shipmentTracking){
            this.shipmentTracking = shipmentTracking;
            return this;
        }
        public Builder invoice(Invoice invoice){
            this.invoice = invoice;
            return this;
        }


        public Shipment build() {
            return new Shipment(this);
        }
    }
}//
