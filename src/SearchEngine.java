/*
 * Name: Zhixing Jiang
 * PID:  A16400450
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Search Engine implementation.
 * 
 * @author Zhixing Jiang
 * @since  November 8, 2021
 */
public class SearchEngine {
    private static final int ARG_TWO = 2;
    private static final int OPTION_ZERO = 0;
    private static final int OPTION_ONE = 1;
    private static final int OPTION_TWO = 2;

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                /* TODO */
                // populate three trees with the information you just read
                // insert the actors as keys and movie as data into the movieTree
                for (String actor: cast) {
                    actor = actor.toLowerCase();
                    if (!movieTree.findKey(actor)) {
                        movieTree.insert(actor);
                    }
                    if(!movieTree.findDataList(actor).contains(movie)) {
                        movieTree.insertData(actor, movie);
                    }
                }
                // insert the studio as keys and movie as data into the studioTree
                for(String studio:studios){
                    studio = studio.toLowerCase();
                    if(!studioTree.findKey(studio)) {
                        studioTree.insert(studio);
                    }
                    if(!studioTree.findDataList(studio).contains(movie)) {
                        studioTree.insertData(studio, movie);
                    }
                }
                // insert the actors as keys and ratings as data into the studioTree
                for(String actor: cast) {
                    actor = actor.toLowerCase();
                    if(!ratingTree.findKey(actor)){
                        ratingTree.insert(actor);
                    }
                    if(!ratingTree.findDataList(actor).contains(rating))
                    {
                        ratingTree.insertData(actor,rating);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        /* TODO */
        // process query
        String[] keys = query.toLowerCase().split(" ");
        // search and output intersection results
        int found = 0;
        if (keys.length > 1) {
            LinkedList<String> intersection;
            //check how many of the keys exist in the searchtree
            for (int i = 0; i < keys.length; i++) {
                if (searchTree.findKey(keys[i])) {
                    found++;
                }
            }
            //the case that none of them exist
            if(found == 0){
                print(query,null);
                for(int i = 0; i < keys.length;i++){
                    print(keys[i],null);
                }
            }
            //the case that all of them exist in the keys
            else if (found == keys.length) {
                    intersection = (LinkedList<String>) searchTree.
                            findDataList(keys[found-1]).clone();
                    for (int i = 0; i < keys.length; i++) {
                        if (searchTree.findKey(keys[i])) {
                            LinkedList<String> temp = new LinkedList<String>();
                            temp.addAll(searchTree.findDataList(keys[i]));
                            intersection.retainAll(temp);
                        }
                    }
                    print(query, intersection);
                // search and output individual results
                // hint: list's addAll() and removeAll() methods could be helpful
                LinkedList<String> tank = new LinkedList<String>();
                for (int i = 0; i < keys.length; i++) {
                    if (searchTree.findKey(keys[i])) {
                        LinkedList<String> temp_1 = new LinkedList<String>();
                        temp_1.addAll(searchTree.findDataList(keys[i]));
                        temp_1.removeAll(intersection);
                        //remove everything that the previous keys contains
                        temp_1.removeAll(tank);
                        tank.addAll(searchTree.findDataList(keys[i]));
                        //no need to print if it is empty is this case
                        //based on the input output samples provided
                        if(!temp_1.isEmpty()) {
                            print(keys[i], temp_1);
                        }
                    }
                }
            }
            //the case that some of them exist
            else {
                print(query, null);
                LinkedList<String> tank = new LinkedList<String>();
                for (int i = 0; i < keys.length; i++) {
                    if (searchTree.findKey(keys[i])) {
                        LinkedList<String> temp_1 = new LinkedList<String>();
                        temp_1.addAll(searchTree.findDataList(keys[i]));
                        //remove everything that the previous keys contains
                        temp_1.removeAll(tank);
                        tank.addAll(searchTree.findDataList(keys[i]));
                        print(keys[i], temp_1);
                    }
                    else{
                        print(keys[i],null);
                    }
                }
            }
        }
        else{
            if(searchTree.findKey(keys[0])) {
                LinkedList<String> temp = new LinkedList<>();
                temp.addAll(searchTree.findDataList(keys[0]));
                print(keys[0], temp);
            }
            else{
                print(keys[0],null);
            }
        }

    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        /* TODO */
        // initialize search trees
        BSTree<String> movieTree = new BSTree<String>();
        BSTree<String> studioTree = new BSTree<String>();
        BSTree<String> ratingTree = new BSTree<String>();
        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);
        //convert the arguments from array into string
        String query = "";
        for (int i = ARG_TWO; i < args.length-1; i++){
            query += args[i] + " ";
        }
        query += args[args.length-1];
        // populate search trees

        populateSearchTrees(movieTree,studioTree,ratingTree,fileName);
        // choose the right tree to query
        if(searchKind == OPTION_ZERO) {
            searchMyQuery(movieTree, query);
        }
        else if(searchKind == OPTION_ONE){
            searchMyQuery(studioTree, query);
        }
        else if(searchKind == OPTION_TWO){
            searchMyQuery(ratingTree, query);
        }
    }
}
