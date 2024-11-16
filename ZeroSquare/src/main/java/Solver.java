import java.util.*;

public class Solver {

    private ZeroSqure initialGame;
    private Set<List<List<String>>> visitedBoards;

    public Solver(ZeroSqure initialGame) {
        this.initialGame = initialGame;
        this.visitedBoards = new HashSet<>();
    }

    // DFS Solver
   public boolean solveDFS() {
    Stack<Pair<ZeroSqure, List<ZeroSqure>>> stack = new Stack<>();
    stack.push(new Pair<>(initialGame, new ArrayList<>())); // Push initial game and empty path
    List<List<ZeroSqure>> winningPaths = new ArrayList<>();

    while (!stack.isEmpty()) {
        Pair<ZeroSqure, List<ZeroSqure>> currentPair = stack.pop();
        ZeroSqure currentGame = currentPair.getKey();
        List<ZeroSqure> path = currentPair.getValue();

        // Check if the current game is a winning game
        if (currentGame.win()) {
            // Add the current (winning) game state to the path
            path.add(currentGame);
            winningPaths.add(new ArrayList<>(path)); // Add path to winningPaths
            continue; // Continue searching for more paths
        }

        // Check if the board has already been visited
        List<List<String>> boardTuple = convertBoardToTuple(currentGame.getBoard());
        if (visitedBoards.contains(boardTuple)) {
            continue;
        }

        visitedBoards.add(boardTuple);

        // Explore the next possible steps
        for (ZeroSqure nextGame : currentGame.nextStep()) {
            List<List<String>> nextBoardTuple = convertBoardToTuple(nextGame.getBoard());
            if (!visitedBoards.contains(nextBoardTuple)) {
                List<ZeroSqure> newPath = new ArrayList<>(path);
                newPath.add(currentGame);
                stack.push(new Pair<>(nextGame, newPath)); // Add next game to stack
            }
        }
    }

    // If we found winning paths, print them
    if (!winningPaths.isEmpty()) {
        System.out.println("Found multiple winning paths!");
        for (int i = 0; i < winningPaths.size(); i++) {
            System.out.println("Winning Path " + (i + 1) + ":");
            for (int j = 0; j < winningPaths.get(i).size(); j++) {
                System.out.println("Step " + j + ":");
                winningPaths.get(i).get(j).printBoard(); // Print each step in the path
            }
        }
        return true;
    } else {
        System.out.println("No solution found.");
        return false;
    }
}


    // BFS Solver
   public boolean solveBFS() {
    Queue<Pair<ZeroSqure, List<ZeroSqure>>> queue = new LinkedList<>();
    queue.add(new Pair<>(initialGame, new ArrayList<>())); // Add initial game and path to queue
    List<List<ZeroSqure>> winningPaths = new ArrayList<>();

    while (!queue.isEmpty()) {
        Pair<ZeroSqure, List<ZeroSqure>> currentPair = queue.poll();
        ZeroSqure currentGame = currentPair.getKey();
        List<ZeroSqure> path = currentPair.getValue();

        // Check if the current game is a winning game
        if (currentGame.win()) {
            // Add the current (winning) game state to the path
            path.add(currentGame);
            winningPaths.add(new ArrayList<>(path)); // Add path to winningPaths
            continue; // Continue searching for more paths
        }

        // Check if the board has already been visited
        List<List<String>> boardTuple = convertBoardToTuple(currentGame.getBoard());
        if (visitedBoards.contains(boardTuple)) {
            continue;
        }

        visitedBoards.add(boardTuple);

        // Explore the next possible steps
        for (ZeroSqure nextGame : currentGame.nextStep()) {
            List<List<String>> nextBoardTuple = convertBoardToTuple(nextGame.getBoard());
            if (!visitedBoards.contains(nextBoardTuple)) {
                List<ZeroSqure> newPath = new ArrayList<>(path);
                newPath.add(currentGame);
                queue.add(new Pair<>(nextGame, newPath)); // Add next game to queue
            }
        }
    }

    // If we found winning paths, print them
    if (!winningPaths.isEmpty()) {
        System.out.println("Found multiple winning paths!");
        for (int i = 0; i < winningPaths.size(); i++) {
            System.out.println("Winning Path " + (i + 1) + ":");
            for (int j = 0; j < winningPaths.get(i).size(); j++) {
                System.out.println("Step " + j + ":");
                winningPaths.get(i).get(j).printBoard(); // Print each step in the path
            }
        }
        return true;
    } else {
        System.out.println("No solution found.");
        return false;
    }
}

    // Helper method to convert the board to a tuple (a List of Lists of Strings)
    private List<List<String>> convertBoardToTuple(String[][] board) {
    List<List<String>> tuple = new ArrayList<>();
    for (String[] row : board) {
        tuple.add(Arrays.asList(row)); // نسخ الصفوف إلى قائمة جديدة
    }
    return tuple;
}

}

// Pair class to hold a game and its path
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
