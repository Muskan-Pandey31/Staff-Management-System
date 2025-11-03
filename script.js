const API_URL = "http://localhost:8080/getStaffDetails";

const staffTableBody = document.querySelector("#staffTable tbody");
const totalStaffElem = document.getElementById("totalStaff");
const modal = document.getElementById("staffModal");
const addStaffBtn = document.getElementById("addStaffBtn");
const closeModalBtn = document.querySelector(".close");
const staffForm = document.getElementById("staffForm");
const modalTitle = document.getElementById("modalTitle");

const searchBtn = document.getElementById("searchBtn");
const resetBtn = document.getElementById("resetBtn");
const searchInput = document.getElementById("searchId");

let editingId = null;


async function loadStaff() {
    const res = await fetch(`${API_URL}/getAllStaff`);
    const staffList = await res.json();

    staffTableBody.innerHTML = "";
    totalStaffElem.textContent = staffList.length;

    staffList.forEach(staff => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${staff.id}</td>
            <td>${staff.firstName}</td>
            <td>${staff.lastName}</td>
            <td>${staff.salary}</td>
            <td>${staff.email}</td>
            <td>
                <button class="action-btn edit-btn" onclick="openEditModal(${staff.id}, '${staff.firstName}', '${staff.lastName}', ${staff.salary}, '${staff.email}')">Edit</button>
                <button class="action-btn delete-btn" onclick="deleteStaff(${staff.id})">Delete</button>
            </td>
        `;
        staffTableBody.appendChild(row);
    });
}


addStaffBtn.onclick = () => {
    modal.style.display = "flex";
    modalTitle.textContent = "Add Staff";
    staffForm.reset();
    editingId = null;
    document.getElementById("staffId").value = "";
}


closeModalBtn.onclick = () => modal.style.display = "none";
window.onclick = (e) => { if(e.target == modal) modal.style.display = "none"; }


staffForm.onsubmit = async (e) => {
    e.preventDefault();
    const staff = {
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        salary: Number(document.getElementById("salary").value),
        email: document.getElementById("email").value
    };

    if(editingId) {
      
        await fetch(`${API_URL}/${editingId}`, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(staff)
        });
    } else {
        
        await fetch(API_URL, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(staff)
        });
    }

    modal.style.display = "none";
    loadStaff();
}


function openEditModal(id, firstName, lastName, salary, email) {
    editingId = id;
    document.getElementById("staffId").value = id;
    document.getElementById("firstName").value = firstName;
    document.getElementById("lastName").value = lastName;
    document.getElementById("salary").value = salary;
    document.getElementById("email").value = email;
    modalTitle.textContent = "Edit Staff";
    modal.style.display = "flex";
}


async function deleteStaff(id) {
    if(confirm("Are you sure you want to delete this staff?")) {
        await fetch(`${API_URL}/${id}`, { method: "DELETE" });
        loadStaff();
    }
}


searchBtn.onclick = async () => {
    const id = searchInput.value;
    if(!id) return alert("Enter ID to search");
    try {
        const res = await fetch(`${API_URL}/getStaffById/${id}`);
        if(!res.ok) throw new Error("Staff not found");
        const staff = await res.json();
        staffTableBody.innerHTML = `
            <tr>
                <td>${staff.id}</td>
                <td>${staff.firstName}</td>
                <td>${staff.lastName}</td>
                <td>${staff.salary}</td>
                <td>${staff.email}</td>
                <td>
                    <button class="action-btn edit-btn" onclick="openEditModal(${staff.id}, '${staff.firstName}', '${staff.lastName}', ${staff.salary}, '${staff.email}')">Edit</button>
                    <button class="action-btn delete-btn" onclick="deleteStaff(${staff.id})">Delete</button>
                </td>
            </tr>
        `;
    } catch(err) {
        alert("Staff not found");
    }
}

resetBtn.onclick = () => {
    searchInput.value = "";
    loadStaff();
}

loadStaff();
