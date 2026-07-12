const BASE_URL = "http://localhost:8080";

const userId = localStorage.getItem("userId");

let skills = [];

// ======================
// Page Load
// ======================

window.onload = () => {

    if (!userId) {
        window.location.href = "login.html";
        return;
    }

    loadProfile();
    loadSkills();
};

// ======================
// Load Profile
// ======================

async function loadProfile() {

    try {

        const response = await fetch(`${BASE_URL}/profile/${userId}`);

        const data = await response.json();

        document.getElementById("fullName").value =
        data.fullName || "";

        document.getElementById("phone").value =
            data.phone || "";

        document.getElementById("education").value =
            data.education || "";

        document.getElementById("college").value =
            data.college || "";

        document.getElementById("targetRole").value =
            data.targetRole || "";

        document.getElementById("githubUrl").value =
            data.githubUrl || "";

        document.getElementById("linkedinUrl").value =
            data.linkedinUrl || "";

        document.getElementById("portfolioUrl").value =
            data.portfolioUrl || "";

        document.getElementById("summary").value =
            data.summary || "";

    } catch (e) {

        console.log(e);

    }

}

// ======================
// Load Skills
// ======================

async function loadSkills() {

    try {

        const response =
            await fetch(`${BASE_URL}/skills/user/${userId}`);

        skills = await response.json();

        renderSkills();

    }

    catch (e) {

        console.log(e);

    }

}

// ======================
// Render Skills
// ======================

function renderSkills() {

    const container =
        document.getElementById("skillsContainer");

    container.innerHTML = "";

    skills.forEach((skill, index) => {

        const name =
            typeof skill === "string"
                ? skill
                : skill.skillName;

        container.innerHTML += `

            <div class="skill-chip">

                ${name}

                <span
                class="remove-skill"
                onclick="removeSkill(${index})">

                ×

                </span>

            </div>

        `;

    });

}

// ======================
// Add Skill
// ======================

function addSkill() {

    const input =
        document.getElementById("skillInput");

    let skill = input.value.trim();

    if (skill === "") {

        alert("Enter skill");

        return;

    }

    const exists = skills.some(s => {

        const value =
            typeof s === "string"
                ? s
                : s.skillName;

        return value.toLowerCase() ===
            skill.toLowerCase();

    });

    if (exists) {

        alert("Skill already exists");

        return;

    }

    skills.push(skill);

    renderSkills();

    input.value = "";

}

// ======================
// Remove Skill
// ======================

function removeSkill(index) {

    skills.splice(index,1);

    renderSkills();

}

// ======================
// Save Profile
// ======================

async function saveProfile() {

    const profile = {

        fullName: document.getElementById("fullName").value,

        phone:
            document.getElementById("phone").value,

        education:
            document.getElementById("education").value,

        college:
            document.getElementById("college").value,

        targetRole:
            document.getElementById("targetRole").value,

        githubUrl:
            document.getElementById("githubUrl").value,

        linkedinUrl:
            document.getElementById("linkedinUrl").value,

        portfolioUrl:
            document.getElementById("portfolioUrl").value,

        summary:
            document.getElementById("summary").value

    };

    try {

        // Profile Save

        await fetch(`${BASE_URL}/profile/${userId}`, {

            method: "PUT",

            headers: {

                "Content-Type":"application/json"

            },

            body: JSON.stringify(profile)

        });


        // Skills Save

        await fetch(`${BASE_URL}/skills/${userId}`,{

            method:"POST",

            headers:{

                "Content-Type":"application/json"

            },

            body:JSON.stringify(skills)

        });


        alert("Profile Updated Successfully");

        window.location.href="profile.html";

    }

    catch(e){

        console.log(e);

        alert("Something went wrong.");

    }

}