import BatteryList from "../components/BatteryList";
import MapComponent from "../components/MapComponent";

function Home() {
    return (
        <div style={{ padding: "20px" }}>
            <h1 style={{ textAlign: "center" }}>GridWeaver Dashboard</h1>

            <BatteryList />

            <br />
            <br />

            <MapComponent />
        </div>
    );
}

export default Home;