package org.demo;

public class MediumSolution {

    //反转字符串里的单词
    public static String reverseWords(String s) {
        String[] splitMsg = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        String[] resultMsg = new String[splitMsg.length];
        for (int i = splitMsg.length - 1; i >= 0; i--) {
            if (splitMsg[i].equals("")) continue;
            sb.append(splitMsg[i] + " ");
        }
        return sb.toString().trim();
    }

    //查找峰值，峰值大于左右两边数
    public int FindPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if ((mid <= l || nums[mid] > nums[mid - 1]) &&
                    (mid >= r || nums[mid] > nums[mid + 1]))
                return mid;
            if (mid <= l || nums[mid] > nums[mid - 1])
                l = mid + 1;
            else
                r = mid - 1;
        }
        return -1;
    }
}
