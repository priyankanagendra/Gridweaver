import { useEffect, useState } from 'react'

import StatCard from './StatCard'
import GridMap from './GridMap'

import mockNodes from '../data/mockNodes'
import {
  getBatteries,
  processTelemetry
} from '../services/api'

function Dashboard({ onUnauthorized }) {

  const [batteries, setBatteries] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [successMessage, setSuccessMessage] = useState('')
  const [processingId, setProcessingId] = useState(null)

  const totalNodes = mockNodes.length

  const activeNodes = mockNodes.filter(
    (node) => node.status === 'ACTIVE'
  ).length

  const warningNodes = mockNodes.filter(
    (node) => node.status === 'WARNING'
  ).length

  const offlineNodes = mockNodes.filter(
    (node) => node.status === 'OFFLINE'
  ).length


  const fetchBatteries = async () => {

    try {

      setLoading(true)
      setError('')

      const data = await getBatteries()

      console.log('Battery data from backend:', data)

      setBatteries(data)

    } catch (error) {

      console.error('Error fetching batteries:', error)

      if (error.message === 'UNAUTHORIZED') {
        onUnauthorized()
        return
      }

      setError('Unable to load battery data')

    } finally {

      setLoading(false)

    }
  }


  useEffect(() => {

    fetchBatteries()

  }, [])


  const handleTelemetry = async (batteryId) => {

    try {

      setProcessingId(batteryId)
      setError('')
      setSuccessMessage('')

      await processTelemetry(batteryId)

      await fetchBatteries()

      setSuccessMessage(
        `Telemetry processed successfully for Battery ${batteryId}`
      )

    } catch (error) {

      console.error('Error processing telemetry:', error)

      if (error.message === 'UNAUTHORIZED') {
        onUnauthorized()
        return
      }

      setError('Unable to process battery telemetry')

    } finally {

      setProcessingId(null)

    }
  }


  return (
    <main className="dashboard">

      <h2>Grid Overview</h2>

      <div className="stats-container">

        <StatCard
          title="Total Nodes"
          value={totalNodes}
        />

        <StatCard
          title="Active Nodes"
          value={activeNodes}
        />

        <StatCard
          title="Warning Nodes"
          value={warningNodes}
        />

        <StatCard
          title="Offline Nodes"
          value={offlineNodes}
        />

      </div>


      <section className="battery-section">

        <h2>Battery Overview</h2>

        {successMessage && (
          <p className="battery-success">
            {successMessage}
          </p>
        )}

        {loading && (
          <p className="battery-message">
            Loading battery data...
          </p>
        )}

        {error && (
          <p className="battery-error">
            {error}
          </p>
        )}

        {!loading && !error && (

          <div className="battery-table-container">

            <table className="battery-table">

              <thead>
                <tr>
                  <th>ID</th>
                  <th>Battery Name</th>
                  <th>Battery Type</th>
                  <th>Capacity</th>
                  <th>Voltage</th>
                  <th>State</th>
                  <th>Action</th>
                </tr>
              </thead>

              <tbody>

                {batteries.map((battery) => (

                  <tr key={battery.id}>

                    <td>{battery.id}</td>

                    <td>{battery.batteryName}</td>

                    <td>{battery.batteryType}</td>

                    <td>{battery.capacity}</td>

                    <td>{battery.voltage} V</td>

                    <td>
                      <span
                        className={`battery-state ${
                          battery.state
                            ? battery.state.toLowerCase()
                            : 'not-assigned'
                        }`}
                      >
                        {battery.state ?? 'Not Assigned'}
                      </span>
                    </td>

                    <td>
                      <button
                        className="telemetry-button"
                        onClick={() =>
                          handleTelemetry(battery.id)
                        }
                        disabled={
                          processingId === battery.id
                        }
                      >
                        {processingId === battery.id
                          ? 'Processing...'
                          : 'Process Telemetry'}
                      </button>
                    </td>

                  </tr>

                ))}

              </tbody>

            </table>

          </div>

        )}

      </section>


      <GridMap />

    </main>
  )
}

export default Dashboard