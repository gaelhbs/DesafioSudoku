package br.com.dio;

import br.com.dio.model.Board;
import br.com.dio.model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    private static Board board;

    private final static  int BOARD_SIZE = 9;


    public static void main(String[] args) {
        final var positions = Stream.of(args)
                .collect(Collectors.toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));

        var option = -1;
        while (true){
            System.out.println("1 - Start a new game");
            System.out.println("2 - Put a new number");
            System.out.println("3 - Remove a number");
            System.out.println("4 - View the actual game");
            System.out.println("5 - Verify the game status");
            System.out.println("6 - Clear the game");
            System.out.println("7 - Finish the game");
            System.out.println("8 - Exit");
            option = scanner.nextInt();
            switch (option){
                case 1:
                    startGame(positions);
                    break;
                case 2:
                    inputNumber();
                    break;
                case 3:
                    removeNumber();
                    break;
                case 4:
                    showCurrentGame();
                    break;
                case 5:
                    showGameStatus();
                case 6:
                    clearGame();
                    break;
                case 7:
                    finishGame();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Invalid option");
            }
        }


    }

    private static void startGame(Map<String, String> positions) {
        if (nonNull(board)){
            System.out.println("The game has already started");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++){
            spaces.add(new ArrayList<>());

            for (int j = 0; j < BOARD_SIZE; j++){
                var positionConfig = positions.get("%s,%s".formatted(i, j));
                var expected = Integer.parseInt(positionConfig.split(";")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(";")[1]);
                var currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }
        board = new Board(spaces);
        System.out.println("Game started");
    }

    private static void inputNumber() {
        if (isNull(board)){
            System.out.println("The game has not started yet");
            return;
        }
        System.out.println("Enter the column");
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Enter the row");
        var row = runUntilGetValidNumber(0, 8);
        System.out.printf("Enter the number to be inserted on the position %s, %s", col, row);
        var number = runUntilGetValidNumber(1, 9);
        if (!board.changeValue(col, row, number)) {
            System.out.printf("The position [%s, %s] is fixed and cannot be changed%n", col, row);
        } else {
            System.out.println("Number inserted successfully");
        }


    }

    private static void removeNumber() {
        if (isNull(board)){
            System.out.println("The game has not started yet");
            return;
        }
        System.out.println("Enter the column");
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Enter the row");
        var row = runUntilGetValidNumber(0, 8);
        System.out.printf("Enter the number to be inserted on the position %s, %s", col, row);

        if (!board.clearValue(col, row)) {
            System.out.printf("The position [%s, %s] is fixed and cannot be changed%n", col, row);
        } else {
            System.out.println("Number removed successfully");
        }

    }

    private static void showCurrentGame() {

    }

    private static void showGameStatus() {

    }

    private static void clearGame() {

    }


    private static void finishGame() {
    }

    private static int runUntilGetValidNumber(final int min, final int max) {
        Scanner scanner = new Scanner(System.in);
        var current = scanner.nextInt();

        while (current < min || current > max){
            System.out.printf("Input a number between %s and %s\n%n", min, max);
            current = scanner.nextInt();
        }

        return current;
    }













}