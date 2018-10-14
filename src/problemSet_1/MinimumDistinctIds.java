package problemSet_1;

import java.util.*;

/**
 * Created by Prakash on 16-05-2018.
 */
/*

https://practice.geeksforgeeks.org/problems/minimum-distinct-ids/0

Given an array of items, an i-th index element denotes the item id’s and given a number m, the task is to remove m elements such that there should be minimum distinct id’s left.Print the number of distinct id’s.

        Input:
        The first line of the input contains a single integer T, denoting the number of test cases. Then T test case follows, the three lines of the input, the first line contains N, denoting number of elements in an array,second line contains N elements/ids, and third line contains the number M.

        Output:
        For each test case, print the minimum number of distinct ids.

        Constraints:
        1<=T<=100
        1<=N<=100
        1<=arr[i]<=10^6
        1<=M<=100

        Example:
        Input:
        2
        6
        2 2 1 3 3 3
        3
        8
        2 4 1 5 3 5 1 3
        2
        Output:
        1
        3
*/

public class MinimumDistinctIds {
    public static void main(String [] a){

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int testCases = Integer.valueOf(s.split(" ")[0]);
        int[] answers = new int[testCases];
        for(int i = 0; i < testCases; i++){
            s = in.nextLine();
            int numberOfElements = Integer.valueOf(s.split(" ")[0]);
            s = in.nextLine();
            String[] s1 = s.split(" ");
            int[] elements = new int[numberOfElements];
            for(int j = 0; j < numberOfElements; j++)
                elements[j] = Integer.valueOf(s1[j]);
            int numOfElementToRemove = Integer.valueOf(in.nextLine().split(" ")[0]);

            // Store element and it's frequency
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(Integer e : elements){
                if(map.containsKey(e))
                    map.put(e, map.get(e) +1);
                else
                    map.put(e, 1);
            }

            // sort Nodes in order of frequency
            Set<Node> set = new TreeSet<Node>();
            for(Map.Entry<Integer, Integer> e : map.entrySet())
                set.add(new Node(e.getKey(), e.getValue()));

            int k = 0;
            for(Node node : set){
                if(numOfElementToRemove > 0 && node.frequency <= numOfElementToRemove){
                    numOfElementToRemove = numOfElementToRemove - node.frequency;
                    k++;
                } else
                    break;
            }

            answers[i] = set.size() - k;
        }

        for(Integer i : answers)
            System.out.println(i);

    }

    private static class Node implements Comparable<Node>{
        Integer element;
        Integer frequency;
        Node(Integer element, Integer frequency){
            this.element = element;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node o) {
            int result =  this.frequency.compareTo(o.frequency);
            return result != 0 ? result : 1;
        }
    }


}

// Time Complexity = O(n)
// Space Complexity = O(2n)