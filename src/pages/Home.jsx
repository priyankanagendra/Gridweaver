import { useState } from "react";
import DashboardCards from "../components/DashboardCards";
import BatteryChart from "../components/BatteryChart";
import BatteryForm from "../components/BatteryForm";
import BatteryList from "../components/BatteryList";
import MapComponent from "../components/MapComponent";

function Home() {

    const [selectedBattery, setSelectedBattery] = useState(null);
    const [refresh, setRefresh] = useState(false);

    const refreshData = () => {
        setRefresh(prev => !prev);
    };

    return (

        <div className="container mt-4">

            <h1 className="text-center text-primary mb-4">
                GridWeaver Dashboard
            </h1>

            <DashboardCards refresh={refresh} />

            <br />

            <BatteryChart refresh={refresh} />

            <br />

            <BatteryForm
                selectedBattery={selectedBattery}
                refreshData={refreshData}
            />

            <br />

            <BatteryList
                setSelectedBattery={setSelectedBattery}
                refresh={refresh}
            />

            <br />

            <MapComponent
                refresh={refresh}
            />

        </div>

    );
}

export default Home;