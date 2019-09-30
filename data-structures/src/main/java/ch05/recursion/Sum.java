package ch05.recursion;

/**
 * @author carl.z.chen
 * @Date 2019/9/30
 */
public class Sum {

    private static int sum(int[] arr){
        return sum(arr, 0);
    }

    private static int sum(int[] arr, int l){
        if(l == arr.length){
            return 0;
        }else{
            return arr[l] + sum(arr, l + 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        System.out.println(sum(arr));
    }
}
