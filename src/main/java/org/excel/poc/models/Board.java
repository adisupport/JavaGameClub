package org.excel.poc.models;

import org.excel.poc.dto.TicTakToeMoves;
import org.excel.poc.enums.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Board {
    List<List<Cell>> cells;
    int emptyCells = 0;

    public record Size(int n ,int m){}

    public Board(Size size){
        cells = new ArrayList<>();
        emptyCells = size.n() * size.m();
        for(int i=0;i<size.n();i++){
            var list = new ArrayList<Cell>();
            for(int j=0;j<size.m();j++){
                list.add( new Cell());
            }
            cells.add(list);
        }
    }

    public boolean isFull(){
        return emptyCells == 0;
    }

    public void fillCell(TicTakToeMoves moves){
       TicTakToeMoves.Pos pos =  moves.pos();
       var cell = cells.get(pos.row()).get(pos.col());
       cell.setPlayer(moves.player());
       emptyCells--;
    }


    public Cell getCell(TicTakToeMoves.Pos pos){
        return cells.get(pos.row()).get(pos.col());
    }

    public Boolean matching(TicTakToeMoves.Pos pos , Player player){
        String store = Optional.ofNullable(cells.get(pos.row()).get(pos.col()))
                .map(Cell::getPlayer)
                .map(Player::symbol)
                .map(Symbol::getValue)
                .orElse("");
        String given = Optional.ofNullable(player)
                .map(Player::symbol)
                .map(Symbol::getValue)
                .orElse("-");
        return store.equals(given);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Cell> cell : cells) {
            for (Cell c : cell) {
                sb.append(c);
                sb.append("\t");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
