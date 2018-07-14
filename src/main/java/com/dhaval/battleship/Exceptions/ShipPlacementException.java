package com.dhaval.battleship.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST,reason = "Ship positions are invalid")
public class ShipPlacementException extends Exception{

}
