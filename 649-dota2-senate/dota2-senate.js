var predictPartyVictory = function(senate) {
    const n = senate.length;
    const radiant = [];
    const dire = [];

    // Step 1: Fill queues with indexes
    for (let i = 0; i < n; i++) {
        if (senate[i] === 'R') radiant.push(i);
        else dire.push(i);
    }

    // Step 2: Simulate rounds
    while (radiant.length && dire.length) {
        const rIndex = radiant.shift();
        const dIndex = dire.shift();

        // Lower index bans the higher one
        if (rIndex < dIndex) {
            // Radiant bans Dire, re-add Radiant with new index
            radiant.push(rIndex + n);
        } else {
            // Dire bans Radiant, re-add Dire with new index
            dire.push(dIndex + n);
        }
    }

    // Step 3: Return winner
    return radiant.length ? "Radiant" : "Dire";
};
