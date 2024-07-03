package com.sanienterprise.dawn.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @GetMapping
    public String getError(HttpServletRequest request) {

        System.out.println("An error occured!");

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null & Integer.valueOf(status.toString()) == HttpStatus.NOT_FOUND.value()) {
            return "404";
        } else if(status != null & Integer.valueOf(status.toString()) == HttpStatus.FORBIDDEN.value()) {
            return "403";
        } else if(status != null & Integer.valueOf(status.toString()) == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return "500";
        } else if(status != null & Integer.valueOf(status.toString()) == HttpStatus.BAD_REQUEST.value()) {
            return "400";
        } else if(status != null & Integer.valueOf(status.toString()) == HttpStatus.UNAUTHORIZED.value()) {
            return "401";
        } else if(status != null & Integer.valueOf(status.toString()) == HttpStatus.METHOD_NOT_ALLOWED.value()) {
            return "405";
        } else if(status != null & Integer.valueOf(status.toString()) == HttpStatus.CONFLICT.value()) {
            return "409";
        } else if(status != null & Integer.valueOf(status.toString()) == HttpStatus.TOO_MANY_REQUESTS.value()) {
            return "429";
        } else if(status != null & Integer.valueOf(status.toString()) == HttpStatus.BAD_GATEWAY.value()) {
            return "502";
        } else if(status != null & Integer.valueOf(status.toString()) == HttpStatus.SERVICE_UNAVAILABLE.value()) {
            return "503";
        }

        return "redirect:/";
    }
}