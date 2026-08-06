function MapLegend() {
  return (
    <div className="map-legend">

      <h3>Node Status</h3>

      <div className="legend-item">
        <span className="legend-color active"></span>
        <span>Active</span>
      </div>

      <div className="legend-item">
        <span className="legend-color warning"></span>
        <span>Warning</span>
      </div>

      <div className="legend-item">
        <span className="legend-color offline"></span>
        <span>Offline</span>
      </div>

    </div>
  )
}

export default MapLegend