import { useEffect, useState } from "react";
import BatteryService from "../services/BatteryService";

function BatteryList() {

    const [batteries, setBatteries] = useState([]);

    useEffect(() => {
        BatteryService.getAllBatteries()
        .then((response) => {
            console.log(response.data);
            setBatteries(response.data);
        })
            .catch((error) => {
                console.error(error);
            });
    }, []);

    return (
        <div style={{ padding: "20px" }}>
            <h2 style={{ textAlign: "center" }}>GridWeaver Battery Dashboard</h2>

            <table
                style={{
                    width: "100%",
                    borderCollapse: "collapse",
                    marginTop: "20px"
                }}
                border="1"
            >
                <thead style={{ backgroundColor: "#1976d2", color: "white" }}>
                    <tr>
                        <th>ID</th>
                        <th>Battery Name</th>
                        <th>Location</th>
                        <th>Zone</th>
                        <th>Power</th>
                        <th>State</th>
                    </tr>
                </thead>

                <tbody>
                    {batteries.map((battery) => (
                        <tr key={battery.id}>
                            <td>{battery.id}</td>
                            <td>{battery.batteryName}</td>
                            <td>{battery.location}</td>
                            <td>{battery.zone}</td>
                            <td>{battery.power} kW</td>
                            <td>{battery.state}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default BatteryList;