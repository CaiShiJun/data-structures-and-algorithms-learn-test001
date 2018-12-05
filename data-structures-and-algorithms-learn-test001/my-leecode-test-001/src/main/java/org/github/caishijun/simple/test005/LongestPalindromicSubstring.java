package org.github.caishijun.simple.test005;


/*
来源网址：https://leetcode-cn.com/problems/longest-palindromic-substring/

5. 最长回文子串

给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"
 */
public class LongestPalindromicSubstring {

    // start 自己实现的答案
    public static String longestPalindromeMyAnswer(String s) {
        String result = "";

        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i; j < s.length(); j++) {
                String temp = s.substring(i, j);
                String palindromicEvenStr = getPalindromicEven(temp);
                String palindromicOddStr = getPalindromicOdd(temp);

                if (palindromicEvenStr.length() > result.length() && s.contains(palindromicEvenStr)) {
                    result = palindromicEvenStr;

                    //因为偶数回文比奇数回文字符串长度多1，所以偶数回文命中之后，就不需要判断同字符串的奇数回文了，所以用 else if 判断奇数回文。
                }else if(palindromicOddStr.length() > result.length() && s.contains(palindromicOddStr)){
                    result = palindromicOddStr;
                }
            }
        }
        return result;
    }

    /**
     * 返回奇数回文     abcde  > abcdedcba
     * @param str
     * @return
     */
    public static String getPalindromicOdd(String str) {
        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(chars);

        for (int i = chars.length - 2; i >= 0; i--) {
            stringBuilder.append(chars[i]);
        }
        return stringBuilder.toString();
    }

    /**
     * 返回偶数回文     abcde  > abcdeedcba
     * @param str
     * @return
     */
    public static String getPalindromicEven(String str) {
        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(chars);

        for (int i = chars.length - 1; i >= 0; i--) {
            stringBuilder.append(chars[i]);
        }
        return stringBuilder.toString();
    }
    // end 自己实现的答案


    //方法四：中心扩展算法 start
    public static String longestPalindromeOfficialAnswer_004(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenterOfficialAnswer_004(s, i, i);
            int len2 = expandAroundCenterOfficialAnswer_004(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenterOfficialAnswer_004(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
    //方法四：中心扩展算法 end









    public static void main(String[] args) {
        String str1 = "babad";
        String str2 = "cbbd";
        String str3 = "dfasdgweafdasdfggfdsasafqewdasdfsadwerwq";

        long startNanoTime = System.nanoTime();
        //String result1 =  longestPalindromeMyAnswer(str1);
        //String result2 =  longestPalindromeMyAnswer(str2);
        //String result3 =  longestPalindromeMyAnswer(str3);      //endNanoTime - startNanoTime:5789785

        String result1 = longestPalindromeOfficialAnswer_004(str1);
        String result2 = longestPalindromeOfficialAnswer_004(str2);
        String result3 = longestPalindromeOfficialAnswer_004(str3);     //endNanoTime - startNanoTime:72122
        long endNanoTime = System.nanoTime();

        System.out.println("endNanoTime - startNanoTime:" + (endNanoTime - startNanoTime));
        System.out.println("result1:" + result1);
        System.out.println("result2:" + result2);
        System.out.println("result3:" + result3);

    }

}
