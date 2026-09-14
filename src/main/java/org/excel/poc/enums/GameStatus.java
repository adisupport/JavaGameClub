package org.excel.poc.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.excel.poc.models.Player;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public enum GameStatus {
    IN_PROGRESS(null),
    DRAW(null),
    FINISHED(new ArrayList<>());

    final List<Player> player;
}
