import { useEffect, useState } from "react";
import BatteryService from "../services/BatteryService";

function DashboardCards({ refresh }) {

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

    const total = batteries.length;

    const active = batteries.filter(
        battery => battery.state.toUpperCase() === "ACTIVE"
    ).length;

    const charging = batteries.filter(
        battery => battery.state.toUpperCase() === "CHARGING"
    ).length;

    return (
        <div className="container mt-4">
            <div className="row">

                <div className="col-md-4 mb-3">
                    <div className="card text-white bg-primary shadow">
                        <div className="card-body text-center">
                            <h5 className="card-title">Total Batteries</h5>
                            <h1>{total}</h1>
                        </div>
                    </div>
                </div>

                <div className="col-md-4 mb-3">
                    <div className="card text-white bg-success shadow">
                        <div className="card-body text-center">
                            <h5 className="card-title">Active Batteries</h5>
                            <h1>{active}</h1>
                        </div>
                    </div>
                </div>

                <div className="col-md-4 mb-3">
                    <div className="card text-dark bg-warning shadow">
                        <div className="card-body text-center">
                            <h5 className="card-title">Charging Batteries</h5>
                            <h1>{charging}</h1>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    );
}

export default DashboardCards;