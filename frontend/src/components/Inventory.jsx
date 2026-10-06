import { useEffect, useState } from "react";

const API_URL = "http://localhost:8080/api/inventory";

function Inventory() {
  const [inventory, setInventory] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const [formData, setFormData] = useState({
    itemName: "",
    sku: "",
    quantity: "",
    unitWeight: "",
    companyId: "",
  });

  const [editingId, setEditingId] = useState(null);

  // Load inventory
  const getInventory = async () => {
    try {
      setLoading(true);
      setError("");

      const response = await fetch(`${API_URL}/getall`);

      if (!response.ok) {
        throw new Error("Failed to load inventory");
      }

      const data = await response.json();
      setInventory(data);
    } catch (error) {
      setError("Unable to load inventory.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    getInventory();
  }, []);

  // Handle input
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  // Clear form
  const clearForm = () => {
    setFormData({
      itemName: "",
      sku: "",
      quantity: "",
      unitWeight: "",
      companyId: "",
    });

    setEditingId(null);
  };

  // Add inventory
  const addInventory = async (e) => {
    e.preventDefault();

    if (
      !formData.itemName ||
      !formData.sku ||
      !formData.quantity ||
      !formData.unitWeight ||
      !formData.companyId
    ) {
      alert("Please fill in all fields.");
      return;
    }

    try {
      const params = new URLSearchParams();

      params.append("itemName", formData.itemName);
      params.append("sku", formData.sku);
      params.append("quantity", formData.quantity);
      params.append("unitWeight", formData.unitWeight);
      params.append("companyId", formData.companyId);

      const response = await fetch(
        `${API_URL}/create?${params.toString()}`,
        {
          method: "POST",
        }
      );

      if (!response.ok) {
        throw new Error("Failed to add inventory");
      }

      alert("Inventory item added successfully.");

      clearForm();
      getInventory();
    } catch (error) {
      alert("Failed to add inventory item.");
    }
  };

  // Edit inventory
  const editInventory = (item) => {
    setEditingId(item.inventoryId);

    setFormData({
      itemName: item.itemName,
      sku: item.sku,
      quantity: item.quantityAvailable,
      unitWeight: item.unitWeight,
      companyId: item.companyId,
    });

    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  // Update inventory
  const updateInventory = async (e) => {
    e.preventDefault();

    try {
      const updatedItem = {
        inventoryId: editingId,
        itemName: formData.itemName,
        sku: formData.sku,
        quantityAvailable: Number(formData.quantity),
        unitWeight: Number(formData.unitWeight),
        companyId: formData.companyId,
      };

      const response = await fetch(`${API_URL}/update`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(updatedItem),
      });

      if (!response.ok) {
        throw new Error("Failed to update inventory");
      }

      alert("Inventory updated successfully.");

      clearForm();
      getInventory();
    } catch (error) {
      alert("Failed to update inventory.");
    }
  };

  // Delete inventory
  const deleteInventory = async (id) => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this inventory item?"
    );

    if (!confirmDelete) {
      return;
    }

    try {
      const response = await fetch(`${API_URL}/delete/${id}`, {
        method: "DELETE",
      });

      if (!response.ok) {
        throw new Error("Failed to delete inventory");
      }

      alert("Inventory item deleted.");

      getInventory();
    } catch (error) {
      alert("Failed to delete inventory.");
    }
  };

  // Update quantity
  const updateQuantity = async (item) => {
    const quantity = window.prompt(
      "Enter the new quantity:",
      item.quantityAvailable
    );

    if (quantity === null) {
      return;
    }

    if (quantity === "" || Number(quantity) < 0) {
      alert("Please enter a valid quantity.");
      return;
    }

    try {
      const response = await fetch(
        `${API_URL}/update-quantity/${item.inventoryId}?quantity=${Number(
          quantity
        )}`,
        {
          method: "PATCH",
        }
      );

      if (!response.ok) {
        throw new Error("Failed to update quantity");
      }

      alert("Quantity updated.");

      getInventory();
    } catch (error) {
      alert("Failed to update quantity.");
    }
  };

  return (
    <div className="inventory-container">

      <div className="inventory-header">
        <div>
          <h1>Inventory</h1>
          <p>Manage your inventory items and stock levels.</p>
        </div>

        <button
          className="inventory-refresh"
          onClick={getInventory}
        >
          Refresh
        </button>
      </div>

      {error && (
        <div className="inventory-error">
          {error}
        </div>
      )}

      {/* ADD / EDIT FORM */}

      <div className="inventory-form-card">

        <h2>
          {editingId ? "Edit Inventory Item" : "Add Inventory Item"}
        </h2>

        <form
          onSubmit={
            editingId
              ? updateInventory
              : addInventory
          }
        >

          <div className="inventory-form-grid">

            <div className="inventory-input-group">
              <label>Item Name</label>

              <input
                type="text"
                name="itemName"
                value={formData.itemName}
                onChange={handleChange}
                placeholder="Enter item name"
              />
            </div>

            <div className="inventory-input-group">
              <label>SKU</label>

              <input
                type="text"
                name="sku"
                value={formData.sku}
                onChange={handleChange}
                placeholder="Enter SKU"
              />
            </div>

            <div className="inventory-input-group">
              <label>Quantity Available</label>

              <input
                type="number"
                name="quantity"
                value={formData.quantity}
                onChange={handleChange}
                min="0"
                placeholder="Enter quantity"
              />
            </div>

            <div className="inventory-input-group">
              <label>Unit Weight</label>

              <input
                type="number"
                name="unitWeight"
                value={formData.unitWeight}
                onChange={handleChange}
                min="0"
                step="0.01"
                placeholder="Enter weight"
              />
            </div>

            <div className="inventory-input-group">
              <label>Company ID</label>

              <input
                type="text"
                name="companyId"
                value={formData.companyId}
                onChange={handleChange}
                placeholder="Enter company ID"
              />
            </div>

          </div>

          <div className="inventory-form-buttons">

            <button
              type="submit"
              className="inventory-add-button"
            >
              {editingId
                ? "Update Inventory"
                : "Add Inventory"}
            </button>

            {editingId && (
              <button
                type="button"
                className="inventory-cancel-button"
                onClick={clearForm}
              >
                Cancel
              </button>
            )}

          </div>

        </form>

      </div>

      {/* INVENTORY TABLE */}

      <div className="inventory-table-card">

        <div className="inventory-table-header">
          <div>
            <h2>Inventory List</h2>
            <p>
              {inventory.length} item(s) in inventory
            </p>
          </div>
        </div>

        {loading ? (
          <div className="inventory-loading">
            Loading inventory...
          </div>
        ) : inventory.length === 0 ? (
          <div className="inventory-empty">
            <h3>No inventory items found</h3>
            <p>
              Add your first inventory item using the
              form above.
            </p>
          </div>
        ) : (
          <div className="inventory-table-wrapper">

            <table className="inventory-table">

              <thead>
                <tr>
                  <th>Item Name</th>
                  <th>SKU</th>
                  <th>Quantity</th>
                  <th>Unit Weight</th>
                  <th>Company ID</th>
                  <th>Actions</th>
                </tr>
              </thead>

              <tbody>

                {inventory.map((item) => (

                  <tr key={item.inventoryId}>

                    <td>
                      <strong>
                        {item.itemName}
                      </strong>
                    </td>

                    <td>
                      {item.sku}
                    </td>

                    <td>

                      <span
                        className={
                          item.quantityAvailable === 0
                            ? "inventory-stock-out"
                            : item.quantityAvailable <= 10
                            ? "inventory-stock-low"
                            : "inventory-stock-good"
                        }
                      >
                        {item.quantityAvailable}
                      </span>

                    </td>

                    <td>
                      {item.unitWeight}
                    </td>

                    <td>
                      {item.companyId}
                    </td>

                    <td>

                      <div className="inventory-actions">

                        <button
                          className="inventory-edit-button"
                          onClick={() =>
                            editInventory(item)
                          }
                        >
                          Edit
                        </button>

                        <button
                          className="inventory-quantity-button"
                          onClick={() =>
                            updateQuantity(item)
                          }
                        >
                          Quantity
                        </button>

                        <button
                          className="inventory-delete-button"
                          onClick={() =>
                            deleteInventory(
                              item.inventoryId
                            )
                          }
                        >
                          Delete
                        </button>

                      </div>

                    </td>

                  </tr>

                ))}

              </tbody>

            </table>

          </div>
        )}

      </div>

    </div>
  );
}

export default Inventory;