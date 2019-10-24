package codes.simple;

/**
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 */
public class Sqrtx {

    public int mySqrt(int x) {
        int begin = 0,end = x;
        while (begin < end){
            int mid = (begin+end)/2;
            if(mid == 0)return 1;
            if(mid == begin || x/mid == mid){
                return mid;
            }
            if(x/mid > mid){
                begin = mid;
            }else {
                end = mid;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Sqrtx sqrtx = new Sqrtx();
        System.out.println(sqrtx.mySqrt(173107));
        for(int i=0;i<Integer.MAX_VALUE;i++){
            int value = (int)Math.sqrt(i);
            if(sqrtx.mySqrt(i) != value){
                System.out.println(i+":"+sqrtx.mySqrt(i)+":"+value);
            }
        }

    }

}
