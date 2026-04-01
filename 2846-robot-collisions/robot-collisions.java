import java.util.*; // Needed for Arrays, Stack, List, ArrayList

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {

        int n = positions.length; // Total number of robots (used repeatedly, so store once)

        // Create a structure to sort robots by position WITHOUT losing original index
        int[][] robots = new int[n][2]; // Each entry: [position, original_index]

        for (int i = 0; i < n; i++) {
            robots[i][0] = positions[i]; // Store position → used for sorting
            robots[i][1] = i;            // Store original index → needed for final answer order
        }

        // Sort robots by position (left → right)
        // WHY: collisions depend on spatial order, not input order
        Arrays.sort(robots, (a, b) -> a[0] - b[0]);

        Stack<Integer> stack = new Stack<>(); 
        // Stack stores indices of robots moving RIGHT ('R')
        // WHY: only 'R' robots can collide with future 'L' robots

        boolean[] alive = new boolean[n]; 
        Arrays.fill(alive, true); 
        // Track which robots survive
        // Initially assume all are alive, eliminate during collisions

        // Traverse robots in sorted order (important!)
        for (int[] robot : robots) {

            int i = robot[1]; // Get original index of current robot

            if (directions.charAt(i) == 'R') {
                // If robot moves RIGHT → no immediate collision
                // WHY: it can only collide with a future LEFT robot
                stack.push(i); // Store it for future collision
            } else {
                // Current robot moves LEFT → may collide with previous RIGHT robots

                // Continue collisions while there are RIGHT robots waiting
                while (!stack.isEmpty()) {

                    int j = stack.peek(); // Last RIGHT-moving robot

                    if (healths[j] < healths[i]) {
                        // RIGHT robot is weaker

                        alive[j] = false; // RIGHT robot dies
                        stack.pop();      // Remove it from stack

                        healths[i]--;     // LEFT robot loses 1 health after collision

                        // Continue → this LEFT robot may hit more RIGHT robots
                    } 
                    else if (healths[j] > healths[i]) {
                        // LEFT robot is weaker

                        alive[i] = false; // LEFT robot dies
                        healths[j]--;     // RIGHT robot loses 1 health

                        break; // LEFT robot gone → no more collisions
                    } 
                    else {
                        // Equal health → both die

                        alive[i] = false; // LEFT robot dies
                        alive[j] = false; // RIGHT robot dies

                        stack.pop();      // Remove RIGHT robot

                        break; // Collision ends
                    }
                }

                // If stack becomes empty:
                // → no RIGHT robots left → this LEFT robot survives
                // No action needed (already marked alive)
            }
        }

        // Collect surviving robots in ORIGINAL input order
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (alive[i]) {              // Only include survivors
                result.add(healths[i]); // Add their final health
            }
        }

        return result; // Return final surviving healths
    }
}