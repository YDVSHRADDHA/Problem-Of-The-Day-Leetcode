class Solution {
    public String reverseWords(String s) {
         
 
		 //1. remove leading and trailing spaces.
		 s = s.trim();

		 //2. split the string into words.
           String[] words = s.split("\\s+"); 

		// this regex split on one or more space.

		// 3. Reverse the array of words.

		for(int i = 0; i < words.length / 2; i++)
		{
     String temp = words[i];
		 words[i] = words[words.length - i -1];
		 words[words.length - i - 1] = temp;

		}
        // 4. Join the reveser array
		 String reverStr = String.join(" ", words);

        
        return reverStr;
	}
}
