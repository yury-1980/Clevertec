package ru.clevertec.controller;


public class CommandProvider {

    public static Command takeCommand(String commandName) {
        return CommandType.getCommandMap().get(commandName);
    }
}
