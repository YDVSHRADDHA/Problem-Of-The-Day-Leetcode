var kLengthApart = function(nums, k) {
    let prev = -1;  // index of previous 1
    
    for (let i = 0; i < nums.length; i++) {
        
        // detect a 1 using XOR
        if ((nums[i] ^ 1) === 0) {   // nums[i] == 1
            
            // if we already found a previous 1
            if (prev !== -1) {
                
                // count zeros between the two 1's
                let distance = i - prev - 1;
                
                if (distance < k) return false;
            }
            
            prev = i; // update previous 1 position
        }
    }
    
    return true;
};
