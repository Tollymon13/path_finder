# path_finder

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

## Dijkstra's algorithm
![image](https://github.com/Tollymon13/path_finder/assets/159135691/49ef4039-6321-4b29-ab91-33ca0f51e58c)
![image](https://github.com/Tollymon13/path_finder/assets/159135691/a221eee9-9777-4d34-a6b9-640f01d3c381)
