/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val);
 *     this.left = (left===undefined ? null : left);
 *     this.right = (right===undefined ? null : right);
 * }
 */

/**
 * @param {TreeNode} root
 * @return {number}
 */
var maxDepth = function(root) {
    if (root === null) return 0; // no tree => depth 0

    // Recursively find max depth of left and right subtree and add 1 for current node
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
};
