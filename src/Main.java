public class Main {
    public static int totalFruit(int[] fruits) {
        if (fruits.length == 1 || fruits.length == 2)
            return fruits.length;

        // the subsequence of fruits harvested from an array of trees is denoted from left to right (from i-index to j-index)
        int i = 0;
        int j = 1;

        // baskets of two fruits
        int[] baskets = new int[2];
        baskets[0] = fruits[i];
        baskets[1] = fruits[j];
        // if we got first 2 trees with fruits of one type, then we try to find the different one
        if (baskets[0] == baskets[1]) {
            // but we also can go out of array
            while (j != fruits.length && baskets[1] == fruits[j])
                j++;
            // so, if we have only 1 type of fruits, just return the length (all trees)
            if (j == fruits.length)
                return fruits.length;
            else
            // finally, we found the 2nd type
                baskets[1] = fruits[j];
        }

        // now we have 2 indexes (i and j)
        // from i to j in array of trees we have subsequences of trees with 2 types of fruits,
        // but we need to move i-index too, so we have: last_changed_fruit and last_changed_index
        int sum = 0;
        int last_changed_fruit = fruits[j];
        int last_changed_index = j;

        // infinity while to check the entire array of trees
        while (true) {
            // we iterate until:
            while (j != fruits.length && // we find the end of the array or
                    (fruits[j] == baskets[0] || fruits[j] == baskets[1])) { // until we meet the third type of fruit

                // but if during the search the type of fruit that we collected last changed,
                // then we remember this fruit and the index of the tree from which we started collecting it
                if (fruits[j] != last_changed_fruit) {
                    last_changed_fruit = fruits[j];
                    last_changed_index = j;
                }

                j++;
            }

            // if the last iterated subsequence is bigger, then we remember it
            if (sum < j-i) sum = j-i;

            // if we have reached the end of the array of trees, then we return the result,
            if (j == fruits.length) return sum;

            // but if not, then we move the i-index
            i = last_changed_index;
            // and we change the types of fruits that we collect next
            for (int k = 0; k < baskets.length; k++) {
                if (baskets[k] != last_changed_fruit)
                    baskets[k] = fruits[j];
            }
        }
    }
    public static void main(String[] args) {

        int[] fruits = {1,2,3,2,2};
        System.out.println(totalFruit(fruits));
    }
}