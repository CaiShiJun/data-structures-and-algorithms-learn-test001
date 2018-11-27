package org.github.caishijun.simple.test003;

/*
3. 无重复字符的最长子串

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    // start 自己实现的答案
    public static int lengthOfLongestSubstringMyAnswer(String str) {
        String longestSubstring = "";

        for (int i = 0; i < str.length() - 1; i++) {
            for (int j = i; j < str.length() - 1; j++) {
                String substring = str.substring(i, j+1);
                Set tempSet = new HashSet();
                char[] chars = substring.toCharArray();
                for (char c : chars) {
                    tempSet.add(c);
                }
                if (chars.length == tempSet.size() && substring.length() > longestSubstring.length()) {
                    longestSubstring = substring;
                }
            }
        }
        return longestSubstring.length();
    }
    // end 自己实现的答案


    /*
    方法一：暴力法
        思路
            逐个检查所有的子字符串，看它是否不含有重复的字符。
        算法
            假设我们有一个函数 boolean allUnique(String substring) ，如果子字符串中的字符都是唯一的，它会返回true，否则会返回false。 我们可以遍历给定字符串 s 的所有可能的子字符串并调用函数 allUnique。 如果事实证明返回值为true，那么我们将会更新无重复字符子串的最大长度的答案。
        现在让我们填补缺少的部分：
            1、为了枚举给定字符串的所有子字符串，我们需要枚举它们开始和结束的索引。假设开始和结束的索引分别为 i 和 j。那么我们有 0 ≤ i < j ≤ n （这里的结束索引 j 是按惯例排除的）。因此，使用 i 从0到 n - 1 以及 j 从 i+1 到 n 这两个嵌套的循环，我们可以枚举出 s 的所有子字符串。
            2、要检查一个字符串是否有重复字符，我们可以使用集合。我们遍历字符串中的所有字符，并将它们逐个放入 set 中。在放置一个字符之前，我们检查该集合是否已经包含它。如果包含，我们会返回 false。循环结束后，我们返回 true。
     */
    public static int lengthOfLongestSubstringOfficialAnswer_001(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUniqueOfficialAnswer_001(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public static boolean allUniqueOfficialAnswer_001(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

    //方法二：滑动窗口
    public static int lengthOfLongestSubstringOfficialAnswer_002(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    //方法三：优化的滑动窗口
    public static int lengthOfLongestSubstringOfficialAnswer_003(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        String st1 = "abcabcbb";
        String st2 = "bbbbb";
        String st3 = "pwwkew";

        long startNanoTime = System.nanoTime();
        /*
        int result1 = lengthOfLongestSubstringMyAnswer(st1);
        int result2 = lengthOfLongestSubstringMyAnswer(st2);
        int result3 = lengthOfLongestSubstringMyAnswer(st3);        //endNanoTime - startNanoTime:510407
        */
        /*
        int result1 = lengthOfLongestSubstringOfficialAnswer_001(st1);
        int result2 = lengthOfLongestSubstringOfficialAnswer_001(st2);
        int result3 = lengthOfLongestSubstringOfficialAnswer_001(st3);        //endNanoTime - startNanoTime:578923
        */
        /*
        int result1 = lengthOfLongestSubstringOfficialAnswer_002(st1);
        int result2 = lengthOfLongestSubstringOfficialAnswer_002(st2);
        int result3 = lengthOfLongestSubstringOfficialAnswer_002(st3);        //endNanoTime - startNanoTime:477952
        */

        int result1 = lengthOfLongestSubstringMyAnswer(st1);
        int result2 = lengthOfLongestSubstringMyAnswer(st2);
        int result3 = lengthOfLongestSubstringMyAnswer(st3);        //endNanoTime - startNanoTime:621919

        long endNanoTime = System.nanoTime();

        System.out.println("endNanoTime - startNanoTime:" + (endNanoTime - startNanoTime));
        System.out.println("result1:" + result1);
        System.out.println("result2:" + result2);
        System.out.println("result3:" + result3);
    }
}