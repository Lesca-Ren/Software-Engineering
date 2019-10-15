import java.util.ArrayList;
import java.util.List;
 
public class Snippet {
	
	/***
	 * @desc 
	 * 方法一：暴力枚举 
		定义一个最大值max初始化一个很小的数，定义一个变量sum表示求和值，遍历数组元素，从第一个元素开始，依次相加，
		如果和sum比最大值max大就将sum赋值给最大值。然后再来一个循环控制从第i个数组元素开始求和，直到n. 
		时间复杂度:O(n^2)
	 * @param params
	 * @return
	 */
	public static List<Integer> subArraySumItem(int[] params) {
	    int currentSum = 0;
	    int curStart = 0;
	    int maxSum = 0;
	    int start = 0;
	    int end = 0;
	    for (int i = 0; i < params.length; i++) {
	        currentSum += params[i];
	        if(currentSum > 0) {
	            if (currentSum > maxSum) {
	                maxSum = currentSum;
	                start = curStart ;
	                end = i;
	            }
	        }else{
	            currentSum = 0;
	            curStart = i ;//i=2
	            
	        }
	    }
	    List<Integer> list = new ArrayList<Integer>();
	    for (int i = start+1; i <=end ; i++) {
	        list.add(params[i]);
	    }
	    return list;
	}
	
	
	
	
	private static int getMaxSum(int[] arr) {
        int max = -100000;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (max < sum) {
                    max = sum;
                }
            }
        }
 
        return max;
    }
	
	/**
	 * 分析：子数组是指原数组中连续的一组数。求最大值，如果数组中元素都小于0；则直接返回数组的最大值。对于一般的情况。我们只要遍历求数组，
	       同时对其求和，如果和数变得小于0，那就说明了此时这个子数组是不符合题意的，如果和数为正且大于之前求和过程中记录的最大值，那就将这个
	       和数赋值给MAX，这样遍历一趟就将其中的最大和给求出来了。
	 *
	 * @param args
	 */
	public static void main(String[] args) {
        
        int[] arrays = new int[10000];
        int cnt=0;
	   for(String s:args){
           arrays[cnt] = Integer.valueOf(s);
           cnt++;
       }
	    /*List<Integer> list = subArraySumItem(arrays);
	    System.out.println("list=" + list);*/
	    
        int maxSum = getMaxSum(arrays);
        System.out.println("最大子数组的和为：" + maxSum);
	    
	    /*System.out.println("dd->" + getMaxSum2(arrays));*/
	  
    }
}