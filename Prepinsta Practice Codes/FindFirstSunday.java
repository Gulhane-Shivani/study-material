/*Jack is always excited about sunday. It is favourite day, when he gets to play all day. And goes to cycling with his friends. 

So every time when the months starts he counts the number of sundays he will get to enjoy. Considering the month can start with any day, be it Sunday, Monday…. Or so on.

Count the number of Sunday jack will get within n number of days.

 Example 1:

Input 

mon-> input String denoting the start of the month.

13  -> input integer denoting the number of days from the start of the month.

Output :

2    -> number of days within 13 days.
*/


import java.util.*;
public class FindFirstSunday
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter start day :");
		String firstDay = sc.next();
		
		System.out.print("Enter the Number of Days:");
		int n = sc.nextInt();
		
		Map<String, Integer> map = new HashMap<>();

		map.put("sun",0);
		map.put("mon",1);
		map.put("tue",2);
		map.put("wed",3);
		map.put("thu",4);
		map.put("fri",5);
		map.put("sat",6);

		int firstDayIndex = map.get(firstDay);			//Getting the index of our start day

		int firstSunday = (7 - firstDayIndex) % 7;		//finding the first sunday
		
		int count = 0;
		if(firstSunday < n)					//find the remainng days and after that sunday
		{
			count = 1 + (n - firstSunday - 1) / 7;
		}
		System.out.print("No of Sunday in "+ n + " Days :"+count);
	}
}