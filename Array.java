import java.util.ArrayList;
import java.util.List;


public class Array {
    public static void main(String[] args)
    {
        int count = 0;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(5);
        int length = list.size();
        for(int i = 0; i < length; i++)
        {
            for(int j = i +1; j < length; j++)
            {
                if (list.get(i) == list.get(j))
                   count ++;
            }
        }
        System.out.println(count);
        
    }
}
