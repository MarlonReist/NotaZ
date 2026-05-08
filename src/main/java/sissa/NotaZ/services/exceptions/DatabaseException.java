package sissa.NotaZ.services.exceptions;

public class DatabaseException extends RuntimeException{

    public DatabaseException() {
        super("Esse email já existe");
    }

    public DatabaseException(String msg) {
        super(msg);
    }
}

