package com.gridweaver.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.gridweaver.dto.BatteryDTO;
import com.gridweaver.entity.Battery;
import com.gridweaver.repository.BatteryRepository;
import com.gridweaver.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class BatteryServiceImplTest {

    @Mock
    private BatteryRepository batteryRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private BatteryServiceImpl batteryService;
    
    @Test
    void testGetBatteryById() {

        Battery battery = new Battery();

        battery.setId(1L);
        battery.setBatteryName("Amaron");
        battery.setBatteryType("Lead Acid");
        battery.setCapacity(6500.0);
        battery.setVoltage(12.0);

        when(batteryRepository.findById(1L))
                .thenReturn(Optional.of(battery));

        BatteryDTO result = batteryService.getBatteryById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Amaron", result.getBatteryName());
        assertEquals("Lead Acid", result.getBatteryType());
        assertEquals(6500.0, result.getCapacity());
        assertEquals(12.0, result.getVoltage());
    }
    }
    
