package sort;

import java.util.Arrays;

import static sort.CommonUtil.generateArr;
import static sort.CommonUtil.swap;


public class MergeSort {

    public static void helper(int[] arr,int start,int end){
        if(start == end)return;
        int mid = (start+end)/2;
        helper(arr,start,mid);
        helper(arr,mid+1,end);
        insertSort(arr,start,mid,end);
    }

    private static void insertSort(int[] arr,int start,int mid,int end){
        for(int i=mid+1;i<=end;i++){
            for(int j=start;j<=end;j++){
                if(arr[i] < arr[j]){
                    int length = i-j;
                    int res = arr[i];
                    System.arraycopy(arr,j,arr,j+1,length);
                    arr[j] = res;
                    break;
                }
            }
        }
    }

    public static void sort(int[] arr){
        helper(arr,0,arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = generateArr(10);
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
