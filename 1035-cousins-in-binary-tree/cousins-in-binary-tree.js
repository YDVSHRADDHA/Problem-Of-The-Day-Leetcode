var isCousins = function(root, x, y) {
    if (!root) return false;

    const queue = [[root, null, 0]];  // [node, parent, depth]
    let xInfo = null, yInfo = null;

    while (queue.length) {
        const [node, parent, depth] = queue.shift();
        
        if (node.val === x) xInfo = [parent, depth];
        if (node.val === y) yInfo = [parent, depth];

        if (xInfo && yInfo) {
            return xInfo[1] === yInfo[1] && xInfo[0] !== yInfo[0];
        }

        if (node.left) queue.push([node.left, node, depth + 1]);
        if (node.right) queue.push([node.right, node, depth + 1]);
    }

    return false;
};
