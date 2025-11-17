# PA-08-PatientQueue

## Overview
This assignment practices implementing a priority queue using a binary minimum heap data structure. The queue manages hospital patients based on priority, where smaller integers represent greater urgency.

## Learning Objectives
- Implement your own data structure (priority queue)
- Use a binary minimum heap for efficient priority-based operations
- Work with heap ordering properties and array-based storage
- Practice decomposition with helper methods

## Concept: Priority Queue
In a hospital, patients are not served in first-in, first-out (FIFO) order. Instead, patients with more urgent injuries receive care first based on their priority rating. A patient with priority 1 is more urgent than priority 2.

### Example
If patients arrive in this order:
- "Anat" (4)
- "Ben" (9)
- "Sasha" (8)
- "Wu" (7)
- "Rein" (6)
- "Ford" (2)

They would be served in this order: Ford, Anat, Rein, Wu, Sasha, Ben

## Implementation: Binary Min-Heap
The priority queue uses a binary min-heap stored in an array:
- The frontmost element (minimum priority) is always at index 1
- Index 0 is unused to simplify index math
- Each parent index `i` has children at `i * 2` and `i * 2 + 1`
- Parent priority must be smaller than both children (min-heap property)

## Patient Class
A `Patient` type is provided with:
- `String name` - patient's name
- `int priority` - priority rating (public member variables)

## Required Methods

### Constructor
- `PatientQueue()` - Initializes empty queue with initial capacity of 10 elements

### Core Operations
- `void enqueue(String name, int priority)` - Adds patient to queue, bubbles up to maintain heap order, resizes array if needed
- `void enqueue(Patient patient)` - Same as above but takes Patient object directly
- `String dequeue()` - Removes and returns frontmost patient name, bubbles down last element, throws exception if empty
- `String peek()` - Returns frontmost patient name without removing, throws exception if empty
- `int peekPriority()` - Returns frontmost patient priority without removing, throws exception if empty

### Additional Operations
- `void changePriority(String name, int newPriority)` - Modifies priority of existing patient and bubbles appropriately (affects first occurrence only)
- `boolean isEmpty()` - Returns true if queue is empty
- `int size()` - Returns number of elements
- `void clear()` - Removes all elements
- `String toString()` - Returns formatted string like `{Anat (4), Rein (6), Sasha (8), Ben (9), Wu (7), Eve (10)}`

## Important Constraints
- Break priority ties alphabetically (e.g., "Anat" before "Ben")
- Duplicate names and priorities are allowed
- No sorting functions or libraries allowed
- No Java collections allowed (arrays only)
- No temporary data structures except when resizing array
- Avoid unnecessary traversals (leverage heap efficiency)

## Tips
- Implement `toString()` early for debugging
- Write private helper functions for repeated operations
- Test exhaustively with various scenarios
- Each method should have a clear, coherent purpose

## Grading
- Correctness (autograder test cases)
- Code clarity and style
- Proper decomposition with helper methods
- Academic integrity (write your own code)

## Submission
Submit `PatientQueue.java` to Gradescope.
