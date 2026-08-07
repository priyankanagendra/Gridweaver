import { useEffect, useState } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import BatteryService from "../services/BatteryService";

function MapComponent({ refresh }) {

    const [batteries, setBatteries] = useState([]);

    useEffect(() => {
        loadBatteries();
    }, [refresh]);

    const loadBatteries = () => {
        BatteryService.getAllBatteries()
            .then((response) => {
                setBatteries(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    const getCoordinates = (location) => {

        switch (location.toLowerCase()) {

            case "bangalore":
                return [12.9716, 77.5946];

            case "hyderabad":
                return [17.3850, 78.4867];

            case "chennai":
                return [13.0827, 80.2707];

            case "mumbai":
                return [19.0760, 72.8777];

            default:
                return [15.3173, 75.7139];
        }
    };

    return (
        <div className="container mt-4">

            <div className="card shadow">

                <div className="card-header bg-success text-white">
                    <h3 className="text-center mb-0">
                        Battery Locations
                    </h3>
                </div>

                <div className="card-body">

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

                                    <h5>{battery.batteryName}</h5>

                                    <hr />

                                    <b>Location :</b> {battery.location}

                                    <br />

                                    <b>Zone :</b> {battery.zone}

                                    <br />

                                    <b>Power :</b> {battery.power} kW

                                    <br />

                                    <b>Status :</b> {battery.state}

                                </Popup>

                            </Marker>

                        ))}

                    </MapContainer>

                </div>

            </div>

        </div>
    );
}

export default MapComponent;