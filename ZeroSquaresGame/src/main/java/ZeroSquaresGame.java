import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ZeroSquaresGame {
    private static List<String[][]> previousBoards = new ArrayList<>();
    private int n;
    private String[][] board;

    public ZeroSquaresGame(int n, String[][] board) {
        this.n = n;
        this.board = board;
    }

    // Print the board
    public void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check if a move is valid
    private boolean isValidMove(int x, int y, int dx, int dy) {
        if (x + dx >= 0 && x + dx < n && y + dy >= 0 && y + dy < n) {
            String cell = board[x + dx][y + dy];
            return cell.equals(".") || cell.equals("GY") || cell.equals("GR") || 
                   cell.equals("GB") || cell.equals("GC");
        }
        return false;
    }

    // Can move in specific directions
    public boolean canMoveRight(int x, int y) {
        return isValidMove(x, y, 0, 1);
    }

    public boolean canMoveLeft(int x, int y) {
        return isValidMove(x, y, 0, -1);
    }

    public boolean canMoveUp(int x, int y) {
        return isValidMove(x, y, -1, 0);
    }

    public boolean canMoveDown(int x, int y) {
        return isValidMove(x, y, 1, 0);
    }

    // Move method
    public ZeroSquaresGame move(int dx, int dy) {
        String[][] newBoard = new String[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, n);
        }

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                String cell = board[x][y];
                if (!cell.equals("X") && !cell.equals("GR") && !cell.equals("GB") && 
                    !cell.equals("GY") && !cell.equals("GC") && !cell.equals(".")) {
                    int nx = x, ny = y;
                    while (isValidMove(nx, ny, dx, dy)) {
                        nx += dx;
                        ny += dy;
                        if (newBoard[x][y].equals("Y") && board[nx][ny].equals("GY")) {
                            newBoard[x][y] = ".";
                            newBoard[nx][ny] = ".";
                        }
                        if (newBoard[x][y].equals("R") && board[nx][ny].equals("GR")) {
                            newBoard[x][y] = ".";
                            newBoard[nx][ny] = ".";
                        }
                        if (newBoard[x][y].equals("B") && board[nx][ny].equals("GB")) {
                            newBoard[x][y] = ".";
                            newBoard[nx][ny] = ".";
                        }
                        if (newBoard[x][y].equals("C") && board[nx][ny].equals("GC")) {
                            newBoard[x][y] = ".";
                            newBoard[nx][ny] = ".";
                        }
                    }

                    while (newBoard[nx][ny].equals(".") && isValidMove(nx, ny, dx, dy) &&
                            newBoard[nx + dx][ny + dy].equals(".")) {
                        nx += dx;
                        ny += dy;
                    }

                    if (newBoard[nx][ny].equals(".") && newBoard[x][y].equals("Y")) {
                        newBoard[x][y] = ".";
                        newBoard[nx][ny] = "Y";
                    }
                    if (newBoard[nx][ny].equals(".") && newBoard[x][y].equals("R")) {
                        newBoard[x][y] = ".";
                        newBoard[nx][ny] = "R";
                    }
                    if (newBoard[nx][ny].equals(".") && newBoard[x][y].equals("B")) {
                        newBoard[x][y] = ".";
                        newBoard[nx][ny] = "B";
                    }
                    if (newBoard[nx][ny].equals(".") && newBoard[x][y].equals("C")) {
                        newBoard[x][y] = ".";
                        newBoard[nx][ny] = "C";
                    }

                    
                }
            }
        }

        return new ZeroSquaresGame(n, newBoard);
    }

    // Check if the game has been won
    public boolean win() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String cell = board[i][j];
                if (cell.equals("GY") || cell.equals("GR") || cell.equals("GB") || 
                    cell.equals("GR,Y") || cell.equals("GY,R") || cell.equals("GY,B") || 
                    cell.equals("GR,B") || cell.equals("GB,Y") || cell.equals("GB,R")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Play the game
    public void play() {
        printBoard();
        Scanner scanner = new Scanner(System.in);
        while (!win()) {
            System.out.println("Enter move (w/a/s/d) or enter (h) to view the next state: ");
            String move = scanner.nextLine();
            if (move.equals("w")) {
                move(-1, 0).play();
            } else if (move.equals("s")) {
                move(1, 0).play();
            } else if (move.equals("a")) {
                move(0, -1).play();
            } else if (move.equals("d")) {
                move(0, 1).play();
            } else if (move.equals("h")) {
                 nextStep();
             
            } else {
                System.out.println("Invalid move");
            }
            break;
        }

        System.out.println("Congratulations! You are the winner!");
    }

    // Check if the move has been executed before
    private boolean hasMovedBefore(String[][] newBoard) {
        for (String[][] previousBoard : previousBoards) {
            boolean equal = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!previousBoard[i][j].equals(newBoard[i][j])) {
                        equal = false;
                        break;
                    }
                }
            }
            if (equal) return true;
        }
        return false;
    }

    // Move if new
    public void moveIfNew(int dx, int dy) {
        ZeroSquaresGame newGame = move(dx, dy);
        if (!hasMovedBefore(newGame.board)) {
            previousBoards.add(newGame.board);
            newGame.printBoard();
            System.out.println("Move executed");
        } else {
            System.out.println("This move was executed before");
        }
    }

    // Print the next possible steps
    public List<String[][]> nextStep() {
        List<String[][]> outNextStep = new ArrayList<>();
        List<int[]> validPositions = new ArrayList<>();
        System.out.println("Your valid next steps are:");
        
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (!board[x][y].equals("X") && !board[x][y].equals("GR") && !board[x][y].equals("GB") &&
                    !board[x][y].equals("GY") && !board[x][y].equals("GC") && !board[x][y].equals(".")) {
                    validPositions.add(new int[]{x, y});
                }
            }
        }
        for (int[] pos : validPositions) {
            if (canMoveUp(pos[0], pos[1])) {
                outNextStep.add(move(-1, 0).board);
                break;
            }
        }
        for (int[] pos : validPositions) {
            if (canMoveDown(pos[0], pos[1])) {
                outNextStep.add(move(1, 0).board);
                break;
            }
        }
        for (int[] pos : validPositions) {
            if (canMoveRight(pos[0], pos[1])) {
                outNextStep.add(move(0, 1).board);
                break;
            }
        }
        for (int[] pos : validPositions) {
            if (canMoveLeft(pos[0], pos[1])) {
                outNextStep.add(move(0, -1).board);
                break;
            }
        }
        
        return outNextStep;
    }

    
}
