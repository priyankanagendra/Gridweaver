import {
  MapContainer,
  TileLayer,
  CircleMarker,
  Popup
} from 'react-leaflet'

import 'leaflet/dist/leaflet.css'

function GridMap() {

  const nodes = [
    {
      id: 1,
      name: 'Grid Node 1',
      latitude: 12.9716,
      longitude: 77.5946,
      status: 'ACTIVE',
    },
    {
      id: 2,
      name: 'Grid Node 2',
      latitude: 12.9816,
      longitude: 77.6046,
      status: 'ACTIVE',
    },
    {
      id: 3,
      name: 'Grid Node 3',
      latitude: 12.9616,
      longitude: 77.5846,
      status: 'WARNING',
    },
    {
      id: 4,
      name: 'Grid Node 4',
      latitude: 12.9916,
      longitude: 77.5746,
      status: 'OFFLINE',
    },
  ]

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

        {nodes.map((node) => (

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

    </div>
  )
}

export default GridMap