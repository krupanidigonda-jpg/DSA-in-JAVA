class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        n = len(s)
        dici = {}
        l = 0
        ans = 0
        for r in range(n):
            if s[r] in dici and dici[s[r]] >= l:
                l = dici[s[r]] + 1
            dici[s[r]] = r
            ans = max(ans, r - l + 1)
        return ans


        