package sort;

import java.util.Arrays;
import java.util.Random;

import static sort.CommonUtil.swap;
import static sort.CommonUtil.generateArr;

/**
 * 快速排序
 */
public class QuickSort {

    public static void sort(int[] arr){
        helper(arr,0,arr.length-1);
    }

    public static void helper(int[] arr,int start,int end){
        if(start >= end)return;
        int low = start,high = end;
        while (low < high){
            while(low < high && arr[high] >= arr[low]){
                high--;
            }
            swap(arr,low,high);
            while(low < high && arr[high] >= arr[low]){
                low++;
            }
            swap(arr,low,high);
        }
        helper(arr,start,low);
        helper(arr,low+1,end);
    }

    public static void main(String[] args) {
        int[] arr = generateArr(10);
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
