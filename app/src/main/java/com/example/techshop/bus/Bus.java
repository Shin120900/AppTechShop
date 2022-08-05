package com.example.techshop.bus;

public class Bus {


    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    public Bus(){}

}
