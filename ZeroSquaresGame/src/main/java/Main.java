import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example game boards
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
            {"X", ".", ".", ".",".", ".", "X"},
            {"X", "Y", ".", ".", ".", ".", "X"},
            {"X", "X", ".", ".",".", ".", "X"},
            {"X", "X", ".", "R",".", "B", "X"},
            {"X", "X", "X", "X","X", "X", "X"},
            {"X", "X", "X", "X","X", "X", "X"},
        };

        String[][] board3 = {
            {"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
            {"X", "Y", "R", "B", "C", ".", ".", ".", ".", ".", "X"},
            {"X", ".", ".", ".",".", ".", "GY", ".", ".", ".", "X"},
            {"X", "X", ".", ".",".", "GC", ".", ".", ".", ".", "X"},
            {"X", "X", ".", ".","GB", ".", "X", "X", "X", "X", "X"},
            {"X", "X", ".", "GR",".", ".", "X", "X", "X", "X", "X"},
            {"X", "X", "X", "X","X", "X", "X", "X", "X", "X", "X"},
            {"X", "X", "X", "X","X", "X", "X", "X", "X", "X", "X"},
            {"X", "X", "X", "X","X", "X", "X", "X", "X", "X", "X"},
            {"X", "X", "X", "X","X", "X", "X", "X", "X", "X", "X"},
            {"X", "X", "X", "X","X", "X", "X", "X", "X", "X", "X"},
        };

        // Choose the board
        System.out.println("Choose The Board You prefer To Play (1/2/3): ");
        String choice = scanner.nextLine();
        ZeroSquaresGame game;

        if (choice.equals("1")) {
            game = new ZeroSquaresGame(5, board1);
            game.play();
        } else if (choice.equals("2")) {
            game = new ZeroSquaresGame(7, board2);
            game.play();
        } else if (choice.equals("3")) {
            game = new ZeroSquaresGame(11, board3);
            game.play();
        } else {
            System.out.println("Invalid choice");
        }
    }
}
