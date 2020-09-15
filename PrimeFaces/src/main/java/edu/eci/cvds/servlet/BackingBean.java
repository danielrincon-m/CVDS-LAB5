package edu.eci.cvds.servlet;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name="calculadoraBean")
@ApplicationScoped

public class BackingBean {
    private int targetNumber;
    private int attempts;
    private int prize;
    private String state;

    public double CalculateMean(List<Integer> numbers) {
        double sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum / numbers.size();
    }

    public double CalculateStandardDeviation(List<Integer> numbers) {
        return Math.sqrt(CalculateVariance(numbers));
    }

    public double CalculateVariance(List<Integer> numbers) {
        double variance = 0;
        double mean = CalculateMean(numbers);
        for (Integer number : numbers) {
            variance += Math.pow(number - mean, 2);
        }
        return  variance / numbers.size();
    }

    public double CalculateMode(List<Integer> numbers) {
        int maxValue = 0, maxCount = 0;

        for (int i = 0; i < numbers.size(); ++i) {
            int count = 0;
            for (Integer number : numbers) {
                if (number.equals(numbers.get(i))) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = numbers.get(i);
            }
        }

        return maxValue;
    }

    public void restart() {

    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
