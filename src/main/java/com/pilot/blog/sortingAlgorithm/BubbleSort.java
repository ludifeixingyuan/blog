package com.pilot.blog.sortingAlgorithm;

/**
 * 冒泡排序
 *
 * @author wangzongbin
 * @date 2022-03-09
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] target = {55, 200, 1, 2, 5, 9, 7, 66, 88};
        int[] expect = {1, 2, 5, 7, 9, 55, 66, 88, 200};
        int[] res = bubbleSort(target);
        SortUtil.judge(res, expect);
    }

    private static int[] bubbleSort(int[] target) {
        for (int i = target.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (target[j] > target[j + 1]) {
                    int temp = target[j];
                    target[j] = target[j + 1];
                    target[j + 1] = temp;
                }
            }
        }

        return target;
    }
}
