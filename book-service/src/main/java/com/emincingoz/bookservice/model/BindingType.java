package com.emincingoz.bookservice.model;

import lombok.Getter;

/**
 * Enum class for BOOK table binding type column
 * @author Emin Cingoz
 * @version 4/23/2023
 */
@Getter
public enum BindingType {
    /**
     * SADDLE Stitch Binding
     */
    SADDLE_STITCH("SADDLE_STITCH"),
    /**
     * PUR Binding
     */
    PUR("PUR"),
    /**
     * Hardcover Case Binding
     */
    HARDCOVER_CASE("HARDCOVER_CASE"),
    /**
     * Singer Sewn Binding
     */
    SINGER_SEWN("SINGER_SEWN"),
    /**
     * Section Sewn Binding
     */
    SECTION_SEWN("SECTION_SEWN"),
    /**
     * Coptic Stitch Binding
     */
    COPTIC_STITCH("COPTIC_STITCH"),
    /**
     * Spiral Binding
     */
    SPIRAL("SPIRAL"),
    /**
     * Chicago screw binding, also known as Interscrew binding
     */
    CHICAGO_SCREW("CHICAGO_SCREW"),
    /**
     * Japanese Binding
     */
    JAPANESE("JAPANESE");

    private final String text;

    BindingType(String text) {
        this.text = text;
    }
}
