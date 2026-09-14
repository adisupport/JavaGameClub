package org.excel.poc.tictactoe;


import lombok.Getter;
import lombok.Setter;
import org.excel.poc.dto.TicTakToeMoves;
import org.excel.poc.enums.GameStatus;
import org.excel.poc.enums.Symbol;
import org.excel.poc.models.Board;
import org.excel.poc.models.Player;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class TicTacToe {
    private final Board board;
    private Symbol currentSymbol;
    private final Board.Size size;
    private GameStatus gameStatus;
    private Scanner scanner;
    private Player firstPlayer;
    private Player secondPlayer;
    private Player currentPlayer;
    public TicTacToe(Board.Size boardSize, Player firstPlayer, Player secondPlayer) {
        board = new Board(boardSize);
        currentSymbol = firstPlayer.symbol();
        size = boardSize;
        scanner = new Scanner(System.in);
        this.secondPlayer = secondPlayer;
        this.firstPlayer = firstPlayer;
        this.currentPlayer = firstPlayer;
        gameStatus = GameStatus.IN_PROGRESS;
    }

    public GameStatus play() throws IllegalArgumentException{
        List<String> line = List.of(scanner.nextLine().trim().split(" "));
        if(line.size() < 2){ throw  new IllegalArgumentException("Please provide input as row space column."); }
        Integer row = Integer.valueOf(line.get(0));
        Integer col = Integer.valueOf(line.get(1));
        return makeMove(new TicTakToeMoves(currentPlayer,new TicTakToeMoves.Pos(row,col)));
    }

    private GameStatus makeMove(TicTakToeMoves move) throws RuntimeException{
        if(!isValidMove(move)){
            throw new RuntimeException(String.format("Player %s has move", currentSymbol.getValue()));
        }
        board.fillCell(move);
        if(winner(move)){
            GameStatus.FINISHED.getPlayer().add(move.player());
            scanner.close();
            return gameStatus = GameStatus.FINISHED;
        }
        if(board.isFull()){
            return gameStatus = GameStatus.DRAW;
        }
        currentSymbol = Symbol.X.equals(currentSymbol) ? Symbol.O : Symbol.X;
        currentPlayer = currentPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;

        return gameStatus = GameStatus.IN_PROGRESS;
    }

    public Boolean isValidMove(TicTakToeMoves move){
        Symbol curr = move.player().symbol();
        return currentSymbol == curr && board.getCell(move.pos()).isEmpty();
    }

    public Boolean winner(TicTakToeMoves moves){

        for(int i=0;i<size.n();i++){
            int match = 0;
            for(int j=0;j<size.m();j++){
                if(board.matching(new TicTakToeMoves.Pos(i,j), moves.player())){
                    match++;
                }
            }
            if(match == size.m()) return  true;
        }
        for(int i=0;i<size.m();i++){
            int match = 0;
            int leftDiagonal = 0;
            int rightDiagonal = 0;
            for(int j=0;j<size.n();j++){
                Boolean matched = board.matching(new TicTakToeMoves.Pos(i,j), moves.player());
                if(matched){
                    match++;
                }
                if(i == j && matched){
                    leftDiagonal++;
                }
                if(i == size.m() - 1 - j && matched){
                    rightDiagonal++;
                }
            }
            if(match == size.m() || leftDiagonal == size.n() || rightDiagonal == size.n()) return  true;
        }
        return false;
    }

}
