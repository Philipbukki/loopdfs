package org.smunyau.loopdfs.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s: %d",resourceName,fieldName,fieldValue));

    }
}
