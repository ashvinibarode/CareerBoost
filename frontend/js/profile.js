const BASE_URL = "https://careerboost-backend-zkdt.onrender.com";

const userId = localStorage.getItem("userId");

const sidebar = document.getElementById("sidebar");
const menuBtn = document.getElementById("menuBtn");
const overlay = document.getElementById("overlay");


// Page Load


window.onload = () => {

    if (!userId) {
        window.location.href = "login.html";
        return;
    }

    loadProfile();
    loadSkills();
};



// Load Profile


async function loadProfile() {

    try {

        const response = await fetch(
            `${BASE_URL}/profile/${userId}`
        );

        if (!response.ok) {
            throw new Error("Unable to load profile");
        }

        const data = await response.json();

        document.getElementById("fullName").innerText =
            data.fullName;

        document.getElementById("targetRole").innerText =
            data.targetRole || "Not Added";

        document.getElementById("roleText").innerText =
            data.targetRole || "Not Added";

        document.getElementById("email").innerText =
            data.email;

        document.getElementById("phone").innerText =
            data.phone || "-";

        document.getElementById("education").innerText =
            data.education || "-";

        document.getElementById("college").innerText =
            data.college || "-";

        document.getElementById("summary").innerText =
            data.summary || "No Summary";

        loadLinks(data);

        

    }

    catch (error) {

        console.log(error);

        alert("Unable to load profile.");

    }

}




// Skills


async function loadSkills() {

    const container = document.getElementById("skillsContainer");

    try {

        const response = await fetch(`${BASE_URL}/skills/user/${userId}`);

        const skills = await response.json();

        container.innerHTML = "";

        if (skills.length === 0) {
            container.innerHTML = "<p>No Skills Added</p>";
            return;
        }

        skills.forEach(skill => {

            const chip = document.createElement("span");

            chip.className = "skill";

            chip.innerText = skill;

            container.appendChild(chip);

        });

    } catch (e) {

        console.log(e);

    }

}



function goEditProfile() {

    window.location.href = "edit-profile.html";

}


// Links


function loadLinks(data) {

    const github =
        document.getElementById("github");

    github.href =
        data.githubUrl || "#";

    github.innerText =
        data.githubUrl || "Not Added";


    const linkedin =
        document.getElementById("linkedin");

    linkedin.href =
        data.linkedinUrl || "#";

    linkedin.innerText =
        data.linkedinUrl || "Not Added";


    const portfolio =
        document.getElementById("portfolio");

    portfolio.href =
        data.portfolioUrl || "#";

    portfolio.innerText =
        data.portfolioUrl || "Not Added";

}




// Sidebar


menuBtn.onclick = () => {

    sidebar.classList.add("show");

    overlay.classList.add("active");

};


overlay.onclick = () => {

    sidebar.classList.remove("show");

    overlay.classList.remove("active");

};



// Navigation


function goDashboard() {
    window.location.href = "dashboard.html";
}

function goResume() {
    window.location.href = "resume.html";
}

function goJD() {
    window.location.href = "job-description.html";
}

function goQuiz() {
    window.location.href = "quiz.html";
}

function goInterview() {
    window.location.href = "interview.html";
}



// Logout


function logout() {

    localStorage.clear();

    window.location.href = "login.html";

}