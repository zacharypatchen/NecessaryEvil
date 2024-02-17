import java.util.Stack;

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
     * Space Complexity: S(1) for the in-place modification of the digits array.
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

    /**
     * This code uses a stack to reverse the digits of the number and then compares the reversed
     * number with the original number. If they match for every digit, the number is a palindrome.
     *
     * Time Complexity: O(log(x)), where x is the input number. The while loop that extracts digits
     * from the number runs until the number becomes 0, and in each iteration, we reduce the number
     * by a factor of 10. Therefore, the number of iterations is proportional to the number of digits
     * in the input, which is log(x) in base 10.
     *
     * Space Complexity: O(log(x)) as well. In the worst case, the stack will store all the digits of
     * the number, which is again proportional to the number of digits in the input.
     *
     * @param x - an integer
     * @return true if a number is a palindrome. Otherwise, false.
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false; // a negative number cannot be a palindrome
        }

        if (x < 10) {
            return true; // a single-digit number is a palindrome
        }

        Stack<Integer> stack = new Stack<>();
        int originalNumber = x;

        // Push each digit of the number onto the stack
        while (x != 0) {
            int digit = x % 10;
            stack.push(digit);
            x /= 10;
        }

        // Pop each digit from the stack and compare with the original number
        while (!stack.isEmpty()) {
            int digit = stack.pop();
            if (digit != originalNumber % 10) {
                return false; // If digits don't match, it's not a palindrome
            }
            originalNumber /= 10;
        }

        return true;
    }

    /**
     * This code takes advantage of the fact that each character represents a digit in base 26
     * (A corresponds to 1, B to 2, ..., Z to 26). It iterates through the characters, converts each
     * character to its corresponding value, and updates the result by multiplying it by 26 and adding
     * the new value.
     *
     * Time Complexity: O(n), where n is the length of the columnTitle string. The loop iterates
     * through each character in the string once, performing constant time operations for each character.
     *
     * Space Complexity: O(1), The algorithm uses a constant amount of space regardless of the input size.
     *
     * @return a numeric column value
     * @param columnTitle
     */
    public int titleToNumber(String columnTitle) {
        int result = 0;

        for (int i = 0; i < columnTitle.length(); i++) {
            char currentChar = columnTitle.charAt(i);
            int charValue = currentChar - 'A' + 1; // Convert character to its corresponding value

            result = result * 26 + charValue; // Multiply the current result by 26 and add the new value
        }

        return result;
    }

    public static void main(String[] args) {
        //nothing for now
    }
}