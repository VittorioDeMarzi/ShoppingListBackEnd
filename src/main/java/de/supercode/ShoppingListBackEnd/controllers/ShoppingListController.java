package de.supercode.ShoppingListBackEnd.controllers;

import de.supercode.ShoppingListBackEnd.entities.ShoppingListItem;
import de.supercode.ShoppingListBackEnd.servicies.ShoppingListService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1/shoppinglist")
public class ShoppingListController {
    ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    // POST
    @PostMapping
    public ResponseEntity<?> addProductToShoppingList(@Validated @RequestBody ShoppingListItem shoppingListItem) {
        try {
            shoppingListService.addProductToShoppingList(shoppingListItem);
            return new ResponseEntity<>(shoppingListItem, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product " + shoppingListItem.getName() + " is already in the list!");
        }
    }

    // Get
    @GetMapping
    public ResponseEntity<?> getShoppingList() {
        try {
            return new ResponseEntity<>(shoppingListService.getShoppingList(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Filter by Status
    @GetMapping("/status/bought=true")
    public ResponseEntity<?> getBoughtProducts() {
        try {
            return new ResponseEntity<>(shoppingListService.findProductByStatus(true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/status/bought=false")
    public ResponseEntity<?> getProductsToBuy() {
        try {
            return new ResponseEntity<>(shoppingListService.findProductByStatus(false), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    // Delete
    @DeleteMapping("/{listItemId}")
    public ResponseEntity<?> deleteProduct(@PathVariable long listItemId) {
        try {
            shoppingListService.deleteProduct(listItemId);
            return ResponseEntity.status(HttpStatus.OK).body("Product with ID: " + listItemId + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID: " + listItemId + " not found.");
        }
    }

    // update
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable long productId) {
        try {
            shoppingListService.updateProduct(productId);
            return ResponseEntity.status(HttpStatus.OK).body("Product was bought successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID: " + productId + " not found.");
        }
    }

    // Spring Boot Validations - Jpa Jakarta
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationError(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errorMap.put(fieldName, message);
        });
        return errorMap;
    }
}
