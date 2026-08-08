import { useEffect, useState } from "react";
import WebSocketService from "../services/WebSocketService";

function EventLog() {

    const [events, setEvents] = useState([]);

    useEffect(() => {

        console.log("📢 Registering Event Log...");

        WebSocketService.connectToEvents((message) => {

            console.log("📢 Event Log Received:", message);

            const newEvent = {
                id: Date.now() + Math.random(),
                time: new Date().toLocaleTimeString(),
                message: message
            };

            setEvents((previousEvents) => [
                newEvent,
                ...previousEvents
            ]);

        });

    }, []);

    return (

        <div className="container mt-4 mb-4">

            <div className="card shadow">

                <div className="card-header bg-dark text-white">

                    <h3 className="mb-0">
                        Live Event Log
                    </h3>

                </div>

                <div className="card-body">

                    {events.length === 0 ? (

                        <p className="text-muted mb-0">
                            No Events Yet...
                        </p>

                    ) : (

                        <div className="table-responsive">

                            <table className="table table-striped table-hover table-bordered">

                                <thead className="table-dark">

                                    <tr>
                                        <th>Time</th>
                                        <th>Event</th>
                                    </tr>

                                </thead>

                                <tbody>

                                    {events.map((event) => (

                                        <tr key={event.id}>

                                            <td>
                                                {event.time}
                                            </td>

                                            <td>
                                                {event.message}
                                            </td>

                                        </tr>

                                    ))}

                                </tbody>

                            </table>

                        </div>

                    )}

                </div>

            </div>

        </div>

    );
}

export default EventLog;