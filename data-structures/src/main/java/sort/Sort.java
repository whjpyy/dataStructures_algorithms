package sort;

import java.util.Arrays;

/**
 * 常用的排序算法
 *
 * @author carl.z.chen
 * @Date 2019/10/8
 */
public class Sort {

    /**
     * 冒泡排序
     */
    private static void bubbleSort(int[] arr) {

        int temp; // 临时变量

        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println("第" + (i + 1) + "轮遍历前的数组为：" + Arrays.toString(arr));
            // 从后面往前面冒泡
            for (int j = arr.length - 1; j > i; j--) {
                System.out.println("i=" + i + ",j = " + j + ",arr[j]=" + arr[j] + ",arr[j-1]=" + arr[j - 1]);
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "轮遍历后的数组为：" + Arrays.toString(arr));
        }
    }

    /**
     * 冒泡排序优化，如果hu
     *
     * @param arr
     */
    private static void bubbleSort2(int[] arr) {
        int temp; // 临时变量
        boolean flag; // 是否交换数据
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    flag = true;
                }
            }
            System.out.println("第" + (i + 1) + "轮遍历后的数组为：" + Arrays.toString(arr));
            // 如果比较完一轮后，没有任何交换，则说明数组本身是有序的，后面就不需要比较了
            if (!flag) {
                System.out.println("第" + (i + 1) + "轮遍历完后发现已经是有序的数组了.");
                break;
            }
        }
    }

    /**
     * 选择排序
     * 基本思想：
     * 在长度为N的无序数组中，第一次遍历n-1个数，找到最小的数值与第一个元素交换；
     * 第二次遍历n-2个数，找到最小的数值与第二个元素交换；
     * 。。。
     * 第n-1次遍历，找到最小的数值与第n-1个元素交换，排序完成。
     *
     * @param arr
     */
    private static void selectionSort(int[] arr) {

        int temp; // 临时变量

        for (int i = 0; i < arr.length; i++) {
            System.out.println("第" + (i + 1) + "轮遍历前的数组为：" + Arrays.toString(arr));
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
            System.out.println("第" + (i + 1) + "轮遍历后的数组为：" + Arrays.toString(arr) + "\n");
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     * @param length
     */
    private static void insertSort(int[] arr, int length) {
        int temp;
        for (int i = 0; i < length - 1; i++) {
            System.out.println("第" + (i + 1) + "轮遍历前的数组为：" + Arrays.toString(arr));
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    break;
                }
            }
            System.out.println("第" + (i + 1) + "轮遍历后的数组为：" + Arrays.toString(arr) + "\n");
        }
    }

    /**
     * 希尔排序
     * 前言：
     * 数据序列1： 13-17-20-42-28 利用插入排序，13-17-20-28-42. Number of swap:1;
     * 数据序列2： 13-17-20-42-14 利用插入排序，13-14-17-20-42. Number of swap:3;
     * 如果数据序列基本有序，使用插入排序会更加高效。
     * <p>
     * 基本思想：
     * 在要排序的一组数中，根据某一增量分为若干子序列，并对子序列分别进行插入排序。
     * 然后逐渐将增量减小,并重复上述过程。直至增量为1,此时数据序列基本有序,最后进行插入排序。
     *
     * @param array
     * @param lenth
     */
    private static void shellSort(int[] array, int lenth) {
        int temp = 0;
        int incre = lenth;

        while (true) {
            incre = incre / 2;

            //根据增量分为若干子序列
            for (int k = 0; k < incre; k++) {

                for (int i = k + incre; i < lenth; i += incre) {

                    for (int j = i; j > k; j -= incre) {
                        if (array[j] < array[j - incre]) {
                            temp = array[j - incre];
                            array[j - incre] = array[j];
                            array[j] = temp;
                        } else {
                            break;
                        }
                    }
                }
            }

            if (incre == 1) {
                break;
            }
        }
    }

    /**
     * 快速排序
     * <p>
     * 基本思想：（分治）
     * <p>
     * 先从数列中取出一个数作为key值；
     * 将比这个数小的数全部放在它的左边，大于或等于它的数全部放在它的右边；
     * 对左右两个小数列重复第二步，直至各区间只有1个数。
     *
     * 平均时间复杂度：O(N*logN
     *
     * @param a
     * @param l
     * @param r
     */
    public static void quickSort(int a[], int l, int r) {
        if (l >= r) {
            return;
        }

        int i = l;
        int j = r;
        //选择第一个数为key
        int key = a[l];

        while (i < j) {

            //从右向左找第一个小于key的值
            while (i < j && a[j] >= key) {
                j--;
            }
            if (i < j) {
                a[i] = a[j];
                i++;
            }

            //从左向右找第一个大于key的值
            while (i < j && a[i] < key) {
                i++;
            }
            if (i < j) {
                a[j] = a[i];
                j--;
            }
        }
        //i == j
        a[i] = key;
        //递归调用
        quickSort(a, l, i - 1);
        //递归调用
        quickSort(a, i + 1, r);
    }

    public static void main(String[] args) {
        int[] arr = {42, 20, 17, 13, 28, 14, 23, 15};
//        bubbleSort(arr);
//        bubbleSort2(arr);

//        selectionSort(arr);

//        insertSort(arr, 2);

//        shellSort(arr, arr.length);

        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
