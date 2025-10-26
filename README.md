# Assignment 3: Prim's and Kruskal's MST Algorithms

This project is a Java implementation of Prim's and Kruskal's algorithms to find the Minimum Spanning Tree (MST).
The program reads graph data from an `ass_3_input.json` file, computes the MST using both methods, measures performance (execution time and operation count), and writes the detailed results to `ass_3_output.json`.

This README file contains both the instructions for running the project and the complete analytical report for the assignment.

## Tech Stack

* Java 11
* Maven — for dependency management
* Jackson — for JSON parsing and serialization

## How to Run

### Prerequisites

* JDK 11 (or newer).
* Apache Maven.
* Git (for cloning the repository).

### Option 1: Run via Terminal (with Maven)

This is the recommended method for a clean build and run.

1.  **Clone the repository**
    ```bash
    git clone https://github.com/paranoia3/MST
    cd MST
    ```

2.  **Place the input file**
    Ensure the `ass_3_input.json` file (containing all 4 graphs) is in the root directory of the project (next to the `pom.xml` file).

3. **Run the program**

5.  **Check the result**
    After execution, a new file named `ass_3_output.json` will be created in the root directory.

### Option 2: Run via an IDE (IntelliJ IDEA / VS Code)

* Open the project in your IDE (e.g., IntelliJ IDEA) as an "Existing Maven Project".
* The IDE will automatically detect `pom.xml` and download the Jackson dependency.
* Place the `ass_3_input.json` file in the project root (at the same level as `pom.xml`).
* Open the `src/main/java/org/assignment/Main.java` file.
* Click the green "Run" button next to the `main` method.
* The `ass_3_output.json` result file will appear in the root directory.

## Analytical Report

This report analyzes the implementation and performance of Prim's and Kruskal's algorithms for finding the MST on various graph datasets.

### 1. Summary of Results

Both algorithms were tested on a suite of four graphs, varying in size and density (V = vertices, E = edges). Both implementations correctly identified the MST and calculated the identical minimum total cost for each graph, confirming their correctness.

The performance results from our execution are as follows:

| Graph ID | Vertices (V) | Edges (E) | Algorithm | Total Cost | Operations Count | Execution Time (ms) |
|:---------|:-------------|:----------|:----------|:-----------|:-----------------|:--------------------|
| 1        | 5            | 7         | Prim's    | 16         | 47               | 2.6878              |
| 1        | 5            | 7         | Kruskal's | 16         | 56               | 1.4357              |
| 2        | 4            | 5         | Prim's    | 6          | 28               | 0.0438              |
| 2        | 4            | 5         | Kruskal's | 6          | 39               | 0.0291              |
| 3        | 10           | 15        | Prim's    | 32         | 117              | 0.1753              |
| 3        | 10           | 15        | Kruskal's | 32         | 138              | 0.1466              |
| 4        | 15           | 40        | Prim's    | 56         | 340              | 0.471               |
| 4        | 15           | 40        | Kruskal's | 56         | 315              | 0.1327              |

### 2. Comparison of Efficiency and Performance

Analysis of our specific test results reveals a clear and consistent trend:

* **Execution Time:** In all four test cases, Kruskal's algorithms demonstrated superior performance, executing significantly faster than Prim's algorithms. This was true for small, sparse graphs (ID 2) and also for the larger, dense graph (ID 4).
* **Operations Count:** The data for operations count shows a more nuanced story:
    * On the sparse graphs (ID 1, 2, 3), Prim's algorithms consistently completed with fewer operations (e.g., 117 vs 138 for Graph 3), even though its execution time was slower.
    * However, on the dense graph (ID 4), Kruskal's algorithms became more efficient in both metrics: it was faster (0.1327ms vs 0.471ms) AND used fewer operations (315 vs 340).
* This suggests that the "operations" in Prim's algorithms (like adding to and polling from the `PriorityQueue`) are individually more time-consuming (have a higher constant factor) than the operations in Kruskal's (like sorting comparisons and DSU checks).

### 3. Conclusions: Algorithm Selection

Based on the theoretical complexity and our specific experimental results, we can draw the following conclusions:

* **Theoretical vs. Practical Performance:** While Prim's $O(E \log V)$ complexity is theoretically better for dense graphs than Kruskal's $O(E \log E)$, our data shows this is not always true for smaller datasets. The theoretical advantage of Prim's is asymptotic, meaning it truly shows itself only on much larger graphs.
* **Implementation Overhead:** Our results strongly suggest that the real-world overhead of the Java `PriorityQueue` (used by Prim's) is significantly higher than the overhead of Java's `Collections.sort()` and our DSU implementation (used by Kruskal's). Kruskal's algorithms, which sorts all edges once and then performs very fast DSU operations, proved more efficient in practice for all tested scales.
* **Implementation Complexity:**
    * **Kruskal's:** Requires a Disjoint Set Union (DSU) data structure, which adds a layer of implementation complexity.
    * **Prim's:** Can be implemented simply using a `PriorityQueue`, which is a standard library class in Java.
* **Final Conclusion:**
  For the datasets tested (up to 15 vertices and 40 edges), **Kruskal's algorithms was the clear winner in terms of performance**. This demonstrates that while theoretical complexity is a vital guide, for small-to-medium graphs, the "constant factors" and real-world overhead of the specific data structures used (e.g., `PriorityQueue` vs. `sort()`) can be the dominant factor in execution time.

### 4. References

* (Example: Cormen, T. H., et al. *Introduction to Algorithms*.)
* (Example: Java 11 Documentation for `java.util.PriorityQueue` and `java.util.Collections.sort`.)
* (Example: Wikipedia articles on Prim's algorithms, Kruskal's algorithms, and Disjoint Set Union.)

## Project Structure

This project follows the **Separation of Concerns** principle to ensure the code is clean, readable, and maintainable:

* `pom.xml`
  The Maven configuration file. Declares the `jackson-databind` dependency.

* `src/main/java/org/assignment/Main.java`
  **The Conductor.** This is the main entry point that handles reading the input file, executing the algorithms, and writing the results.

* `src/main/java/models/`
  **The Blueprints (POJO Classes).** These classes (`Edge`, `GraphDefinition`, `GraphResult`, etc.) are simple data structures that mirror the JSON structure. They are used by the Jackson library to automatically map JSON to Java objects and vice-versa.

* `src/main/java/algorithms/`
  **The Tools (Algorithm Logic).** All the core logic resides here:
    * `PrimAlgorithm.java`: Implements Prim's algorithms.
    * `KruskalAlgorithm.java`: Implements Kruskal's algorithms.
    * `DisjointSetUnion.java`: A helper data structure (DSU) used by Kruskal's algorithms.