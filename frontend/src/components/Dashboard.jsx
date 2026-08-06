import StatCard from './StatCard'
import GridMap from './GridMap'

function Dashboard() {
  return (
    <main className="dashboard">

      <h2>Grid Overview</h2>

      <div className="stats-container">

        <StatCard
          title="Total Nodes"
          value="12"
        />

        <StatCard
          title="Active Nodes"
          value="9"
        />

        <StatCard
          title="Warning Nodes"
          value="2"
        />

        <StatCard
          title="Offline Nodes"
          value="1"
        />

      </div>

      <GridMap />

    </main>
  )
}

export default Dashboard