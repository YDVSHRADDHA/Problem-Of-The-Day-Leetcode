/**
 * @param {string} s
 * @return {string}
 */
var longestPalindrome = function(s) {
    if (s.length < 1) return '';
    
    let start = 0, maxLength = 1;
    
    function expandFromCenter(left, right) {
        while (left >= 0 && right < s.length && s[left] === s[right]) {
            left--;
            right++;
        }
        return right - left - 1; // The length of the palindrome
    }
    
    for (let i = 0; i < s.length; i++) {
        let len1 = expandFromCenter(i, i); // Odd-length palindrome
        let len2 = expandFromCenter(i, i + 1); // Even-length palindrome
        
        let len = Math.max(len1, len2);
        
        if (len > maxLength) {
            maxLength = len;
            start = i - Math.floor((len - 1) / 2);
        }
    }
    
    return s.substring(start, start + maxLength);
};
