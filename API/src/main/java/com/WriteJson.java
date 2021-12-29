package com;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteJson {
    //getter for topology attribute
    public Topology getTopology() {
        return topology;
    }
    //setter for topology attribute
    public void setTopology(Topology topology) {
        this.topology = topology;
    }
    //topology to be written
    private Topology topology;
    public boolean writeJson()
    {   //defining JSON object for topology
        JSONObject topologyobj=new JSONObject();
        //defining JSON array for components
        JSONArray componentsarray=new JSONArray();
        //putting topology id in topology JSON object as child
        topologyobj.put("id",this.topology.getTopolgyID());
        //getting topology components from topology to be written
        ArrayList<Components> components=this.topology.getComponents();
        //looping over components
        for(Components component : components)
        {   //defining JSON object for each component,netlist
            JSONObject componentObj=new JSONObject();
            JSONObject netList=new JSONObject();
            //putting component id,type in component JSON object as children
            componentObj.put("id",component.getId());
            componentObj.put("type",component.getType());
            //defining JSON object for each characteristics
            JSONObject characteristicsobj = new JSONObject();
            //putting default,min,max in values in characteristics JSON object
            characteristicsobj.put("default",component.getCharacteristics().getDefaultValue());
            characteristicsobj.put("min",component.getCharacteristics().getDefaultMin());
            characteristicsobj.put("max",component.getCharacteristics().getDefaultMax());
            //putting characteristic JSON object in comoponent JSON object as a child
            componentObj.put(component.getCharacteristics().getName(),characteristicsobj);
            //putting netlist nodes in netlist JSON object
            for(String [] netListnodes : component.getNetListNodes().getNetListNode()){
                netList.put(netListnodes[0],netListnodes[1]);
            }
            //putting netlist JSON object in component JSON object
            componentObj.put("netlist",netList);
            //adding component JSON object to components JSON array
            componentsarray.add(componentObj);
        }
        //putting components JSON array in topology JSON object as child
        topologyobj.put("components",componentsarray);
        //trying to define file writer
        try (FileWriter file = new FileWriter(new File(this.getClass().getResource("/topology2.json").getFile()))) {
            //We can write any JSONArray or JSONObject instance to the file so we are writing topology JSON object
            file.write(topologyobj.toJSONString());
            //flushing the file writer
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
