package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
//Putting Rest Controller annotation as functions in this class will respond to requests
//mapping annotations defines the path for the request to be submitted
@RestController
public class TopolgyApi {
     //Array list of topologies in memory
     ArrayList<Topology> topologies;
     //Object of JSON reader
     ReadJson readJson;
     //Object of JSON writer
     WriteJson writeJson;
     //Topology Api Constructor
     public TopolgyApi()
     {    topologies=new ArrayList<Topology>();
          readJson=new ReadJson();
          writeJson=new WriteJson();
     }
     //Read JSON function that reads a topology from a JSON file and returns it
     @GetMapping(value = "/readtopology")
     public Topology ReadJson(@RequestParam(value = "File") String Filename)
     {    //topology read from filename
          Topology topology=readJson.readJSON(Filename);
          //adding this topology to array list of topologies in memory
          topologies.add(topology);
          //return topology
          return topology;
     }
     //Writing a topology wiyj topology ID=TopologyID into a JSON file on the Disk
     @GetMapping(value = "/writetopology")
     public  boolean writeJSON(@RequestParam(value = "TopID")String TopologyID)
     {    //if we do not have topologies in the memory we can not write any topology
          if(topologies.size()==0)
          {
               return false;
          }
          //looping to get the topology we need to write
          for(Topology topology:topologies)
          {
               if(topology.getTopolgyID().equals(TopologyID))
               {    //setting the topology to be written
                    writeJson.setTopology(topology);
               }
          }
          //writing the topology
          return writeJson.writeJson();
     }
     //function returning the topologies currently in memory
     @GetMapping(value = "/queryTopologies")
     public  ArrayList<Topology> queryTopologies()
     {    //retuning arraylist of topologies
          return this.topologies;
     }
     //topology that deletes a specific topology from memory
     @GetMapping(value = "/deleteTopologies")
     public  boolean deleteTopology(@RequestParam(value = "TopID")String TopologyID)
     {    //looping to get the topology we need to delete
          for(Topology topology:topologies)
          {
               if(topology.getTopolgyID().equals(TopologyID))
               {    //removing the topology from the array list of topologies
                    topologies.remove(topology);
                    return true;


               }
          }
          return false;

     }
     @GetMapping(value = "/queryDevices")
     public  ArrayList<Components> queryDevices(@RequestParam(value = "TopologyID")String TopologyID)
     {    ArrayList<Components> components=new ArrayList<>();
          //looping to get the topology we need to return its components
          for(Topology topology:topologies)
          {
               if(topology.getTopolgyID().equals(TopologyID))
               {    //getting components of the required topology
                    components= topology.getComponents();
               }
          }
          //returning components
          return components;
     }
     @GetMapping(value = "/queryDevicesWithNetlistNode")
     public HashSet<Components> queryDevicesWithNetlistNode(@RequestParam(value = "TopID")String TopologyID, @RequestParam(value = "netID")String NetlistNodeID)
     {    HashSet<Components> connectedComponents= new HashSet<Components>();
          //flag to avoid duplicates because a user can read a given topology more than one time
          int flag=1;
          //looping over every pair of topologies
          for(Topology topology:topologies)
          {
               if(topology.getTopolgyID().equals(TopologyID) && flag==1)
               {    flag=0;
                    //looping over every pair of components of each pair of topologies
                    for (Components component:topology.getComponents())
                    {
                         for(Components componentConnected : topology.getComponents())
                         {     //making sure that I am not comparing a component to its self
                              if(!(component.getId().equals(componentConnected.getId())))
                              {    //looping over every pair of net list nodes to compare their values to each other
                                   for(String[] componentnodes :component.getNetListNodes().getNetListNode())
                                   {
                                        for (String[] componentconnectednodes :componentConnected.getNetListNodes().getNetListNode())
                                        {
                                             //if both components net list nodes are equal to the given node add one of them
                                             if(componentnodes[1].equals(NetlistNodeID) && componentconnectednodes[1].equals(NetlistNodeID))
                                             {
                                                  connectedComponents.add(component);
                                             }
                                        }
                                   }
                              }
                         }
                    }
               }
          }

      return  connectedComponents;
     }

}
