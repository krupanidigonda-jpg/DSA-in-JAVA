import java.util.*;

class Solution {
    int i = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> set = solve(expression);
        return new ArrayList<>(new TreeSet<>(set));
    }

    public Set<String> solve(String s) {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        while (i < s.length() && s.charAt(i) != '}') {
            char ch = s.charAt(i);

            if (ch == ',') {
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                i++;
                continue;
            }

            Set<String> temp;

            if (ch == '{') {
                i++;
                temp = solve(s);
                i++;
            } else {
                temp = new HashSet<>();
                temp.add(String.valueOf(ch));
                i++;
            }

            Set<String> next = new HashSet<>();

            for (String a : current) {
                for (String b : temp) {
                    next.add(a + b);
                }
            }

            current = next;
        }

        result.addAll(current);

        return result;
    }
}