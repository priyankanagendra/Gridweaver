import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Login from "./pages/Login";
import Home from "./pages/Home";
import AuthService from "./services/AuthService";

function App() {

    const isLoggedIn = AuthService.isLoggedIn();

    return (

        <BrowserRouter>

            <Routes>

                <Route
                    path="/login"
                    element={
                        isLoggedIn
                            ? <Navigate to="/" />
                            : <Login />
                    }
                />

                <Route
                    path="/"
                    element={
                        isLoggedIn
                            ? <Home />
                            : <Navigate to="/login" />
                    }
                />

            </Routes>

        </BrowserRouter>

    );

}

export default App;