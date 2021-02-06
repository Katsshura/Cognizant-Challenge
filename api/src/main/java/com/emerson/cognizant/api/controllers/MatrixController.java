package com.emerson.cognizant.api.controllers;

import com.emerson.cognizant.api.viewModels.MatrixInputViewModel;
import com.emerson.cognizant.domain.matrix.interfaces.MatrixComparer;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Request processed with success"),
        @ApiResponse(code = 404, message = "You must provide a valid body!"),
        @ApiResponse(code = 500, message = "Something went wrong in ours servers"),
})
@RestController
@RequestMapping("/matrix")
public class MatrixController {

    private final MatrixComparer matrixComparer;

    public MatrixController(MatrixComparer matrixComparer) {
        this.matrixComparer = matrixComparer;
    }

    @ApiOperation(value = "Returns a mapped value with count occurrence values for each element from the input")
    @RequestMapping(method = RequestMethod.POST, produces="application/json")
    private ResponseEntity getArduino(@RequestBody MatrixInputViewModel input) {

        if (!input.isValid()) {
            return new ResponseEntity("You must provide values between 0 and 15!", HttpStatus.BAD_REQUEST);
        }

        try {
            var result = matrixComparer.getCountValuesForAppearanceOnMatrix(input.getValues());
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
