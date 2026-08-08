import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import BatteryForm from "../components/BatteryForm";
import BatteryList from "../components/BatteryList";
import DashboardCards from "../components/DashboardCards";
import BatteryChart from "../components/BatteryChart";
import MapComponent from "../components/MapComponent";
import EventLog from "../components/EventLog";

import WebSocketService from "../services/WebSocketService";
import AuthService from "../services/AuthService";

function Home() {

    const navigate = useNavigate();

    const [refresh, setRefresh] = useState(false);
    const [selectedBattery, setSelectedBattery] = useState(null);

    useEffect(() => {

        // Check Login
        if (!AuthService.isLoggedIn()) {
            navigate("/login");
            return;
        }

        console.log("Connecting to WebSocket...");

        WebSocketService.connect((battery) => {

            console.log("🔥 WebSocket Callback Executed");
            console.log("Received Battery:", battery);

            setRefresh(prev => !prev);

        });

        return () => {
            WebSocketService.disconnect();
        };

    }, [navigate]);

    const handleLogout = () => {

        AuthService.logout();

        alert("Logged Out Successfully");

        navigate("/login");

    };

    return (

        <div className="container mt-4">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h1>GridWeaver Dashboard</h1>

                <button
                    className="btn btn-danger"
                    onClick={handleLogout}
                >
                    Logout
                </button>

            </div>

            {/* Dashboard Cards */}
            <DashboardCards refresh={refresh} />

            <br />

            {/* Pie Chart */}
            <BatteryChart refresh={refresh} />

            <br />

            {/* Battery Form */}
            <BatteryForm
                refresh={refresh}
                setRefresh={setRefresh}
                selectedBattery={selectedBattery}
                setSelectedBattery={setSelectedBattery}
            />

            <br />

            {/* Battery List */}
            <BatteryList
                refresh={refresh}
                setSelectedBattery={setSelectedBattery}
            />

            <br />

            {/* Map */}
            <MapComponent refresh={refresh} />

            <br />

            {/* Event Log */}
            <EventLog />

        </div>

    );
}

export default Home;