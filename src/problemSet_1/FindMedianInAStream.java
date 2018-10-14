package problemSet_1;
import java.util.Scanner;

public class FindMedianInAStream {
    public static void main(String[] a){
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int[] arr = new int[count];
        int[] median = new int[count];
        int endIndex = -1;
        int input;
        for(int i = 0; i < count; i++){
            input = in.nextInt();
            if(endIndex < 0) {
                arr[i] = input;
                median[i] = input;
                endIndex++;
                continue;
            }

            int [] n = getNearestIndexToInsert(arr, 0, endIndex, input);
            int nearestIndex = n[0];
            int indexToInsert = n[1] < 0 ? nearestIndex  : nearestIndex +1 ;
            for(int j = endIndex; j >= indexToInsert; j--)
                arr[j+1] = arr[j];
            endIndex++;
            arr[indexToInsert] = input;
            median[i] = getMedian(arr, endIndex);
        }
        for(int i : median)
            System.out.println(i);

    }

    // Returns an Array whose index 0 denotes nearest index to insert input
    // and if value of index 1 == -1, that means input should be inserted before nearest index
    // and if value of index 1 == 1, that means input should be inserted after nearest index
    private static int[] getNearestIndexToInsert(int[] arr, int startIndex, int endIndex, int input){
        if(startIndex > endIndex)
            return new int[]{startIndex -1, 1};

        if(endIndex - startIndex == 0){
            if(input < arr[startIndex])
                return new int[]{startIndex, -1};
            return new int[]{startIndex, +1};
        }

        int mid = startIndex + (endIndex - startIndex)/2;
        if(arr[mid] < input)
            return getNearestIndexToInsert(arr, mid +1, endIndex, input );
        else
            return getNearestIndexToInsert(arr, startIndex, mid -1, input);

    }

    private static int getMedian(int[] arr, int endIndex){
        if((endIndex +1)%2 != 0 )
            return arr[endIndex/2];
        return (arr[endIndex/2] + arr[endIndex/2 +1])/2;
    }
}


// Time complexity :- O(log n + n) for insertion of each element
// Space complexity :- O(1)