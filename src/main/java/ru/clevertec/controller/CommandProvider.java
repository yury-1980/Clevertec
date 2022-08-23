package ru.clevertec.controller;


public class CommandProvider {
//    private CommandProvider() {
//    }

    public static Command takeCommand(String commandName) {
        return CommandType.getCommandMap().get(commandName);
    }
}
