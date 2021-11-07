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
            root = new BSTNode(null, null, key);
            return true;
        }
        addshelper(this.root, key);

        return false;
    }

    public boolean addshelper(BSTNode cur, T key) {
        int compare = key.compareTo(cur.getKey());

        if (compare == 0) {
            return false;
        } else if (compare < 0) {
            if (cur.getLeft() == null) {
                cur.setleft(new BSTNode(null, null, key));
                return true;
            } else {
                return addshelper(cur.getLeft(), key);
            }
        } else {
            if (cur.getRight() == null) {
                cur.setright(new BSTNode(null, null, key));
                return true;
            } else {
                return addshelper(cur.getRight(), key);
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
        containshelper(this.root, key);
        return false;
    }

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
        findHeightHelper(this.root);
        return 0;
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        /* TODO */
        int leftHeight = 0;
        int rightHeight = 0;
        if (root.getLeft() != null) {
            leftHeight = findHeightHelper(root.getLeft());
        }
        if (root.getRight() != null) {
            rightHeight = findHeightHelper(root.getLeft());
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {
        Stack<T> iterator;

        public BSTree_Iterator() {
            /* TODO */
            iterator = new Stack<T>();
            BSTNode cur = root;
            while (cur.getLeft() != null) {
                iterator.push(cur.getKey());
                cur = cur.getLeft();
            }
        }

        public boolean hasNext() {
            /* TODO */
            if (iterator.size() == 0) {
                return false;
            } else {
                return true;
            }
        }

        public T next() {
            /* TODO */
            T output = iterator.pop();
            BSTNode cur = findKeyHelper(root, output);
            helperNext(cur);
            return (T) output;
        }

        public void helperNext(BSTNode cur){

            if(cur.getLeft() != null){
                iterator.push(cur.getLeft().getKey());
                helperNext(cur.getLeft());
            }

            if(cur.getRight() != null){
                iterator.push(cur.getRight().getKey());
                helperNext(cur.getRight());
            }
        }
    }

        public Iterator<T> iterator() {
            /* TODO */
            return new BSTree_Iterator();
        }


    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }

    public T levelMax(int level) {
        /* TODO */
        return null;
    }
}

