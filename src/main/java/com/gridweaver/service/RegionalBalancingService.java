package com.gridweaver.service;

import org.springframework.stereotype.Service;

import com.gridweaver.dto.PowerTransferResponseDTO;
import com.gridweaver.dto.ZonePowerDTO;

@Service
public class RegionalBalancingService {

    public PowerTransferResponseDTO balancePower(
            ZonePowerDTO sourceZone,
            ZonePowerDTO targetZone) {

        double sourceNetPower =
                sourceZone.getNetPower();

        double targetNetPower =
                targetZone.getNetPower();


        if (sourceNetPower <= 0) {

            return new PowerTransferResponseDTO(
                    sourceZone.getZoneName(),
                    targetZone.getZoneName(),
                    Math.max(sourceNetPower, 0),
                    Math.max(-targetNetPower, 0),
                    0.0,
                    "NO_SURPLUS"
            );
        }


        if (targetNetPower >= 0) {

            return new PowerTransferResponseDTO(
                    sourceZone.getZoneName(),
                    targetZone.getZoneName(),
                    sourceNetPower,
                    0.0,
                    0.0,
                    "NO_DEFICIT"
            );
        }


        double targetDeficit =
                Math.abs(targetNetPower);


        double transferAmount =
                Math.min(
                        sourceNetPower,
                        targetDeficit
                );


        return new PowerTransferResponseDTO(
                sourceZone.getZoneName(),
                targetZone.getZoneName(),
                sourceNetPower,
                targetDeficit,
                transferAmount,
                "SUCCESS"
        );
    }
}