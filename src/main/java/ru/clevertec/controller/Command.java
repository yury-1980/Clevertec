package ru.clevertec.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface Command {
    void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}