/**
 * @param {number} numberOfUsers
 * @param {string[][]} events
 * @return {number[]}
 */
var countMentions = function(numberOfUsers, events) {
 
    const mentions = new Array(numberOfUsers).fill(0);
    const offlineUntil = new Array(numberOfUsers).fill(0);

    // Sort events by timestamp and prioritize OFFLINE before MESSAGE if same timestamp
    events.sort((a, b) => {
        const t1 = parseInt(a[1]), t2 = parseInt(b[1]);
        if (t1 !== t2) return t1 - t2;
        return a[0] === "OFFLINE" ? -1 : 1;
    });

    for (const [type, timeStr, value] of events) {
        const timestamp = parseInt(timeStr);

        // Compute online status based on offlineUntil BEFORE processing this event
        const isOnline = offlineUntil.map(until => timestamp >= until);

        if (type === "OFFLINE") {
            const id = parseInt(value);
            offlineUntil[id] = timestamp + 60;
        } else if (type === "MESSAGE") {
            if (value === "ALL") {
                for (let i = 0; i < numberOfUsers; i++) {
                    mentions[i]++;
                }
            } else if (value === "HERE") {
                for (let i = 0; i < numberOfUsers; i++) {
                    if (isOnline[i]) mentions[i]++;
                }
            } else {
                const ids = value.split(" ");
                for (const token of ids) {
                    if (token.startsWith("id")) {
                        const id = parseInt(token.slice(2));
                        mentions[id]++;
                    }
                }
            }
        }
    }

    return mentions;
}
