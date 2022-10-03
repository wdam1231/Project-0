/**
 * A class of bags whose entries are stored in a fixed-size array.
 */
import java.util.Arrays;
public class ResizeableArrayBag<T> implements BagInterface<T> {
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;

    private boolean integrityOK = false;
    private static final int MAX_CAPACITY = 10000;

    /** Creates an empty bag whose initial capacity is 25. */
    public ResizeableArrayBag(){
        this(DEFAULT_CAPACITY);
    }

    /** Creates an empty bag having a given initial capacity.
     * @param desiredCapacity the interger capacity desired */
    public ResizeableArrayBag(int desiredCapacity){
        if(desiredCapacity <= MAX_CAPACITY){
            // The cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[])new Object[desiredCapacity]; // Unchecked cast
            bag = tempBag;
            numberOfEntries = 0;
            integrityOK = true;
        } else{
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum.");
        }
    }

    // Throws an exception if this object is not initialized
    private void checkIntegrity(){
        if(!integrityOK){
            throw new SecurityException("ArrayBag object is corrupt.");
        }
    }

    // Throws an exception if the client requests a capactiy that is too large.
    private void checkCapacity(int capacity){
        if(capacity > MAX_CAPACITY){
            throw new IllegalStateException("Attempt to crate a bag whose capactiy exceeds allowed maximum of " + MAX_CAPACITY);
        }
    }

    // Doubles the size of the array bag
    // Precondition: checkIntegrity has been called.
    private void doubleCapacity(){
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    }

    /** Adds a new entry to this bag
     * @param newEntry the object to be added as a new entry.
     * @return True. */
    public boolean add(T newEntry){
        checkIntegrity();
        boolean result = true;
        if(isFull()){
            doubleCapacity();
        }
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;
        return result;
    }

    /** Retrieves all entries that are in this bag.
     * @return a newly allocated array of all the entries in the bag */
    public T[] toArray(){
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        for(int i = 0; i < numberOfEntries; i++){
            result[i] = bag[i];
        }
        return result;
    }

    /** Sees whether this bag is full.
     * @return true if the bag is full, or false if not */
    public boolean isFull(){
        return numberOfEntries == bag.length;
    }

    /** Sees whether this bag is empty.
     * @return True if this bag is empty, or false if not. */
    public boolean isEmpty(){
        return numberOfEntries == 0;
    }

    /** Gets the current number of entries in this bag.
     * @return The interger number of entries currently in this bag. */
    public int getCurrentSize(){
        return numberOfEntries;
    }

    /** Counts the number of times a given entry appears in this bag.
     * @param anEntry The entry to be counted.
     * @return THe number of times anEntry appears in this bag. */
    public int getFrequencyOf(T anEntry){
        checkIntegrity();
        int counter = 0;
        for(int i = 0; i < numberOfEntries; i++){
            if(anEntry.equals(bag[i])){
                counter++;
            }
        }
        return counter;
    }

    // Locates a given entry within the array bag
    // Returns the index of the entry, if located, or -1 otherwise
    // Precondition: checkIntegrity has been called
    private int getIndexOf(T anEntry){
        int where = -1;
        boolean found = false;
        int index = 0;
        while(!found && (index < numberOfEntries)){
            if(anEntry.equals(bag[index])){
                found = true;
                where = index;
            }
            index++;
        }
        
        //Assertion: If where > -1, anEntry is in the array bag, and if
        // equals bag[where], otherwise, anEntry is not in the array

        return where;
    }

    private T removeEntry(int givenIndex){
        T result = null;
        if(!isEmpty() && (givenIndex>=0)){
            result = bag[givenIndex];
            bag[givenIndex] = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
        }
        return result;
    }

    /** Removes one occrrence of a gien entry from this bag.
     * @param anEntry The entry to be removed.
     * @return True if the removal was successful, false if not. */
    public boolean remove(T anEntry){
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }

    /** Removes one unspecififed entry from this bag, if possible.
     * @return Either the removed entry, if the removal was successful, or null otherwise. */
    public T remove(){
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);
        return result;
    }

    /** Removes all entries from this bag. */
    public void clear(){
        while(!isEmpty()){
            remove();
        }
    }

    /** Tests whether this bag contains a given entry.
     * @param anEntry The entry to locate.
     * @return True if this bag contains anEntry, or false otherwise. */
    public boolean contains(T anEntry){
        checkIntegrity();
        return getIndexOf(anEntry) > -1;
    }

    /** Creates a new bag that combines the contents of this bag and other bag
     * without affecting the contents of the original two bags. 
     * @param otherBag bag we are passing in to the union method with
     * @return resulting bag the union of the bag receiving the call to the method
     * and the bag that is the method's one argument. */
    @Override
    public BagInterface<T> union(BagInterface<T> otherBag){
        if((otherBag == null) || (this == null)){ // Checks to see if bag being called or being passed in is null
            throw new NullPointerException("One of the bags is null");
        }
        // Resulting bag that contains the union of both bags
        ResizeableArrayBag<T> result = new ResizeableArrayBag<T>();
        T[] bag = this.toArray(); // Retrieves all objects in the bag
        // traverses through the bag and adds entries from this bag to the resulting bag
        for(T entry: bag){
            result.add(entry);
        }
        T[] other = otherBag.toArray(); // Retrieves all objects in the bag
        // traverses through the bag and adds entries from the other bag to the resulting bag        
        for(T entry: other){
            result.add(entry);
        }
        return result; 
    }

    /** Creates a new bag that contains objects that occur in both this bag and other bag
     * without affecting the original two bags.
     * @param otherBag bag we are passing in to do the intersection method with
     * @return resulting bag the intersection of the bag receiving the call to the method
     * and the bag that is the method's one argument. */
    @Override
    public BagInterface<T> intersection(BagInterface<T> otherBag){
        if((otherBag == null) || (this == null)){ // Checks to see if bag being called or being passed in is null
            throw new NullPointerException("One of the bags is null");
        }
        // Resulting bag that consists of entries that occur in both bags
        LinkedBag<T> result = new LinkedBag<T>();
        LinkedBag<T> newResult = new LinkedBag<T>();
        T[] bag = this.toArray();  // Retrieves all objects in the bag
        // traverses through the bag and adds overlapping entries from this bag to the resulting bag
        for(T entry: bag){
            result.add(entry);
        }
        T[] other = otherBag.toArray(); // Retrieves all objects in the bag
        // traverses through the bag and adds overlapping entries from the other bag to the resulting bag
        for(T entry: other){
            if(result.contains(entry)){
                newResult.add(entry); // Adds object that occurs in both bags to resulting bag
                result.remove(entry); // and removes the object that occurs in both bags from this bag so that
                                    // you don't get accidental duplicate objects
            }
        }
        return newResult; 
    }

    /** Creates a new bag of objectst that would be left in this bag after removing those that occur
     * in the other bag without affecting the original two bags.
     * @param otherBag bag we are passing in to the difference method with 
     * @return resulting bag the difference of the bag receiving the call to the method
     * and the bag that is the method's one argument. */
    @Override
    public BagInterface<T> difference(BagInterface<T> otherBag){
        if((otherBag == null) || (this == null)){ // Checks to see if bag being called or being passed in is null
            throw new NullPointerException("One of the bags is null");
        }
        // Resulting bag that consists of entries that would be left in one bag after removing
        // those that also occur in the second bag.
        ResizeableArrayBag<T> result = new ResizeableArrayBag<T>();
        T[] bag = this.toArray(); // Retrieves all objects in the bag
        // traverses through the bag and adds all objects to the resulting bag
        for(T entry: bag){
            result.add(entry);
        }
        T[] other = otherBag.toArray(); // Retrieves all objects in the bag
        // traverses through the other bag 
        for(T entry: other){
            if(result.contains(entry)){ // removes objects in resulting bag that is inside of the other bag
                result.remove(entry);
            }
        }
        return result; 
    }
}
