class FoodRatings {
    // Mapping food -> cuisine
    Map<String, String> foodToCuisine = new HashMap<>();
    // Mapping food -> rating
    Map<String, Integer> foodToRating = new HashMap<>();
    // Mapping cuisine -> TreeSet of food names with custom sorting
    Map<String, TreeSet<String>> cuisineToFoods = new HashMap<>();

    // Custom comparator for TreeSet
    Comparator<String> foodComparator = (a, b) -> {
        int ratingA = foodToRating.get(a);
        int ratingB = foodToRating.get(b);
        if (ratingA != ratingB) {
            return ratingB - ratingA; // higher rating comes first
        }
        return a.compareTo(b); // lexicographically smaller comes first
    };

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRating.put(food, rating);

            cuisineToFoods.putIfAbsent(cuisine, new TreeSet<>(foodComparator));
            cuisineToFoods.get(cuisine).add(food);
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        TreeSet<String> foodSet = cuisineToFoods.get(cuisine);
        
        foodSet.remove(food); // Remove old value
        foodToRating.put(food, newRating); // Update rating
        foodSet.add(food); // Re-insert with new rating
    }

    public String highestRated(String cuisine) {
        return cuisineToFoods.get(cuisine).first();
    }
}
