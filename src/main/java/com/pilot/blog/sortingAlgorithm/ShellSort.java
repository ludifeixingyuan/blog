package com.pilot.blog.sortingAlgorithm;

/**
 * 希尔排序
 *
 * @author wangzongbin
 * @date 2022-03-09
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] target = {55, 200, 1, 2, 5, 9, 7, 66, 88};
        int[] expect = {1, 2, 5, 7, 9, 55, 66, 88, 200};
        int[] res = shellSort(target);
        SortUtil.judge(res, expect);
    }

    private static int[] shellSort(int[] target) {


        return target;
    }
}
