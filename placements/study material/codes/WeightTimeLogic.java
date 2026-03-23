import java.util.*;
import java.io.*;

/*9. The Washing Machine (Wait Time Logic)
A smart washing machine has three modes based on weight:
•	Low (0–2000g): 25 mins
•	Medium (2001–4000g): 35 mins
•	High (4001–7000g): 45 mins
If weight is 0, time is 0. If weight > 7000, output "OVERLOAD".
•	Input: 2500
•	Output: 	
*/

public class WeightTimeLogic
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a Weight :" );
		int weight = sc.nextInt();
		
		if(weight == 0)
		{
			System.out.println("time is 0");
		}
		else if(weight > 7000)		
		{
			System.out.println("OVERLOAD");
		}
		else if(weight <= 2000)
		{
			System.out.println("Time Estimated: 25 Minutes");
		}
		else if(weight <= 4000)
		{
			System.out.println("Time Estimated: 35 Minutes");
		}
		else if(weight <= 7000)
		{
			System.out.println("Time Estimated: 45 Minutes");
		}
		sc.close();
	}
}