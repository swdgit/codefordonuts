/**
 * 
 */
package com.rally.ex3;

/**
 * @author swdmac
 *
 */
public class SpiralNumbers {

    /**
     * default constructor that isn't used.
     */
    public SpiralNumbers() {
    
    }

    /**
     * provide a simple output of a spiral representation of the 
     * number provided.
     * @param spiralNumber
     */
    public SpiralNumbers(Integer spiralNumber) {
        // how many rows and columns will I need.
        // square root of the spiralNumber gets us close
        double sqr = Math.sqrt(spiralNumber);
        
        // push the number up to the next integer
        int xy = (int)Math.ceil(sqr);
        
        
        int rowsAndColumns[][] = new int[xy][xy];

        // We've got a grid, now we need to put some numbers in it.
        // What's my center? 
        
        int centerIsh = Math.round(xy / 2);
        System.out.println(centerIsh);
        
        rowsAndColumns[centerIsh][centerIsh] = 0;
        
        for (int i = 0; i < spiralNumber; i++) {
            
            
            
            
        }
        
        // done... Now show off our results.
        System.out.println(xy);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        boolean printUsage = true;
        
        // check for an arg.
        if (args != null && args.length >= 1) {
            
            try {
                Integer spiralNumber = Integer.parseInt(args[0]);
                // simple square spiral is all I want.
                // let's not get too crazy.. Anything to large could get a little out of hand.
                if (spiralNumber > 4 || spiralNumber < 50) {
                    new SpiralNumbers(spiralNumber);
                    printUsage = false;
                } 
                
            } catch (NumberFormatException nfe) {
                System.out.println("Value was not a good number : " + args[0]);
            } 
        } 
        
        if (printUsage) {
            //no arg print usage
            // usage -- enter an integer (number) to get a spiral output.
            // invalid input as well.
            System.out.println("This tiny little app will print a good Spiral if you provide a number.");
            System.out.println("A number between 4 and 2,500 will be safe for most monitors.");
        }
        
    }
}