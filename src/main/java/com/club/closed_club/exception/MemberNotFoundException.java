package com.club.closed_club.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Integer id) {
        super("Member not found" + id);
    }

}