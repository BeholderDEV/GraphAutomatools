/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.algoritmos;

/**
 *
 * @author lite
 */
public class SearchAlgorithmFactory {
    public static int DEEP_FIRST_SEARCH = 1;
    public static int BREADTH_FIRST_SEARCH = 2;
    public static SearchAlgorithm build(int method){
        if(method == DEEP_FIRST_SEARCH){
            return new DeepFirstSearch();
        }
        if(method == BREADTH_FIRST_SEARCH){
            return new BreadthFirstSearch();
        }
        return null;
    }
}
