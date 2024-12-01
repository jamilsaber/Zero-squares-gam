import java.util.*;

public class UCS {

    
    private ZeroSqure currentState;  
    private Set<String> visited = new HashSet<>();
    private List<ZeroSqure> states = new ArrayList<>();
    private int depth = 0;
    private ZeroSqure init;  

    public UCS(ZeroSqure initialGame) {
        this.init = initialGame;
        this.visited = new HashSet<>();
    }

    
    
    public void ucs() {
      // DeepCopy deepCopy = new DeepCopy();
        Map<String, ZeroSqure> parentMap = new HashMap<>();
        int visitNum = 0;
        int costToChild = 0;
        PriorityQueue<ZeroSqure> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(state -> state.getMoveCost()));  // تم تعديل النوع

        priorityQueue.add(this.init);  // Assuming init is a starting state
        Map<String, Integer> costMap = new HashMap<>();
        costMap.put(hashBoard(this.init.getBoard()), 0);

        while (!priorityQueue.isEmpty()) {
            currentState = priorityQueue.poll();
            String boardHash = hashBoard(currentState.getBoard());

            if (currentState.win()) { 
                visited.add(boardHash);
                visitNum++;
                states.add(currentState);

                while (parentMap.containsKey(boardHash)) {
                    depth++;
                    currentState = parentMap.get(boardHash);
                    states.add(currentState);
                    boardHash = hashBoard(currentState.getBoard());
                }

                System.out.println("Path to solution:");
                Collections.reverse(states);
                for (int i = 0; i < states.size(); i++) {
                    System.out.println("Board Number: \n-" + i + "-\n");
                    states.get(i).printBoard();
                    System.out.println();
                }

                System.out.println("The depth of UCS: " + depth);
                break;
            }

            if (!visited.contains(boardHash)) {
                visited.add(boardHash);
                visitNum++;

                List<ZeroSqure> children = currentState.nextStep();  
                for (ZeroSqure child : children) {
                    String childHash = hashBoard(child.getBoard());
                    costToChild = costMap.get(boardHash) + child.getMoveCost();  

                    if (!costMap.containsKey(childHash) || costToChild < costMap.get(childHash)) {
                        priorityQueue.add(child);
                        costMap.put(childHash, costToChild);
                        parentMap.put(childHash, currentState);  
                    }
                }
            }
        }

        System.out.println("\nNumber of Visited: " + visitNum);
        System.out.println("\nThe Cost Of Goal: " + costToChild);  
    }

    // Hash board method (assuming it's some unique string hash for a board state)
    private String hashBoard(String[][] board) {  
        return Arrays.deepToString(board);  
    }
}
