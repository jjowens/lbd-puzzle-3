# lbd-puzzle-3
## Overview
A proposed solution for a puzzle game. We have been given a set of files. 
We have to find the optimal path where it finds the max sum from top to bottom.

## TLDR
Want to skip to the end? Run SolutionTest in the test folder. It will read each text file, finding the optimal path, and then export
the results to a HTML file in the output folder. For each file, it will create two versions of the optimal; one searches from the top and the other from the bottom (reverse).
I wanted to see if it was doable searching for the max from the bottom.

## Explanation
There are two services; PyramidService and PyramidQueryService

- PyramidService - This service looked for the highest value on each line to get the maximum sum. 
This didn't work because some of the nodes would jump over several nodes to the node with the highest value. 
It's like chess, your pawn can only move one tile forward in any direction.
- PyramidQueryService - This service was designed to look for the highest value on each line to get the maximum sum, 
while within reaching distance of the highest value on the previous line. This was done by searching for the highest value 
on the next line within the range of the column indexes. For example, on row 5, the highest value in column 7. On the next
line, it would search for the highest value from column 6 to 8, within reaching distance.

### PyramidQueryService

This service has two crucial methods. getOptimalPath and getOptimalPathInReverse

Both methods works in a similar way. I wrote a function where it splits each node into a data model, PyramidCell. 
I built a large list of PyramidCells. While it builds the collection, it looks for the maximum sum in each subsequent row. 
If a cell in the pyramid was identified as having the highest value, their details are stored in a variable; row index, column index, their original 
value and their actual value. The actual value is a hexadecimal value of the original value. If the original value was d, 
the actual value was 13.

Then subsequently, then service will search for each row in the list of PyramidCells. It looks for a specific row and column, 
then finds the max value. Then on the next row, it looks for a specific row and column, then finds the max value, 
within reaching distance from the highest on the previous line.

## Flaws
I spotted a few problems
- This might be overengineered. The search function could be simplified, possibly without a datamodel.
- Maybe plain Java was not the right framework to query for the optimal path. I imagine a mathematical framework, 
like FSharp, might be ideal in searching for the right optimal path
- The function where it checks the range of column indexes might be wonky. The pyramid cell from the previous row is checked if it was the first or last cell. 
If the highest value was found in the last cell/column, it could create an incorrect range of column indexes, meaning it might overlook the highest value outside the column index range.

## Conclusion
I enjoyed working on this solution, even it might not be the best. I don't know if I found the right way. 

I've learnt a lot from this project. I discovered ways of running unit tests with a shared variable which had a collection of file names; the example files etc. 
This saved me duplicating the list of filenames for each new unit test, then having to update each one.
I also found that I can create an instance of the unit test and pass in a variable. This helped with isolating unit testing a specific file. 
