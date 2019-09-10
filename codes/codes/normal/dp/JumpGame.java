package codes.normal.dp;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 */
public class JumpGame {


    /**
     * 回溯+递归
     */
    public boolean canJump1(int[] nums) {
        return canJumpByIndex(0,nums);
    }

    private boolean canJumpByIndex(int position,int[] nums){
        if(position == nums.length - 1){
            return true;
        }
        //获取所有能到达的位置,判断是否能到达最后位置
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for(int i=position+1;i<=furthestJump;i++){
            if(canJumpByIndex(i,nums)){
                return true;
            }
        }
        return false;
    }


    /**
     * 自顶向下动态规划
     */
    public boolean canJump2(int[] nums) {

        boolean[] canArrive = new boolean[nums.length];
        canArrive[nums.length - 1] = true;
        return canJumpByIndex(0,nums,canArrive);
    }

    private boolean canJumpByIndex(int position,int[] nums,boolean[] canArrive){
        if(canArrive[position]){
            return true;
        }
        //获取所有能到达的位置,判断是否能到达最后位置
        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for(int i=position+1;i<=furthestJump;i++){
            if(canJumpByIndex(i,nums,canArrive)){
                canArrive[position] = true;
                return true;
            }
        }
        return false;
    }


    /**
     * 自底向上动态规划
     */
    public boolean canJump3(int[] nums) {
        boolean[] canArrive = new boolean[nums.length];
        canArrive[nums.length - 1] = true;
        for(int i=nums.length-2;i>=0;i--){
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for(int j=i+1;j<=furthestJump;j++){
                if(canArrive[j]){
                    canArrive[i] = true;
                    break;
                }
            }
        }
        return canArrive[0];
    }



    public static void main(String[] args) {
        System.out.println(new JumpGame().canJump3(new int[]{3,2,1,0,4}));
    }

}
