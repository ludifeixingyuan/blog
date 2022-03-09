package com.pilot.blog.sortingAlgorithm;

/**
 * 插入排序
 *
 * @author wangzongbin
 * @date 2022-03-09
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] target = {200, 55, 1, 2, 5, 9, 7, 66, 88};
        int[] expect = {1, 2, 5, 7, 9, 55, 66, 88, 200};
        int[] res = insertionSort(target);
        SortUtil.judge(res, expect);
    }

    private static int[] insertionSort(int[] a) {
        int i, j, k;
        for (i = 1; i < a.length; i++) {
            int temp = a[i];
            for (j = i - 1; j >= 0; j--) {
                if (a[i] > a[j])
                    break;
            }

            for (k = i - 1; k > j; k--) {
                a[k+1] = a[k];
            }
            a[j+1] = temp;
        }

        return a;
    }
}
