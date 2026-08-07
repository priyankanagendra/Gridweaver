import { useEffect, useState } from "react";
import BatteryService from "../services/BatteryService";

import {
    Chart as ChartJS,
    ArcElement,
    Tooltip,
    Legend
} from "chart.js";

import { Pie } from "react-chartjs-2";

ChartJS.register(
    ArcElement,
    Tooltip,
    Legend
);

function BatteryChart({ refresh }) {

    const [batteries, setBatteries] = useState([]);

    useEffect(() => {
        BatteryService.getAllBatteries()
            .then((response) => {
                setBatteries(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }, [refresh]);

    const active = batteries.filter(
        battery => battery.state.toUpperCase() === "ACTIVE"
    ).length;

    const charging = batteries.filter(
        battery => battery.state.toUpperCase() === "CHARGING"
    ).length;

    const inactive = batteries.filter(
        battery => battery.state.toUpperCase() === "INACTIVE"
    ).length;

    const data = {
        labels: ["ACTIVE", "CHARGING", "INACTIVE"],
        datasets: [
            {
                data: [active, charging, inactive],
                backgroundColor: [
                    "#28a745",
                    "#ffc107",
                    "#dc3545"
                ]
            }
        ]
    };

    return (
        <div className="container mt-4">

            <div className="card shadow">

                <div className="card-header bg-info text-white">
                    <h3 className="text-center">
                        Battery Status Chart
                    </h3>
                </div>

                <div className="card-body">

                    <div style={{
                        width: "350px",
                        margin: "auto"
                    }}>

                        <Pie data={data} />

                    </div>

                </div>

            </div>

        </div>
    );
}

export default BatteryChart;