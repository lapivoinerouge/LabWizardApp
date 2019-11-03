package com.lab.wizard.controller.exception;

public class NotFoundException extends Exception {
    public NotFoundException(Long id) {
        super("Id " + id + " not found.");
    }
}
