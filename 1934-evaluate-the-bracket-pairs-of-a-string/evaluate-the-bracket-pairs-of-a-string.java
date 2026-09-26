class Solution {
    public static String evaluate(String s, List<List<String>> knowledge) {
        HashMap<String,String> hm = new HashMap<>();

        for(int i = 0; i < knowledge.size(); i++){
            hm.put(knowledge.get(i).get(0), knowledge.get(i).get(1));
        }

        String sb = "";

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                i++;
                String temp = "";

                while(s.charAt(i) != ')'){
                    temp += s.charAt(i);
                    i++;
                }

                if(hm.containsKey(temp)){
                    sb += hm.get(temp);
                }
                else{
                    sb += "?";
                }
            }
            else{
                sb += s.charAt(i);
            }
        }

        return sb;
    }
}