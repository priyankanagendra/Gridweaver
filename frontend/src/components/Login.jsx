import { useState } from 'react'
import { loginUser } from '../services/api'

function Login({ onLoginSuccess }) {

  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [message, setMessage] = useState('')

  const handleLogin = async (event) => {

    event.preventDefault()

    try {

      const data = await loginUser(email, password)

      console.log('Login successful')

      localStorage.setItem('token', data.token)

      setMessage('Login successful')

      onLoginSuccess()

    } catch (error) {

      console.error('Login failed:', error)

      setMessage('Invalid email or password')

    }
  }

  return (
    <div className="login-container">

      <h2>GridWeaver Login</h2>

      <form onSubmit={handleLogin}>

        <div>
          <label>Email</label>

          <input
            type="email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            required
          />
        </div>

        <div>
          <label>Password</label>

          <input
            type="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            required
          />
        </div>

        <button type="submit">
          Login
        </button>

      </form>

      {message && <p>{message}</p>}

    </div>
  )
}

export default Login