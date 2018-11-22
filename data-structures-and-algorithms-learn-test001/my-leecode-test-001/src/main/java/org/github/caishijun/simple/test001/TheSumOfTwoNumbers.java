package org.github.caishijun.simple.test001;

/*
1. 两数之和

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的 两个 整数。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]



来源网址：https://leetcode-cn.com/problems/two-sum/solution/
*/
public class TheSumOfTwoNumbers {

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i == j) {
                    continue;
                } else if (nums[i] + nums[j] == 9) {
                    return new int[] {nums[i], nums[j]};
                }
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 5, 8, 0, 3,1, 5, 2, 7, 3,1, 8, 2,};
        int target = 9;

        int[] result = twoSum(nums, target);

        for (int i = 0; i < result.length; i++) {
            System.out.println("result" + i + ":" + result[i]);
        }
    }
}
