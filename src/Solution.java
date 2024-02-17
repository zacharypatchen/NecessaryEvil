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



    public static void main(String[] args) {
        int [] test = {1,1,1,1,1,2,3};
        Solution s = new Solution();
        int out = s.removeDuplicates(test);
        System.out.println(out);
        //System.out.println(test.toString());
    }
}