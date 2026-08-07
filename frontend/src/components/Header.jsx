function Header({ onLogout }) {
  return (
    <header className="header">

      <div className="header-content">

        <div>
          <h1>GridWeaver</h1>
          <p>Real-Time Microgrid Monitoring Dashboard</p>
        </div>

        <button
          className="logout-button"
          onClick={onLogout}
        >
          Logout
        </button>

      </div>

    </header>
  )
}

export default Header