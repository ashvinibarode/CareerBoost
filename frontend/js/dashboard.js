const BASE_URL = "https://careerboost-backend-zkdt.onrender.com";

const userId = localStorage.getItem("userId");

const sidebar = document.getElementById("sidebar");
const mainContent = document.getElementById("mainContent");
const menuBtn = document.getElementById("menuBtn");
const overlay = document.getElementById("overlay");

// ----------------------
// Load Dashboard
// ----------------------

window.onload = function () {

    if (!userId) {
        window.location.href = "login.html";
        return;
    }

    loadDashboard();
};

// ----------------------
// Dashboard API
// ----------------------

function loadDashboard() {

    fetch(`${BASE_URL}/dashboard/${userId}`)

        .then(response => {

            if (!response.ok) {
                throw new Error("Dashboard loading failed");
            }

            return response.json();
        })

        .then(data => {

            document.getElementById("userName").innerText = data.fullName;

            document.getElementById("email").innerHTML =
                "<strong>Email:</strong> " + data.email;

            document.getElementById("role").innerHTML =
                "<strong>Role:</strong> " + data.role;

            if (data.resumeUploaded) {

                document.getElementById("resumeStatus").innerHTML =
                    "✅ Resume Uploaded";

                document.getElementById("resumeName").innerHTML =
                    "<strong>File:</strong> " + data.resumeFileName;

                document.getElementById("uploadDate").innerHTML =
                    "<strong>Uploaded:</strong> " +
                    formatDate(data.uploadedAt);

            } else {

                document.getElementById("resumeStatus").innerHTML =
                    "❌ Resume Not Uploaded";

                document.getElementById("resumeName").innerHTML = "";

                document.getElementById("uploadDate").innerHTML = "";

            }

        })

        .catch(error => {

            console.error(error);

            alert("Unable to load dashboard.");

        });

}

// ----------------------
// Date Format
// ----------------------

function formatDate(date) {

    if (!date) return "";

    return new Date(date).toLocaleString();

}

// ----------------------
// Sidebar
// ----------------------

menuBtn.addEventListener("click", () => {

    if (window.innerWidth <= 900) {

        sidebar.classList.toggle("show");

        overlay.classList.toggle("active");

    } else {

        sidebar.classList.toggle("collapsed");

        mainContent.classList.toggle("expand");

    }

});

// ----------------------
// Overlay Close
// ----------------------

overlay.addEventListener("click", () => {

    sidebar.classList.remove("show");

    overlay.classList.remove("active");

});

// ----------------------
// Navigation
// ----------------------

function goDashboard() {

    window.location.href = "dashboard.html";

}

function openResume() {

    window.location.href = "resume.html";

}

function openJD() {

    window.location.href = "job-description.html";

}

function openQuiz() {

    window.location.href = "quiz.html";

}

function openInterview() {

    window.location.href = "interview.html";

}

function openProfile() {

    window.location.href = "profile.html";

}

// ----------------------
// Logout
// ----------------------

function logout() {

    if (confirm("Are you sure you want to logout?")) {

        localStorage.clear();

        window.location.href = "login.html";

    }

}