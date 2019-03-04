package org.demo;

public class MediumSolution {
	
	 //反转字符串里的单词
        public static String reverseWords(String s)
        {
            String[] splitMsg = s.Trim().Split(" ");
            StringBuilder sb = new StringBuilder();
            string[] resultMsg = new String[splitMsg.Length];
            for (int i = splitMsg.Length - 1; i >= 0; i--)
            {
                if (String.IsNullOrEmpty(splitMsg[i])) continue;
                sb.Append(splitMsg[i] + " ");
            }
            return sb.ToString().Trim();
        }
		
		 //查找峰值，峰值大于左右两边数
        public int FindPeakElement(int[] nums)
        {
            int l = 0, r = nums.Length - 1;
            while (l <= r)
            {
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
