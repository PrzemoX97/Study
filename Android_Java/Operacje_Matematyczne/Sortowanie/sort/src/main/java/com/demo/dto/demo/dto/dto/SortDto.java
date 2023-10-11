package com.demo.dto.demo.dto.dto;

import java.util.Arrays;

public class SortDto {
    private String numbers;

    public SortDto(){}

    public SortDto(String numbers) {
        this.numbers = numbers;
    }

    public String sort(String numbers){
        String[] array;
        array = numbers.split(",");

        Arrays.sort(array);
        numbers = Arrays.toString(array);

        return numbers;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "SortDto{" + "sort='" + numbers + '\'' + '}';
    }
}
