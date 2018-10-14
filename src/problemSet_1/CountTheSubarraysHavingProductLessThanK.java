package problemSet_1;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Prakash on 29-06-2018.
 * https://practice.geeksforgeeks.org/problems/count-the-subarrays-having-product-less-than-k/0
 */
public class CountTheSubarraysHavingProductLessThanK {
    public static void main(String[] a){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        int[] answers = new int[testCases];
        for(int test = 0; test < testCases; test++){
            String[] s = in.nextLine().split(" ");
            int k = Integer.valueOf(s[1]);
            int[] arr = new int[Integer.valueOf(s[0])];
            s = in.nextLine().split(" ");
            for(int i = 0; i < arr.length; i++)
                arr[i] = Integer.valueOf(s[i]);

            List<Integer> validValuesAtEachStage = new CopyOnWriteArrayList<>();
            for(int i = 0; i < arr.length; i++){
                if(!validValuesAtEachStage.isEmpty()){
                    for(Integer availableValue : validValuesAtEachStage){
                        validValuesAtEachStage.remove(availableValue);
                        if(availableValue * arr[i] < k){
                            validValuesAtEachStage.add(availableValue*arr[i]);
                            answers[test]++;
                        }
                    }
                }

                if(arr[i] < k){
                    validValuesAtEachStage.add(arr[i]);
                    answers[test]++;
                }

            }

/*            for(int i = 0; i < arr.length; i++){
                int product = 1;
                for(int j = i; j < arr.length; j++){
                    product *= arr[j];
                    if(product < k){
                        answers[test]++;
                    } else
                        break;
                }
            }*/
        }
        for(int i = 0;i < answers.length; i++)
            System.out.println(answers[i]);
    }
}
