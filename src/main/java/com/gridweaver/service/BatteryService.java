package com.gridweaver.service;
import com.gridweaver.exception.BatteryNotFoundException;
import com.gridweaver.dto.BatteryDTO;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gridweaver.entity.Battery;
import com.gridweaver.repository.BatteryRepository;

@Service
public class BatteryService {
	
	
	
    @Autowired
    private BatteryRepository batteryRepository;

    public Battery saveBattery(Battery battery) {
        return batteryRepository.save(battery);
    } 
    

    public List<Battery> getAllBatteries() {
        return batteryRepository.findAll();
    }

    public Battery getBatteryById(Long id) {
        return batteryRepository.findById(id)
                .orElseThrow(() -> new BatteryNotFoundException("Battery not found with id: " + id));
    }
    
        // public Battery updateBattery(Long id, Battery battery) {
      //  battery.setId(id);
       // return batteryRepository.save(battery);
        public Battery updateBattery(Long id, Battery battery) {

            Battery existingBattery = batteryRepository.findById(id)
                    .orElseThrow(() -> new BatteryNotFoundException("Battery not found with id: " + id));

            existingBattery.setBatteryName(battery.getBatteryName());
            existingBattery.setLocation(battery.getLocation());
            existingBattery.setPower(battery.getPower());
            existingBattery.setState(battery.getState());

            return batteryRepository.save(existingBattery);
        }
    

    //public void deleteBattery(Long id) {
     //   batteryRepository.deleteById(id);
      /*  public void deleteBattery(Long id) {

            Battery battery = batteryRepository.findById(id)
                    .orElseThrow(() -> new BatteryNotFoundException("Battery not found with id: " + id));

            batteryRepository.delete(battery);
        }
    }*/
        
        public void deleteBattery(Long id) {

            Battery battery = batteryRepository.findById(id)
                    .orElseThrow(() -> new BatteryNotFoundException("Battery not found with id: " + id));

            batteryRepository.delete(battery);
        }

        public List<BatteryDTO> getAllBatteryDTOs() {

            return batteryRepository.findAll()
                    .stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        }

        private BatteryDTO convertToDTO(Battery battery) {

            BatteryDTO dto = new BatteryDTO();

            dto.setId(battery.getId());
            dto.setBatteryName(battery.getBatteryName());

            // Change these according to your Battery entity fields
            //dto.setBatteryType(battery.getState());
            //dto.setCapacity(battery.getPower());
            //dto.setVoltage(12.0);
            dto.setLocation(battery.getLocation());
            dto.setPower(battery.getPower());
            dto.setState(battery.getState());

            return dto;
        }
        }
