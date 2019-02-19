package org.demo;

import Model.ListNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * 判断x是否为回文数
     *
     * @param x 判断参数
     * @return 结果
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int number = 0;
        while (x > number) {
            number = number * 10 + x % 10;
            x /= 10;
        }
        return x == number || x == number / 10;
    }

    /**
     * 两数之和
     *
     * @param nums
     * @param target 目标值
     * @return 返回数组下标
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (hashMap.containsKey(complement)) {
                return new int[]{hashMap.get(complement), i};
            }
            hashMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 两数相加
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 相加结果
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode p = l1, q = l2, curr = result;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p == null) ? 0 : p.val;
            int y = (q == null) ? 0 : q.val;
            int sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return result.next;
    }


    /**
     * 两个数的最小公倍数
     * m * n / 最大公约数
     *
     * @return
     */
    public int getMinNumber(int m, int n) {
        int max = getMaxNumber(m, n);
        return m * n / max;
    }

    /**
     * 多个数的最小公倍数
     *
     * @param s
     * @return
     */

    public int getMinNumbers(int s[]) {
        int result = getMinNumber(s[0], s[1]);
        ;
        for (int i = 2; i < s.length; i++) {
            result = getMinNumber(result, s[i]);
        }
        return result;
    }

    /**
     * 最大公约数
     *
     * @return
     */
    public int getMaxNumber(int m, int n) {
        if (n > m) {
            int x = m;
            m = n;
            n = x;
        }

        int result = 0;
        while (n != 0) {
            result = m % n;
            m = n;
            n = result;
        }
        return m;
    }

    /**
     * 多个数的最大公约数
     *
     * @param s
     * @return
     */
    public int getMaxNumbers(int s[]) {
        int max = getMaxNumber(s[0], s[1]);
        for (int i = 2; i < s.length; i++) {
            max = getMaxNumber(max, s[i]);
        }
        return max;
    }


    /// <summary>
    /// KMP算法查找字符串
    /// </summary>
    /// <param name="haystack">操作字符串</param>
    /// <param name="needle">要查找的字符串</param>
    /// <returns>字符串第一次出现的位置索引</returns>
    public static int Arithmetic_KMP(String haystack, String needle) {
        int index = -1;   //正确匹配的开始索引
        int[] tableValue = GetPartialMatchTable(needle);
        int i = 0, j = 0; //操作字符串和匹配字符串 索引迭代
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) //当第一个字符匹配上，接着匹配第二、、、
            {
                if (j == 0) index = i;       //记录第一个匹配字符的索引
                j++;
                i++;
            } else  //当没有匹配上的时候
            {
                if (j == 0) //如果第一个字符就没匹配上
                {
                    i = i + j + 1 - tableValue[j]; //移动位数 =已匹配的字符数 - 对应的部分匹配值
                } else {
                    i = index + j - tableValue[j - 1]; //如果已匹配的字符数不为零，则重新定义i迭代
                }
                j = 0; //将已匹配迭代置为0
            }
        }
        return index;
    }

    /// <summary>
    /// 产生 部分匹配表
    /// </summary>
    /// <param name="str">要查找匹配的字符串</param>
    /// <returns></returns>
    private static int[] GetPartialMatchTable(String str) {
        String[] left, right; //前缀、后缀
        int[] result = new int[str.length()]; //保存 部分匹配表
        for (int i = 0; i < str.length(); i++) {
            left = new String[i]; //实例化前缀 容器
            right = new String[i]; //实例化后缀容器
            //前缀
            for (int j = 0; j < i; j++) {
                if (j == 0)
                    left[j] = String.valueOf(str.charAt(j));
                else
                    left[j] = left[j - 1] + str.charAt(j);
            }
            //后缀
            for (int k = i; k > 0; k--) {
                if (k == i)
                    right[k - 1] = String.valueOf(str.charAt(k));
                else
                    right[k - 1] = str.charAt(k) + right[k];
            }
            //找到前缀和后缀中相同的项，长度即为相等项的长度（相等项应该只有一项）
            int num = left.length - 1;
            for (int m = 0; m < left.length; m++) {
                if (right[num] == left[m]) {
                    result[i] = left[m].length();
                }
                num--;
            }
        }
        return result;
    }


}
