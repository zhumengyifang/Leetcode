package org.demo;

public class Sort {

    /**
     * 冒泡排序
     *
     * @param s
     * @return
     */
    public int[] bubbleSort(int[] s, int n) {
        int i = n - 1;  //初始时,最后位置保持不变
        while (i > 0) {
            int pos = 0; //每趟开始时,无记录交换
            for (int j = 0; j < i; j++)
                if (s[j] > s[j + 1]) {
                    pos = j; //记录交换的位置
                    int tmp = s[j];
                    s[j] = s[j + 1];
                    s[j + 1] = tmp;
                }
            i = pos; //为下一趟排序作准备
        }
        return s;
    }

    /**
     * 快速排序
     *
     * @param s
     * @param left  数组下标开始位置
     * @param right 数组下标结束位置
     * @return
     */
    public int[] quickSort(int[] s, int left, int right) {
        if (left > right) {
            return s;
        }
        int temp = s[left];
        int i = left, j = right, t = 0;
        while (i != j) {
            while (s[j] >= temp && i < j) j--;
            while (s[i] <= temp && i < j) i++;
            if (i < j) {
                t = s[i];
                s[i] = s[j];
                s[j] = t;
            }
        }
        s[left] = s[i];
        s[j] = temp;

        quickSort(s, left, i - 1);
        quickSort(s, i + 1, right);
        return null;
    }

    /**
     * 归并排序
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    public int[] mergeSort(int[] s, int left, int right) {
        int mid = (left + right) / 2;
        if (left < right) {
            mergeSort(s, left, mid);
            mergeSort(s, mid + 1, right);
            merge(s, left, mid, right);
        }
        return s;
    }

    private void merge(int[] s, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (s[i] < s[j]) temp[k++] = s[i]++;
            else temp[k++] = s[j++];
        }
        while (i <= mid) {
            temp[k++] = s[i++];
        }
        while (j <= mid) {
            temp[k++] = s[j++];
        }
        for (int k2 = 0; k2 < temp.length; k2++) {
            s[k2 + left] = temp[k2];
        }
    }

    /**
     * 折半插入排序
     *
     * @param s
     * @return
     */
    public int[] insertionSort(int[] s) {
        for (int i = 1; i < s.length; i++) {
            int left = 0, right = i - 1, temp = s[i];
            while (left <= right) {
                int mid = (left + right) / 2;
                if (s[mid] > temp) right = mid - 1;
                else left = mid + 1;
            }
            for (int j = i - 1; j >= left; j--) {
                s[j + 1] = s[j];
            }
            s[left] = temp;
        }
        return s;
    }

    /**
     * 希尔排序
     *
     * @param s
     * @return
     */
    public int[] shellSort(int[] s) {
        for (int i = s.length / 2; i > 0; i = i / 2) {
            for (int j = i; i < s.length; j++) {
                int temp = s[i];
                int k;
                for (k = j - i; k >= 0 && temp < s[k]; k = k - i) {
                    s[k - i] = s[k];
                }
                s[k + i] = temp;
            }
        }
        return s;
    }

    /**
     * 选择排序
     *
     * @param s
     * @param n
     */
    public int[] selectSort(int s[], int n) {
        int min = s[0];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) // 每次选出序列中最小的数
                if (s[j] < min)
                    min = s[j];
            s[i] = min; // 将每次选好的数放在正确的位置
        }
        return s;
    }

    /**
     * 二元选择排序
     *
     * @param s
     * @param n
     */
    public int[] selectSort2(int s[], int n) {
        int i, j, min, max, tmp;
        for (i = 1; i <= n / 2; i++) {
            // 做不超过n/2趟选择排序
            min = i;
            max = i; //分别记录最大和最小关键字记录位置
            for (j = i + 1; j <= n - i; j++) {
                if (s[j] > s[max]) {
                    max = j;
                    continue;
                }
                if (s[j] < s[min]) {
                    min = j;
                }
            }
            //该交换操作还可分情况讨论以提高效率
            tmp = s[i - 1];
            s[i - 1] = s[min];
            s[min] = tmp;
            tmp = s[n - i];
            s[n - i] = s[max];
            s[max] = tmp;
        }
        return s;
    }


}
