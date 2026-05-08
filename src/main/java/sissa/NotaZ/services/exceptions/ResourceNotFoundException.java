package sissa.NotaZ.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id) {
        super("Recurso não encontrado. Id: " + id);
    }
}

