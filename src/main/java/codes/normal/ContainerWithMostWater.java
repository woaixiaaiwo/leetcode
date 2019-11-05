package codes.normal;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 使用双指针法
 * 1.i和j，小的指针向大的指针移动，如果相等，就取i向j移动
 * 2.如果移动时的值小于当前值，继续移动，直到碰到比当前值大的，停止
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int i=0,j=height.length-1;
        int max = 0;
        while (i < j){
            int h = Math.min(height[i],height[j]);
            max = Math.max(h*(j-i),max);
            if(height[i] <= height[j]){
                i++;
            }else {
                j--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new ContainerWithMostWater().maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

}
