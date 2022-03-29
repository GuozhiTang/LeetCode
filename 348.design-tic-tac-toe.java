class TicTacToe {

  private int[] rows;
  private int[] cols;
  private int diagonal;
  private int antidiagonal;

  public TicTacToe(int n) {
      rows = new int[n];
      cols = new int[n];
  }
  
  // Time: O(1)
  // Space: O(n)
  // We only need to keep a count for each row and column. If at any time a row or column matches the size of the board then that player has won.
  // https://leetcode.com/problems/design-tic-tac-toe/discuss/81898/Java-O(1)-solution-easy-to-understand
  public int move(int row, int col, int player) {
      int toAdd = player == 1 ? 1 : -1;

      rows[row] += toAdd;
      cols[col] += toAdd;

      if (row == col) {
        diagonal += toAdd;
      }

      if (col == cols.length - row - 1) {
        antidiagonal += toAdd;
      }

      int size = rows.length;
      if (Math.abs(rows[row]) == size ||
      Math.abs(cols[col]) == size ||
      Math.abs(diagonal) == size ||
      Math.abs(antidiagonal) == size) {
        return player;
      }

      return 0;
  }
}