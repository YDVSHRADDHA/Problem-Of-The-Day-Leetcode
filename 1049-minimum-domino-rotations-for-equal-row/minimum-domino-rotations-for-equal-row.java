class Solution {
    int minDominoRotations(int[] tops, int[] bottoms) {
    int result = check(tops[0], tops, bottoms);
    if (result != -1) return result;
    return check(bottoms[0], tops, bottoms);
}

int check(int target, int[] tops, int[] bottoms) {
    int rotationTop = 0, rotationBottom = 0;
    for (int i = 0; i < tops.length; i++) {
        if (tops[i] != target && bottoms[i] != target)
            return -1; // impossible to make target
        else if (tops[i] != target)
            rotationTop++; // need to rotate this domino to bring target to top
        else if (bottoms[i] != target)
            rotationBottom++; // need to rotate to bring target to bottom
    }
    return Math.min(rotationTop, rotationBottom);
}

}