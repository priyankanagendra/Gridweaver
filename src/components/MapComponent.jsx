import { useEffect, useState } from "react";
import {
    MapContainer,
    TileLayer,
    Marker,
    Popup,
    useMap
} from "react-leaflet";

import "leaflet/dist/leaflet.css";
import BatteryService from "../services/BatteryService";


// =====================================================
// CITY COORDINATES
// =====================================================

const cityCoordinates = {

    // Karnataka
    "bangalore": [12.9716, 77.5946],
    "bengaluru": [12.9716, 77.5946],

    "ballari": [15.1394, 76.9214],
    "bellary": [15.1394, 76.9214],

    "mysore": [12.2958, 76.6394],
    "mysuru": [12.2958, 76.6394],

    "mangalore": [12.9141, 74.8560],
    "mangaluru": [12.9141, 74.8560],

    // Telangana
    "hyderabad": [17.3850, 78.4867],

    // Tamil Nadu
    "chennai": [13.0827, 80.2707],
    "coimbatore": [11.0168, 76.9558],

    // Maharashtra
    "mumbai": [19.0760, 72.8777],
    "pune": [18.5204, 73.8567],

    // Kerala
    "kerala": [10.8505, 76.2711],
    "kochi": [9.9312, 76.2673],

    // Andhra Pradesh
    "vijayawada": [16.5062, 80.6480],
    "visakhapatnam": [17.6868, 83.2185],

    // Delhi
    "delhi": [28.6139, 77.2090],
    "new delhi": [28.6139, 77.2090]
};


// =====================================================
// GET CITY COORDINATES
// =====================================================

const getCoordinates = (location) => {

    if (!location) {
        return null;
    }

    const city = location
        .trim()
        .toLowerCase();

    return cityCoordinates[city] || null;
};


// =====================================================
// AUTOMATICALLY MOVE MAP TO BATTERY LOCATIONS
// =====================================================

function MapUpdater({ batteries }) {

    const map = useMap();

    useEffect(() => {

        if (!batteries || batteries.length === 0) {
            return;
        }

        const validCoordinates = batteries
            .map((battery) => getCoordinates(battery.location))
            .filter((coordinate) => coordinate !== null);


        if (validCoordinates.length === 0) {
            return;
        }


        // Only one city
        if (validCoordinates.length === 1) {

            map.setView(
                validCoordinates[0],
                8
            );

        }

        // Multiple cities
        else {

            map.fitBounds(
                validCoordinates,
                {
                    padding: [50, 50]
                }
            );

        }

    }, [batteries, map]);


    return null;
}


// =====================================================
// MAIN MAP COMPONENT
// =====================================================

function MapComponent({ refresh }) {


    // Store all batteries
    const [batteries, setBatteries] = useState([]);


    // =================================================
    // LOAD BATTERIES
    // =================================================

    useEffect(() => {

        loadBatteries();

    }, [refresh]);


    const loadBatteries = () => {

        BatteryService.getAllBatteries()

            .then((response) => {

                console.log(
                    "Batteries loaded:",
                    response.data
                );

                setBatteries(response.data);

            })

            .catch((error) => {

                console.error(
                    "Error loading batteries:",
                    error
                );

            });

    };


    // =================================================
    // FILTER ONLY ACTIVE BATTERIES
    // =================================================

    const activeBatteries = batteries.filter(

        (battery) =>

            battery.state &&

            battery.state
                .trim()
                .toLowerCase() === "active"

    );


    // =================================================
    // DISPLAY MAP
    // =================================================

    return (

        <div className="container mt-4">

            <div className="card shadow">


                {/* =====================================
                    HEADER
                ===================================== */}

                <div className="card-header bg-success text-white">

                    <h3 className="text-center mb-0">

                        Battery Locations

                    </h3>

                </div>


                {/* =====================================
                    MAP
                ===================================== */}

                <div className="card-body">

                    <MapContainer

                        center={[
                            15.3173,
                            75.7139
                        ]}

                        zoom={6}

                        style={{
                            height: "500px",
                            width: "100%"
                        }}

                    >


                        {/* =================================
                            OPEN STREET MAP
                        ================================= */}

                        <TileLayer

                            attribution="&copy; OpenStreetMap contributors"

                            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"

                        />


                        {/* =================================
                            AUTOMATIC MAP POSITION
                        ================================= */}

                        <MapUpdater
                            batteries={activeBatteries}
                        />


                        {/* =================================
                            BATTERY MARKERS
                        ================================= */}

                        {activeBatteries.map(

                            (battery) => {


                                // Get coordinates
                                const coordinates =
                                    getCoordinates(
                                        battery.location
                                    );


                                // If coordinates are not
                                // available, don't show
                                // an incorrect marker.

                                if (!coordinates) {

                                    console.warn(

                                        "Coordinates not found for city:",

                                        battery.location

                                    );

                                    return null;

                                }


                                return (

                                    <Marker

                                        key={battery.id}

                                        position={
                                            coordinates
                                        }

                                    >


                                        {/* =========================
                                            BATTERY POPUP
                                        ========================= */}

                                        <Popup>

                                            <h5>

                                                {
                                                    battery.batteryName
                                                }

                                            </h5>


                                            <hr />


                                            <b>
                                                Location :
                                            </b>

                                            {" "}

                                            {
                                                battery.location
                                            }


                                            <br />


                                            <b>
                                                Zone :
                                            </b>

                                            {" "}

                                            {
                                                battery.zone
                                            }


                                            <br />


                                            <b>
                                                Power :
                                            </b>

                                            {" "}

                                            {
                                                battery.power
                                            }

                                            {" kW"}


                                            <br />


                                            <b>
                                                Status :
                                            </b>

                                            {" "}

                                            {
                                                battery.state
                                            }


                                        </Popup>


                                    </Marker>

                                );

                            }

                        )}

                    </MapContainer>

                </div>

            </div>

        </div>

    );

}


export default MapComponent;