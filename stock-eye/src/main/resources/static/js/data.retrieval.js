document.addEventListener('DOMContentLoaded', function () {
    // Add event listener to show modal when "Add New Item" button is clicked
    document.getElementById('addNewItemBtn').addEventListener('click', function () {
        var addItemModal = new bootstrap.Modal(document.getElementById('addItemModal'));
        addItemModal.show();
    });

    // Add event listener to handle form submission
    document.getElementById('addItemForm').addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent default form submission

        // Get input values
        var itemName = document.getElementById('itemName').value;
        var itemQuantity = document.getElementById('itemQuantity').value;

        // Create new item object
        var newItem = {
            name: itemName,
            quantity: itemQuantity
        };

        // Log the new item object for debugging
        console.log('New Item:', newItem);

        // Send POST request to backend API to add new item
        fetch('/api/items', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newItem)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Log the response data for debugging
                console.log('Success:', data);

                // Close modal after successfully adding item
                var addItemModalEl = document.getElementById('addItemModal');
                var addItemModal = bootstrap.Modal.getInstance(addItemModalEl);
                addItemModal.hide();

                // Refresh table to display new item
                refreshTable();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('An error occurred while adding the item.');
            });
    });

    // Initial table refresh to load existing items
    refreshTable();
});

// Function to refresh table data
function refreshTable() {
    fetch('/api/items')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('tbody');
            tableBody.innerHTML = '';

            data.forEach(item => {
                const row = `
            <tr>
              <td>${item.name}</td>
              <td>${item.quantity}</td>
              <td>
                <button type="button" class="btn btn-sm btn-primary" onclick="editItem(${item.id})">Edit</button>
                <button type="button" class="btn btn-sm btn-danger" onclick="deleteItem(${item.id})">Delete</button>
              </td>
            </tr>
          `;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred while fetching inventory data.');
            console.log(error);
        });
}