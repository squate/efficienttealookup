package com.moonsplain.efficienttealookup;

public class Tea {
    String name;
    String color;
    double temp;
    int time;
    int tsp;


    Tea(String name, int temp, int time, int tsp){
        this.name = name;
        this.temp = temp;
        this.time = time;
        this.tsp = tsp;
    }


    double getTemp(int degSystem){
        if (degSystem != 1){ return this.temp; }
        else { return this.temp * 1.8000 + 32.00; }
    }


    void setColor(String color){
        this.color = color;
    }

    public String toString(){
        return this.name+"\n"+ this.temp +"°C   " + this.tsp+" tsp  "+ this.time +"s";
    }
    public int getTime(){
        return this.time;
    }
}

