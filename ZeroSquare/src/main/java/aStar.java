import java.util.*;

public class aStar {

    private ZeroSqure currentState;
    private Set<String> visited = new HashSet<>();
    private List<ZeroSqure> states = new ArrayList<>();
    private int depth = 0;
    private ZeroSqure init;

    public aStar(ZeroSqure initialGame) {
        this.init = initialGame;
    }

    public void aStar() {
        Map<String, ZeroSqure> parentMap = new HashMap<>();
        int visitNum = 0;

  
        PriorityQueue<ZeroSqure> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(state -> state.getMoveCost() + state.getHeuristicValue()));
        priorityQueue.add(this.init);
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

                System.out.println("The depth of A* search: " + depth);
                break;
            }

         
            if (!visited.contains(boardHash)) {
                visited.add(boardHash);
                visitNum++;

            
                List<ZeroSqure> children = currentState.nextStep();
                for (ZeroSqure child : children) {
                    String childHash = hashBoard(child.getBoard());
                    int costToChild = costMap.get(boardHash) + child.getMoveCost();

                    
                    int f = costToChild + child.getHeuristicValue();

                   
                    if (!costMap.containsKey(childHash) || costToChild < costMap.get(childHash)) {
                        priorityQueue.add(child);
                        costMap.put(childHash, costToChild);
                        parentMap.put(childHash, currentState);
                    }
                }
            }
        }

        System.out.println("\nNumber of Visited: " + visitNum);
        System.out.println("\nThe Cost Of Goal: " + currentState.getMoveCost());
    }


    private String hashBoard(String[][] board) {
        return Arrays.hashCode(Arrays.stream(board).flatMap(Arrays::stream).toArray()) + "";
    }
}
