# path_finder

#### Video Demo: https://youtu.be/1XdHsY3EiTo

## Introduction
This project designs, implements and demonstrates an algorithm which takes any input map 
(similar to the map below) and, starting at a given source point, finds a short path to a given
destination point, without passing through any building shapes. The objective is to do this while 
minimising the overall travel distance and using a computationally efficient algorithm

![image](https://github.com/Tollymon13/path_finder/assets/159135691/baa419ea-c62b-4a52-86ad-f456ded83e48)

The choice of algorithm, I have made is Dijkstra's algorithm, as it is:
• Correct: The path found is valid, avoids the obstacles and reaches the destination
• General: Works correctly for any given set of shapes and
source/destination points and is guaranteed to always terminate with a solution
• Accurate: Path length found is close to (or equal to) the shortest possible path
• Efficient: It has good computational complexity

## General Approach
The path finding algorithm will be based on the concept of a visibility graph. Given a set of polygon 
shapes, the nodes of the visibility graph comprise all points of the convex hulls of the polygons. The 
edges of the visibility graph describe which points can be directly reached from other points in the 
graph, unobstructed by any of the polygons.

Some of the code has been provided, while the main method computing the path finding (i.e. VisibleFrom) has been left for implementation.

## VisibleFrom method implementation

VisibleFrom returns all points of a map that are visible from a specified point p. The function will return a
List of Point 2D objects, while taking two arguments. Inside the function, there is a for loop that runs
through all the map hull points. Next, each convex hull point is initialized with a Point 2D object. Then,
using a for each loop, the code will go through all the polygons in the map, while checking if the specified
point touches or intersects the polygon. The last for loop checks two specific cases, shown in Figure 1. If the
for loop is true, the point is added to the list and at the end the list is returned.
## Dijkstra's algorithm

![image](https://github.com/Tollymon13/path_finder/assets/159135691/49ef4039-6321-4b29-ab91-33ca0f51e58c)
![image](https://github.com/Tollymon13/path_finder/assets/159135691/a221eee9-9777-4d34-a6b9-640f01d3c381)
![image](https://github.com/Tollymon13/path_finder/assets/159135691/3f15e5ae-1e05-4ebe-8f8c-bd487c02e9d3)

## Computational Complexity of the Algorithm

The path finding solution method uses the Dijkstra Algorithm with a linked list. This means that a
complexity of O(N^3) as the worst-case scenario, with the weight updating with O(V). However, our case is
not that simple. The complexity is increased, as we must make the links between the nodes and weigh the
edges. There are two for loop used for the creation of links, so that is O(N^2), whereas the weight is only
using a for loop, so a complexity of O(N).

Overall, the complexity is O(N^3) + O(N^2) + O(N), or just O(N^3).
