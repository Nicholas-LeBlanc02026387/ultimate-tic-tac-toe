class SubBoard {
  // 0 = blank, 1 = X, 2 = O
  public int[][] board;

  public SubBoard() {
    board = new int[3][3];
  }

  public void set(int x, int y, int val) {
    board[x][y] = val;
  }
  
  public int testWin() {
    return Main.testWin(board);
  }
  
  public int get(int x, int y) {
    return board[x][y];
  }

  public boolean isFull() {
    for(int i = 0; i < 9; i++) {
      if(board[i/3][i%3] == 0) return false;
    }
    return true;
  }
}
