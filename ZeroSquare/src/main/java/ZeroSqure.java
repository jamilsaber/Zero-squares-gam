import java.util.*;

public class ZeroSqure {
    private static List<String[][]> previousBoards = new ArrayList<>();
    private int n;
    private String[][] board;

    public ZeroSqure(int n, String[][] board) {
        this.n = n;
        this.board = board;
    }

    public int getN() {
        return n;
    }

    public String[][] getBoard() {
        return this.board;
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
        boolean useDFS = false;
        boolean useBFS = false;

        while (!win()) {
            System.out.println("Enter move (w/a/s/d) to move or (h) to view next step or (dfs) to run DFS: ");
            String move = scanner.nextLine();

            if (move.equals("w")) {
                move(-1, 0).play();
            } else if (move.equals("s")) {
                move(1, 0).play();
            } else if (move.equals("a")) {
                move(0, -1).play();
            } else if (move.equals("d")) {
                move(0, 1).play();
            } else if (move.equals("dfs")) {
                useDFS = true;  // Toggling DFS
                break;           // Exit loop to run DFS
            }
            else if (move.equals("bfs")) {
                useBFS = true;  // Toggling DFS
                break;           // Exit loop to run DFS
            }
            else if (move.equals("h")) {
                nextStep();
            } else {
                System.out.println("Invalid move");
            }
        }

        if (useDFS) {
            System.out.println("Running DFS to find solution...");
            Solver solver = new Solver(this);
            solver.solveDFS();  // Run DFS Algorithm
        }
        if (useBFS) {
            System.out.println("Running DFS to find solution...");
            Solver solver = new Solver(this);
            solver.solveDFS();  // Run DFS Algorithm
        }

        System.out.println("Congratulations! You are the winner!");
    }

    // Check if the move has been executed before
    private boolean hasMovedBefore(String[][] newBoard) {
        for (String[][] previousBoard : previousBoards) {
            if (Arrays.deepEquals(previousBoard, newBoard)) {
                return true;
            }
        }
        return false;
    }

    // Print the next possible steps
    public List<ZeroSqure> nextStep() {
    List<ZeroSqure> outNextStep = new ArrayList<>();
    
    // الحركات الممكنة حسب الاتجاهات
    int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} }; // (w, s, a, d)
    
    for (int[] direction : directions) {
        int dx = direction[0];
        int dy = direction[1];
        
        // تنفيذ الحركة
        ZeroSqure newBoard = move(dx, dy);
        
        // إضافة اللوح الجديد إلى القائمة إذا لم يتم زيارته من قبل
        if (!hasMovedBefore(newBoard.getBoard())) {
            outNextStep.add(newBoard);
        }
    }

    return outNextStep;
}


    // Move method
    public ZeroSqure move(int dx, int dy) {
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

        return new ZeroSqure(n, newBoard);
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
    
    
}
