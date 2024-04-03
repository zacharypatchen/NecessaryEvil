import java.util.*;

public class TestTwo {
    /**
     * Time Complexity: O(N), where N is the number of nodes in the binary tree.
     * The function traverses through each node of the tree exactly once.
     * Space Complexity: O(N), where N is the height of the binary tree in the worst case.
     * In the worst case scenario, the recursive call stack can go as deep as the height of the tree,
     * resulting in O(N) space complexity.
     */
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if(root == null){
            return 0; // base case of null root
        }
        else if(root.left != null &&
                root.left.left == null
                && root.left.right == null){ //locate the leaf (if it exists)
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        else{
            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        }
    }
    /**
     * Time Complexity: O(N), where N is the number of nodes in the binary tree.
     * The function visits each node exactly once, performing constant time operations on each node.
     *
     * Space Complexity: O(N), where N is the height of the binary tree.
     * The space complexity is determined by the depth of the recursion stack, which can go as deep as the height of the tree.
     * In the worst case, the space complexity is O(N).
     */

    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return root; // Trivial case, return a null root value if the root  is null.
        }
        TreeNode left = invertTree(root.left); // get left child
        TreeNode right = invertTree(root.right); // get right child
        //swap
        root.right = left;
        root.left = right;

        return root;
    }

    /**
     * Time Complexity: O(N), where N is the length of the input string 's'.
     * The function iterates through each character of the string once and performs constant time operations inside the loop.
     *
     * Space Complexity: O(1), since the space used is independent of the input size.
     * The function only uses a constant amount of extra space regardless of the input size.
     */
    public boolean checkDistances(String s, int[] distance) {
        // Get the length of the input string
        int N = s.length();
        // Iterate through each character of the string
        for (int i = 0; i < N; i++) {
            // Get the current character
            char currentChar = s.charAt(i);
            // Find the index of the first occurrence of the current character in the string
            int firstIndex = s.indexOf(currentChar);
            // Find the index of the last occurrence of the current character in the string
            int lastIndex = s.lastIndexOf(currentChar);
            // Calculate the distance between the first and last occurrences of the current character
            // Subtract 1 because we don't want to count the character itself in the distance
            int dist = lastIndex - firstIndex - 1;
            // Check if the calculated distance matches the distance specified in the distance array
            if (dist != distance[currentChar - 'a']) {
                // If the distances don't match, return false
                return false;
            }
        }
        // If all characters pass the distance check, return true
        return true;
    }

    /**
     * Time Complexity: O(N), where N is the number of nodes in the binary tree.
     * The function iterates through each node of the tree exactly once, performing constant time operations inside the loop.
     *
     * Space Complexity: O(N), where N is the number of nodes in the binary tree.
     * The space complexity is determined by the stack used for traversal. In the worst case scenario, the stack can hold all nodes of the tree.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> output = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root == null)return output; //trivial case
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode currentNode = stack.pop();// visit current node
            output.add(currentNode.val);// store current node value in pre-order
            if(currentNode.right!= null){
                stack.add(currentNode.right); // we evaluate right node first because using a stack means we are adding in reverse order
            }
            if(currentNode.left != null){
                stack.add(currentNode.left);
            }
        }
        return output;
    }
    /**
     * Time Complexity: O(N), where N is the total number of nodes in the smaller of the two trees.
     * The function traverses each node of the smaller tree once, performing constant time operations for each node.
     *
     * Space Complexity: O(H), where H is the height of the smaller of the two trees.
     * The space complexity is determined by the depth of the recursion stack, which can go as deep as the height of the smaller tree.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q == null){ //base case if both roots are null
            return true;
        }
        else if(p==null || q == null){ // base case if only one root is null
            return false;
        }
        else if(p.val != q.val){
            return false; // not the same tree
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right); // go to next level of tree
    }
    /**
     * Time Complexity: O(N*M), where N is the number of elements in the input array 'cpdomains' and M is the
     * average length of the domain strings. The function iterates through each domain string in the input array
     * and splits it into substrings. For each domain string, it performs operations proportional to the length
     * of the string, which is O(M). Therefore, the overall time complexity is O(N*M).
     *
     * Space Complexity: O(N*M), where N is the number of elements in the input array 'cpdomains' and M is the
     * average length of the domain strings. The space complexity is dominated by the HashMap 'map' which can
     * hold at most N*M unique domain substrings and their corresponding counts.
     */

    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> map = new HashMap<>();
        for(String s : cpdomains){
            String[] array = s.split(" ");
            int number = Integer.parseInt(array[0]);
            int length = array[1].length();
            if(map.containsKey(array[1])){
                map.put(array[1],map.get(array[1])+number);
            }
            else{
                map.put(array[1],number);
            }
            for(int i = 0; i < length; i++){
                if(array[1].charAt(i)=='.'){
                    String temp = array[1].substring(i+1,length);
                    if(map.containsKey(temp)){
                        map.put(temp, map.get(temp) + number);
                    }
                    else{
                        map.put(temp, number);
                    }
                }
            }
        }
        List<String> answer = new LinkedList<>();
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            String number = entry.getValue() + "";
            answer.add(number + " "+ entry.getKey());
        }
        return answer;
    }

    // 978 Longest Turbulent Subarray
    /**
     * Time Complexity: O(N), where N is the length of the input array 'arr'.
     * The function iterates through each element of the array once, performing constant time operations inside the loop.
     *
     * Space Complexity: O(N), where N is the length of the input array 'arr'.
     * The space complexity is determined by the additional array 'dp' used, which has the same length as the input array.
     */

    public int maxTurbulenceSize(int[] arr) {
        int[] dp = new int[arr.length]; // Create an array to store the longest subsequence length ending at each index of 'arr'.

        dp[0] = 1; // Initialize the first element of the 'dp' array to 1.

        if (arr.length == 1) { // Check if the length of 'arr' is 1.
            return 1; // If so, return 1 as it's the longest subsequence length.
        }

        if (arr.length == 2 && (arr[1] != arr[0])) { // Check if the length of 'arr' is 2 and the two elements are different.
            dp[1] = dp[0] + 1; // If so, set the second element of 'dp' to 2 and return it.
            return dp[1];
        }

        for (int i = 1; i < arr.length - 1; i++) { // Iterate over the elements of 'arr' except for the first and last elements.
            if (arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) { // Check if the current element is smaller than its neighbors.
                dp[i] = dp[i - 1] + 1; // If so, update the 'dp' array for the current index.
            } else if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) { // Check if the current element is greater than its neighbors.
                dp[i] = dp[i - 1] + 1; // If so, update the 'dp' array for the current index.
            } else if (arr[i] == arr[i + 1] && arr[i] == arr[i - 1]) { // Check if the current element is equal to its neighbors.
                dp[i] = 0; // If so, set the 'dp' array value for the current index to 0.
            } else { // If none of the above conditions are met.
                dp[i] = 1; // Set the 'dp' array value for the current index to 1.
            }
        }

        int v_max = 0; // Initialize a variable to store the maximum value in the 'dp' array.

        // Get max value in dp
        for (int j = 1; j < arr.length; j++) { // Iterate over the elements of 'arr' except for the first element.
            // System.out.print(dp[j] + " ");
            if (v_max < dp[j]) { // Check if the current 'dp' value is greater than the maximum value encountered so far.
                v_max = dp[j]; // If so, update the maximum value.
            }
        }

        return v_max + 1; // Return the maximum value in the 'dp' array incremented by 1.
    }

    //Path Sum II
    /**
     * Time Complexity: O(N), where N is the number of nodes in the binary tree.
     * The function traverses through each node of the tree once.
     *
     * Space Complexity: O(H * M), where H is the height of the binary tree and M is the average number of nodes in the paths that sum up to the target.
     * The space complexity is determined by the recursion stack depth, which can go as deep as the height of the tree, and the space required to store each valid path.
     */

    // Initialize a list of lists to store the result paths.
    List<List<Integer>> res = new LinkedList<>();

    // Method to find paths in the binary tree that sum up to the target sum.
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        // If the root is null, return the empty result list.
        if (root == null) return res;
        // Perform depth-first search starting from the root node.
        dfs(root, targetSum, new LinkedList<>());
        // Return the resulting list of paths.
        return res;
    }

    // Recursive function to perform depth-first search.
    private void dfs(TreeNode root, int targetSum, List<Integer> path) {
        // Update the target sum by subtracting the current node's value.
        targetSum -= root.val;
        // Add the current node's value to the path list.
        path.add(root.val);
        // Base case: if the current node is a leaf node and the target sum is 0, add the current path to the result list.
        if (root.left == null && root.right == null && targetSum == 0) {
            res.add(new LinkedList<>(path));
        } else { // Recursive case: explore the left and right subtrees.
            if (root.left != null) {
                dfs(root.left, targetSum, path); // Recursive call for the left child.
            }
            if (root.right != null) {
                dfs(root.right, targetSum, path); // Recursive call for the right child.
            }
        }
        // Backtrack: remove the current node from the path to explore other paths.
        path.remove(path.size() - 1);
    }

    // 41 First Missing Positive
    /**
     * Time Complexity: O(N), where N is the number of elements in the input array 'nums'.
     * The function iterates through the array twice, once to rearrange the elements and once to find the missing positive integer. Both iterations are linear in the size of the array.
     *
     * Space Complexity: O(1), since the space used is independent of the input size.
     * The function only uses a constant amount of extra space regardless of the input size.
     */

        // Method to find the first missing positive integer in the given array.
        public int firstMissingPositive(int[] nums) {
            int n = nums.length; // Get the length of the array.
            int i = 0; // Initialize a pointer to traverse the array.

            // Iterate through the array to rearrange the elements such that nums[i] is at its correct position (nums[i] - 1).
            while (i < n) {
                int correctIndex = nums[i] - 1; // Calculate the correct index for the current element.
                // If the current element is positive, within the range [1, n], and not already at its correct position, swap it.
                if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[correctIndex]) {
                    swap(nums, i, correctIndex);
                } else {
                    i++; // Move to the next element if no swap is needed.
                }
            }

            // Iterate through the array again to find the first missing positive integer.
            for (i = 0; i < n; i++) {
                if (nums[i] != i + 1) { // If the current element is not equal to its expected value (i + 1), return the missing integer.
                    return i + 1;
                }
            }

            return n + 1; // If all integers from 1 to n are present, return n + 1.
        }

        // Method to swap elements at given indices in the array.
        private void swap(int[] nums, int index1, int index2) {
            int temp = nums[index1]; // Store the value of nums[index1] in a temporary variable.
            nums[index1] = nums[index2]; // Assign the value of nums[index2] to nums[index1].
            nums[index2] = temp; // Assign the value stored in the temporary variable to nums[index2].
        }


}
