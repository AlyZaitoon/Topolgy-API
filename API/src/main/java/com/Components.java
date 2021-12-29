package com;

import java.util.ArrayList;

public class Components {
    //defining components attributes type,ID,Characteristics object , netlistobject
    private String type;
    private String id;
    private Characteristics characteristics;
    private NetList netListNodes;
    //Components default and parameterized Constructors
    public Components() {

    }

    public Components(String type, String id, Characteristics characteristics, NetList netListNodes) {
        this.type = type;
        this.id = id;
        this.characteristics = characteristics;
        this.netListNodes = netListNodes;
    }
    //setter and getter for attributes type,ID,Characteristics object , netlistobject
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }

    public NetList getNetListNodes() {
        return netListNodes;
    }

    public void setNetListNodes(NetList netListNodes) {
        this.netListNodes = netListNodes;
    }
}
