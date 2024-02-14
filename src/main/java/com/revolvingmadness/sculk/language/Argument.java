package com.revolvingmadness.sculk.language;

import java.util.Objects;

public class Argument {
    public final String name;
    public final String type;

    public Argument(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Argument argument = (Argument) o;
        return Objects.equals(this.name, argument.name) && Objects.equals(this.type, argument.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.type);
    }
}
