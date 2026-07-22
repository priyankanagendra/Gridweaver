import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";

function MapView() {
  return (
    <MapContainer
      center={[13.0827, 80.2707]}
      zoom={12}
      style={{ height: "500px", width: "100%" }}
    >
      <TileLayer
        attribution='&copy; OpenStreetMap contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />

      <Marker position={[13.0827, 80.2707]}>
        <Popup>
          Solar Node 1 <br />
          Status: Charging
        </Popup>
      </Marker>
    </MapContainer>
  );
}

export default MapView;