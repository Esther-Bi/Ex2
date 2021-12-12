# Ex2
# Classes explanations:
### 1. GeoLocationC:
***functions:***<br>
* _distance:_ computs the distance between two 3D points.<br>
### 2. NodeDataC:
key: integer that represents the node.<br>
location: 3D point in which the node located in.<br>
weight: keeps the shortest path from a different node.<br>
info: holds String.<br>
tag: holds int.<br>
<br>
***functions:***<br>
getters and setters.<br>
<br>
### 3. EdgeDataC:
src: key of source node.<br>
dest: key of destination node.<br>
weight: weight of this edge.<br>
ingo: holds String.<br>
tag: holds int.<br>
<br>
***functions:***<br>
getters and setters.<br>
<br>
### 4. DirectedWeightedGraphC:
nodeCollection: Hashmap<Integer,NodeData> when Integer are the keys of the nodes and NodeData are the nodes thenselves.<br>
edgeCollection: Hashmap<Integer,Hashmap<Integer,EdgeData>> when the keys are the source nodes and the secon keys are the desnination nodes of the edges.<br>
edgeCollectionFromDest: Hashmap<Integer,Hashmap<Integer,EdgeData>> like the edgeCollection but all the oposite edges.<br>
MC: counts the differences that where made in the graph.<br>
<br>
***functions:***<br>
* getters and setters.<br>
* _addNode:_  adds a node to the graph.<br>
* _connect:_  connects two nodes in the graph with an edge.<br>
* _NodeIter:_  iterator<br>
* _EdgeIter:_  iterator that gets a key.<br>
* _EdgeIter:_  iterator<br>
* _removeNode:_  removes a node in order of k when k are the edges connected to the node. uses the two edges collections.<br>
* _removeEdge:_  removes a edge according to keys of nodes.<br>
* _nodeSize:_  returns the amount of nodes in the graph.<br>
* _edgeSize:_  returns the amount of edges in the graph.<br>
* _minXValue:_  returns the minimum between all x values in GeoLocation of nodes.<br>
* _minYValue:_  returns the minimum between all y values in GeoLocation of nodes.<br>
* _maxXValue:_  returns the maximum between all x values in GeoLocation of nodes.<br>
* _maxYValue:_  returns the maximum between all y values in GeoLocation of nodes.<br>
<br>

### 5. DirectedWeightedGraphAlgorithmsC:
DirectedWeightedGraphC: holds a graph.<br>
<br>
***functions:***<br>
* _Init:_  initializing the graph.<br>
* _getGraph:_  return the graph of this object.<br> 
* _copy_ : make a deep copy from the graph we get to our graph.<br>
* _isConnected:_  return true if we can get from each node of the graph to any other node by the edges, otherwise return false.<br>
* _BFS:_  Runs on the graph in transition of BFS, in this way it goes through all the vertices in the graph that have access (connected with some edegs).<br>
* _getTranspose:_  the function returns a transpose of the graph.<br>
* _shortestPathDist:_  return the weight of the shortest path between src and dest using dijkastra algorithm.<br>
* _minWeight:_  returns the node with the minimum weight in the hashmap.<br>
* _dijkstra:_  An algorithm that runs on the entire graph, gets one node to be the source and initializes the weight of all of the other nodes to be the minimum path from the source node to the node itself.<br> 
pseudocode:<br>
![image](https://user-images.githubusercontent.com/80401712/145459603-e3d2347c-77b8-4f8e-b4f7-14f19838e18f.png)<br>
_relax:_  help function for dijkastra, Updates key and pointer for each node, and removes the irrelevant nodes from the list Q.<br>
pseudocode:<br>
![image](https://user-images.githubusercontent.com/80401712/145459660-12d71edd-f916-410c-a399-a7e877eb5463.png)<br>
* _shortestPath:_  return the path (through which nodes need to go) in the fastest way from the source node to the destination node using edges of the graph by using dijkastra algorithm.<br>
* _center:_  return the node which represents the center of the graph by using dijkastra algorithm.<br>
* _centerOfNode:_  help function for center, find the length of the longest path between the src node to the farthest (going in the shortest path that can be reached to each node).<br>
* _tsp:_  Gets list of nodes, return the nods in the order that creates the shortest path that contains them all (the traveling agent problem).<br>
A greedy algorithm, starting from the first node in the list, goes over all the other nodes in the list and returns the node in the shortest path by using dijkastra algorithm (can also pass in nodes that are not in the list but in the graph), once a node enters the sorted list it's removed from the list we received. Each time pulling out the next node closest to the current node.<br>
* _save:_  save the graph to a new json file.<br>
* _load:_  creat the graph from json file. <br>

<br>

### GFrame and PanelGraph:
The two classes holds all the gui data and show the graph with all the options of acctions to do on it.<br>

***Buttons Options:***<br>
* Clicking on Load: loads a new graph.<br>
* Clicking on Save: saves the graph wwith the changes that were made.<br>
* Clicking on TSP: calculates the TSP of the given cities.<br>
* Clicking on Shortest Path: shows the shortest path between the two given nodes.<br>
* Clicking on Shortest Path Dist: returns the weight of the shortest path.<br>
* Clicking on Add Node: adds to the graph a new node according to given values and shows it.<br>
* Clicking on Add Edge: adds to the graph a new edge according to given values and shows it.<br>
* Clicking on Remove Node: removes the given node and all the edges connected to it from the graph and shows the graph.<br>
* Clicking on Remove Edge: removes the given edge from the graph and shows the graph.<br>

# Exsampels from our GUI:
***presentation exsample of G2:***<br>
![WhatsApp Image 2021-12-12 at 20 38 04](https://user-images.githubusercontent.com/79116742/145725157-f4bdb1a8-3d96-472b-acbe-92bcfc7a61da.jpeg)<br>
<br>
***our menu:***<br>
![WhatsApp Image 2021-12-12 at 20 26 37](https://user-images.githubusercontent.com/79116742/145724949-1ac75074-7bd0-4a1f-ae6b-4117d847c0ab.jpeg)
![WhatsApp Image 2021-12-12 at 20 27 03](https://user-images.githubusercontent.com/79116742/145724962-62340eef-ffc3-48a8-b7e2-8ae7d4d5c11c.jpeg)
![WhatsApp Image 2021-12-12 at 20 27 25](https://user-images.githubusercontent.com/79116742/145724963-98a6ce4f-e973-45f7-b57b-45a2f2ba891d.jpeg)
<br>
<br>
***show the center in G3:***<br>
![WhatsApp Image 2021-12-12 at 20 28 13](https://user-images.githubusercontent.com/79116742/145725028-22060992-3d0b-4d8d-ad30-3e6c2d248194.jpeg)<br>
<br>
***show shortestPath between 13 and 27:***<br>
![WhatsApp Image 2021-12-12 at 20 42 27](https://user-images.githubusercontent.com/79116742/145725258-a4541c23-68f9-4d69-87f5-79ff1283550c.jpeg)<br>
<br>
***isConnected in G3:***<br>
![WhatsApp Image 2021-12-12 at 20 44 29](https://user-images.githubusercontent.com/79116742/145725310-9b05ca2b-94d5-4363-afff-6b935d7478f8.jpeg)<br>
<br>
***shortPathfDist:***<br>
![image](https://user-images.githubusercontent.com/80401712/145461621-6d01d247-9aa7-4e38-8f59-dece29fc2709.png)<br>
<br>
***TSP in G2, the given list: 16,7,2,13.***<br>
![image](https://user-images.githubusercontent.com/80401712/145462344-6c2ca5e9-3464-4a9f-aa7e-2de88f6331ce.png)<br>
<br>
***add node 38:***<br>
![image](https://user-images.githubusercontent.com/80401712/145462833-16fc9a3b-fc92-455e-a55b-a9eb1f9a20c2.png)<br>
<br>
***add adge between 6 and 15:***<br>
![image](https://user-images.githubusercontent.com/80401712/145462804-48ff283f-7439-41e7-b2e1-01541810d23f.png)<br>
<br>
***remove nodes 32,39 frome G3:***<br>
![image](https://user-images.githubusercontent.com/80401712/145463022-8f8723fa-debf-4466-a058-c523ee339b12.png)<br>
<br>
***remove edges 15-16, and 7-8:***<br>
![image](https://user-images.githubusercontent.com/80401712/145463051-7ba599f1-a9ff-41c1-b8cd-936ada03295e.png)<br>
<br>
## UML:
![Ex2_UMLpng](https://user-images.githubusercontent.com/80401712/145724358-c7400be3-2a04-41c5-baae-df3617343d8c.png)<br>
## Results:
Results | G1 | G2 | G3 | 1000Nodes | 10000Nodes | 100000Nodes
--- | --- | --- | --- |--- |--- |---
Center | 30 ms | 27 ms | 33 ms | 1 sec 865 ms	| 17 min 27 sec | --
Shortest Path Distance | 22 ms | 23 ms | 23 ms | 73 ms | 1 sec 158 ms | 57 sec 44 ms
Shortest Path | 23 ms	| 23 ms	| 23 ms	| 64 ms	| 1 sec 134 ms | 3 min 40 sec
TSP |26 ms | 28 ms | 38 ms | 1 sec 802 ms | 16 min 18 sec | up then 1 h 10 min
Is Connected |24 ms | 28 ms | 23 ms | 47 ms | 149 ms | 2 sec 399 ms
