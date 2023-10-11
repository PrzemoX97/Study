package Files;
import java.util.*;

public class Max {
    private String numbers;

    public Max(String numbers) {
        this.numbers = numbers;
    }

    private String max(String numbers) {
        List<Integer> tabOfNumbers = new ArrayList<Integer>();
        for(int i = 0; i<(numbers.split(",").length); i++) {
            tabOfNumbers.add(Integer.parseInt(numbers.split(",")[i]));
        }
        int result = tabOfNumbers.get(0);
        for(int i = 0; i<(numbers.split(",").length); i++) {
            if(result < tabOfNumbers.get(i)) {
                result = tabOfNumbers.get(i);
            }
        }
        String max = Integer.toString(result);
        return max;
    }
    public String getMax() {
        return max(numbers);
    }
}
