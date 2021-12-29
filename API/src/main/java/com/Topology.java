package com;

import java.util.ArrayList;

public class Topology {
    //defining topolgy attributes ID,arraylist of components
    private String topologyID;
    private ArrayList<Components> components;
    //Topology Constructor
    public Topology(String topolgyID, ArrayList<Components> components){
        this.topologyID=topolgyID;
        this.components=components;


    }
    //setter and getter for ID,arraylist of components
    public String getTopolgyID() {
        return topologyID;
    }

    public void setTopolgyID(String topolgyID) {
        this.topologyID = topolgyID;
    }

    public ArrayList<Components> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Components> components) {
        this.components = components;
    }
    //function to print a topology
    public void print()
    {
        System.out.println("Topolgy Id: "+this.getTopolgyID());
        for(int i=0;i<this.getComponents().size();i++)
        {
            Components components=this.getComponents().get(i);
            System.out.println("Component Id: "+components.getId());
            System.out.println("Component Type: "+components.getType());
            ArrayList<String[]> netlist=components.getNetListNodes().getNetListNode();
            Characteristics characteristics=components.getCharacteristics();
            System.out.println("Characteristic name:"+characteristics.getName());
            System.out.println("default value: "+characteristics.getDefaultValue()+" min value: "+characteristics.getDefaultMin()+" max value: "+characteristics.getDefaultMax());
            System.out.println("Net List:");
            for (String[] arr : netlist) {
                System.out.println(arr[0] + ":" + arr[1]);
            }
        }
    }



}
