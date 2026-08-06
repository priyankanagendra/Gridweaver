import { useEffect, useState } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import BatteryService from "../services/BatteryService";

function MapComponent() {

    const [batteries, setBatteries] = useState([]);

    useEffect(() => {
        BatteryService.getAllBatteries()
            .then((response) => {
                setBatteries(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }, []);

    const getCoordinates = (location) => {

        switch (location.toLowerCase()) {

            case "bangalore":
                return [12.9716, 77.5946];

            case "hyderabad":
                return [17.3850, 78.4867];

            default:
                return [15.3173, 75.7139];
        }
    };

    return (
        <div style={{ marginTop: "20px" }}>
            <h2>Battery Locations</h2>

            <MapContainer
                center={[15.3173, 75.7139]}
                zoom={6}
                style={{ height: "500px", width: "100%" }}
            >
                <TileLayer
                    attribution="&copy; OpenStreetMap contributors"
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />

                {batteries.map((battery) => (
                    <Marker
                        key={battery.id}
                        position={getCoordinates(battery.location)}
                    >
                        <Popup>
                            <b>{battery.batteryName}</b>
                            <br />
                            Location: {battery.location}
                            <br />
                            Zone: {battery.zone}
                            <br />
                            Power: {battery.power} kW
                            <br />
                            State: {battery.state}
                        </Popup>
                    </Marker>
                ))}

            </MapContainer>
        </div>
    );
}

export default MapComponent;