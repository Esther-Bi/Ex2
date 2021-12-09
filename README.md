# Ex2
# Classes explanations:
### GeoLocationC:
***functions:***<br>
distance: computs the distance between two 3D points.<br>
### NodeDataC:
key: integer that represents the node.<br>
location: 3D point in which the node located in.<br>
weight: keeps the shortest path from a different node.<br>
info: holds String.<br>
tag: holds int.<br>
<br>
***functions:***<br>
getters and setters.<br>
<br>
### EdgeDataC:
src: key of source node.<br>
dest: key of destination node.<br>
weight: weight of this edge.<br>
ingo: holds String.<br>
tag: holds int.<br>
<br>
***functions:***<br>
getters and setters.<br>
<br>
### DirectedWeightedGraphC:
nodeCollection: Hashmap<Integer,NodeData> when Integer are the keys of the nodes and NodeData are the nodes thenselves.<br>
edgeCollection: Hashmap<Integer,Hashmap<Integer,EdgeData>> when the keys are the source nodes and the secon keys are the desnination nodes of the edges.<br>
edgeCollectionFromDest: Hashmap<Integer,Hashmap<Integer,EdgeData>> like the edgeCollection but all the oposite edges.<br>
MC: counts the differences that where made in the graph.<br>
<br>
***functions:***<br>
getters and setters.<br>
addNode: adds a node to the graph.<br>
connect: connects two nodes in the graph with an edge.<br>
NodeIter: iterator<br>
EdgeIter: iterator that gets a key.<br>
EdgeIter: iterator<br>
removeNode: removes a node in order of k when k are the edges connected to the node. uses the two edges collections.<br>
removeEdge: removes a edge according to keys of nodes.<br>
nodeSize: returns the amount of nodes in the graph.<br>
edgeSize: returns the amount of edges in the graph.<br>
minXValue: returns the minimum between all x values in GeoLocation of nodes.<br>
minYValue: returns the minimum between all y values in GeoLocation of nodes.<br>
maxXValue: returns the maximum between all x values in GeoLocation of nodes.<br>
maxYValue: returns the maximum between all y values in GeoLocation of nodes.<br>
<br>

### DirectedWeightedGraphAlgorithmsC:
DirectedWeightedGraphC: hold a graph.<br>
<br>
***functions:***<br>
Init: initialize the graph.<br>
getGraph: return the graph of this object.<br> 
copy: make deep copy from the graph we get to our graph.<br>
isConnected: return true if we can get from each node of the graph to any other node by the edges,otherwise return false.<br>
BFS: Runs on the graph in transition of BFS, in this way it goes through all the vertices in the graph that have access (connected with the edegs).<br>
getTranspous: fanction that return transpous of the graph.<br>
shortestPathDist: return the weight of the shortest path between src and dest.<br>
minWeight: return the node with the minimum weight in the hashmap.<br>
dijkastra: An algorithm that runs on the entire graph, gets one node to be the source and initializes the weight of all of the other nodes to be the minimum path from the source node to the node itself.<br> 
pseudocode:
![alt text](C:\Users\talia\Downloads\psd_dijkastra.jpeg)
relax:help function for dijkastra, Updates key and pointer for each node, and removes the irrelevant nodes from the list Q.<br>
pseudocode:
![image](https://user-images.githubusercontent.com/80401712/145459436-fb1d6848-123f-4f88-bf5e-5a157dc0409c.png)Image description
shortestPath: return the path (through which nodes need to go) in the fastest way from the source node to the destinent node using edges of the graph.<br>
center: return the node which represents the center of the graph.<br>
centerOfNode: help fanction to center, find the length of the longest path between the src node to the farthest (going in the shortest path that can be reached to each node).<br>
tsp: Gets list of nodes, return the nods in the order that creates the shortest path that contains them all (the traveling agent problem).<br>
A greedy algorithm, starting from the first node in the list, goes over all the other nodes in the list and returns the node in the shortest path (can also pass in nodes that are not in the list but in the graph), once a node enters the sorted list it's removed from the list we received. Each time pulling out the next node closest to the current node.<br>
save: save the graph to json file.<br>
load: creat the graph from json file. <br>

<br>

### GFrame and PanelGraph:
The two classes holds all the gui data and show the graph with all the options of acctions to do on it.<br>

***Buttons Options:***<br>
Clicking on Load: loads a new graph.<br>
Clicking on Save: saves the graph wwith the changes that were made.<br>
Clicking on TSP: calculates the TSP of the given cities.<br>
Clicking on Shortest Path: shows the shortest path between the two given nodes.<br>
Clicking on Shortest Path Dist: returns the weight of the shortest path.<br>
Clicking on Add Node: adds to the graph a new node according to given values and shows it.<br>
Clicking on Add Edge: adds to the graph a new edge according to given values and shows it.<br>
Clicking on Remove Node: removes the given node and all the edges connected to it from the graph and shows the graph.<br>
Clicking on Remove Edge: removes the given edge from the graph and shows the graph.<br>

##Exsampels from our GUI:
![alt text](http://url/to/img.png)
