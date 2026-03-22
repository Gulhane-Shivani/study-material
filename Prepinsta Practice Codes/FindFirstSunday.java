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
		if(firstSunday < n)
		{
			count = 1 + (n - firstSunday - 1) / 7;
		}
		System.out.print("No of Sunday in "+ n + " Days :"+count);
	}
}