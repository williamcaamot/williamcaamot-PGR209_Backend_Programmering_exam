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
        List<String> partDescriptions = new ArrayList<>();
        partDescriptions.add("For screwing");
        partDescriptions.add("For bolting");
        partDescriptions.add("For screwing in wood");
        partDescriptions.add("For screwing metal");

        int randomNumber = random().nextInt(0, partDescriptions.size() - 1);
        return partDescriptions.get(randomNumber);
    }


    public String subassemblyName(){

        List<String> subassemblyNames = new ArrayList<>();

        subassemblyNames.add("TorqueScrew Screw Module");
        subassemblyNames.add("TorqueBolt Bolt Screw Module");
        subassemblyNames.add("SuperFan Cooling System");
        subassemblyNames.add("ExtraRemote Wireless Controller");
        subassemblyNames.add("PowerSafe 400W Power Supply");

        int randomNumber = random().nextInt(0, subassemblyNames.size() - 1);
        return subassemblyNames.get(randomNumber);
    }

    public String subassemblyDescription(){

        List<String> subassemblyDescriptions = new ArrayList<>();

        subassemblyDescriptions.add("For screwing screws with high precision torque.");
        subassemblyDescriptions.add("For screwing bolts with high precision torque.");
        subassemblyDescriptions.add("Super high speed fans for cooling any type of machine.");
        subassemblyDescriptions.add("Wireless control module with long range up to 500ft.");
        subassemblyDescriptions.add("Robust SMPS power supply with surge control");

        int randomNumber = random().nextInt(0, subassemblyDescriptions.size() - 1);
        return subassemblyDescriptions.get(randomNumber);
    }

    public String machineName(){
        List<String> machineNames = new ArrayList<>();

        machineNames.add("5inch Speaker Cone Manufacturing machine");
        machineNames.add("12inch Speaker Cone Manufacturing machine");
        machineNames.add("18inch Speaker Cone Manufacturing machine");
        machineNames.add("3Inch tweeter manufacturing machine");
        machineNames.add("800W Amplifier Module Manufacturing machine");
        machineNames.add("1800W Amplifier Module manufacturing machine");

        int randomNumber = random().nextInt(0, machineNames.size() - 1);
        return machineNames.get(randomNumber);
    }


    public String machineDescription(){
        List<String> machineDescriptions = new ArrayList<>();

        machineDescriptions.add("For manufacturing high quality speaker cones made out of kevlar");
        machineDescriptions.add("For manufacturing high quality tweeters with a titanium diafragm");
        machineDescriptions.add("For manufacturing hight quality amplifiers with SMPS with voltage from 120v-240v");
        machineDescriptions.add("For manufacturing high efficiency speaker elements with nedynum magnets.");

        int randomNumber = random().nextInt(0, machineDescriptions.size() - 1);
        return machineDescriptions.get(randomNumber);
    }


}
