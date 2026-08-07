import { useState } from 'react'

import {
  MapContainer,
  TileLayer,
  CircleMarker,
  Popup
} from 'react-leaflet'

import 'leaflet/dist/leaflet.css'

import MapLegend from './MapLegend'
import HeatmapLayer from './HeatmapLayer'


function GridMap({ nodes }) {

  const [heatmapMode, setHeatmapMode] =
    useState('consumption')

  const [showHeatmap, setShowHeatmap] =
    useState(false)


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

      <div className="map-header">

        <h2>Microgrid Map</h2>

        <div className="heatmap-controls">

          <button
            className="heatmap-button"
            onClick={() =>
              setShowHeatmap(
                (current) => !current
              )
            }
          >
            {showHeatmap
              ? 'Hide Heatmap'
              : 'Show Heatmap'}
          </button>


          {showHeatmap && (

            <select
              className="heatmap-select"
              value={heatmapMode}
              onChange={(event) =>
                setHeatmapMode(
                  event.target.value
                )
              }
            >

              <option value="consumption">
                Power Consumption
              </option>

              <option value="generation">
                Power Generation
              </option>

            </select>

          )}

        </div>

      </div>


      <MapContainer
        center={[12.9716, 77.5946]}
        zoom={12}
        style={{
          height: '450px',
          width: '100%'
        }}
      >

        <TileLayer
          attribution='&copy; OpenStreetMap contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />


        {showHeatmap && (

          <HeatmapLayer
            nodes={nodes}
            mode={heatmapMode}
          />

        )}


        {nodes.map((node) => (

          <CircleMarker
            key={node.id}
            center={[
              node.latitude,
              node.longitude
            ]}
            radius={10}
            pathOptions={{
              color:
                getStatusColor(node.status),

              fillColor:
                getStatusColor(node.status),

              fillOpacity: 0.8
            }}
          >

            <Popup>

              <strong>
                {node.name}
              </strong>

              <br />

              Status: {node.status}


              {node.powerOutput !== undefined && (
                <>
                  <br />
                  Power Output: {node.powerOutput}
                </>
              )}


              {node.powerConsumption !== undefined && (
                <>
                  <br />
                  Power Consumption:{' '}
                  {node.powerConsumption}
                </>
              )}


              {node.powerGeneration !== undefined && (
                <>
                  <br />
                  Power Generation:{' '}
                  {node.powerGeneration}
                </>
              )}

            </Popup>

          </CircleMarker>

        ))}

      </MapContainer>

      <MapLegend />

    </div>
  )
}

export default GridMap