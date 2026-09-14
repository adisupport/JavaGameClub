package org.excel.poc;

import org.excel.poc.enums.GameStatus;
import org.excel.poc.enums.Symbol;
import org.excel.poc.models.Board;
import org.excel.poc.models.Player;
import org.excel.poc.tictactoe.TicTacToe;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        TicTacToe game = new TicTacToe( new Board.Size(3,3), new Player(Symbol.X), new Player(Symbol.O));
        System.out.println("Welcome to TicTacToe Game!");

        while (GameStatus.IN_PROGRESS.equals(game.getGameStatus())){
            try {
                System.out.println(game.getBoard());
                game.play();
            }
            catch (Exception e){
                System.out.println();
                System.out.println(e.getMessage());
                System.out.println("Please provide input in row space column. row and column should be in range");
            }
        }
        System.out.println(game.getBoard());

        if(GameStatus.FINISHED.equals(game.getGameStatus())){
            var winner = GameStatus.FINISHED.getPlayer();
            System.out.printf("Player %s Wins\n", winner.getFirst().symbol());
        }

        if(GameStatus.DRAW.equals(game.getGameStatus())){
            System.out.println("Draw!");
        }

        System.out.println("Thanks for playing!");
        System.exit(0);

    }
}
