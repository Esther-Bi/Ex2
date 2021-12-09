# Ex2
#class explenation:
##GeolocationC:
distance: computs the distans beetuin two 3d points.<br>
##NodeDataC:
key: integer that represents the node
location: 3D point in which the node located in.
weight: keeps the shortest path from a different node.
info: holds String.
tag: holds int.

functions:
getters and setters.

##EdgeDataC:
src: key of source node.
dest: key of destination node.
weight: weight of this edge.
ingo: holds String.
tag: holds int.

functions:
getters and setters.

## DirectedWeightedGraphC:
nodeCollection: Hashmap<Integer,NodeData> when Integer are the keys of the nodes and NodeData are the nodes thenselves.
edgeCollection: Hashmap<Integer,Hashmap<Integer,EdgeData>> when the keys are the source nodes and the secon keys are the desnination nodes of the edges.
edgeCollectionFromDest: Hashmap<Integer,Hashmap<Integer,EdgeData>> like the edgeCollection but all the oposite edges.
MC: counts the differences that where made in the graph.

functions:
getters and setters.
addNode: adds a node to the graph.
connect: connects two nodes in the graph with an edge.
NodeIter: iterator
EdgeIter: iterator that gets a key.
EdgeIter: iterator
removeNode: removes a node in order of k when k are the edges connected to the node. uses the two edges collections.
removeEdge: removes a edge according to keys of nodes.
nodeSize: returns the amount of nodes in the graph.
edgeSize: returns the amount of edges in the graph.
minXValue: returns the minimum between all x values in GeoLocation of nodes.
minYValue: returns the minimum between all y values in GeoLocation of nodes.
maxXValue: returns the maximum between all x values in GeoLocation of nodes.
maxYValue: returns the maximum between all y values in GeoLocation of nodes.







