function showAlert() {
    alert("The button was clicked!");
}

function redirectToEmployeeDetails(employeeId) {
    window.location.href = '/employee-details?id=' + employeeId;
}