var zigzagLevelOrder = function(root) {
    if (!root) return [];

    let result = [];
    let queue = [root];
    let leftToRight = true;

    while (queue.length) {
        let levelSize = queue.length;
        let level = [];

        for (let i = 0; i < levelSize; i++) {
            let node = queue.shift();
            if (leftToRight) {
                level.push(node.val);
            } else {
                level.unshift(node.val); // Insert at front
            }

            if (node.left) queue.push(node.left);
            if (node.right) queue.push(node.right);
        }

        result.push(level);
        leftToRight = !leftToRight;
    }

    return result;
};
