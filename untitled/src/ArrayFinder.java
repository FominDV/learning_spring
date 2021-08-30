import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ArrayFinder {

    public static void main(String[] args) {
        int[] arr = {3,1};
        System.out.println(new ArrayFinder().findAboutNumber(arr));
    }

    private int findAboutNumber(int[] arr) {
        Arrays.sort(arr);
        int index = Arrays.binarySearch(arr, 10);
        if (index > 0) {
            return arr[index];
        }
        index = -index - 1;
        return arr[index] - 10 < 10 - arr[index - 1] ? arr[index] : arr[index - 1];
    }

}
