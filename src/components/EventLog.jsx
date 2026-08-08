import { useEffect, useState } from "react";
import WebSocketService from "../services/WebSocketService";

function EventLog() {

    const [events, setEvents] = useState([]);

    useEffect(() => {

        WebSocketService.connect((message) => {

            const event = {
                time: new Date().toLocaleTimeString(),
                message:
                    typeof message === "string"
                        ? message
                        : JSON.stringify(message)
            };

            setEvents((prev) => [event, ...prev]);

        });

        return () => {
            WebSocketService.disconnect();
        };

    }, []);

    return (

        <div className="container mt-4">

            <div className="card shadow">

                <div className="card-header bg-dark text-white">

                    <h3 className="mb-0">
                        Live Event Log
                    </h3>

                </div>

                <div
                    className="card-body"
                    style={{
                        maxHeight: "350px",
                        overflowY: "auto"
                    }}
                >

                    {events.length === 0 ? (

                        <p className="text-muted">
                            No Events Yet...
                        </p>

                    ) : (

                        <table className="table table-striped">

                            <thead>

                                <tr>
                                    <th>Time</th>
                                    <th>Event</th>
                                </tr>

                            </thead>

                            <tbody>

                                {events.map((event, index) => (

                                    <tr key={index}>

                                        <td>{event.time}</td>

                                        <td>{event.message}</td>

                                    </tr>

                                ))}

                            </tbody>

                        </table>

                    )}

                </div>

            </div>

        </div>

    );
}

export default EventLog;