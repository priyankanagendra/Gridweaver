import axios from "axios";

const BASE_URL = "http://localhost:8080/battery";

class BatteryService {

    getHeaders() {

        const token = localStorage.getItem("token");

        return {
            headers: {
                Authorization: `Bearer ${token}`
            }
        };

    }

    // Get All Batteries
    getAllBatteries() {

        return axios.get(
            BASE_URL,
            this.getHeaders()
        );

    }

    // Add Battery
    saveBattery(battery) {

        return axios.post(
            BASE_URL,
            battery,
            this.getHeaders()
        );

    }

    // Update Battery
    updateBattery(id, battery) {

        return axios.put(
            `${BASE_URL}/${id}`,
            battery,
            this.getHeaders()
        );

    }

    // Delete Battery
    deleteBattery(id) {

        return axios.delete(
            `${BASE_URL}/${id}`,
            this.getHeaders()
        );

    }

}

export default new BatteryService();