import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeliverySchedule implements Serializable {
    // Private fields to store delivery points, distance matrix, and the shortest path
    private List<DeliveryPoint> deliveryPoints;
    private DistanceMatrix distanceMatrix;
    private List<DeliveryPoint> shortestPath;

    // Constructor to create a DeliverySchedule object with delivery points and a distance matrix
    public DeliverySchedule(List<DeliveryPoint> deliveryPoints, DistanceMatrix distanceMatrix) {
        this.deliveryPoints = deliveryPoints; // Setting the deliveryPoints field
        this.distanceMatrix = distanceMatrix; // Setting the distanceMatrix field
    }

    // Method to calculate the shortest path between delivery points
    // Using the Nearest Neighbor Algorithm
    public List<DeliveryPoint> calculateShortestPath() {
        shortestPath = new ArrayList<>(); // Initialize List
        DeliveryPoint currentPoint = deliveryPoints.get(0); // Starting from index 0 (first delivery point)
        shortestPath.add(currentPoint); // Adding the starting point to the shortest path

        // Continue until all delivery points are visited
        while (shortestPath.size() < deliveryPoints.size()) {
            DeliveryPoint nextPoint = getClosestUnvisitedPoint(currentPoint);
            shortestPath.add(nextPoint); // Adding the closest point to the shortest path
            currentPoint = nextPoint; // Updating the current point for the next iteration
        }
        return shortestPath; 
    }

    // Method to find the closest unvisited delivery point from the current point
    private DeliveryPoint getClosestUnvisitedPoint(DeliveryPoint currentPoint) {
        int minDistance = Integer.MAX_VALUE;
        DeliveryPoint closestPoint = null;

        // Iterate through all delivery points
        for (DeliveryPoint deliveryPoint : deliveryPoints) {
            // Check if the point has not been visited yet
            if (!shortestPath.contains(deliveryPoint)) {
                // Calculate the distance from the current point to the delivery point
                int distance = distanceMatrix.getDistance(currentPoint.getIndex() - 1, deliveryPoint.getIndex() - 1);
                
                // Update the closest point if this point is closer
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPoint = deliveryPoint;
                }
            }
        }
        return closestPoint;
    }
}
