import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import BatteryForm from "../components/BatteryForm";
import BatteryList from "../components/BatteryList";
import DashboardCards from "../components/DashboardCards";
import BatteryChart from "../components/BatteryChart";
import MapComponent from "../components/MapComponent";

import WebSocketService from "../services/WebSocketService";
import AuthService from "../services/AuthService";

function Home() {

    const navigate = useNavigate();

    const [refresh, setRefresh] = useState(false);
    const [selectedBattery, setSelectedBattery] = useState(null);

    // ==========================================
    // EVENT LOG
    // ==========================================

    const [events, setEvents] = useState(() => {

        const savedEvents =
            localStorage.getItem("gridweaver_events");

        return savedEvents
            ? JSON.parse(savedEvents)
            : [];
    });


    // ==========================================
    // WEBSOCKET CONNECTION
    // ==========================================

    useEffect(() => {

        // Check login
        if (!AuthService.isLoggedIn()) {

            navigate("/login");

            return;
        }

        console.log("🔌 Connecting to WebSocket...");


        WebSocketService.connect(

            // ==================================
            // BATTERY CALLBACK
            // ==================================

            (battery) => {

                console.log(
                    "🔥 Battery WebSocket Callback Executed"
                );

                console.log(
                    "📩 Received Battery:",
                    battery
                );

                // Refresh dashboard
                setRefresh(
                    (previous) => !previous
                );
            },


            // ==================================
            // EVENT LOG CALLBACK
            // ==================================

            (message) => {

                console.log(
                    "📝 Event Log Message:",
                    message
                );


                setEvents((previousEvents) => {

                    const newEvent = {

                        id: Date.now(),

                        message: message,

                        time:
                            new Date()
                                .toLocaleTimeString()
                    };


                    const updatedEvents = [

                        ...previousEvents,

                        newEvent

                    ];


                    // Save events in browser
                    localStorage.setItem(

                        "gridweaver_events",

                        JSON.stringify(updatedEvents)

                    );


                    return updatedEvents;

                });

            }

        );


        // ==========================================
        // CLEANUP
        // ==========================================

        return () => {

            console.log(
                "🔌 Cleaning up WebSocket..."
            );

            WebSocketService.disconnect();

        };


    }, [navigate]);


    // ==========================================
    // REFRESH DATA
    // ==========================================

    const refreshData = () => {

        console.log(
            "🔄 Refreshing dashboard data..."
        );

        setRefresh(
            (previous) => !previous
        );

    };


    // ==========================================
    // LOGOUT
    // ==========================================

    const handleLogout = () => {

        console.log(
            "🔌 Disconnecting WebSocket..."
        );

        WebSocketService.disconnect();


        AuthService.logout();


        alert(
            "Logged Out Successfully"
        );


        navigate("/login");

    };


    // ==========================================
    // CLEAR EVENT LOG
    // ==========================================

    const clearEvents = () => {

        setEvents([]);

        localStorage.removeItem(
            "gridweaver_events"
        );

    };


    // ==========================================
    // UI
    // ==========================================

    return (

        <div className="container mt-4">


            {/* ================================= */}
            {/* HEADER */}
            {/* ================================= */}

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h1>
                    GridWeaver Dashboard
                </h1>


                <button
                    className="btn btn-danger"
                    onClick={handleLogout}
                >
                    Logout
                </button>

            </div>


            {/* ================================= */}
            {/* DASHBOARD CARDS */}
            {/* ================================= */}

            <DashboardCards
                refresh={refresh}
            />

            <br />


            {/* ================================= */}
            {/* BATTERY CHART */}
            {/* ================================= */}

            <BatteryChart
                refresh={refresh}
            />

            <br />


            {/* ================================= */}
            {/* BATTERY FORM */}
            {/* ================================= */}

            <BatteryForm
                selectedBattery={selectedBattery}
                setSelectedBattery={setSelectedBattery}
                refreshData={refreshData}
            />

            <br />


            {/* ================================= */}
            {/* BATTERY LIST */}
            {/* ================================= */}

            <BatteryList
                refresh={refresh}
                setSelectedBattery={setSelectedBattery}
            />

            <br />


            {/* ================================= */}
            {/* MAP */}
            {/* ================================= */}

            <MapComponent
                refresh={refresh}
            />

            <br />


            {/* ================================= */}
            {/* EVENT LOG */}
            {/* ================================= */}

            <div className="card shadow mb-5">


                <div className="card-header bg-dark text-white d-flex justify-content-between align-items-center">

                    <h4 className="mb-0">
                        Event Log
                    </h4>


                    <button
                        className="btn btn-sm btn-light"
                        onClick={clearEvents}
                    >
                        Clear
                    </button>

                </div>


                <div className="card-body">


                    {events.length === 0 ? (

                        <p className="text-muted mb-0">

                            No events received yet.

                        </p>

                    ) : (

                        <div>

                            {events
                                .slice()
                                .reverse()
                                .map((event) => (

                                    <div
                                        key={event.id}
                                        className="border-bottom py-2"
                                    >


                                        <div className="d-flex justify-content-between">


                                            <span>

                                                📢 {event.message}

                                            </span>


                                            <small className="text-muted">

                                                {event.time}

                                            </small>


                                        </div>


                                    </div>

                                ))}

                        </div>

                    )}

                </div>

            </div>


        </div>

    );

}


export default Home;