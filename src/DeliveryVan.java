import java.util.Stack;

public class DeliveryVan {
    private int capacity; // Maximum capacity of the van
    private Stack<DeliveryPoint> vanLoad; // Stack to represent items loaded on the van

    // Constructor to initialize the delivery van with a capacity
    public DeliveryVan(int capacity) {
        this.capacity = capacity;
        this.vanLoad = new Stack<>();
    }

    // Getter method for capacity
    public int getCapacity() {
        return capacity;
    }

    // Method to load the van with a delivery point
    public boolean load(DeliveryPoint point) {
        int currentLoadSize = getLoadSize();
        int goodsQuantity = point.getGoodsQuantity();
        if (currentLoadSize + goodsQuantity <= capacity) {
            vanLoad.push(point); // Load the point onto the van
            return true; // Successfully loaded
        } else {
            System.out.println("Item limit exceeded for item: " + point.getItemName() + ". Stopping further loading.");
            return false; // Capacity exceeded, unable to load
        }
    }

    // Method to check the current load size of the van
    public int getLoadSize() {
        int loadSize = 0; // Initialize a variable to keep track of the total load size

        // Iterate through each delivery point in the vanLoad stack
        for (DeliveryPoint point : vanLoad) {
            // For each delivery point, add its goods quantity to the total load size
            loadSize += point.getGoodsQuantity();
        }

        return loadSize; // Return the total load size
    }

    // Method to deliver items from the van
    public String deliver() {
        while (!vanLoad.isEmpty()) {
            DeliveryPoint point = vanLoad.pop(); // Remove and deliver the item
            System.out.println("Delivered item to: " + point.getAddress());
        }
        return "Delivered all items";
    }

    // // Method to remove the furthest point from the van based on distance matrix
    // public DeliveryPoint removeFurthestPoint(int[][] distanceMatrix) {
    // if (!vanLoad.isEmpty()) {
    // DeliveryPoint currentLocation = vanLoad.peek(); // Get the current location
    // from the stack
    // int maxDistance = -1; // Initialize max distance
    // DeliveryPoint furthestPoint = null; // Initialize the furthest point

    // for (DeliveryPoint point : vanLoad) {
    // // Calculate the distance between the current location and the delivery point
    // int distance = distanceMatrix[currentLocation.getIndex() -
    // 1][point.getIndex() - 1];
    // if (distance > maxDistance) {
    // maxDistance = distance;
    // furthestPoint = point;
    // }
    // }

    // if (furthestPoint != null) {
    // vanLoad.remove(furthestPoint); // Remove the furthest point from the van
    // }
    // return furthestPoint;
    // } else {
    // return null; // The van is empty
    // }
    // }

}
