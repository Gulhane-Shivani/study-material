/*You are given a sorted array arr[] containing positive integers. 
Your task is to remove all duplicate elements from this array such that each element appears only once. 
Return an array containing these distinct elements in the same order as they appeared.

Examples :
Input: arr[] = [2, 2, 2, 2, 2]
Output: [2]

*/
import java.util.*;
class ArrayListDuplicate
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number of Element You want");
		int n = sc.nextInt();
		
		int[] arr = new int[n];
		for (int i=0;i<n;i++)
		{
			arr[i] = sc.nextInt();
		}
	//      System.out.println(Arrays.toString(arr));

		Set<Integer> set = new LinkedHashSet<>();
		for(int num:arr)
		{
			set.add(num);
		}
		
		int[] result = new int[set.size()];
                int i = 0;
		for (int num:set)
                {
		    result[i++]=num;
                }
                System.out.println(Arrays.toString(result));
	
	}
}	