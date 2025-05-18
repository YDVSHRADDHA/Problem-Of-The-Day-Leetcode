const MOD = 1e9 + 7;

/**
 * @param {number} m
 * @param {number} n
 * @return {number}
 */
var colorTheGrid = function(m, n) {
    // Step 1: Generate all valid colorings for a column
    const colorings = [];
    
    const backtrack = (pos, curr) => {
        if (pos === m) {
            colorings.push([...curr]);
            return;
        }
        for (let color = 0; color < 3; color++) {
            if (pos === 0 || curr[pos - 1] !== color) {
                curr.push(color);
                backtrack(pos + 1, curr);
                curr.pop();
            }
        }
    };
    
    backtrack(0, []);
    const k = colorings.length;

    // Step 2: Precompute transitions between valid colorings
    const transitions = new Array(k).fill(0).map(() => []);
    for (let i = 0; i < k; i++) {
        for (let j = 0; j < k; j++) {
            let valid = true;
            for (let r = 0; r < m; r++) {
                if (colorings[i][r] === colorings[j][r]) {
                    valid = false;
                    break;
                }
            }
            if (valid) transitions[i].push(j);
        }
    }

    // Step 3: Dynamic programming
    let dp = new Array(k).fill(1); // First column: all valid colorings
    for (let col = 1; col < n; col++) {
        const newDp = new Array(k).fill(0);
        for (let i = 0; i < k; i++) {
            for (let j of transitions[i]) {
                newDp[i] = (newDp[i] + dp[j]) % MOD;
            }
        }
        dp = newDp;
    }

    // Sum up all possible coloring ways for the last column
    return dp.reduce((sum, val) => (sum + val) % MOD, 0);
};
