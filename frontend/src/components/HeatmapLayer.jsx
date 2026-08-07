import { useEffect } from 'react'
import { useMap } from 'react-leaflet'
import L from 'leaflet'
import 'leaflet.heat'

function HeatmapLayer({ nodes, mode }) {

  const map = useMap()

  useEffect(() => {

    const points = nodes
      .filter((node) => {
        if (mode === 'consumption') {
          return node.powerConsumption !== undefined
        }

        return node.powerGeneration !== undefined
      })
      .map((node) => {

        const value =
          mode === 'consumption'
            ? node.powerConsumption
            : node.powerGeneration

        const intensity = Math.min(value / 100, 1)

        return [
          node.latitude,
          node.longitude,
          intensity
        ]
      })


    if (points.length === 0) {
      return
    }


    const heatLayer = L.heatLayer(points, {
      radius: 35,
      blur: 25,
      maxZoom: 17
    })


    heatLayer.addTo(map)


    return () => {

      map.removeLayer(heatLayer)

    }

  }, [map, nodes, mode])


  return null
}

export default HeatmapLayer