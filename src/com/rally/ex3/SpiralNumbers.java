/**
 * 
 */
package com.rally.ex3;

import java.util.ArrayList;


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

        // determine the corner values as the spiral progresses away from zero.
        ArrayList<Integer> corners = new ArrayList<Integer>();
        
        int corner = -1;
        int currentCorner = 0;
        while (spiralNumber >= currentCorner) {
            currentCorner = new Integer(corner * (corner + 1));
            corners.add(currentCorner);
            corner += 2;
        }

        for (Integer integer : corners) {
            System.out.print(" " + integer.toString());
        }

        // leverage these corners to fill out the array
        
        System.out.println("");
        
        
        
        
        
        
        // how many rows and columns will I need.
        // square root of the spiralNumber gets us close
        double sqr = Math.sqrt(spiralNumber);
        
        // push the number up to the next integer so the grid is big enough
        int xy = (int)Math.ceil(sqr);
        xy += 3; // giving the grid some wiggle room here. couple of issues with the start point and final grid size.
        
        System.out.println("grid size : " + xy + " : " + xy);
        String rowsAndColumns[][] = new String[xy][xy];

        // hard init for the array so we don't get null displays.
        for (int r = 0; r < xy; r++) {
            for (int c = 0; c < xy; c++) {
                rowsAndColumns[r][c] = "";
            }
        }
        
        
        // start point is zero based on the array.
        int start = Math.round(xy/2) - 1; 
        System.out.println("Starting at : " + start);

        try {
            boolean rowUp   = false;
            boolean rowDown = false;
            boolean colBack = false;
            boolean colFwd  = true;
            
            int row = start;
            int col = start;
            int lastCorner = 0;
            int segment    = -1; // which corner segment are we on.
            int currentSegment = segment;
            int segmentLeg = 1;
            
            for (Integer c : corners) {
                rowsAndColumns[start][start] = c.toString();
                rowUp   = true;
                rowDown = false;
                colBack = false;
                colFwd  = false;

                // fill in the other numbers based on each corner           
                for (int a = c.intValue(); a >= lastCorner ; a--) {
                    // location, location, location...
                    // I know what my starting point is. 
                    // I need to cycle around that till I fill in all the numbers for this corner
                    //
                    System.out.print(a + " ");
    
                    // need to set position of row and col here as we cycle backwards
                    // to the lastCorner.
                    if (a <= spiralNumber) {
                        rowsAndColumns[row][col] = String.valueOf(a);
                    } else {
                        rowsAndColumns[row][col] = "";                       
                    }
                    
                    if (rowUp) {
                        row--;
                        if (currentSegment <= segmentLeg) {
                            rowUp   = false;
                            colBack = true;
                            
                            currentSegment = segment;
                            if (segment >= 7) {
                                currentSegment = segment - 1;
                            }
                            segmentLeg = 1;
                            continue;
                        }
                        
                    }
                    
                    if (colBack) {
                        col--;
                        if (currentSegment <= segmentLeg) {
                            colBack = false;
                            rowDown = true;

                            currentSegment = segment - 1;
                            if (segment >= 7) {
                                currentSegment = segment - 2;
                            }

                            segmentLeg = 1;
                            continue;
                        }
                    }
                    
                    if (rowDown) {
                        row++;
                        if (currentSegment <= segmentLeg) {
                            rowDown = false;
                            colFwd  = true;

                            currentSegment = segment - 1;
                            segmentLeg = 1;
                            continue;
                        }
                    }
                    
                    if (colFwd) {
                        col++;
                        if (currentSegment <= segmentLeg) {
                            colFwd = false;
                            rowUp  = true;

                            currentSegment = segment - 2;
                            segmentLeg = 1;
                            continue;
                        }
                    }
                    
                    segmentLeg++;
                }
                segment += 2;
                if (segment >= 7) {
                    segment++;
                }
                currentSegment = segment;
                lastCorner = c.intValue();
                start++;
                row = start;
                col = start;

                System.out.println(" segment :  " + segment);
            }
        } catch (Exception e) {
            System.out.println("Wa Happen :: " + e.getMessage());
        }

        // ok done with the spiral and we can print it out.
        for (int r = 0; r < xy; r++) {
            for (int c = 0; c < xy; c++) {
                System.out.print(padLeft(rowsAndColumns[r][c], 4));
            }
            System.out.println("");
           
        }
    }
    
    /**
     * @param s value to pad
     * @param n how much padding
     * @return padded value
     */
    private String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
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
            System.out.println("*****************");
            System.out.println("*****************");
            System.out.println("This tiny little app will print a good Spiral if you provide a number.");
            System.out.println("A number between 4 and 2,500 will be safe for most monitors.");
            System.out.println("*****************");
            System.out.println("*****************");
        }
    }
        
}