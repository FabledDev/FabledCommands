package dev.fabled.fabledcommands;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

final class CommandMethod {

    final List<Parameter> parameters;

    CommandMethod(Parameter... parameters) {
        this.parameters = new ArrayList<>(List.of(parameters));
    }

}
