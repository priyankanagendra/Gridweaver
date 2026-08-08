import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client/dist/sockjs";

class WebSocketService {

    constructor() {

        this.client = null;

        this.connected = false;
        this.connecting = false;

        this.batterySubscription = null;
        this.messageSubscription = null;

        this.batteryCallback = null;
        this.messageCallback = null;
    }


    // ==========================================
    // CONNECT
    // ==========================================

    connect(onBatteryReceived, onMessageReceived) {

        console.log("🔌 Starting WebSocket connection...");


        // Store callbacks
        this.batteryCallback = onBatteryReceived;
        this.messageCallback = onMessageReceived;


        // ==========================================
        // PREVENT DUPLICATE CONNECTION
        // ==========================================

        if (
            this.client &&
            (
                this.connected ||
                this.connecting ||
                this.client.active
            )
        ) {

            console.log(
                "⚠️ WebSocket already connected/connecting"
            );

            return;
        }


        // ==========================================
        // CONNECTION STARTING
        // ==========================================

        this.connecting = true;


        // ==========================================
        // STOMP CLIENT
        // ==========================================

        this.client = new Client({

            // Create a new SockJS connection
            webSocketFactory: () => {

                console.log(
                    "🔌 Creating SockJS connection..."
                );

                return new SockJS(
                    "http://localhost:8080/battery-websocket"
                );
            },


            // Reconnect automatically if connection
            // is unexpectedly lost
            reconnectDelay: 5000,


            // ======================================
            // DEBUG
            // ======================================

            debug: (message) => {

                console.log(
                    "STOMP:",
                    message
                );
            },


            // ======================================
            // CONNECTED
            // ======================================

            onConnect: () => {

                console.log(
                    "✅ Connected to WebSocket"
                );


                this.connected = true;
                this.connecting = false;


                // ==================================
                // REMOVE OLD SUBSCRIPTIONS
                // ==================================

                if (this.batterySubscription) {

                    console.log(
                        "⚠️ Removing old battery subscription"
                    );

                    this.batterySubscription.unsubscribe();

                    this.batterySubscription = null;
                }


                if (this.messageSubscription) {

                    console.log(
                        "⚠️ Removing old message subscription"
                    );

                    this.messageSubscription.unsubscribe();

                    this.messageSubscription = null;
                }


                // ==================================
                // BATTERY SUBSCRIPTION
                // ==================================

                this.batterySubscription =
                    this.client.subscribe(

                        "/topic/batteries",

                        (message) => {

                            console.log(
                                "📩 Battery message received:",
                                message.body
                            );


                            // Ignore empty messages
                            if (!message.body) {

                                return;
                            }


                            const body =
                                message.body.trim();


                            // =================================
                            // CHECK JSON
                            // =================================

                            if (
                                !body.startsWith("{") &&
                                !body.startsWith("[")
                            ) {

                                console.warn(
                                    "⚠️ Ignoring non-JSON battery message:",
                                    body
                                );

                                return;
                            }


                            // =================================
                            // PARSE JSON
                            // =================================

                            try {

                                const battery =
                                    JSON.parse(body);


                                console.log(
                                    "🔋 Battery:",
                                    battery
                                );


                                // =================================
                                // BATTERY CALLBACK
                                // =================================

                                if (
                                    this.batteryCallback
                                ) {

                                    console.log(
                                        "🔥 Battery Callback Executed"
                                    );


                                    this.batteryCallback(
                                        battery
                                    );
                                }


                            } catch (error) {

                                console.error(
                                    "❌ Invalid battery JSON:",
                                    body
                                );


                                console.error(
                                    "❌ JSON parsing error:",
                                    error
                                );
                            }

                        }

                    );


                console.log(
                    "✅ Subscribed to /topic/batteries"
                );


                // ==================================
                // EVENT LOG SUBSCRIPTION
                // ==================================

                this.messageSubscription =
                    this.client.subscribe(

                        "/topic/messages",

                        (message) => {

                            console.log(
                                "📢 Event message received:",
                                message.body
                            );


                            // Ignore empty messages
                            if (!message.body) {

                                return;
                            }


                            // =================================
                            // EVENT LOG CALLBACK
                            // =================================

                            if (
                                this.messageCallback
                            ) {

                                console.log(
                                    "📝 Event Log Callback Executed"
                                );


                                this.messageCallback(
                                    message.body
                                );
                            }

                        }

                    );


                console.log(
                    "✅ Subscribed to /topic/messages"
                );

            },


            // ======================================
            // STOMP ERROR
            // ======================================

            onStompError: (frame) => {

                console.error(
                    "❌ STOMP Error:",
                    frame
                );


                this.connected = false;
                this.connecting = false;

            },


            // ======================================
            // WEBSOCKET ERROR
            // ======================================

            onWebSocketError: (error) => {

                console.error(
                    "❌ WebSocket Error:",
                    error
                );

            },


            // ======================================
            // WEBSOCKET CLOSED
            // ======================================

            onWebSocketClose: (event) => {

                console.warn(
                    "⚠️ WebSocket Closed:",
                    event
                );


                this.connected = false;
                this.connecting = false;


                this.batterySubscription = null;
                this.messageSubscription = null;

            },


            // ======================================
            // DISCONNECT
            // ======================================

            onDisconnect: () => {

                console.log(
                    "⚠️ STOMP Disconnected"
                );


                this.connected = false;
                this.connecting = false;


                this.batterySubscription = null;
                this.messageSubscription = null;

            }

        });


        // ==========================================
        // ACTIVATE CLIENT
        // ==========================================

        this.client.activate();

    }


    // ==========================================
    // DISCONNECT
    // ==========================================

    disconnect() {

        console.log(
            "🔌 Disconnecting WebSocket..."
        );


        // ==========================================
        // NO CLIENT
        // ==========================================

        if (!this.client) {

            console.log(
                "ℹ️ No WebSocket client to disconnect"
            );

            return;
        }


        // ==========================================
        // UNSUBSCRIBE BATTERY
        // ==========================================

        if (this.batterySubscription) {

            try {

                this.batterySubscription.unsubscribe();

            } catch (error) {

                console.error(
                    "❌ Error unsubscribing battery:",
                    error
                );

            }

            this.batterySubscription = null;
        }


        // ==========================================
        // UNSUBSCRIBE EVENT LOG
        // ==========================================

        if (this.messageSubscription) {

            try {

                this.messageSubscription.unsubscribe();

            } catch (error) {

                console.error(
                    "❌ Error unsubscribing messages:",
                    error
                );

            }

            this.messageSubscription = null;
        }


        // ==========================================
        // DEACTIVATE CLIENT
        // ==========================================

        try {

            this.client.deactivate();

        } catch (error) {

            console.error(
                "❌ Error disconnecting WebSocket:",
                error
            );

        }


        // ==========================================
        // RESET
        // ==========================================

        this.client = null;

        this.connected = false;
        this.connecting = false;

        this.batteryCallback = null;
        this.messageCallback = null;


        console.log(
            "❌ WebSocket Disconnected"
        );

    }

}


// ==========================================
// SINGLE INSTANCE
// ==========================================

export default new WebSocketService();