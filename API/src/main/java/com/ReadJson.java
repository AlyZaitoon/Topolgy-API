package com;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadJson  {

    //defining a topology to assign values to
    private Topology topolgy;
    //Read topology function that reads a topology from a given filename
    public Topology readJSON(String FileName) {
        //appending a front slash to filename
        FileName="/"+FileName;
        //trying to define a file reader
        try(FileReader fileReader= new FileReader(new File(this.getClass().getResource(FileName).getFile()))){
            //Getting the root JSON object (topology)
            JSONObject topologyObj=(JSONObject) JSONValue.parse(fileReader);
            //Parsing topology
            parseTopology(topologyObj);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.topolgy;
    }
    private void parseTopology(JSONObject topologyObj) {
        //getting topology ID
        String id = (String) topologyObj.get("id");
        //defining arraylist of components
        ArrayList <Components> topologyComponents=new ArrayList<Components>();
        //defining a json array containing components
        JSONArray componentsarray=(JSONArray) topologyObj.get("components");
        //looping over JSON array of components and parsing it
        for (Object o : componentsarray) {
            this.parseComponent(o,topologyComponents);
            //constructing the topology with its id and components
            this.topolgy = new Topology(id, topologyComponents);
        }
    }
    private void parseComponent(Object o,ArrayList <Components> topologyComponents)
    {   //defining an object for components
        Components components = new Components();
        //defining an arraylist for netlist nodes
        ArrayList<String[]> netlistarray = new ArrayList<>();
        //defining an object for characteristics
        Characteristics characteristics = new Characteristics();
        //defining an object for netlist
        NetList netList = new NetList();
        //extracting the component as JSON object
        JSONObject componentobj = (JSONObject)o;
        //Getting component object children --> id,type,characteristic,netlist
        Object[] componentchildren = componentobj.keySet().toArray();
        for (Object componentchild : componentchildren) {
            //extracting child of component and switching on it
            String key = componentchild.toString();
            switch (key) {
                case "id":
                    //setting component ID
                    components.setId((String) componentobj.get(key));

                    break;
                case "type":
                    //setting component type
                    components.setType((String) componentobj.get(key));
                    break;
                //setting component netlist
                case "netlist":
                    //extracting netlist of component
                    JSONObject netlistobj = (JSONObject) componentobj.get(key);
                    //parsing netlist
                    parseNetList(netlistobj, netlistarray);
                    //setting the netlist nodes to net list object of the component
                    netList.setNetListNode(netlistarray);
                    break;
                default:
                    //setting component characteristic
                JSONObject characteristicsobj = (JSONObject) componentobj.get(key);
                //setting characteristic name
                characteristics.setName(key);
                //parsing characteristic
                parseCharacteristics(characteristics,characteristicsobj);
                //setting component netlist object(that has netlist nodes as an attribute)
                components.setNetListNodes(netList);
                //setting components characteristics
                components.setCharacteristics(characteristics);
                //adding the component to the arraylist of components
                topologyComponents.add(components);

                    break;
            }

        }

    }
    //function to parse component netlist
    private void parseNetList(JSONObject netlistobj,ArrayList<String[]> netlistarray)
    {   //defining string array for netlist node
        String[] strings;
        //getting netlist children --> nodes
        Object[] netlistchildren = netlistobj.keySet().toArray();
        //looping over each node
        for (Object netlistchild : netlistchildren) {
            //constructing a string array for each node and setting its values
            strings = new String[2];
            strings[0] = netlistchild.toString();
            strings[1] = netlistobj.get(netlistchild).toString();
            //adding node to netlist arraylist
            netlistarray.add(strings);
    }
    }
    //function to parse component characteristics
    private void parseCharacteristics(Characteristics characteristics,JSONObject characteristicsobj){
        //getting characteristic children
        Object[] characteristicschildren = characteristicsobj.keySet().toArray();
        //looping over each child and switching on it then setting each characteristic variables according to its associated JSON object
        for (Object characteristicschild : characteristicschildren) {
            switch (characteristicschild.toString()) {
                case "default":
                    characteristics.setDefaultValue(Float.parseFloat(characteristicsobj.get(characteristicschild).toString()));
                    break;
                case "max":
                    characteristics.setDefaultMax(Float.parseFloat(characteristicsobj.get(characteristicschild).toString()));
                    break;
                case "min":
                    characteristics.setDefaultMin(Float.parseFloat(characteristicsobj.get(characteristicschild).toString()));
                    break;
            }
        }
    }
}
