package com.pilot.blog.sortingAlgorithm;

import java.util.Arrays;

/**
 * 排序util
 *
 * @author wangzongbin
 * @date 2022-03-09
 */
public class SortUtil {

    public static void judge(int[] res, int[] expect) {
        if (res.length == expect.length) {
            for (int i = 0; i < res.length; i++) {
                if (res[i] != expect[i]) {
                    System.out.println("不符合预期，结果：" + Arrays.toString(res));
                    return;
                }
            }
        }
        System.out.println("符合预期，结果：" + Arrays.toString(res));
        return;
    }
}
