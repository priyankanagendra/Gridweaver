import { useState } from 'react'
import './App.css'

import Login from './components/Login'
import Header from './components/Header'
import Dashboard from './components/Dashboard'

function App() {

  const [isLoggedIn, setIsLoggedIn] = useState(
    localStorage.getItem('token') !== null
  )

  const handleLoginSuccess = () => {
    setIsLoggedIn(true)
  }

  return (
    <div className="app">

      {isLoggedIn ? (
        <>
          <Header />
          <Dashboard />
        </>
      ) : (
        <Login onLoginSuccess={handleLoginSuccess} />
      )}

    </div>
  )
}

export default App