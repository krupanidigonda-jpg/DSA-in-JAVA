class Solution {
    public static String removeOuterParentheses(String s){
        String ans="";
        int count=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                if(count>0){
                    ans+=s.charAt(i);
                }
            count++;
            }
            
            else{
                count--;
                if(count>0){
                    ans+=s.charAt(i);
                }
            }
        }
        return ans;
    }
}