import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";

function Login() {

    const navigate = useNavigate();

    const [loginData, setLoginData] = useState({
        username: "",
        password: ""
    });

    const handleChange = (e) => {

        setLoginData({
            ...loginData,
            [e.target.name]: e.target.value
        });

    };

    const handleLogin = async (e) => {

        e.preventDefault();

        try {

            const response = await AuthService.login(loginData);

            const token = response.data.token;

            localStorage.setItem("token", token);

            alert("Login Successful");

            navigate("/");

        } catch (error) {

            console.error(error);

            alert("Invalid Username or Password");

        }

    };

    return (

        <div className="container mt-5">

            <div className="row justify-content-center">

                <div className="col-md-5">

                    <div className="card shadow">

                        <div className="card-header bg-primary text-white text-center">

                            <h3>GridWeaver Login</h3>

                        </div>

                        <div className="card-body">

                            <form onSubmit={handleLogin}>

                                <div className="mb-3">

                                    <label>Username</label>

                                    <input
                                        type="text"
                                        name="username"
                                        className="form-control"
                                        value={loginData.username}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <div className="mb-3">

                                    <label>Password</label>

                                    <input
                                        type="password"
                                        name="password"
                                        className="form-control"
                                        value={loginData.password}
                                        onChange={handleChange}
                                        required
                                    />

                                </div>

                                <button
                                    type="submit"
                                    className="btn btn-primary w-100"
                                >
                                    Login
                                </button>

                            </form>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );
}

export default Login;