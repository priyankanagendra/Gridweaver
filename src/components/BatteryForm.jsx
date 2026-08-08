import { useState, useEffect } from "react";
import BatteryService from "../services/BatteryService";
import AuthService from "../services/AuthService";

function BatteryForm({ selectedBattery, refreshData }) {

    const role = AuthService.getRole();

    const [battery, setBattery] = useState({
        id: "",
        batteryName: "",
        location: "",
        zone: "",
        power: "",
        state: ""
    });

    useEffect(() => {
        if (selectedBattery) {
            setBattery(selectedBattery);
        }
    }, [selectedBattery]);

    const handleChange = (e) => {
        setBattery({
            ...battery,
            [e.target.name]: e.target.value
        });
    };

    const clearForm = () => {
        setBattery({
            id: "",
            batteryName: "",
            location: "",
            zone: "",
            power: "",
            state: ""
        });
    };

    const handleSubmit = (e) => {

        e.preventDefault();

        if (battery.id) {

            BatteryService.updateBattery(battery.id, battery)
                .then(() => {
                    alert("Battery Updated Successfully!");
                    clearForm();
                    refreshData();
                })
                .catch((error) => {
                    console.error(error);
                    alert("Failed to Update Battery");
                });

        } else {

            BatteryService.saveBattery(battery)
                .then(() => {
                    alert("Battery Added Successfully!");
                    clearForm();
                    refreshData();
                })
                .catch((error) => {
                    console.error(error);
                    alert("Failed to Add Battery");
                });

        }

    };

    // VIEWER cannot see the form
    if (role === "ROLE_VIEWER") {
        return null;
    }

    return (

        <div className="container mt-4">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h3 className="text-center">
                        {battery.id ? "Update Battery" : "Add Battery"}
                    </h3>

                </div>

                <div className="card-body">

                    <form onSubmit={handleSubmit}>

                        <div className="mb-3">
                            <label className="form-label">Battery Name</label>
                            <input
                                type="text"
                                className="form-control"
                                name="batteryName"
                                value={battery.batteryName}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">Location</label>
                            <input
                                type="text"
                                className="form-control"
                                name="location"
                                value={battery.location}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">Zone</label>
                            <input
                                type="text"
                                className="form-control"
                                name="zone"
                                value={battery.zone}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">Power (kW)</label>
                            <input
                                type="number"
                                className="form-control"
                                name="power"
                                value={battery.power}
                                onChange={handleChange}
                                required
                            />
                        </div>

                        <div className="mb-3">
                            <label className="form-label">State</label>
                            <select
                                className="form-select"
                                name="state"
                                value={battery.state}
                                onChange={handleChange}
                                required
                            >
                                <option value="">Select State</option>
                                <option value="ACTIVE">ACTIVE</option>
                                <option value="CHARGING">CHARGING</option>
                                <option value="INACTIVE">INACTIVE</option>
                            </select>
                        </div>

                        <button
                            type="submit"
                            className={
                                battery.id
                                    ? "btn btn-warning w-100"
                                    : "btn btn-success w-100"
                            }
                        >
                            {battery.id ? "Update Battery" : "Save Battery"}
                        </button>

                    </form>

                </div>

            </div>

        </div>

    );
}

export default BatteryForm;