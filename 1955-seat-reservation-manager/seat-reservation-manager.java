import java.util.*;

class SeatManager {
    // Min-heap for efficiently retrieving the smallest unreserved seat
    private PriorityQueue<Integer> availableSeats;

    public SeatManager(int n) {
        availableSeats = new PriorityQueue<>();
        // Initialize the min-heap with all seats from 1 to n
        for (int i = 1; i <= n; i++) {
            availableSeats.offer(i);
        }
    }

    // Reserve the smallest unreserved seat
    public int reserve() {
        return availableSeats.poll(); // Get and remove the smallest seat from the heap
    }

    // Unreserve the given seat
    public void unreserve(int seatNumber) {
        availableSeats.offer(seatNumber); // Add the seat back to the heap
    }
}
