package printer;

import model.Order;
import model.OrderItem;

public class ReceiptPrinter {

    public void print(Order order) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("              ORDER RECEIPT");
        System.out.println("==============================================");

        System.out.println("Order ID : " + order.getId());
        System.out.println("Customer : " + order.getCustomer().getName());

        if (order.getDeliveryNote() != null &&
                !order.getDeliveryNote().isEmpty()) {
            System.out.println("Delivery Note : " + order.getDeliveryNote());
        }

        if (order.getDiscountCode() != null &&
                !order.getDiscountCode().isEmpty()) {
            System.out.println("Discount Code : " + order.getDiscountCode());
        }

        System.out.println();
        System.out.println("---------------- ITEMS ----------------");

        for (OrderItem item : order.getItems()) {

            System.out.printf(
                    "%s x%d%n",
                    item.getMenuItem().getName(),
                    item.getQuantity()
            );

            if (item.isLargeSize()) {
                System.out.println("   + Large Size");
            }

            if (item.hasExtraSauce()) {
                System.out.println("   + Extra Sauce");
            }

            System.out.printf(
                    "   Subtotal : %.2f%n",
                    item.getSubTotalPrice()
            );

            System.out.println();
        }

        System.out.println("--------------------------------------");
        System.out.printf("TOTAL : %.2f%n", order.getTotalPrice());
        System.out.println("==============================================");
    }

}