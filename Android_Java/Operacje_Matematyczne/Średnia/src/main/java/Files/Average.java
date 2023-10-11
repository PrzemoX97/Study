package Files;
import java.util.*;

public class Average {
    private String numbers;

    public Average(String numbers) {
        this.numbers = numbers;
    }

    private String average(String numbers) {
        List<Double> tabNumbers = new ArrayList<Double>();
        double sum = 0;
        double mean = 0;
        for(int i=0; i<(numbers.split(",").length); i++) {
            tabNumbers.add(Double.parseDouble(numbers.split(",")[i]));
            sum += tabNumbers.get(i);
        }
        mean += (sum/(numbers.split(",").length));
        String average = Double.toString(mean);
        return average;
    }
    public String getAverage() {
        return average(numbers);
    }
}
