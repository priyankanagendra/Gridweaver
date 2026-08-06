import axios from "axios";

const BASE_URL = "http://localhost:8080/battery";

class BatteryService {

    getAllBatteries() {
        return axios.get(BASE_URL);
    }

}

export default new BatteryService();