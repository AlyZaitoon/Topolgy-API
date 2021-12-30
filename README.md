# Topolgy-API
This project is a Rest API written in java programming language that manipulates topologies and have the functionality to read topology from disk to memory(in an arraylist), write topology to the disk , delete topology from memory , query topologies available in memory , query devices of a specicfic topology available in memory, get number of devices in a topology conncected to a single node.

This API is tested using REST assured library and TESTNG library.


Note:
In test test_queryDevicesWithNetlistNode_02 function in TopologyApiApplicationTest class:
It is specifically targeting the topologies that has 2 devices connected to a specific node 
like topology.json has 2 devices(resistance,nmos) connected to node n1
