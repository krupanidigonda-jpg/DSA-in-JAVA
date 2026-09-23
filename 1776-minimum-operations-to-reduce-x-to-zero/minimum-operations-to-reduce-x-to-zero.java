class Solution {
    public static int totalsum(int arr[]){
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        return sum;
    }
    
    public static int minOperations(int[] arr, int x) {
        int target=totalsum(arr)-x;
        if(target<0){
            return -1;
        }
        
        int l=0;
        int temp=0;
        int ans=Integer.MIN_VALUE;
        
        for(int r=0;r<arr.length;r++){
            temp+=arr[r];
            
            while(temp>target){
                temp-=arr[l];
                l++;
            }
            if(temp==target){
                ans=Math.max(ans,r-l+1);
            }
        }
        
        if(ans==Integer.MIN_VALUE){ 
            return -1; 
            
        } 
        return arr.length-ans;
    }
}