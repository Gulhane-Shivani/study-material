/* A furnishing company is manufacturing a new collection of curtains. The curtains are of two colors aqua(a) and black (b). The curtains color is represented as a string(str) consisting of a’s and b’s of length N. Then, they are packed (substring) into L number of curtains in each box. The box with the maximum number of ‘aqua’ (a) color curtains is labeled. The task here is to find the number of ‘aqua’ color curtains in the labeled box. 
Input :
abbbaabbb -> Value of str
5   -> Value of L

Output:
2   -> Maximum number of a’s

Explanation:
From the input given above,
Dividing the string into sets of 5 characters each.
Set 1: {a,b,b,b,b}

Set 2: {a,a,b,b,b}
Among both the sets, set 2 has more number of a’s. The number of a’s in set 2 is 2. */

import java.util.*;

class AquaCurtains{
public static void main(String args[])
{
	Scanner sc= new Scanner(System.in);
	String str= sc.nextLine();
	int boxLength=sc.nextInt();

	int max=0;  int count=0;
	for(int i=0; i<str.length(); i++)
	{
	   if(i % boxLength ==0)
	   {
		max=Math.max(count, max);
		count=0;
	   }

	 if(str.charAt(i)=='a')
	 {
	   count++;
	 }
       }
    max=Math.max(count, max);
    System.out.print(max);

}
}
