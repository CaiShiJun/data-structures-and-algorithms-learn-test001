package org.github.caishijun.simple.test004;


/*
4. 寻找两个有序数组的中位数

给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5
 */


public class MedianOfTwoSortedArrays {

    // start 自己实现的答案 TODO（ 未实现 ）
    public static double findMedianSortedArraysMyAnswer(int[] nums1, int[] nums2) {
        double sum = 0;
        double denominator = 0;

        int count = nums1.length + nums2.length;
        int calculationTimes = count / 2 + 1;
        int index_1 = 0;
        int index_2 = 0;
        while (calculationTimes != 0){
            int temp = 0;
            if(nums1[index_1]>nums2[index_2]){
                temp = nums1[index_1];
                if(nums2.length-1 == index_2){
                    index_1 ++;
                }else{
                    index_2 ++;
                }
            }else{
                temp = nums2[index_2];
                if(nums1.length-1 == index_1){
                    index_2 ++;
                }else{
                    index_1 ++;
                }
            }

            if(calculationTimes == 2 && count%2==0 || calculationTimes == 1){
                sum = sum + temp;
                denominator += 1;
            }
            calculationTimes--;
        }

        return sum/denominator;
    }
    // end 自己实现的答案


    //方法：递归法
    public static double findMedianSortedArraysOfficialAnswer_001(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }



    public static void main(String[] args) {
        int[] nums_a1 = {1,3};
        int[] nums_b1 = {2};

        int[] nums_a2 = {1,2};
        int[] nums_b2 = {3,4};

        int[] nums_a3 = {1,3,5,7,8,9,11,13,16};
        int[] nums_b3 = {3,4,5};

        long startNanoTime = System.nanoTime();
        double result1 =  findMedianSortedArraysMyAnswer(nums_a1,nums_b1);
        double result2 =  findMedianSortedArraysMyAnswer(nums_a2,nums_b2);
        double result3 =  findMedianSortedArraysMyAnswer(nums_a3,nums_b3);
        long endNanoTime = System.nanoTime();

        System.out.println("endNanoTime - startNanoTime:" + (endNanoTime - startNanoTime));
        System.out.println("result1:" + result1);
        System.out.println("result2:" + result2);
        System.out.println("result3:" + result3);

    }






}
