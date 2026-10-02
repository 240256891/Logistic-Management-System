import axios from 'axios';

const API_BASE = 'http://localhost:8080/api/shipmenttracking';

export async function getShipmentTracking() {
    const response = await axios.get(`${API_BASE}/getall`);
    return response.data;
}

export async function createShipmentTracking(shipmentTracking) {
    const response = await axios.post(
        `${API_BASE}/create`,
        shipmentTracking
    );

    return response.data;
}

export async function updateShipmentTracking(shipmentTracking) {
    const response = await axios.put(
        `${API_BASE}/update`,
        shipmentTracking
    );

    return response.data;
}

export async function deleteShipmentTracking(trackingId) {
    await axios.delete(
        `${API_BASE}/delete/${trackingId}`
    );
}

export async function updateShipmentStatus(
    trackingId,
    shipmentStatus
) {
    const response = await axios.patch(
        `${API_BASE}/update-status/${trackingId}`,
        null,
        {
            params: {
                shipmentStatus: shipmentStatus
            }
        }
    );

    return response.data;
}