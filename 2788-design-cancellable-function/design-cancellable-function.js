/**
 * Takes a generator and returns a cancel function and a promise.
 * If cancel is called, the promise is rejected with "Cancelled".
 *
 * @param {Generator} generator - A generator that yields Promises.
 * @return {[Function, Promise]} - Returns [cancelFunction, resultPromise]
 */
var cancellable = function(generator) {
    // Flag to track if cancellation has been requested
    var cancelled = false;

    // This will hold the cancel function that rejects the promise
    var cancel;

    // This promise will be used to reject when cancellation is triggered
    const cancelPromise = new Promise((_, reject) => {
        // Define the cancel function
        cancel = () => {
            cancelled = true;        // Set cancelled flag
            reject("Cancelled");     // Reject the promise with "Cancelled"
        };
    });

    // The main promise that runs the generator asynchronously
    const promise = (async () => {
        // Start the generator (initial .next() call gives us the first yield)
        let next = generator.next();

        // Keep running the generator until done or cancelled
        while (!cancelled && !next.done) {
            try {
                // Wait for either the yielded promise OR the cancelPromise (whichever comes first)
                const result = await Promise.race([next.value, cancelPromise]);

                // Pass the result back into the generator to get the next yield
                next = generator.next(result);

            } catch (e) {
                // If any error occurs (e.g., from cancelled promise or rejected async op)
                // Throw it back into the generator so it can handle it if needed
                next = generator.throw(e);
            }
        }

        // When the generator is done, return the final value
        return next.value;
    })();

    // Return the cancel function and the promise as a tuple
    return [cancel, promise];
};
