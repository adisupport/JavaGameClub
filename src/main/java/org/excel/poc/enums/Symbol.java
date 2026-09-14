package org.excel.poc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Symbol {
    X("X"),
    O("O");

    private final String value;
}
