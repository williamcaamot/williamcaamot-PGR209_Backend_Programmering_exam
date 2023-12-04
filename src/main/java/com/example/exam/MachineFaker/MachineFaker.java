package com.example.exam.MachineFaker;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class MachineFaker extends Faker {

    public MachineFaker() {
        super();
    }


    public String partName() {
        List<String> partNames = new ArrayList<>();
        partNames.add("5M Bolt");
        partNames.add("4M Bolt");
        partNames.add("5M mumle");
        partNames.add("4M mumle");
        partNames.add("Square Fitting");
        partNames.add("Triangle Fitting");
        partNames.add("Round fitting");
        partNames.add("4CM wood screw");
        partNames.add("2CM wood screw");

        int randomNumber = random().nextInt(0, partNames.size() - 1);
        return partNames.get(randomNumber);
    }

    public String partDescription() {


        return new String("Lol");
    }


}
