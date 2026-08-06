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