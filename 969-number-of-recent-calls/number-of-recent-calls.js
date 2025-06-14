var RecentCounter = function() {
    this.q = [];
};

/** 
 * @param {number} t
 * @return {number}
 */
RecentCounter.prototype.ping = function(t) {
    this.q.push(t);
    
    // Remove all requests older than t - 3000
    while (this.q[0] < t - 3000) {
        this.q.shift();
    }
    
    return this.q.length;
};
