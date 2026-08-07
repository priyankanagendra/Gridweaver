package com.gridweaver.service;

import org.springframework.stereotype.Service;

import com.gridweaver.dto.ZonePowerDTO;

@Service
public class RegionalBalancingService {

    public String balancePower(
            ZonePowerDTO sourceZone,
            ZonePowerDTO targetZone) {

        double sourceNetPower =
                sourceZone.getNetPower();

        double targetNetPower =
                targetZone.getNetPower();


        if (sourceNetPower <= 0) {

            return sourceZone.getZoneName()
                    + " does not have surplus power";
        }


        if (targetNetPower >= 0) {

            return targetZone.getZoneName()
                    + " does not require additional power";
        }


        double targetDeficit =
                Math.abs(targetNetPower);


        double transferAmount =
                Math.min(
                        sourceNetPower,
                        targetDeficit
                );


        return String.format(
                "Transferred %.2f units of power from %s to %s",
                transferAmount,
                sourceZone.getZoneName(),
                targetZone.getZoneName()
        );
    }
}