package com.emincingoz.librarymanagement.core.utilities;

import com.emincingoz.librarymanagement.core.utilities.results.Result;

public class BusinessRules {

    public static Result run(Result... rules) {
        for (Result rule : rules) {
            if (!rule.isSuccess())
                return rule;
        }
        return null;
    }
}
