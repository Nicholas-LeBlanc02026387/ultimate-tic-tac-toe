import java.util.Scanner;

// https://docs.google.com/document/d/19xiv7oDdaK5jWHBC0mv8DV3MLIObRpkv1jSMHFqBWgI
class Main {
  private static Scanner sc;
  private static Board board;

  public static void main(String[] args) {
    board = new Board();
    sc = new Scanner(System.in);
    System.out.println("Ultimate Tic-Tac-Toe: Enter your move as battleship coordinates (ex. A4, F8).");
    
    int player = 1;
    int lastX = -1, lastY = -1;
    while(true) {
      int[] xy = doTurn(player, lastX, lastY);
      lastX = xy[0]%3; lastY = xy[1]%3;
      int win = testWin(board.getSubBoardWins());
      if(win != 0) {
        board.printBoard();
        System.out.println("Player " + playerToText(win) + " wins!");
        return;
      }
      if(board.isFull()) {
        board.printBoard();
        System.out.println("It's a tie!");
        return;
      }
      player = 3 - player;
    }
  }

  public static int[] doTurn(int player, int lastX, int lastY) {
    board.printBoard();
    int[] xy;
    while(true) {
      System.out.print(playerToText(player) + " > ");
      String code = sc.nextLine();
      xy = parseXY(code);
      int x = xy[0], y = xy[1];
      if(x < 0 || y < 0 || x >= 9 || y >= 9 ){
        System.out.println("Out of bounds.");
        continue;
      }
      if(board.isValidMove(x, y, lastX, lastY)) {
        board.set(x, y, player);
        break;
      }
      System.out.println("You can't move there.");
    }
    return xy;
  }

  // A1 ... I9
  public static int[] parseXY(String code) {
    if(code.equals("flip the table")) flipTheTable();
    if(code.length() != 2) return new int[]{-1,-1};
    int x = code.codePointAt(0) - 65;
    int y = code.codePointAt(1) - 49;
    return new int[]{x, y};
  }

  public static String playerToText(int player) {
    if(player == 0) return " ";
    else if(player == 1) return "\u001b[92mX\u001b[0m";
    else return "\u001b[91mO\u001b[0m";
  }

  public static void flipTheTable() {
    System.out.print("  (ノ͡° ͜ʖ ͡°)ノ︵┻┻  ");
    flipTheTable();
  }

  public static int testWin(int[][] board) {
    //for (int i=0; i<3; i++){
     // if (board[i][i] != 0){
       // return 
     // }
    //}
    //return 0;
    
          
  
  
    //tests top left win
    if (board[0][0] != 0){
      if (board[0][0] == board [1][0] && board[0][0] == board[2][0]){
        return board[0][0];
      }
      else if (board[0][0] == board [0][1] && board[0][0] == board[0][2]){
        return board[0][0];
      }
    }
    
    //tests middle win
    if (board[1][1] != 0){
      if (board[1][1] == board[2][1] && board[1][1] == board[0][1]){
        return board[1][1];
      }
      else if (board[1][1] == board[1][2] && board[1][1] == board[1][0]){
        return board[1][1];
      }
      else if (board[1][1] == board[0][0] && board[1][1] == board[2][2]){
        return board[1][1];
      }
      else if (board[1][1] == board[0][2] && board[1][1] == board[2][0]){
        return board[1][1];
      }
    }
    
    //tests bottom right win
    if (board[2][2] != 0){
      if (board[2][2] == board [1][2] && board[2][2] == board[0][2]){
        return board[2][2];
      }
      else if (board[2][2] == board [2][1] && board[2][2] == board[2][0]){
        return board[2][2];
      }
    }
    return 0;
    
  }
}