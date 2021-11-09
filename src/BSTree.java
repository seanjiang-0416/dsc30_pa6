/*
 * Name: Zhixing Jiang
 * PID:  A16400450
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Zhixing Jiang
 * @since  A16400450
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            /* TODO */
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            /* TODO */
            this.left = left;
            this.right = right;
            this.dataList = new LinkedList<>();
            this.key = key;
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            /* TODO */
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            /* TODO */
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            /* TODO */
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            /* TODO */
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {
            /* TODO */
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {
            /* TODO */
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            /* TODO */
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            /* TODO */
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            /* TODO */
            return dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        /* TODO */
        this.root = null;
        this.nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        /* TODO */
        if (this.nelems == 0) {
            return null;
        }
        return this.root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        /* TODO */
        return this.nelems;
    }

    /**
     * Insert a key into BST
     *
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        /* TODO */
        if (key == null) {
            throw new NullPointerException();
        }
        if (this.root == null) {
            this.root = new BSTNode(null, null, key);
            this.nelems++;
            return true;
        }
        addhelper(this.root, key);
        this.nelems++;
        return false;
    }
    /**
     * Helper method for the insertion method
     *
     * @param cur the current node
     * @param key the key inserted to the BST
     * @return the method for recursion or false
     */
    private boolean addhelper(BSTNode cur, T key) {
        int compare = key.compareTo(cur.getKey());

        if (compare == 0) {
            return false;
        } else if (compare < 0) {
            if (cur.getLeft() == null) {
                cur.setleft(new BSTNode(null, null, key));
                return true;
            } else {
                return addhelper(cur.getLeft(), key);
            }
        } else {
            if (cur.getRight() == null) {
                cur.setright(new BSTNode(null, null, key));
                return true;
            } else {
                return addhelper(cur.getRight(), key);
            }
        }
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        /* TODO */
        if (key == null) {
            throw new NullPointerException();
        }
        return containshelper(this.root, key);
    }
    /**
     * Check if the BST contains the key
     *
     * @param cur the current node
     * @param key the key used to check if it is in the BST
     * @return the method for recursion, true if the key is found
     *      false if cur is null
     */
    public boolean containshelper(BSTNode cur, T key) {
        if (cur == null) {
            return false;
        }

        if (key.compareTo(cur.getKey()) < 0) {
            return containshelper(cur.getLeft(), key);
        } else if (key.compareTo(cur.getKey()) > 0) {
            return containshelper(cur.getRight(), key);
        } else {
            return true;
        }
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If either key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        /* TODO */
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        findKeyHelper(this.root, key).getDataList().add(data);
    }

    public BSTNode findKeyHelper(BSTNode cur, T key) {
        if (key.compareTo(cur.getKey()) == 0) {
            return cur;
        }
        if (key.compareTo(cur.getKey()) > 0) {
            return findKeyHelper(cur.getRight(), key);
        } else {
            return findKeyHelper(cur.getLeft(), key);
        }

    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        /* TODO */
        if (key == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        return findKeyHelper(this.root, key).getDataList();
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        /* TODO */
        if (this.root == null) {
            return -1;
        }
        if(this.root.getLeft() == null && this.root.getRight()==null){
            return 0;
        }
        return findHeightHelper(this.root);

    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */

    private int findHeightHelper(BSTNode root) {
        /* TODO */
        if(root == null){
            return -1;
        }
        return Math.max(findHeightHelper(root.left),
                findHeightHelper(root.right)) + 1;
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {
        public Stack<T> iterator;

        /**
         * BSTree_Iterator initialize a stack that pushes all the
         * left path elements and a root
         */
        public BSTree_Iterator() {
            /* TODO */
            iterator = new Stack<T>();
            BSTNode cur = root;
            while (cur != null) {
                iterator.push(cur.getKey());
                cur = cur.getLeft();
            }
        }
        /**
         * Check if the iterator has the next element
         *
         * @return true if there is a next element
         *      false if otherwise
         */
        public boolean hasNext() {
            /* TODO */
            if (iterator.size() == 0) {
                return false;
            } else {
                return true;
            }
        }
        /**
         * Get the next element in the iterator
         *
         * @return the element that was popped out
         * of the stack
         */
        public T next() {
            /* TODO */
            if(iterator.size()==0){
                throw new NoSuchElementException();
            }
            T output = this.iterator.pop();
            BSTNode cur = findKeyHelper(root, output).getRight();
            while (cur != null) {
                iterator.push(cur.getKey());
                cur = cur.getLeft();
            }
            return (T) output;
        }
    }
        /**
         * Initialize the iterator
         *
         * @return the new iterator
         */
        public Iterator<T> iterator() {
            /* TODO */
            return new BSTree_Iterator();
        }


    /* * * * * Extra Credit Methods * * * * */
    /**
     * Find the intersection between two iterators
     *
     * @return the intersection between two iterators
     * in arraylist
     */
    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        ArrayList<T> array_1 = new ArrayList<T>();
        while(iter1.hasNext()){
            array_1.add(iter1.next());
        }
        ArrayList<T> array_2 = new ArrayList<T>();
        while(iter2.hasNext()){
            array_2.add(iter2.next());
        }
        ArrayList<T> output = new ArrayList<T>();
        for(int i = 0; i < array_1.size(); i++){
            if(array_2.contains(array_1.get(i))){
                output.add(array_1.get(i));
            }
        }
        Collections.sort(output);
        return output;
    }
/*
    public T levelMax(int level) {

        int count = 0;
        BSTNode cur = this.root;
        while(true){
            ArrayList<T> test = new ArrayList<T>();
            if(level == count){

                return
            }
            cur.getLeft();
        }
        return null;
    }

    public T helperMax(BSTNode cur){
        if(cur == null){

        }
        ArrayList<T> test = new ArrayList<T>();


    }
    */
}

