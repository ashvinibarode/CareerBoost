const BASE_URL = "https://careerboost-backend-zkdt.onrender.com";

const sidebar = document.getElementById("sidebar");
const menuBtn = document.getElementById("menuBtn");
const overlay = document.getElementById("overlay");

const loading = document.getElementById("loading");
const questionsContainer = document.getElementById("questionsContainer");

// ======================
// Sidebar
// ======================

menuBtn.onclick = () => {

    sidebar.classList.add("show");
    overlay.classList.add("active");

};

overlay.onclick = () => {

    sidebar.classList.remove("show");
    overlay.classList.remove("active");

};

// ======================
// Generate Questions
// ======================

async function generateQuestions() {

    const technology =
        document.getElementById("technology").value.trim();

    const level =
        document.getElementById("level").value;

    const type =
        document.getElementById("type").value;

    if (technology === "") {

        alert("Please enter technology.");

        return;
    }

    if (level === "") {

        alert("Please select level.");

        return;
    }

    if (type === "") {

        alert("Please select interview type.");

        return;
    }

    loading.style.display = "block";
    questionsContainer.innerHTML = "";

    try {

        const response = await fetch(
            `${BASE_URL}/interview/generate`,
            {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({

                    technology: technology,
                    level: level,
                    type: type

                })

            });

        if (!response.ok) {

            throw new Error("Failed to generate interview questions.");

        }

        const data = await response.json();

        loading.style.display = "none";

        renderQuestions(data.questions);

    }

    catch (error) {

        loading.style.display = "none";

        console.log(error);

        alert("Unable to generate interview questions.");

    }

}

// ======================
// Render Questions
// ======================

function renderQuestions(questions) {

    questionsContainer.innerHTML = "";

    if (!questions || questions.length === 0) {

        questionsContainer.innerHTML = `
        
            <div class="no-data">

                No Questions Generated.

            </div>
        
        `;

        return;

    }

    questions.forEach((item, index) => {

        questionsContainer.innerHTML += `

            <div class="question-card">

                <h3>
                    Question ${index + 1}
                </h3>

                <p class="question">

                    ${item.question}

                </p>

                <button
                    class="show-btn"
                    onclick="toggleAnswer(${index})">

                    Show Answer

                </button>

                <div
                    class="answer"
                    id="answer${index}">

                    ${item.answer}

                </div>

            </div>

        `;

    });

}

// ======================
// Show / Hide Answer
// ======================

function toggleAnswer(index) {

    const answer =
        document.getElementById("answer" + index);

    const button =
        answer.previousElementSibling;

    if (answer.style.display === "block") {

        answer.style.display = "none";

        button.innerHTML = "Show Answer";

    }

    else {

        answer.style.display = "block";

        button.innerHTML = "Hide Answer";

    }

}

// ======================
// Navigation
// ======================

function goDashboard() {

    window.location.href = "dashboard.html";

}

function goProfile() {

    window.location.href = "profile.html";

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

function logout() {

    localStorage.clear();

    window.location.href = "login.html";

}