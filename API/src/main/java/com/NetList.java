package com;

import java.util.ArrayList;

public class NetList {
    //defining arraylist of netlist nodes as an attribute
    private ArrayList<String[]> netListNode;
    //netlist default constructor
    public NetList() {

    }
    //netlist parameterized Constructor
    public NetList(ArrayList<String[]> netListNode) {
        this.netListNode = netListNode;
    }
    //setter and getter for netlist nodes arraylist
    public ArrayList<String[]> getNetListNode() {
        return netListNode;
    }

    public void setNetListNode(ArrayList<String[]> netListNode) {
        this.netListNode = netListNode;
    }
}
