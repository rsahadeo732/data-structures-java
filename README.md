# data-structures-java

Java projects from my Data Structures class (CS112 at Rutgers).

Each folder is a small project where I wrote the data structure myself
and then used it in some kind of toy app or demo.

---

## Projects

### `color-game-wqu`
Weighted quick-union (union–find) on a 2D color grid.  
I use union/find to track which cells are connected.

### `iceCreamStack`
Simple stack implementation to model an ice cream stack.  
Push, pop, peek, plus a small test driver.

### `linked-train-cars`
Singly linked list of train cars.  
Adds/removes cars by changing `next` pointers instead of using arrays.

### `doublylinked-webpage`
Doubly linked list that acts like browser history.  
You can move back and forward between `WebPage` nodes in constant time.

### `murder-mystery`
Text-based murder mystery game.  
Multiple classes (`Mansion`, `Room`, `Person`, `Item`, etc.) and file input
to build the world from `.in` files.

### `venom`
Binary search tree of “hosts”.  
Insert, search, delete, and other tree operations written with recursion.

### `air-quality`
Hash table with separate chaining.  
Stores air quality info by state and county, and computes summary stats.

### `election-analysis`
Parses election CSV files into linked structures grouped by state and year.  
Lets me walk the structure and summarize results.

### `flightpath`
Graph of airports and routes using adjacency lists.  
Used to explore which airports connect to which.

### `trickortreat`
Graph of houses and streets for a trick-or-treat neighborhood.  
Models where you can walk and which houses you can visit.

---

## What this shows

- Comfortable writing Java outside of just one-file labs
- Implemented core data structures myself:
  stacks, linked lists, doubly linked lists, union–find, BSTs, hash tables, graphs
- Used tests / drivers to check that the structures actually work
- Worked with file input and small simulations, not just textbook examples
