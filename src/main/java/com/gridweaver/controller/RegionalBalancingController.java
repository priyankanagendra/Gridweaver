package com.gridweaver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gridweaver.dto.ZonePowerDTO;
import com.gridweaver.service.RegionalBalancingService;

@RestController
@RequestMapping("/balancing")
public class RegionalBalancingController {

    private final RegionalBalancingService regionalBalancingService;

    public RegionalBalancingController(
            RegionalBalancingService regionalBalancingService) {

        this.regionalBalancingService = regionalBalancingService;
    }

    @PostMapping
    public String balancePower(
            @RequestBody ZonePowerDTO[] zones) {

        if (zones == null || zones.length != 2) {
            return "Exactly two zones are required for balancing";
        }

        ZonePowerDTO sourceZone = zones[0];
        ZonePowerDTO targetZone = zones[1];

        return regionalBalancingService.balancePower(
                sourceZone,
                targetZone
        );
    }
}