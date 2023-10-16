import java.io.Serializable;

public class DistanceMatrix implements Serializable {
    // Private field to store the distance matrix
    private int[][] distanceMatrix;

    // Constructor
    public DistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix; // Setting the distanceMatrix field
    }

    // Method to get the distance between two points in the distance matrix
    public int getDistance(int fromIndex, int toIndex) {
        return distanceMatrix[fromIndex][toIndex]; // Retrieve the distance value from the matrix
    }
}