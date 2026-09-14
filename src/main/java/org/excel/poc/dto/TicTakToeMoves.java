package org.excel.poc.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.excel.poc.models.Player;

@Builder
public record TicTakToeMoves(Player player, Pos pos) {

    public record Pos(Integer row, Integer col) {}
}
