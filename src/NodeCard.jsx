function NodeCard() {

    const node = {
        id: 1,
        status: "Charging",
        power: "5.2 kW"
    };

    return (
        <div
            style={{
                border: "1px solid gray",
                padding: "15px",
                width: "250px",
                borderRadius: "10px"
            }}
        >
            <h3>Node {node.id}</h3>
            <p>Status : {node.status}</p>
            <p>Power : {node.power}</p>
        </div>
    );
}

export default NodeCard;