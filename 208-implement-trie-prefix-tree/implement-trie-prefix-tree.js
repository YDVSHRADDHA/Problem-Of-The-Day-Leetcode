var Trie = function() {
    this.root = {}; // Initialize an empty root node
};

/** 
 * @param {string} word
 * @return {void}
 */
Trie.prototype.insert = function(word) {
    let node = this.root;
    for (let char of word) {
        if (!node[char]) {
            node[char] = {}; // Create a new node if it doesn't exist
        }
        node = node[char];
    }
    node.isEnd = true; // Mark the end of a word
};

/** 
 * @param {string} word
 * @return {boolean}
 */
Trie.prototype.search = function(word) {
    let node = this.root;
    for (let char of word) {
        if (!node[char]) return false;
        node = node[char];
    }
    return node.isEnd === true;
};

/** 
 * @param {string} prefix
 * @return {boolean}
 */
Trie.prototype.startsWith = function(prefix) {
    let node = this.root;
    for (let char of prefix) {
        if (!node[char]) return false;
        node = node[char];
    }
    return true;
};
