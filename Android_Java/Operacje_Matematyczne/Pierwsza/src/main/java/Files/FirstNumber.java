package Files;


public class FirstNumber {
    private int number;

    public FirstNumber(int number) {
        this.number = number;
    }

    private String firstNumber(int number) {
        String answer;
        if((number == 2) | (number == 3)) {
            answer = "true";
        }
        else {
            if(((number%2) != 0) && ((number%3) != 0)) {
                answer = "true";
            }
            else {
                answer = "false";
            }
        }
        return answer;

    }
    public String getFirstNumber() {
        return firstNumber(number);

    }


}
