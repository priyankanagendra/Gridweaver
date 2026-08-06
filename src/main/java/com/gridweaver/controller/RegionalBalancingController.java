package com.gridweaver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gridweaver.dto.BalanceRequest;
import com.gridweaver.service.RegionalBalancingService;

@RestController
@RequestMapping("/balance")
public class RegionalBalancingController {

    @Autowired
    private RegionalBalancingService regionalBalancingService;

    @PostMapping
    public String balancePower(@RequestBody BalanceRequest request) {

        return regionalBalancingService.balancePower(request);

    }

}