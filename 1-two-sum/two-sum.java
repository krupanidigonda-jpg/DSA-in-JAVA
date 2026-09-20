class Solution {
    public int[] twoSum(int[] arr, int target) {
        int f[]=new int[2];
		int ans=0;
		HashMap<Integer,Integer>hm=new HashMap<>();
		for(int i=0;i<arr.length;i++){
		    ans=target-arr[i];
		    if(hm.containsKey(ans)){
		        f[0]=hm.get(ans);
		        f[1]=i;
		        break;
		    }
		    hm.put(arr[i],i);
		}
        return f;
    }
}