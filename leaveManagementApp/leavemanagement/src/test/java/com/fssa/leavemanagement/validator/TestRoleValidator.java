package com.fssa.leavemanagement.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.leavemanagement.exceptions.InvalidRoleException;
import com.fssa.leavemanagement.model.Role;
import com.fssa.leavemanagement.model.RoleErrors;

public class TestRoleValidator {
    @Test
    void testValidate() {
        Role role = new Role(1, "Manager");
        try {
            Assertions.assertTrue(RoleValidator.validate(role));
        } catch (InvalidRoleException e) {
        	Assertions.assertEquals(RoleErrors.INVALID_ROLE, e.getMessage());
        }
    }

    @Test
    void testInvalid() {
        try {
            Assertions.assertTrue(RoleValidator.validate(null));
            // Should throw an exception for a null role
            Assertions.fail("Expected InvalidRoleException for null role");
            
        } catch (InvalidRoleException e) {
            Assertions.assertEquals(RoleErrors.INVALID_ROLE, e.getMessage());
        }
    }

    @Test
    void testValidId() {
        try {
            Assertions.assertTrue(RoleValidator.validateId(2));
        } catch (InvalidRoleException e) {
            // Should not throw an exception for a valid id
        	Assertions.assertEquals(RoleErrors.INVALID_ID, e.getMessage());
        }
    }

    @Test
    void testInvalidId() {
        try {
            Assertions.assertTrue(RoleValidator.validateId(0));
            // Should throw an exception for an invalid id
            Assertions.fail("Expected InvalidRoleException for an invalid id");
        } catch (InvalidRoleException e) {
            Assertions.assertEquals(RoleErrors.INVALID_ID, e.getMessage());
        }
    }

    @Test
    void testValidName() {
        try {
            Assertions.assertTrue(RoleValidator.validateName("HR"));
        } catch (InvalidRoleException e) {
            // Should not throw an exception for a valid name
            Assertions.fail("Unexpected InvalidRoleException for a valid name");
        }
    }

    @Test
    void testInvalidName() {
        try {
            RoleValidator.validateName("i");
            // Expecting an exception, so the test should fail if no exception is thrown
            Assertions.fail("Expected InvalidRoleException for an invalid name");
        } catch (InvalidRoleException e) {
        	Assertions.assertEquals(RoleErrors.INVALID_NAME, e.getMessage());
        }
    }
}
