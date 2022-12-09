package com.neoris.portaltransaccional.controller.advice;

import com.neoris.portaltransaccional.dto.RespuestaError;
import com.neoris.portaltransaccional.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ClientNotFoundException.class)
    public ResponseEntity<RespuestaError> clientNotFoundException(ClientNotFoundException clientNotFoundException) {
        return new ResponseEntity<>(new RespuestaError(ClientNotFoundException.class.getSimpleName(), "No existe el cliente seleccionado"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AccountNotFoundException.class)
    public ResponseEntity<RespuestaError> accountNotFoundException(AccountNotFoundException accountNotFoundException) {
        return new ResponseEntity<>(new RespuestaError(AccountNotFoundException.class.getSimpleName(), "No existe la cuenta seleccionada"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TransactionNotFoundException.class)
    public ResponseEntity<RespuestaError> transactionNotFoundException(TransactionNotFoundException transactionNotFoundException) {
        return new ResponseEntity<>(new RespuestaError(TransactionNotFoundException.class.getSimpleName(), "No existe el movimiento seleccionado"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TransactionConflictException.class)
    public ResponseEntity<RespuestaError> transactionConflictException(TransactionConflictException transactionConflictException) {
        return new ResponseEntity<>(new RespuestaError(TransactionConflictException.class.getSimpleName(),
                "No se puede editar el movimiento debido a que ya existen movimientos posteriores para la cuenta"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = BalanceLowerThanZeroException.class)
    public ResponseEntity<RespuestaError> balanceLowerThanZeroException(BalanceLowerThanZeroException balanceLowerThanZeroException) {
        return new ResponseEntity<>(new RespuestaError(BalanceLowerThanZeroException.class.getSimpleName(),
                "Saldo no disponible"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ClientAlreadyExistsException.class)
    public ResponseEntity<RespuestaError> clientAlreadyExistsException(ClientAlreadyExistsException clientAlreadyExistsException) {
        return new ResponseEntity<>(new RespuestaError(ClientAlreadyExistsException.class.getSimpleName(),
                "El cliente con la identificacion enviada ya se encuentra registrado"), HttpStatus.BAD_REQUEST);
    }
}
