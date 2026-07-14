
// CareerBoost Register
const form = document.getElementById("registerForm");

form.addEventListener("submit", async function (e) {

    e.preventDefault();

    

const API_URL = "https://careerboost-backend-zkdt.onrender.com/api/auth/register";


// Password Toggle


const password = document.getElementById("password");
const confirmPassword = document.getElementById("confirmPassword");

const togglePassword = document.getElementById("togglePassword");
const toggleConfirmPassword = document.getElementById("toggleConfirmPassword");

togglePassword.addEventListener("click", () => {

    if (password.type === "password") {
        password.type = "text";
        togglePassword.classList.replace("fa-eye", "fa-eye-slash");
    } else {
        password.type = "password";
        togglePassword.classList.replace("fa-eye-slash", "fa-eye");
    }

});

toggleConfirmPassword.addEventListener("click", () => {

    if (confirmPassword.type === "password") {
        confirmPassword.type = "text";
        toggleConfirmPassword.classList.replace("fa-eye", "fa-eye-slash");
    } else {
        confirmPassword.type = "password";
        toggleConfirmPassword.classList.replace("fa-eye-slash", "fa-eye");
    }

});


// Register Form


document.getElementById("registerForm").addEventListener("submit", registerUser);

async function registerUser(e) {

    e.preventDefault();

    const fullName = document.getElementById("fullName").value.trim();
    const email = document.getElementById("email").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    // Validation

    if (fullName.length < 3) {
        alert("Full name must contain at least 3 characters.");
        return;
    }

    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailPattern.test(email)) {
        alert("Enter a valid email.");
        return;
    }

    if (!/^\d{10}$/.test(phone)) {
        alert("Enter a valid 10-digit phone number.");
        return;
    }

    if (password.length < 8) {
        alert("Password must be at least 8 characters.");
        return;
    }

    if (password !== confirmPassword) {
        alert("Passwords do not match.");
        return;
    }

    const user = {

        fullName: fullName,
        email: email,
        phone: phone,
        password: password

    };

    try {

        const response = await fetch(API_URL, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(user)

        });

        if (response.ok) {

            alert("Registration Successful!");

            window.location.href = "login.html";

        } else {

            const message = await response.text();

            alert(message);

        }

    }

    catch (error) {

        console.error(error);

        alert("Server is not running or API error.");

    }

}
});