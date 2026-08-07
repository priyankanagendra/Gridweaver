import { Client } from '@stomp/stompjs'

let stompClient = null

export function connectGridWebSocket(onGridUpdate) {

  stompClient = new Client({

    brokerURL: 'ws://localhost:8080/ws',

    reconnectDelay: 5000,

    onConnect: () => {

      console.log('WebSocket connected')

      stompClient.subscribe('/topic/grid', (message) => {

        const update = JSON.parse(message.body)

        console.log('Grid update received:', update)

        onGridUpdate(update)
      })
    },

    onStompError: (frame) => {

      console.error(
        'WebSocket STOMP error:',
        frame.headers['message']
      )
    },

    onWebSocketError: (error) => {

      console.error('WebSocket error:', error)
    }
  })

  stompClient.activate()
}


export function disconnectGridWebSocket() {

  if (stompClient) {

    stompClient.deactivate()

    stompClient = null
  }
}