class Solution {
    /**
     * This algorithm measures the amount of unique elements in a list.
     * Once a unique element is found it will be placed at the index of
     * uniqueCount in the array.
     *
     * Time complexity of O(N)
     * Space complexity of S(1)
     *
     * @param nums
     * @return size of array with unique elements only
     */
    public int removeDuplicates(int[] nums) {
        // Check if the array is empty
        if (nums.length == 0) {
            return 0; // If empty, there are no unique elements
        }

        int uniqueCount = 1;  // At least one unique element (the first element)

        // Iterate through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // Check if the current element is different from the previous one
            if (nums[i] != nums[i - 1]) {
                // Found a new unique element
                nums[uniqueCount] = nums[i]; // Place the unique element in the next position
                uniqueCount++; // Increment the count of unique elements
            }
        }

        // uniqueCount represents the number of unique elements in the modified array
        return uniqueCount;
    }

    /**
     *This algorithm handles the case where the last digit is 9 by iterating backward through the array,
     * finding the first non-9 digit (or reaching the beginning if all digits are 9).
     * If all digits are 9, it creates a new array with an extra digit at the beginning.
     * Otherwise, it increments the first non-9 digit found.
     * If the last digit is not 9, it simply increments it by 1
     *
     * Time Complexity: O(n), where n is the length of the input array digits.
     * In the worst case, when all digits are 9, the algorithm needs to iterate through the entire
     * array to find the first non-9 digit or reach the beginning of the array.
     *
     * Space Complexity: O(1) for the in-place modification of the digits array.
     * The algorithm doesn't use any additional data structures that scale with the input size
     *
     * @param digits
     * @return a number represented as an array which has been incremented by one
     */
    public int[] plusOne(int[] digits) {

        int n = digits.length;

        // Check if the last digit is 9
        if (digits[n - 1] == 9) {
            // Iterate from the end of the array to find the first non-9 digit
            int i = n - 1;
            while (i >= 0 && digits[i] == 9) {
                digits[i] = 0; // Set the current digit to 0
                i--;
            }

            // If all digits were 9, we need to add an extra digit at the beginning
            if (i == -1) {
                int[] result = new int[n + 1];
                result[0] = 1; // Set the first digit to 1
                return result;
            } else {
                digits[i]++; // Increment the non-9 digit
            }
        } else {
            // The last digit is not 9, so simply add 1 to it
            digits[n - 1]++;
        }

        return digits;
    }



    public static void main(String[] args) {
        int [] test = {2,8,9,9};
        Solution s = new Solution();
        //int out = s.removeDuplicates(test);
        //System.out.println(out);
        int[] out = s.plusOne(test);
        for(int x: out){
            System.out.println(x);
        }
    }
}