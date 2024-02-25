import java.util.Stack;

class Solution {
    /**
     * Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
     * Return the linked list sorted as well.
     *
     * Time Complexity: O(n), where n is the number of nodes in the linked list.
     * The algorithm iterates through the entire linked list once.
     * Space Complexity: O(1).
     * The algorithm uses a constant amount of extra space for the currentNode variable.
     *
     * @param head
     * @return head
     */
    public ListNode deleteDuplicateO(ListNode head) {
            ListNode currentNode = head; // assign head of list to member variable currentNode
            while(currentNode!=null && currentNode.next!=null){ // iterate up to the tail of the list
                if(currentNode.next.val == currentNode.val){ // if a node's value matches the next node's value we
                                                             // have found a duplicate
                    currentNode.next = currentNode.next.next; //  swap the successor node for duplicate
                                                              // (i.e the node after the dup)
                } else {
                    currentNode = currentNode.next; // normal traversal
                }
            }
            return head;
    }

    /**
     * This algorithm handles the case where the last digit is 9 by iterating backward through the array,
     * finding the first non-9 digit (or reaching the beginning if all digits are 9).
     * If all digits are 9, it creates a new array with an extra digit at the beginning.
     * Otherwise, it increments the first non-9 digit found.
     * If the last digit is not 9, it simply increments it by 1.
     *
     * Time Complexity: O(n), where n is the length of the input array digits.
     * In the worst case, when all digits are 9, the algorithm needs to iterate through the entire
     * array to find the first non-9 digit or reach the beginning of the array.
     *
     * Space Complexity: O(1) for the in-place modification of the digits array.
     * The algorithm doesn't use any additional data structures that scale with the input size.
     *
     * @param digits - an array representing a number
     * @return a new array representing the number obtained by incrementing the input array by one
     */
    public int[] plusOne(int[] digits) {

        int n = digits.length;
        for (int i = n-1; i >= 0; i--){ // traverse backwards through array starting at last index
            if(digits[i] <9){ // this handles digits less than 9 in which we simply increment the last digit of the
                digits[i]++;  // array if it is not equal to 9 (i.e [1,2,3] -> [1,2,4]) then return the array
                return digits; // or in the case where we have incremented 9 to 0 and are still looping
                               // i.e [1,2,0] -> [1,3,0]
            }
            digits[i] = 0; // if the digit is 9 we swap it with 0 and continue looping;
        }
        int [] newNumber = new int[n+1]; // in order to reach this condition our input must be all 9s. By this point
                                         // the array has been transformed to all 0s from the previous line of code
                                         // so, we must create a new array with size n+1 larger with its first digit
                                         // being 1. Example: [9, 9, 9] -> [0,0,0] -> [1,0,0,0]
        newNumber[0] =1;
        return newNumber;
    }

    /**
     * This algorithm uses a stack to reverse the digits of the number and then compares the reversed
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

        // push each digit of the number onto the stack
        while (x != 0) {
            int digit = x % 10;
            stack.push(digit);
            x /= 10;
        }

        // pop each digit from the stack and compare with the original number
        while (!stack.isEmpty()) {
            int digit = stack.pop();
            if (digit != originalNumber % 10) {
                return false; // if digits don't match, it's not a palindrome
            }
            originalNumber /= 10;
        }

        return true;
    }

    /**
     * This algorithm takes advantage of the fact that each character represents a digit in base 26
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
        int n = columnTitle.length();
        for (int i = 0; i < n; i++) {
            char currentChar = columnTitle.charAt(i);
            int charValue = currentChar - 'A' + 1; // convert character to its corresponding value

            result = result * 26 + charValue; // multiply the current result by 26 and add the new value
        }

        return result;
    }

    /**
     * This algorithm maintains a dummy node and a current pointer to keep track of the merged list.
     * It iterates through both lists, comparing the current nodes' values, and appending the smaller
     * one to the merged list. After the loop, it appends the remaining nodes from the non-empty list.
     * The merged list starts from the next of the dummy node.
     *
     * Time Complexity: O(m + n), where m and n are the lengths of the input lists list1 and list2
     *
     * Space Complexity: O(1) because the algorithm only uses a constant amount of extra space regardless
     * of the input size. The only additional memory used is for the dummy and current pointers,
     * which do not depend on the size of the input lists
     *
     * @param list1
     * @param list2
     * @return a merged list
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(); // dummy node to simplify code
        ListNode current = dummy; // pointer to the current node in the merged list

        // iterate through both lists until one of them becomes empty
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                // if the value in list1 is smaller, append it to the merged list
                current.next = list1;
                list1 = list1.next; // move to the next node in list1
            } else {
                // if the value in list2 is smaller or equal, append it to the merged list
                current.next = list2;
                list2 = list2.next; // move to the next node in list2
            }
            current = current.next; // move the current pointer to the newly appended node
        }

        // if one of the lists is not empty, append the remaining nodes
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return dummy.next; // return the merged list starting from the next of the dummy node

    }

    /**
     * In this simplified algorithm, a dummy node is added at the start to make the code cleaner.
     * The first and second pointers begin at the dummy node. The first pointer advances by n+1 steps,
     * creating a gap of n nodes. Both pointers move together until the first one reaches the end of the list.
     * At this point, the second pointer is right before the node to be removed. The n-th node is removed
     * by updating the next pointer of the second node to skip it.
     *
     * Time Complexity: O(N), where N is the number of nodes in the linked list.
     *
     * Space Complexity:  O(1) since it uses a constant amount of extra space regardless of the input size.
     *
     * @param head
     * @param n the nth node from the end of list to be removed
     * @return the modified list, starting from the next node of the dummy node
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(); // dummy node to simplify code
        dummy.next = head;

        ListNode first = dummy;
        ListNode second = dummy;

        // move the first pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // move both pointers until the first pointer reaches the end
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // remove the n-th node from the end
        second.next = second.next.next;

        return dummy.next; // return the modified list
    }

    /**
     * This algorithm maintains three pointers, low, high, and current, to keep track of the positions
     * for placing the next 0 and 2, and for traversing through the array.
     *
     * Time Complexity: O(n), where n is the length of the input array, as it iterates through the array once.
     *
     * Space Complexity: O(1) since it uses only a constant amount of extra space
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int low = 0;               // index for placing the next 0
        int high = nums.length - 1; // index for placing the next 2
        int current = 0;            // index for traversal through the array

        while (current <= high) {
            if (nums[current] == 0) {
                // if the current element is 0, swap it with the element at the 'low' index
                swap(nums, low, current);
                low++;      // move the 'low' index to the right
                current++;  // move the 'current' index to the right
            } else if (nums[current] == 2) {
                // if the current element is 2, swap it with the element at the 'high' index
                swap(nums, current, high);
                high--;     // move the 'high' index to the left
                // 'current' is not incremented here because the swapped element needs to be re-evaluated
            } else {
                // if the current element is 1, just move the 'current' index to the right
                current++;
            }
        }
    }

    /**
     * helper method to swap elements in array for sortColors
     * @param nums
     * @param i
     * @param j
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Finds the median of two sorted arrays using binary search.
     *
     * The algorithm performs binary search on the smaller of the two arrays and ensures
     * that the partitioning condition is satisfied for both arrays. The median is then
     * calculated based on the elements at the partitioned indices.
     *
     * Time Complexity: O(log(m, n)), the binary search is performed on the smaller of the two arrays.
     * The time complexity is logarithmic because, in each step, we are reducing the search space by half.
     *
     * Space Complexity: O(1), the algorithm uses a constant amount of extra space regardless of the input size.
     * The space complexity is constant because the algorithm does not use additional data structures whose size
     * depends on the input.
     *
     * @param nums1 The first sorted array.
     * @param nums2 The second sorted array.
     * @return The median of the two sorted arrays.
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0;
        int high = m;

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (m + n + 1) / 2 - partitionX;

            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxX <= minY && maxY <= minX) {
                // Correct partition found
                if ((m + n) % 2 == 0) {
                    // Even number of elements, return the average of the max of left and min of right
                    return (Math.max(maxX, maxY) + Math.min(minX, minY)) / 2.0;
                } else {
                    // Odd number of elements, return the max of left
                    return Math.max(maxX, maxY);
                }
            } else if (maxX > minY) {
                // Need to move left in partitionX
                high = partitionX - 1;
            } else {
                // Need to move right in partitionX
                low = partitionX + 1;
            }
        }

        // Control should never reach here in a valid input scenario
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

}