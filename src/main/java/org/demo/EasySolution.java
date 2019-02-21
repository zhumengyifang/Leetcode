package org.demo;

import Model.ListNode;

import java.util.*;

public class EasySolution {

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
    public static int arithmetic_KMP(String haystack, String needle) {
        int index = -1;   //正确匹配的开始索引
        int[] tableValue = getPartialMatchTable(needle);
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
    private static int[] getPartialMatchTable(String str) {
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

    //移除元素
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int len = nums.length;
        while (i < len) {
            if (nums[i] == val) {
                nums[i] = nums[--len];
            } else {
                i++;
            }
        }
        return len;
    }

    //删除链表重复项
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * public int val;
     * public ListNode next;
     * public ListNode(int x) { val = x; }
     * }
     */
    //合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(0);
        ListNode d = p;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                d.next = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                d.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            d = d.next;
        }

        if (l1 != null) {
            d.next = l1;
        } else if (l2 != null) {
            d.next = l2;
        }
        return p.next;
    }

    //最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        for (int i = 0; i < strs[0].length(); ) {
            boolean p = true;
            char msg = strs[0].charAt(i);
            for (int k = 1; k < strs.length; k++) {
                if (strs[k].length() == 0) return "";
                if (strs[k].length() - 1 < i) p = false;
                p = p && strs[k].charAt(i) == msg;
            }
            i++;
            if (!p || strs[0].length() - 1 < i) return strs[0].substring(0, p ? i : i - 1);
        }
        return "";
    }

    //搜索插入位置
    public int searchInsert(int[] nums, int target) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;

            if (nums[i] < target) index++;

            if (nums[i] > target) return index;
        }
        return index;
    }

    //最大子序和
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum > 0)
                sum += nums[i];
            else
                sum = nums[i];
            res = res > sum ? res : sum;
        }
        return res;
    }

    //最后一个单词的长度
    public int lengthOfLastWord(String s) {
        return s.trim().split(" ")[s.trim().split(" ").length - 1].length();
    }

    //加一
    public int[] plusOne(int[] digits) {
        boolean p = true;
        for (int i = digits.length - 1; i >= 0 && p; i--) {
            if (digits[i] == 9) {
                p = true;
                digits[i] = 0;
                if (i == 0) {
                    int[] res = new int[digits.length + 1];
                    res[0] = 1;
                    System.arraycopy(digits, 0, res, 1, digits.length);
                    return res;
                }
            } else {
                p = false;
                digits[i] += 1;
            }
        }
        return digits;
    }

    /**
     * 二进制求和
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();
        StringBuilder result = new StringBuilder();
        int temp = 0;
        for (int i = 0; i < Math.max(a.length(), b.length()); i++) {
            int x = (i >= a.length()) ? 0 : Integer.valueOf(String.valueOf(a.charAt(i)));
            int y = (i >= b.length()) ? 0 : Integer.valueOf(String.valueOf(b.charAt(i)));
            int sum = x + y + temp;
            temp = sum / 2;
            result.append(String.valueOf(sum % 2));
        }
        if (temp == 1)
            result.append(String.valueOf(temp));
        return result.reverse().toString();
    }

    //x的平方根
    //神奇的 0x5f3759df
    public int mySqrt(int x) {
        long t = x;
        t = 0x5f3759df - (t >> 1);
        while (!(t * t <= x && (t + 1) * (t + 1) > x))
            t = (x / t + t) / 2;
        return (int) t;
    }

    //爬楼梯
    //假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    //每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    //本质就是斐波那契数列
    public int climbStairs(int n) {
        if (n == 1 || n == 0) return 1;
        else if (n == 2) return 2;
        else {
            int f1 = 1;
            int f2 = 2;
            int f3 = 0;
            for (int i = 2; i < n; i++) {
                f3 = f2 + f1;
                f1 = f2;
                f2 = f3;
            }
            return f3;
        }
    }

    //删除排序列表的重复元素
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    //删除未排序的List
    public ListNode deleteDuplicates(ListNode head, boolean continuous) {
        if (continuous) {
            return deleteDuplicates(head);
        } else {
            if (head == null) return head;
            Set<Integer> set = new HashSet<Integer>();
            set.add(head.val);
            ListNode next = head;
            while (next != null && next.next != null) {
                if (!set.add(next.next.val)) {
                    next.next = next.next.next;
                } else {
                    next = next.next;
                }
            }
            return head;
        }
    }



}

