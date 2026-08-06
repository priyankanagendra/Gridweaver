import StatCard from './StatCard'
import GridMap from './GridMap'
import mockNodes from '../data/mockNodes'

function Dashboard() {

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

      <GridMap />

    </main>
  )
}

export default Dashboard