class Solution {
    public static int lengthOfLastWord(String s) {
        int i = s.length() - 1;

        while (s.charAt(i) == ' ') {
            i--;
        }

        int j = i;

        while (j > 0 && s.charAt(j) != ' ') {
            j--;
        }

        if (s.charAt(j) == ' ') {
            return i - j;
        }

        return i + 1;
    }
}