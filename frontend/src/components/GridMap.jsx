import {
  MapContainer,
  TileLayer,
  CircleMarker,
  Popup
} from 'react-leaflet'

import 'leaflet/dist/leaflet.css'
import MapLegend from './MapLegend'
import mockNodes from '../data/mockNodes'

function GridMap() {

  const getStatusColor = (status) => {

    if (status === 'ACTIVE') {
      return 'green'
    }

    if (status === 'WARNING') {
      return 'orange'
    }

    if (status === 'OFFLINE') {
      return 'red'
    }

    return 'gray'
  }

  return (
    <div className="map-section">

      <h2>Microgrid Map</h2>

      <MapContainer
        center={[12.9716, 77.5946]}
        zoom={12}
        style={{ height: '450px', width: '100%' }}
      >

        <TileLayer
          attribution='&copy; OpenStreetMap contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />

        {mockNodes.map((node) => (

          <CircleMarker
            key={node.id}
            center={[node.latitude, node.longitude]}
            radius={10}
            pathOptions={{
              color: getStatusColor(node.status),
              fillColor: getStatusColor(node.status),
              fillOpacity: 0.8,
            }}
          >

            <Popup>
              <strong>{node.name}</strong>
              <br />
              Status: {node.status}
            </Popup>

          </CircleMarker>

        ))}

      </MapContainer>

      <MapLegend />

    </div>
  )
}

export default GridMap