import axios from "axios";

const BASE_URL = "http://localhost:8080";

class AuthService {

    login(loginData) {
        return axios.post(`${BASE_URL}/auth/login`, loginData);
    }

    getToken() {
        return localStorage.getItem("token");
    }

    logout() {
        localStorage.removeItem("token");
    }

    isLoggedIn() {
        return this.getToken() !== null;
    }

    // Decode JWT
    parseJwt(token) {

        if (!token) return null;

        try {

            return JSON.parse(atob(token.split(".")[1]));

        } catch (error) {

            return null;

        }
    }

    // Get Username
    getUsername() {

        const token = this.getToken();

        const decoded = this.parseJwt(token);

        return decoded ? decoded.sub : "";

    }

    // Get Role
    getRole() {

        const token = this.getToken();

        const decoded = this.parseJwt(token);

        return decoded ? decoded.role : "";

    }

}

export default new AuthService();