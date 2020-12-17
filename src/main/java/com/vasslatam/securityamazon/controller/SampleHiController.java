/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasslatam.securityamazon.controller;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego Silva diego.silva at vasslatam.com
 */
@RestController
public class SampleHiController {

    @GetMapping(path = "/hola/1", produces = TEXT_PLAIN_VALUE)
    public String hi1() {
        return "Hola 1";
    }

    @GetMapping(path = "/hola/2", produces = TEXT_PLAIN_VALUE)
    public String hi2() {
        return "Hola 2";
    }
}
