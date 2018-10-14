package problemSet_1;

import java.util.ArrayList;
import java.util.List;

/**
 * https://practice.geeksforgeeks.org/problems/stock-buy-and-sell/0
 *
 * Created by Prakash on 22-08-2018.
 */
public class StockBuyAndSell {
    public static void main(String[] a) {
        int[] stocks = {23, 13, 25, 29, 33, 19, 34, 45, 65, 67};//{100, 180, 260, 310, 40, 535, 695};
        int buyPoint = 0;
        int buyIndex = -1;
        int salePoint = 0;
        int saleIndex = -1;
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < stocks.length; i++){
            if(buyPoint == 0){
                buyPoint = stocks[i];
                buyIndex = i;
                continue;
            }
            if(stocks[i] < buyPoint && salePoint == 0){
                buyPoint = stocks[i];
                buyIndex = i;
                continue;
            }
            if(stocks[i] < salePoint){
                int[] arr = {buyIndex, saleIndex};
                list.add(arr);
                salePoint = 0;
                saleIndex = -1;
                buyPoint = stocks[i];
                buyIndex = i;
                continue;
            }
            if(stocks[i] > salePoint){
                salePoint = stocks[i];
                saleIndex = i;
                continue;
            }
        }

        if(buyPoint != 0 && salePoint != 0) {
            int[] arr = {buyIndex,saleIndex};
            list.add(arr);
        }

        for(int[] ar : list){
            System.out.println(ar[0] +" "+ ar[1]);
        }
    }
}
