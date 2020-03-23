package sort;

import java.util.Random;

/**
 * Copyright (C), 上海秦苍信息科技有限公司
 * <p>
 * <类描述>
 *
 * @author wub
 * @version CommonUtil, v1.0 2020/3/20 18:01
 */
public class CommonUtil {



    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] generateArr(int num){
        int[] arr = new int[num];
        Random random = new Random();
        for(int i=0;i<arr.length;i++){
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

}
