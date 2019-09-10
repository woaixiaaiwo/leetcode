package codes.normal;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPpermutation {

    public void nextPermutation(int[] nums) {

        int size = nums.length;
        if(size > 1){
            //先倒序找
            int last = size-1, next = last,prev = next - 1,index;
            while(prev >= 0 && nums[prev] >= nums[next]){
                prev --;
                next --;
            }
            if(prev < 0 ){
                reverse(nums,0,last);
            }else{
                index = next;
                while(index<=last && nums[index] > nums[prev]){
                    index ++;
                }
                index--;
                swap(nums,prev,index);
                reverse(nums,next,last);
            }
        }
    }

    private void reverse(int[] nums,int begin,int end){
        while(begin < end){
            swap(nums,begin++,end--);
        }
    }

    private void swap(int[] nums,int begin,int end){
        int tmp = nums[begin];
        nums[begin] = nums[end];
        nums[end] = tmp;
    }

    public static void main(String[] args) {
        NextPpermutation test = new NextPpermutation();
        int[] arr = new int[]{1,1,2,3};
        //test.nextPermutation(arr);
        for(int i=0;i<24;i++){
            test.nextPermutation(arr);
        }
    }

}
