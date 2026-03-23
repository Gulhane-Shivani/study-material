import java.util.*;
class MajorityElementProb
{
    public static int majorityElement(int arr[])
    {
        int count =0;
        int candidate = 0;

        for (int num:arr)
        {
            if (candidate==0)
            {
                candidate = num;
            }
            if (num == candidate)
            {
                count++;
            }
            else{
                count--;
            }

        }

        for (int num:arr)
        {
            if (num == candidate)
            {
                count++;
            }
        }
        if (count > arr.length/2)        {
            return candidate;
        }
        return -1;
    }

    
    public static void main(String[] args)
    {
        int arr[] = {3,3,4,2,3,3,3,1};
         int result = majorityElement(arr);
         System.out.println(result);
    }
}