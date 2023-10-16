import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        // Create a list to store delivery points
        List<DeliveryPoint> deliveryPoints = new ArrayList<>();

        // Adding delivery points with indices and names
        deliveryPoints.add(new DeliveryPoint(1, "Company", "Company", 000, 0));
        deliveryPoints.add(new DeliveryPoint(2, "A", "Refrigerator", 101, 2));
        deliveryPoints.add(new DeliveryPoint(3, "B", "LG Television", 102, 2));
        deliveryPoints.add(new DeliveryPoint(4, "C", "Samsung Galaxy S23 Ultra", 103, 2));
        deliveryPoints.add(new DeliveryPoint(5, "D", "Lenovo Legion 5 Pro laptop", 104, 2));
        deliveryPoints.add(new DeliveryPoint(6, "E", "Logitech 504 mouse", 105, 10));

        // Distance matrix representing distances in kilometers between delivery points
        int[][] distanceMatrix = {
                /* Company to all other points */ { 0, 20, 10, 30, 40, 50 },
                /* A to all other points */ { 15, 0, 5, 30, 20, 35 },
                /* B to all other points */ { 10, 5, 0, 30, 40, 25 },
                /* C to all other points */ { 20, 25, 30, 0, 15, 40 },
                /* D to all other points */ { 30, 35, 40, 15, 0, 20 },
                /* E to all other points */ { 40, 45, 50, 25, 20, 0 }
        };

        // Create a DistanceMatrix object to encapsulate (To hold and manage) the
        // distance matrix
        DistanceMatrix distanceMatrixObject = new DistanceMatrix(distanceMatrix);

        // Create a DeliverySchedule object with delivery points and the distance matrix
        DeliverySchedule deliverySchedule = new DeliverySchedule(deliveryPoints, distanceMatrixObject);

        // Calculate the shortest path using the Nearest Neighbor Algorithm
        List<DeliveryPoint> shortestPath = deliverySchedule.calculateShortestPath();

        // Print the shortest path
        System.out.println("Shortest path: Addresses");
        System.out.println();

        for (int i = 0; i < shortestPath.size(); i++) {
            DeliveryPoint point = shortestPath.get(i);
            System.out.print(point.getAddress());
            if (i < shortestPath.size() - 1) {
                System.out.print(" -> ");
            } else if (i == shortestPath.size() - 1) {
                System.out.print(" -> Company"); // Add this line to print "Company" at the end
            }
        }
        System.out.println();

        System.out.println();
        DeliveryVan van = new DeliveryVan(10);

        Stack<DeliveryPoint> loadingOrder = new Stack<>();
        loadingOrder.addAll(shortestPath);

        System.out.println("Loading order");
        System.out.println();

        StringBuilder loadingOrderLine = new StringBuilder();
        List<DeliveryPoint> detailedLoadingOrder = new ArrayList<>(); // Create a list to preserve order

        // Load delivery points onto the van while respecting capacity limits
        // Start the loop from the last item to load in reverse order
        for (int i = loadingOrder.size() - 1; i >= 0; i--) {
            DeliveryPoint point = loadingOrder.get(i);

            // Skip the "Company" delivery point at index 1 for loading
            if (point.getIndex() == 1) {
                continue;
            }

            // Check if loading this item exceeds the van's capacity
            if (van.getLoadSize() + point.getGoodsQuantity() <= van.getCapacity()) {
                // Load the item onto the van
                boolean loaded = van.load(point);

                // If the item was successfully loaded, add it to the loading order line
                if (loaded) {
                    loadingOrderLine.append("Address: ")
                            .append(point.getAddress())
                            .append(" (Item - ")
                            .append(point.getItemName())
                            .append(") -> ");

                    detailedLoadingOrder.add(point); // Store the item in the detailed loading order list
                } else {
                    System.out.println("Item skipped due to capacity limit: " + "Address " + point.getAddress() + " ("
                            + point.getItemName() + ")");
                }
            } else {
                System.out.println("Item skipped due to capacity limit: " + "Address " + point.getAddress() + " ("
                        + point.getItemName() + ")");
            }
        }

        // Print the loading order
        System.out.println(loadingOrderLine.toString());

        System.out.println();
        System.out.println("Loading order with details:");
        System.out.println();
        for (DeliveryPoint point : detailedLoadingOrder) {
            System.out.println("Address: " + point.getAddress());
            System.out.println("Item: " + point.getItemName());
            System.out.println("Quantity: " + point.getGoodsQuantity());
            System.out.println("Invoice ID: " + point.getInvoiceId());
            System.out.println();
        }

        System.out.println();
        System.out.println("Delivery order:");
        System.out.println();
        System.out.println(van.deliver());
    }
}
