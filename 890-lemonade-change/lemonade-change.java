class Solution {
    public boolean lemonadeChange(int[] bills) {
        // Count of $5 and $10 bills in hand

        int fiveDB = 0;
        int tenDB = 0;

        // Iterate through each customer's bill

        for(int cB :  bills){
            if(cB == 5)
            {
                // Just add it to our count
                fiveDB++;
            }
            else if (cB == 10)
            {
                // We need to give $5 change
                if(fiveDB > 0 ){
                    fiveDB--;
                    tenDB++;
                }
                else{
                // can't provide chage, return false
                return false;
                }
            }
            else { // customerBill == 20
                // We need to give $15 change

                if(tenDB > 0 && fiveDB > 0 ){
                     // Give change as one $10 and one $5
                    fiveDB--;
                    tenDB--;
                }
                else if (fiveDB >= 3) {
                    // Give change as three $5
                    fiveDB -= 3;
                }
                 else {
                    // Can't provide change, return false
                    return false;
                }
            }
        }

    // If we've made it through all customers, return true
        return true;
    }
}