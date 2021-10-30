class Board {
  // 0 = blank, 1 = X, 2 = O
  SubBoard[][] board;

  public Board() {
    board = new SubBoard[3][3];
    for(int i = 0; i < 9; i++) {
      board[i/3][i%3] = new SubBoard();
    }
  }

  public void set(int x, int y, int val) {
    int x0 = x/3;
    int x1 = x%3;
    int y0 = y/3;
    int y1 = y%3;
    board[x0][y0].set(x1,y1,val);
  }

  public int get(int x, int y) {
    int x0 = x/3;
    int x1 = x%3;
    int y0 = y/3;
    int y1 = y%3;
    return board[x0][y0].get(x1,y1);
  }

  public int[][] getSubBoardWins() {
    int[][] subBoardWins = new int[3][3];
    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++) {
        subBoardWins[i][j] = board[i][j].testWin();
      }
    }
    return subBoardWins;
  }

  public boolean isFull() {
    int[][] subBoardWins = getSubBoardWins();
    for(int i = 0; i < 9; i++) {
      if(subBoardWins[i/3][i%3] == 0 && !board[i/3][i%3].isFull()) return false;
    }
    return true;
  }

  public boolean isValidMove(int x, int y, int lastX, int lastY) {
    int[][] subBoardWins = getSubBoardWins();
    if(subBoardWins[x/3][y/3] != 0) return false;
    if(lastX != -1 && subBoardWins[lastX][lastY] == 0 && !board[lastX][lastY].isFull()) {
      if(x/3 != lastX || y/3 != lastY) return false;
    }
    return get(x, y) == 0;
  }

  //     A   B   C   D   E   F   G   H   I
  // 1   ? | ? | ? | ? | ? | ? | ? | ? | ?
  //     --+---+-- | --+---+-- | --+---+--
  // 2   ? | ? | ? | ? | ? | ? | ? | ? | ?
  //     --+---+-- | --+---+-- | --+---+--
  // 3   ? | ? | ? | ? | ? | ? | ? | ? | ?
  //    -----------+-----------+-----------
  // 4   ? | ? | ? | ? | ? | ? | ? | ? | ?
  //     --+---+-- | --+---+-- | --+---+--
  // 5   ? | ? | ? | ? | ? | ? | ? | ? | ?
  //     --+---+-- | --+---+-- | --+---+--
  // 6   ? | ? | ? | ? | ? | ? | ? | ? | ?
  //    -----------+-----------+-----------
  // 7   ? | ? | ? | ? | ? | ? | ? | ? | ?
  //     --+---+-- | --+---+-- | --+---+--
  // 8   ? | ? | ? | ? | ? | ? | ? | ? | ?
  //     --+---+-- | --+---+-- | --+---+--
  // 9   ? | ? | ? | ? | ? | ? | ? | ? | ?
  public void printBoard() {
    int[][] subBoardWins = getSubBoardWins();
    System.out.println("   A   B   C   D   E   F   G   H   I");
    for(int sy = 0; sy < 3; sy++) {
      for(int iy = 0; iy < 3; iy++) {
        System.out.print((sy*3+iy+1) + "  ");
        for(int sx = 0; sx < 3; sx++) {
          if(subBoardWins[sx][sy] != 0) {
            if(iy == 1) {
              System.out.print(
                "    " + Main.playerToText(subBoardWins[sx][sy]) + "    ");
            } else {
              System.out.print("         ");
            }
            if(sx != 2) {
              System.out.print(" ┃ ");
            }
          } else {
            for(int ix = 0; ix < 3; ix++) {
              System.out.print(cellText(sx*3+ix, sy*3+iy));
              if(sx*3+ix != 8 && (sx*3+ix+1)%3 == 0) System.out.print(" ┃ ");
              else if(sx*3+ix != 8) System.out.print(" │ ");
            }
          }
        }
        
        if(iy != 2) {
          System.out.print("\n   ");
          for(int sx = 0; sx < 3; sx++) {
            if(subBoardWins[sx][sy] == 0) System.out.print("──┼───┼──");
            else System.out.print("         ");
            if(sx != 2) System.out.print(" ┃ ");
          }
          System.out.println();
        }
      }
      if(sy != 2) System.out.println("\n  ━━━━━━━━━━━╋━━━━━━━━━━━╋━━━━━━━━━━━");
    }
    System.out.println();
  }
  
  // https://en.wikipedia.org/wiki/ANSI_escape_code#Colors
  private String cellText(int x, int y) {
    return Main.playerToText(get(x, y));
  }
  
}
