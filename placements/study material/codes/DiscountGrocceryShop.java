import java.util.*;
public class DiscountGrocceryShop
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the bill Amount :");
		double bill = sc.nextDouble();

		double discount = 0;
		if(bill >=5000)
		{
			discount = bill * 0.15;
		}
		else if(bill >=2000)
		{
			discount = bill * 0.10;
		}
		else 
		{
			discount = bill * 0.05;
		}

		double finalAmount = bill - discount;
		
		System.out.println("Discount :"+discount);
		System.out.println("Final bill of product is :"+finalAmount);
	}
}