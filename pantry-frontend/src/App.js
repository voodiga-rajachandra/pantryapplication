import React, { useEffect, useState } from "react";
import axios from "axios";

function App() {
    const [items, setItems] = useState([]);
    const [orders, setOrders] = useState([]);
    const [selectedItem, setSelectedItem] = useState("");

    useEffect(() => {
        axios.get("http://localhost:8080/api/items").then(res => setItems(res.data));
        axios.get("http://localhost:8080/api/orders").then(res => setOrders(res.data));
    }, []);

    const placeOrder = () => {
        axios.post("http://localhost:8080/api/order", {
            userName: "User1",
            itemName: selectedItem
        }).then(() => window.location.reload());
    };

    const completeOrder = (id) => {
        axios.put(`http://localhost:8080/api/order/${id}/complete`).then(() => window.location.reload());
    };

    return (
        <div>
            <h2>Available Items</h2>
            <select onChange={(e) => setSelectedItem(e.target.value)}>
                <option>Select Item</option>
                {items.map(item => <option key={item.id}>{item.name}</option>)}
            </select>
            <button onClick={placeOrder}>Place Order</button>

            <h2>Orders</h2>
            <ul>
                {orders.map(order => (
                    <li key={order.id}>{order.userName} ordered {order.itemName} - {order.status}
                        {order.status === "pending" && <button onClick={() => completeOrder(order.id)}>Complete</button>}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default App;
