/* Jack is always excited about sunday. It is favourite day, when he gets to play all day. And goes to cycling with his friends. 
So every time when the months starts he counts the number of sundays he will get to enjoy. Considering the month can start with any day, be it Sunday, Monday…. Or so on.
Count the number of Sunday jack will get within n number of days.

 Example 1:
Input :
mon-> input String denoting the start of the month.

13  -> input integer denoting the number of days from the start of the month.
Output :
2    -> number of days within 13 days. */ 

import java.util.*;

public class CountSunday{

 public static void main(String args[])
  {
	Scanner sc= new Scanner(System.in);
	String Startday= sc.next().toLowerCase();
	int n= sc.nextInt();

	Map<String, Integer> daymap= new HashMap<>();
	 
	daymap.put("sun", 0);
	daymap.put("mon", 1);
	daymap.put("thue", 2);
	daymap.put("web", 3);
	daymap.put("thus", 4);
	daymap.put("fri", 5);
	daymap.put("sat", 6);

	int StartId= daymap.get(Startday);

	int firstSunday= (7 - StartId )% 7;
	
	int count=0;
	if(firstSunday < n)
	{ 
	   count= 1 + ( n - firstSunday - 1) /7;   // +1 add firstsunday count , -1 remove firstsunday from number off day because is added already
	}
	System.out.println(count);
}
}