import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Define the boards as 2D arrays of strings
        String[][] board1 = {
            {"X", "X", "X", "X", "X"},
            {"X", "Y", ".", ".", "X"},
            {"X", ".", "GY", ".", "X"},
            {"X", ".", ".", "X", "X"},
            {".", "X", "X", "X", "."}
        };

        String[][] board2 = {
            {"X", "X", "X", "X", "X", "X", "X"},
            {"X", "GB", "X", "GY", "X", "GR", "X"},
            {"X", ".", ".", ".", ".", ".", "X"},
            {"X", "Y", ".", ".", ".", ".", "X"},
            {"X", "X", ".", ".", ".", ".", "X"},
            {"X", "X", ".", "R", ".", "B", "X"},
            {"X", "X", "X", "X", "X", "X", "X"},
            {"X", "X", "X", "X", "X", "X", "X"}
        };

        String[][] board3 = {
            {"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
            {"X", "Y", "R", "B", "C", ".", ".", ".", ".", ".", "X"},
            {"X", ".", ".", ".", ".", ".", "GY", ".", ".", ".", "X"},
            {"X", "X", ".", ".", ".", "GC", ".", ".", ".", ".", "X"},
            {"X", "X", ".", ".", "GB", ".", "X", "X", "X", "X", "X"},
            {"X", "X", ".", "GR", ".", ".", "X", "X", "X", "X", "X"},
            {"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
            {"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
            {"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
            {"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
            {"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"}
        };

        // Scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Ask user to choose a board
        System.out.println("Choose The Board You prefer To Play (1/2/3): ");
        String choose = scanner.nextLine();

        // Create a game instance based on the user's choice
        ZeroSqure game = null;
        if (choose.equals("1")) {
          game = new ZeroSqure (5, board1);
        } else if (choose.equals("2")) {
            game = new ZeroSqure(7, board2);
        } else if (choose.equals("3")) {
            game = new ZeroSqure(11, board3);
        } else {
            System.out.println("Invalid choice");
            return; // Exit the program if input is invalid
        }

        // Start the game
        game.play();
    }
}

        
        
       