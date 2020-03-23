package sort;

import java.util.Arrays;

import static sort.CommonUtil.swap;
import static sort.CommonUtil.generateArr;


public class HeapSort {

    public static void adjustHeap(int[] arr,int i,int end){
        int left = i * 2;
        while(left < end){
            int childVal = arr[left];
            int right = left+1;
            if(right < end && arr[right] > arr[left]){
                childVal = arr[right];
                left = right;
            }
            if(arr[i] < childVal){
                swap(arr,i,left);
            }else break;
            i = left;
            left = i*2;
        }
    }

    public static void sort(int[] arr){
        for(int i=arr.length-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }
        for(int i=arr.length-1;i>0;i--){
            swap(arr,i,0);
            adjustHeap(arr,0,i);
        }
    }

    public static void main(String[] args) {
        int[] arr = generateArr(10);
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
