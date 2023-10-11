package com.demo.dto.demo.dto.dto;


public class CountDto {

    private int length;

    public CountDto(){}

    public CountDto(int length){
        this.length = length;
    }


    public int countLength(String text, char character){
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) == character || text.charAt(i) == Character.toUpperCase(character))
                length++;
        }

        return length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "CountDto{" +
                "count=" + length +
                '}';
    }
}
