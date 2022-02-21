import java.util.Queue;
import java.util.LinkedList;

class Solution {
    
  private static int[][] DIRS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //right-down-left-up
  private static char START = '*';
  private static char FOOD = '#';
  // private static char FREE = 'O';
  private static char OBSTACLE = 'X';
  
  
  // Time: O(rows*cols)
  // Space: O(rows*cols)
  // BFS (layer level)
  public int getFood(char[][] grid) {
      // Corner Cases
      if (grid == null || grid.length == 0 || grid[0].length == 0) {
          return -1;
      }
      
      int rows = grid.length, cols = grid[0].length;
      Queue<int[]> queue = new LinkedList<>();
      int pathSum = 0, result = -1;
      
      for (int row = 0; row < rows; row++) {
          for (int col = 0; col < cols; col++) {
              if (grid[row][col] == START) {
                  queue.offer(new int[] {row, col});
                  grid[row][col] = OBSTACLE;
                  result = bfs(queue, rows, cols, grid, pathSum); 
              }
          }
      }
      return result;
  }
  
  private int bfs(Queue<int[]> queue, int rows, int cols, char[][] grid, int pathSum) {
      while (!queue.isEmpty()) {
          int length = queue.size();
          
          for (int index = 0; index < length; index++) {
              int[] position = queue.poll();
          
              for (int[] DIR : DIRS) {
                  int positionX = position[0] + DIR[0];
                  int positionY = position[1] + DIR[1];

                  if (!isValid(positionX, positionY, rows, cols, grid)) {
                      continue;
                  } else if (grid[positionX][positionY] == FOOD) {
                      return (pathSum + 1);
                  }

                  grid[positionX][positionY] = OBSTACLE;
                  queue.offer(new int[] {positionX, positionY});
              }
          }
          pathSum++;
      }
      return -1;
  }
  
  private boolean isValid(int positionX, int positionY, int rows, int cols, char[][] grid) {
      if (positionX < 0 || positionX >= rows || positionY < 0 || positionY >= cols
         || grid[positionX][positionY] == OBSTACLE) {
          return false;
      }
      return true;
  }
}