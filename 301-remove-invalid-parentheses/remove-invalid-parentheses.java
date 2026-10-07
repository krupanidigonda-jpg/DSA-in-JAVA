import java.util.*;

class Solution {
    Set<String> set = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0)
                    left--;
                else
                    right++;
            }
        }

        solve(s, 0, left, right, 0, new StringBuilder());

        return new ArrayList<>(set);
    }

    void solve(String s, int i, int left, int right, int open, StringBuilder cur) {
        if (i == s.length()) {
            if (left == 0 && right == 0 && open == 0) {
                set.add(cur.toString());
            }
            return;
        }

        char c = s.charAt(i);

        if (c == '(' && left > 0) {
            solve(s, i + 1, left - 1, right, open, cur);
        }

        if (c == ')' && right > 0) {
            solve(s, i + 1, left, right - 1, open, cur);
        }

        if (c == '(') {
            cur.append(c);
            solve(s, i + 1, left, right, open + 1, cur);
            cur.deleteCharAt(cur.length() - 1);
        } 
        else if (c == ')') {
            if (open > 0) {
                cur.append(c);
                solve(s, i + 1, left, right, open - 1, cur);
                cur.deleteCharAt(cur.length() - 1);
            }
        } 
        else {
            cur.append(c);
            solve(s, i + 1, left, right, open, cur);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}