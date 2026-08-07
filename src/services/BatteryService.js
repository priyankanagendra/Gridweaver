import axios from "axios";

const BASE_URL = "http://localhost:8080/battery";

class BatteryService {

    // Get All Batteries
    getAllBatteries() {
        return axios.get(BASE_URL);
    }

    // Add Battery
    saveBattery(battery) {
        return axios.post(BASE_URL, battery);
    }

    // Update Battery
    updateBattery(id, battery) {
        return axios.put(`${BASE_URL}/${id}`, battery);
    }

    // Delete Battery
    deleteBattery(id) {
        return axios.delete(`${BASE_URL}/${id}`);
    }

}

export default new BatteryService();