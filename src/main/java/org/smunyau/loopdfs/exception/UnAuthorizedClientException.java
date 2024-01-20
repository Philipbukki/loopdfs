package org.smunyau.loopdfs.exception;
public class UnAuthorizedClientException extends RuntimeException{
    public UnAuthorizedClientException(String resourceName, Long resourceValue) {
        super(String.format("Invalid client id entered, you can't view cards for %s " +
                "with client id %d",resourceName,resourceValue));

    }
}
