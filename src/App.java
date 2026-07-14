import java.util.Scanner;
import model.Customer;
import model.Order;
import model.OrderItem;
import model.MenuItem;
import model.Menu;
import service.OrderService;
import service.ServiceFactory;
import payment.PaymentMethod;
import payment.PaymentType;
import notification.NotificationChannel;
import notification.NotificationType;


public class App {

    private final Scanner scanner = new Scanner(System.in);

    private final ServiceFactory factory = ServiceFactory.getInstance();

    private final Menu menu = factory.getMenu();

    private final OrderService orderService =
            factory.createOrderService();

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
            scanner.nextLine();

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
                            .build()
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
                break;
            case 2:
                type = PaymentType.CARD;
                break;
            case 3:
                type = PaymentType.VALUE;
                break;
            default:
                System.out.println("Invalid choice.");
                type = null;
                break;
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

        return factory.createNotificationChannel(type);

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