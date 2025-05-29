var invalidTransactions = function(transactions) {
    const parsed = transactions.map((t, i) => {
        const [name, time, amount, city] = t.split(',');
        return {
            name,
            time: parseInt(time),
            amount: parseInt(amount),
            city,
            index: i,
            original: t
        };
    });

    const invalid = Array(transactions.length).fill(false);

    // Rule 1: Amount > 1000
    for (let i = 0; i < parsed.length; i++) {
        if (parsed[i].amount > 1000) {
            invalid[i] = true;
        }
    }

    // Rule 2: Same name, different city, within 60 minutes
    for (let i = 0; i < parsed.length; i++) {
        for (let j = 0; j < parsed.length; j++) {
            if (i === j) continue;

            if (
                parsed[i].name === parsed[j].name &&
                parsed[i].city !== parsed[j].city &&
                Math.abs(parsed[i].time - parsed[j].time) <= 60
            ) {
                invalid[i] = true;
                invalid[j] = true;
            }
        }
    }

    // Collect invalid transactions
    const result = [];
    for (let i = 0; i < parsed.length; i++) {
        if (invalid[i]) {
            result.push(parsed[i].original);
        }
    }

    return result;
};
