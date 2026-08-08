import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client/dist/sockjs";

class WebSocketService {

    constructor() {
        this.client = null;
    }

    connect(onMessageReceived) {

        const socket = new SockJS("http://localhost:8080/battery-websocket");

        this.client = new Client({
            webSocketFactory: () => socket,

            reconnectDelay: 5000,

            onConnect: () => {

                console.log("✅ Connected to WebSocket");

                this.client.subscribe("/topic/batteries", (message) => {

                    if (message.body) {

                        try {

                            // Try to parse JSON (Battery Object)
                            const data = JSON.parse(message.body);

                            onMessageReceived(data);

                        } catch (error) {

                            // If not JSON, it is a Kafka String message
                            onMessageReceived(message.body);

                        }

                    }

                });

            },

            onStompError: (frame) => {

                console.error("STOMP Error:", frame);

            }

        });

        this.client.activate();
    }

    disconnect() {

        if (this.client) {

            this.client.deactivate();

            console.log("❌ WebSocket Disconnected");

        }

    }

}

export default new WebSocketService();