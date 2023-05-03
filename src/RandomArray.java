// Class to return an array of length n integers
public class RandomArray {
    public static int[] initialise(int length){
        int[] randomArray = new int[length];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = (int)(Math.random() * 5000000);
        }
        return randomArray;
    }
}
