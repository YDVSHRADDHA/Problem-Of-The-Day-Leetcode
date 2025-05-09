class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        // Map from stop to list of buses (routes) that stop there
        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToBuses.computeIfAbsent(stop, x -> new ArrayList<>()).add(i);
            }
        }

        Set<Integer> visitedStops = new HashSet<>();
        Set<Integer> visitedBuses = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        // Add all buses starting from the source stop
        if (!stopToBuses.containsKey(source)) return -1;
        for (int bus : stopToBuses.get(source)) {
            queue.offer(bus);
            visitedBuses.add(bus);
        }

        int busesTaken = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int currentBus = queue.poll();

                for (int stop : routes[currentBus]) {
                    if (stop == target) return busesTaken;

                    if (visitedStops.contains(stop)) continue;
                    visitedStops.add(stop);

                    for (int nextBus : stopToBuses.get(stop)) {
                        if (!visitedBuses.contains(nextBus)) {
                            queue.offer(nextBus);
                            visitedBuses.add(nextBus);
                        }
                    }
                }
            }
            busesTaken++;
        }

        return -1;
    }
}
