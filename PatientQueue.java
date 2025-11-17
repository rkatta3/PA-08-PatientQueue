/*
 * Author: Rithvik Katta
 * Course: CSC 210 - Summer 2025
 * File: PatientQueue.java
 * Purpose: Implements a patient priority queue using a binary min-heap.
 * Stores Patient objects with priorities where smaller values indicate
 * higher urgency. Supports enqueue, dequeue, peek, priority change,
 * and other utility methods. The queue is backed by a dynamically
 * resizing array starting at index 1.
 */

public class PatientQueue {

    private Patient[] heap; // Array representing the binary heap 
    private int size;       // Number of patients currently in the queue

    /*
     * PatientQueue() -- Constructs an empty patient queue with an
     * initial capacity of 10.
     */
    public PatientQueue() {
        heap = new Patient[10]; // start with capacity for 9 patients (index 1-based)
        size = 0;
    }

    /*
     * resize() -- Doubles the capacity of the heap array.
     */
    private void resize() {
        Patient[] newHeap = new Patient[heap.length * 2]; // allocate bigger array
        for (int i = 1; i <= size; i++) {                  
            newHeap[i] = heap[i];
        }
        heap = newHeap;                                    // replace old array
    }

    /*
     * swap(i, j) -- Swaps two patients in the heap at positions i and j.
     */
    private void swap(int i, int j) {
        Patient temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /*
     * isHigherPriority(a, b) -- Returns true if patient a should come
     * before patient b. Compares priority first, then name for ties.
     */
    private boolean isHigherPriority(Patient a, Patient b) {
        if (a.priority != b.priority) {
            return a.priority < b.priority; // smaller number = higher priority
        }
        return a.name.compareTo(b.name) < 0; 
    }

    /*
     * enqueue(name, priority) -- Adds a new patient with the given name
     * and priority to the queue.
     */
    public void enqueue(String name, int priority) {
        enqueue(new Patient(name, priority)); // delegate to object version
    }

    /*
     * enqueue(patient) -- Adds the given patient to the queue and
     * restores heap order by bubbling up.
     */
    public void enqueue(Patient patient) {
        if (size + 1 == heap.length) { // array full - expand
            resize();
        }
        size++;                        // new patient increases size
        heap[size] = patient;          // place at end of heap
        bubbleUp(size);                
    }

    /*
     * bubbleUp(index) -- Moves the patient at index upward until
     * heap property is satisfied.
     */
    private void bubbleUp(int index) {
        while (index > 1) { // stop at root
            int parent = index / 2;
            if (isHigherPriority(heap[index], heap[parent])) {
                swap(index, parent); // swap with parent if higher priority
                index = parent;     
            } else {
                break;               
            }
        }
    }

    /*
     * dequeue() -- Removes and returns the name of the highest-priority
     * patient. Throws an exception if queue is empty.
     */
    public String dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        String name = heap[1].name; 
        heap[1] = heap[size];       // move last patient to root
        heap[size] = null;          
        size--;                     // reduce size
        if (size > 0) {
            bubbleDown(1);          // restore heap order
        }
        return name;
    }

    /*
     * bubbleDown(index) -- Moves the patient at index downward until
     * heap property is satisfied.
     */
    private void bubbleDown(int index) {
        while (true) {
            int left = index * 2;   // left child index
            int right = left + 1;   // right child index
            int smallest = index;   // assume current is smallest

            // Check if left child has higher priority
            if (left <= size && isHigherPriority(heap[left], heap[smallest])) {
                smallest = left;
            }
            // Check if right child has higher priority than current smallest
            if (right <= size && isHigherPriority(heap[right], heap[smallest])) {
                smallest = right;
            }
            // Swap if a smaller child was found
            if (smallest != index) {
                swap(index, smallest);
                index = smallest; 
            } else {
                break; 
            }
        }
    }

    /*
     * isEmpty() -- Returns true if no patients are in the queue.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * peek() -- Returns the name of the highest-priority patient without
     * removing them.
     */
    public String peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return heap[1].name;
    }

    /*
     * peekPriority() -- Returns the priority of the highest-priority
     * patient without removing them.
     */
    public int peekPriority() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return heap[1].priority;
    }

    /*
     * changePriority(name, newPriority) -- Finds the first patient with
     * the given name and updates their priority. Restores heap order.
     */
    public void changePriority(String name, int newPriority) {
        for (int i = 1; i <= size; i++) {
            if (heap[i].name.equals(name)) { 
                int oldPriority = heap[i].priority;
                if (oldPriority == newPriority) {
                    return; 
                }
                heap[i].priority = newPriority; // update priority
                if (newPriority < oldPriority) {
                    bubbleUp(i); // became more urgent - move up
                } else {
                    bubbleDown(i); // less urgent - move down
                }
                return; // stop after first match
            }
        }
    }

    /*
     * size() -- Returns the number of patients in the queue.
     */
    public int size() {
        return size;
    }

    /*
     * clear() -- Removes all patients from the queue.
     */
    public void clear() {
        for (int i = 1; i <= size; i++) {
            heap[i] = null; // remove each patient
        }
        size = 0; // reset size
    }

    /*
     * toString() -- Returns the queue in the format:
     * {Name (priority), Name (priority), ...}
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 1; i <= size; i++) {
            sb.append(heap[i].toString());
            if (i < size) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
