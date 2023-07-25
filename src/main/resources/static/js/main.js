document.addEventListener('DOMContentLoaded', function () {
    const hiringDate = document.getElementById("hiring-date");
    const departureDate = document.getElementById("departure-date");
    hiringDate.addEventListener('change', function () {
        const actual = new Date(this.value)
        departureDate.setAttribute('min', actual.toISOString().split('T')[0])
    })
})