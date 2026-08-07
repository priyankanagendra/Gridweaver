const BASE_URL = 'http://localhost:8080'


export async function loginUser(email, password) {

  const response = await fetch(`${BASE_URL}/auth/login`, {
    method: 'POST',

    headers: {
      'Content-Type': 'application/json',
    },

    body: JSON.stringify({
      email: email,
      password: password,
    }),
  })

  if (!response.ok) {
    throw new Error('Login failed')
  }

  const data = await response.json()

  return data
}


export async function getBatteries() {

  const token = localStorage.getItem('token')

  const response = await fetch(`${BASE_URL}/battery`, {
    method: 'GET',

    headers: {
      'Authorization': `Bearer ${token}`,
    },
  })

  if (response.status === 401) {

    localStorage.removeItem('token')

    throw new Error('UNAUTHORIZED')
  }

  if (!response.ok) {
    throw new Error('Failed to fetch batteries')
  }

  const data = await response.json()

  return data
}


export async function processTelemetry(batteryId) {

  const token = localStorage.getItem('token')

  const response = await fetch(
    `${BASE_URL}/telemetry/${batteryId}`,
    {
      method: 'POST',

      headers: {
        'Authorization': `Bearer ${token}`,
      },
    }
  )

  if (response.status === 401) {

    localStorage.removeItem('token')

    throw new Error('UNAUTHORIZED')
  }

  if (!response.ok) {
    throw new Error('Failed to process telemetry')
  }

  const data = await response.text()

  return data
}