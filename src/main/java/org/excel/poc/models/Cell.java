package org.excel.poc.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Cell {
    private  Player player;

    public Boolean isEmpty(){
        return player == null;
    }

    @Override
    public String toString() {
        if(isEmpty()){ return "-";}
        return player.symbol().getValue();
    }
}
