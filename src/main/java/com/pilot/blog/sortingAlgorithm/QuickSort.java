package com.pilot.blog.sortingAlgorithm;

/**
 * 快速排序
 *
 * @author wangzongbin
 * @date 2022-03-09
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] target = {55, 200, 1, 2, 5, 9, 7, 66, 88};
        int[] expect = {1, 2, 5, 7, 9, 55, 66, 88, 200};
        int[] res = quickSort(target, 0, target.length - 1);
        SortUtil.judge(res, expect);
    }

    private static int[] quickSort(int[] target, int left, int right) {
        if (left < right) {
            int i = left, j = right, temp = target[i];
            while (i < j) {
                while (i < j && target[j] > temp) {
                    j--;
                }
                if (i < j) {
                    target[i++] = target[j];
                }

                while (i < j && target[i] < temp) {
                    i++;
                }
                if (i < j) {
                    target[j--] = target[i];
                }
            }
            target[i] = temp;
            quickSort(target, left, i - 1);
            quickSort(target, i + 1, right);
        }

        return target;
    }
}
