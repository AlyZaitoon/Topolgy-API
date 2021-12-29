package com;

public class main {
    public static void main(String args[]) {

       TopolgyApi topolgyApi=new TopolgyApi();
       Topology topology1=topolgyApi.ReadJson("topology.json");
       topology1.print();
        System.out.println(topolgyApi.writeJSON("top1"));
        System.out.println(topolgyApi.queryDevicesWithNetlistNode("top1","n1").size());
        topolgyApi.deleteTopology("top1");

    }
}
