import java.util.*;
/*10. The Keyword Search (String Matching)
An HR software filters resumes. Given a list of "Must-have" keywords and a "Resume String," check if the resume contains all the keywords (case-insensitive).
•	Input: Keywords=["Java", "Python"], Resume="I am proficient in JAVA and python coding."
•	Output: Qualified
*/
public class StringMatching
{
    public static void main(String args[])
    {
        String resume = "I am proficient in JAVA and python coding.";
        String keyword[] = {"JAVA","python"};
        
        boolean Qualified = true;
        String resumelower = resume.toLowerCase();
        
        for(String key:keyword)
        {
            if(!resumelower.contains(key.toLowerCase()))
            {
                Qualified = false;
                break;
            }
        }
        if(Qualified){
            System.out.println("Qualified");
        }
        else{
            System.out.println("Not Qulifeid");
        }
    }
}