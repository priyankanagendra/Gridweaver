import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";

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

                        const battery = JSON.parse(message.body);

                        onMessageReceived(battery);

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