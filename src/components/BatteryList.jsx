import { useEffect, useState } from "react";
import BatteryService from "../services/BatteryService";
import AuthService from "../services/AuthService";

function BatteryList({ setSelectedBattery, refresh }) {

    const role = AuthService.getRole();

    const [batteries, setBatteries] = useState([]);
    const [search, setSearch] = useState("");
    const [filterState, setFilterState] = useState("ALL");

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

    const handleEdit = (battery) => {
        setSelectedBattery(battery);
    };

    const handleDelete = (id) => {

        if (window.confirm("Are you sure you want to delete this battery?")) {

            BatteryService.deleteBattery(id)
                .then(() => {
                    alert("Battery Deleted Successfully!");
                    loadBatteries();
                })
                .catch((error) => {
                    console.error(error);
                    alert("Failed to Delete Battery");
                });

        }
    };

    const filteredBatteries = batteries.filter((battery) => {

        const matchesSearch =
            battery.batteryName.toLowerCase().includes(search.toLowerCase()) ||
            battery.location.toLowerCase().includes(search.toLowerCase()) ||
            battery.zone.toLowerCase().includes(search.toLowerCase());

        const matchesState =
            filterState === "ALL" ||
            battery.state.toUpperCase() === filterState;

        return matchesSearch && matchesState;

    });

    return (

        <div className="container mt-4">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">
                    <h3 className="text-center mb-0">
                        GridWeaver Battery Dashboard
                    </h3>
                </div>

                <div className="card-body">

                    <div className="row mb-3">

                        <div className="col-md-8">

                            <input
                                type="text"
                                className="form-control"
                                placeholder="Search by Battery Name, Location or Zone"
                                value={search}
                                onChange={(e) => setSearch(e.target.value)}
                            />

                        </div>

                        <div className="col-md-4">

                            <select
                                className="form-select"
                                value={filterState}
                                onChange={(e) => setFilterState(e.target.value)}
                            >
                                <option value="ALL">All States</option>
                                <option value="ACTIVE">ACTIVE</option>
                                <option value="CHARGING">CHARGING</option>
                                <option value="INACTIVE">INACTIVE</option>
                            </select>

                        </div>

                    </div>

                    <div className="table-responsive">

                        <table className="table table-striped table-hover table-bordered align-middle">

                            <thead className="table-dark">

                                <tr>
                                    <th>ID</th>
                                    <th>Battery Name</th>
                                    <th>Location</th>
                                    <th>Zone</th>
                                    <th>Power</th>
                                    <th>State</th>
                                    <th>Actions</th>
                                </tr>

                            </thead>

                            <tbody>

                                {filteredBatteries.length > 0 ? (

                                    filteredBatteries.map((battery) => (

                                        <tr key={battery.id}>

                                            <td>{battery.id}</td>
                                            <td>{battery.batteryName}</td>
                                            <td>{battery.location}</td>
                                            <td>{battery.zone}</td>
                                            <td>{battery.power} kW</td>
                                            <td>{battery.state}</td>

                                            <td>

                                                {(role === "ROLE_ADMIN" || role === "ROLE_OPERATOR") && (
                                                    <>
                                                        <button
                                                            className="btn btn-primary btn-sm me-2"
                                                            onClick={() => handleEdit(battery)}
                                                        >
                                                            Edit
                                                        </button>

                                                        <button
                                                            className="btn btn-danger btn-sm"
                                                            onClick={() => handleDelete(battery.id)}
                                                        >
                                                            Delete
                                                        </button>
                                                    </>
                                                )}

                                                {role === "ROLE_VIEWER" && (
                                                    <span className="text-muted">
                                                        View Only
                                                    </span>
                                                )}

                                            </td>

                                        </tr>

                                    ))

                                ) : (

                                    <tr>

                                        <td
                                            colSpan="7"
                                            className="text-center text-danger"
                                        >
                                            No Batteries Found
                                        </td>

                                    </tr>

                                )}

                            </tbody>

                        </table>

                    </div>

                </div>

            </div>

        </div>

    );
}

export default BatteryList;