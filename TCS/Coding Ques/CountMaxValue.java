import java.util.*;

public class CountMaxValue{

	public static void main(String args[])
{
	Scanner sc= new Scanner(System.in);
	int n= sc.nextInt();
	int arr[]=new int[n];
	
	for(int i=0; i<n; i++)
	arr[i]=sc.nextInt();
	
	
	int max=Integer.MIN_VALUE;
      //  System.out.print(max);

	int count=0;
	
	for(int i=0; i<n; i++)
	{
	     if(arr[i] > max)
		{
		   max=arr[i];
		   count++;
		}
	}
	
	System.out.print("Count=" +count);
}
}
