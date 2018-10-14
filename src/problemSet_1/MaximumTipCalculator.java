package problemSet_1;

import java.util.*;

// https://practice.geeksforgeeks.org/problems/maximum-tip-calculator/0

public class MaximumTipCalculator {
    public static void main(String[] a){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.valueOf(in.nextLine());
        int[] answers = new int[testCases];
        for(int t = 0; t < testCases; t++){
            String[] s = in.nextLine().split(" ");
            int numberOfCustomer = Integer.valueOf(s[0]);
            int noOfCustRahulCanHandle = Integer.valueOf(s[1]);
            int noOfCustAnkitCanHandle = Integer.valueOf(s[2]);

            s = in.nextLine().split(" ");
            String[] ankitTips = in.nextLine().split(" ");

            Customer[] arr = new Customer[numberOfCustomer];
            for(int i = 0; i < numberOfCustomer; i++){
                arr[i] = new Customer(Integer.valueOf(s[i]), Integer.valueOf(ankitTips[i]),
                        Integer.valueOf(s[i]) - Integer.valueOf(ankitTips[i]));
            }

            // Sorting customers in descending order of difference in tips,
            // so that all the customers who give tip more to Rahul, would be available first in the array
            // and all the customers who give more tip to Ankit, would be available in last in the array
            Arrays.sort(arr, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    int result =  -o1.diffInTips.compareTo(o2.diffInTips);
                    return result == 0 ? 1 : result;
                }
            });

            int startIndex = 0;
            int endIndex = arr.length -1;
            int result =0;

            // Here we have to prioritise the customer based on the difference of tips given to Rahul and Ankit.
            // We will pick the customer first whose difference in tips in highest
            while(startIndex <= endIndex){

                // We have the customer at startIndex of array whose difference in tip is highest
                // Customer gives tip to Rahul more than Ankit
                // OR
                // All the customer, who are not handled yet, gives more tip to Rahul than that of Ankit
                if(noOfCustRahulCanHandle >0 &&
                        ((arr[startIndex].diffInTips > -arr[endIndex].diffInTips) || (arr[endIndex].diffInTips > 0)) ){
                    result += arr[startIndex].tipForRahul;
                    noOfCustRahulCanHandle --;
                    startIndex++;
                    continue;
                }

                // We have the customer at endIndex of array whose difference in tip is highest
                // Customer gives tip to Ankit more than Rahul
                // OR
                // All the customer, who are not handled yet, gives more tip to Ankit than that of Rahul
                if(noOfCustAnkitCanHandle >0 &&
                        ((arr[startIndex].diffInTips < -arr[endIndex].diffInTips) || (arr[startIndex].diffInTips <0))){
                    result += arr[endIndex].tipForAnkit;
                    noOfCustAnkitCanHandle --;
                    endIndex --;
                    continue;
                }

                // At this stage, we left with the customer who give equal tip to both Rahul and Ankit
                // OR
                // either of noOfCustRahulCanHandle or noOfCustAnkitCanHandle = 0
                // In such situation, anyone (Rahul or Ankit) left with his own capacity to handle the customer, can handle the customer

                if(noOfCustRahulCanHandle > 0){
                    result += arr[startIndex].tipForRahul;
                    noOfCustRahulCanHandle --;
                    startIndex++;
                    continue;
                }

                result += arr[endIndex].tipForAnkit;
                noOfCustAnkitCanHandle --;
                endIndex --;
                continue;

            }
            answers[t] = result;
        }

        for(int i : answers)
            System.out.println(i);
    }

    private static class Customer{
        private Integer tipForRahul;
        private Integer tipForAnkit;
        private Integer diffInTips;  // Rahul's tip (-) Ankit's tip

        Customer(Integer tipForRahul, Integer tipForAnkit, Integer diffInTips){
            this.tipForRahul = tipForRahul;
            this.tipForAnkit = tipForAnkit;
            this.diffInTips = diffInTips;
        }
    }
}
