package stud.oiv.migration.controller;

public interface Command {
    String execute(String ... params);
}
