class Solution {
    public static long waysToBuyPensPencils(int total, int cost1, int cost2) {
        // Ensure cost1 is the greater value for consistency in calculations
        if (cost2 > cost1)
            return waysToBuyPensPencils(total, cost2, cost1);

        // If total is less than the cost of one pen, we can only buy pencils
        if (total < cost1)
            return total / cost2 + 1;

        // Calculate the "slope" in terms of how many pencils we can buy per pen
        int slope = cost1 / cost2;

        // Maximum number of pens we can buy
        int pivotX = total / cost1;

        // With the leftover money after buying max pens, how many pencils can we buy
        int pivotY = (total % cost1) / cost2;

        // Use arithmetic sum formula to calculate total ways in this triangular region
        // triangle = (2 * pivotY + slope * pivotX) * (pivotX + 1) / 2
        long triangle = (long)(2L * pivotY + slope * pivotX) * (pivotX + 1) / 2;

        // Check if cost1 is a multiple of cost2
        int r = cost1 % cost2;
        if (r == 0)
            return triangle + pivotX + 1; // +1 for the (0 pens, 0 pencils) case

        // Handle the remaining fractional part using recursion
        // f is the money left after buying pivotX pens and pivotY pencils
        int f = total % cost1 % cost2;

        // Recurse with new total and new costs to calculate remaining cases
        return triangle - f / r + waysToBuyPensPencils(f + r * pivotX, cost2, r);
    }
}
