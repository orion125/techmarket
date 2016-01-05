/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.techmarket.view;

/**
 *
 * @author jonathan.capitao
 */
public class NewClass {
    
    public static int counter = 0;
    
    public static boolean acceptRow(int[][] problem, int row){
        for (int i = 0;i< 9; i++)
            for (int j = 0;j< 9; j++)
                if (problem[row][i] == problem[row][j] && i != j)
                    return false;
        return true;
    }
    public static boolean acceptColumn(int[][] problem, int column){
        for(int i= 0; i<9;i++)
            for(int j= 0; j<9;j++)
                if (problem[i][column] == problem[j][column] && i != j)
                    return false;
        return true;
    }
    public static boolean acceptSubSquare(int[][] problem, int row, int column){
        int carrRow = row / 3;
        int carrCol = column / 3;
        for(int i=0; i <3;i++)
            for (int j = 0; j<3;j++)
                if (problem[i+(carrRow*3)][j+(carrCol*3)] == 
                        problem[row][column] && i!= row && j!= column){
                    return false;
                }
        return true;
    }
     
    public static boolean isResolved(int[][] problem){
        return false;
    }
     
    public static void printSudoku(int[][] sudoku){
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                System.out.print(sudoku[i][j]+" ");
            }
            System.out.println("");
        }
    }
     
    public static int getRow(int cell) {
        return (int)Math.floor(cell/9);
    }
    public static int getColumn(int cell){
        return cell%9;
    }
     
    public static boolean accept(int[][] problem,int cell){
        return (acceptRow(problem, getRow(cell))&& 
                acceptColumn(problem, getColumn(cell))&&
                acceptSubSquare(problem, getRow(cell), getColumn(cell)));
    } 
    
    public static int[][] copyDoubleArray(int[][] array_to_copy){
        //let's assume it's a square
        int size = array_to_copy.length;
        int[][] array_copied = new int[size][size];
        for (int r=0;r<size;r++){
            for (int c=0;c<size;c++){
                array_copied[r][c] = array_to_copy[r][c];
            }
        }
        return array_copied;
    } 

    public static int resolved = 0;
    
    public static void backtrack(int[][] prob, int cell) {
        //if (resolved){return;}
        System.out.println("Trying cell " + getRow(cell) + ":" + getColumn(cell));
        if (isResolved(prob)) {
             return;
        } else {
            //is cell already filled  up?
            
        }
    }
    
      
    public static void main(String[] args){
        int[][] evil_problem = 
        {
            {0,0,0,0,0,0,0,1,0},
            {0,6,0,0,9,0,4,8,0},
            {3,0,0,7,0,0,0,0,9},
            {7,0,8,2,0,6,0,0,0},
            {0,0,6,0,0,0,8,0,0},
            {0,0,0,9,0,8,5,0,7},
            {2,0,0,0,0,4,0,0,5},
            {0,7,1,0,5,0,0,4,0},
            {0,3,0,0,0,0,0,0,0}              
        };
        int[][] hard_problem = 
        {
            {0,0,0,2,0,7,0,8,0},
            {4,0,1,0,8,0,0,0,0},
            {0,0,0,3,0,0,0,0,6},
            {0,9,7,0,0,0,0,0,0},
            {0,6,8,0,2,0,3,1,0},
            {0,0,0,0,0,0,5,2,0},
            {6,0,0,0,0,2,0,0,0},
            {0,0,0,0,6,0,7,0,2},
            {0,7,0,4,0,1,0,0,0}              
        };
        int[][] easy_problem = 
        {
            {0,0,1,0,0,2,6,9,7},
            {0,0,7,0,9,0,1,0,4},
            {0,6,8,0,0,1,0,0,0},
            {5,9,0,0,4,0,0,6,8},
            {0,1,0,0,0,0,0,3,0},
            {7,3,0,0,5,0,0,1,2},
            {0,0,0,3,0,0,9,7,0},
            {2,0,3,0,1,0,8,0,0},
            {1,7,9,5,0,0,2,0,0}              
        };
        backtrack(hard_problem,0);
        System.out.println("Needed "+counter+" tries");
        System.out.println("Has "+resolved+" solutions");
    }
}
    
    



