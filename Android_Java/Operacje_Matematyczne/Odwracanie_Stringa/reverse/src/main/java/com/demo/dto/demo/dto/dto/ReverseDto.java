package com.demo.dto.demo.dto.dto;

public class ReverseDto {
    private String reverse;

    public ReverseDto(String reverse) {
        this.reverse = reverse;
    }

    public String getReverse() {
        return reverse;
    }

    public void setReverse(String reverse) {
        this.reverse = reverse;
    }

    @Override
    public String toString() {
        return "ReverseDto{" +
                "reverse='" + reverse + '\'' + '}';
    }
}
