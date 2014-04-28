package misc;

import java.util.Arrays;

public class FindKthMin {
    private static void exchange(int[] givenArray, int firstIndex, int secondIndex) {
        final int tempElem = givenArray[firstIndex];
        givenArray[firstIndex] = givenArray[secondIndex];
        givenArray[secondIndex] = tempElem;
    }

    private static int partition(int[] givenArray, int start, int end, int pivotIndex) {
        final int pivot = givenArray[pivotIndex];
        int left = start;
        int right = end;
        while (true) {
            while (left < pivotIndex && givenArray[left] <= pivot) {
                left++;
            }

            while (right > pivotIndex && givenArray[right] > pivot) {
                right--;
            }

            if ((left >= pivotIndex - 1) && (right <= pivotIndex + 1)) {
                break;
            }
            exchange(givenArray, right, left);
        }
        return pivotIndex;
    }

    private static int findKthMin_RanP_Helper(int[] givenArray, int start, int end, int k) {
        if (start > end) {
            return -1;
        }
        // Generate a random num in the range[start, end].
        final int rand = (int) (start + Math.random() * (end - start + 1));
        // Using this random num as the pivot index to partition the array in the current scope.
        final int split = partition(givenArray, start, end, rand);
        if (k == split + 1) {
            return givenArray[split];
        } else if (k < split + 1) {
            return findKthMin_RanP_Helper(givenArray, start, split - 1, k);
        } else {
            return findKthMin_RanP_Helper(givenArray, split + 1, end, k);
        }
    }

    public static int findKthMin_RanP(int[] givenArray, int k) {
        final int size = givenArray.length;
        if (k < 1 || k > size) {
            return -1;
        }
        return findKthMin_RanP_Helper(givenArray, 0, size - 1, k);
    }


    // Main method to test.
    public static void main(String[] args) {
        // Test data: {8, 9, 5, 2, 8, 4}.
        final int[] givenArray = {1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1};

        Arrays.sort(givenArray);
        System.out.println(Arrays.toString(givenArray));
        // Test finding the Kth min elem by randomized pivot method.
        final int kth = 3;
        System.out.println("Test finding the " + kth + " min elem by randomized pivot method, rest = "
                + findKthMin_RanP(givenArray, kth));
    }
}
