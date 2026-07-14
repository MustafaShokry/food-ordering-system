package src;

import java.util.Scanner;
import src.model.Customer;
import src.model.Order;
import src.model.OrderItem;
import src.model.MenuItem;
import src.model.Menu;
import src.service.OrderService;
import src.service.ServiceFactory;
import src.payment.PaymentMethod;
import src.payment.PaymentType;
import src.notification.NotificationChannel;
import src.notification.NotificationType;


public class App {

    private final Scanner scanner = new Scanner(System.in);

    private final ServiceFactory factory = ServiceFactory.getInstance();

    private final Menu menu = factory.getMenu();

    private final OrderService orderService =
            factory.getOrderService();

    public static void main(String[] args) {
        new App().run();
    }

    public void run() {
        System.out.println("===== Food Ordering System =====");

        Customer customer = createCustomer();

        Order.Builder orderBuilder = orderService.createOrder(customer);

        addItems(orderBuilder);

        orderBuilder.deliveryNote(readDeliveryNote());

        orderBuilder.discountCode(readDiscountCode());

        Order order = orderBuilder.build();

        PaymentMethod payment = choosePayment();

        NotificationChannel notification = chooseNotification();

        orderService.checkout(
                order,
                payment,
                notification
        );

    }

    private Customer createCustomer() {

        System.out.print("Enter your Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your Phone: ");
        String phone = scanner.nextLine();

        return new Customer(name,email,phone);
    }

    private void addItems(Order.Builder orderBuilder) {

        while(true){

            displayMenu();

            System.out.print("Enter the Item ID you want to add or -1 to exit: ");

            int id = scanner.nextInt();

            if(id == -1)
                break;

            MenuItem menuItem = menu.getItemById(id);

            if(menuItem == null){

                System.out.println("Invalid item.");

                continue;

            }

            System.out.print("Quantity: ");

            int quantity = scanner.nextInt();

            scanner.nextLine();

            System.out.print("Large size? (y/n): ");

            boolean large =scanner.nextLine().equalsIgnoreCase("y");

            System.out.print("Extra sauce? (y/n): ");

            boolean sauce =scanner.nextLine().equalsIgnoreCase("y");

            orderBuilder.addItem(

                    OrderItem.builder()
                            .menuItem(menuItem)
                            .quantity(quantity)
                            .largeSize(large)
                            .extraSauce(sauce)
                            .build();

            );

        }

    }

    private void displayMenu() {

        System.out.println("\nMenu");

        for(MenuItem item : menu.getItems()){

            System.out.printf(
                    "%d - %s ($%.2f)%n",
                    item.getId(),
                    item.getName(),
                    item.getPrice()
            );

        }

    }

    private PaymentMethod choosePayment(){

        System.out.println();

        System.out.println("1. Cash");

        System.out.println("2. Card");

        System.out.println("3. Valu");

        int choice = scanner.nextInt();

        scanner.nextLine();

        PaymentType type;

        switch (choice){
            case 1:
                type = PaymentType.CASH;
            case 2:
                type = PaymentType.CARD;
            case 3:
                type = PaymentType.VALUE;
        }

        return factory.createPaymentMethod(type);

    }

    private NotificationChannel chooseNotification(){

        System.out.println();

        System.out.println("1. Email");

        System.out.println("2. SMS");

        int choice = scanner.nextInt();

        scanner.nextLine();

        NotificationType type = choice == 1 ? NotificationType.EMAIL : NotificationType.SMS;

        return factory.createNotificationService(type);

    }

    private String readDeliveryNote(){

        System.out.print("Delivery note: ");

        return scanner.nextLine();

    }

    private String readDiscountCode(){

        System.out.print("Discount code (optional): ");

        return scanner.nextLine();

    }

}