package com.masterclass.employee.directory.util;

public class TextBlocks {
    public String border(){
        int repeatCount = 100;

        StringBuilder builder = new StringBuilder();
        for(int i =0; i < repeatCount; i++) {
            builder.append('=');
        }

        String repeatedBoarder = builder.toString();

        return repeatedBoarder;

    }

    public String infoDisplay(){

        StringBuilder builder = new StringBuilder();
        int empNumberWidth = 45;
        int nameWidth = 45;
        int dateHiredWidth = 40;

        builder.append(String.format("%-" + empNumberWidth + "s%-" + nameWidth + "s%-" + dateHiredWidth + "s", "Employee Number", "Name", "Date Hired"));

        String tableFormat = builder.toString();

        return tableFormat;
    }
}
