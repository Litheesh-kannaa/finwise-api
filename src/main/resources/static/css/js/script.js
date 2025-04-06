function createUser() {
    const user = {
        fullName: document.getElementById("fullName").value,
        email: document.getElementById("email").value,
        passwordHash: document.getElementById("password").value
    };

    fetch("/api/users", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user)
    })
    .then(res => res.json())
    .then(data => alert("User created: " + JSON.stringify(data)))
    .catch(err => alert("Error: " + err));
}

function getAllUsers() {
    fetch("/api/users")
        .then(res => res.json())
        .then(users => {
            const list = users.map(u => `<li>${u.fullName} - ${u.email}</li>`).join('');
            document.getElementById("userList").innerHTML = `<ul>${list}</ul>`;
        });
}

function analyzeInvestment() {
    const json = document.getElementById("investmentInput").value;
    try {
        const recommendationInput = JSON.parse(json);
        fetch("/api/investment-recommendations/analyze", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(recommendationInput)
        })
        .then(res => res.text())
        .then(result => {
            document.getElementById("investmentResult").innerText = "Recommendation: " + result;
        });
    } catch (e) {
        alert("Invalid JSON");
    }
}
