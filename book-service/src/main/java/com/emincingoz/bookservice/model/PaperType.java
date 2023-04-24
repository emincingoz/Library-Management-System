package com.emincingoz.bookservice.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Enum for BOOK Table paper type column
 * @author Emin Cingoz
 * @version 4/23/2023
 */
@Getter
public enum PaperType {
    /**
     * Bond Paper
     */
    BOND_PAPER("BOND_PAPER"),
    /**
     * Wood Free Paper
     */
    WOOD_FREE_PAPER("WOOD_FREE_PAPER"),
    /**
     * Wove Paper
     */
    WOVE("WOVE"),
    /**
     * Munken Paper
     */
    MUNKEN("MUNKEN"),
    /**
     * Recycled Paper
     */
    RECYCLED_PAPER("RECYCLED_PAPER"),
    /**
     * Parchment Paper
     */
    PARCHMENT_PAPER("PARCHMENT_PAPER"),
    /**
     * Card Paper
     */
    CARD_PAPER("CARD_PAPER"),
    /**
     * Cotton Paper
     */
    COTTON_PAPER("COTTON_PAPER"),
    /**
     * Cartridge Paper
     */
    CARTRIDGE_PAPER("CARTRIDGE_PAPER"),
    /**
     * Newsprint Paper
     */
    NEWSPRINT_PAPER("NEWSPRINT_PAPER"),
    /**
     * Laid Paper
     */
    LAID_PAPER("LAID_PAPER"),
    /**
     * Transparent Paper
     */
    TRANSPARENT_PAPER("TRANSPARENT_PAPER"),
    /**
     * End Papers
     */
    END_PAPERS("END_PAPERS"),
    /**
     * Special/Other Papers
     */
    SPECIAL_OTHER("SPECIAL_OTHER"),
    /**
     * FSC Certified Papers
     */
    FSC("FSC");

    private final String text;

    PaperType(String text) {
        this.text = text;
    }
}
