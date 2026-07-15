const BASE_URL = "https://careerboost-backend-zkdt.onrender.com/quiz/java";

const userId = localStorage.getItem("userId");

let questions = [];
let answers = [];

let currentQuestion = 0;

let technology = "";
let difficulty = "";


// Sidebar


const sidebar = document.getElementById("sidebar");
const menuBtn = document.getElementById("menuBtn");
const overlay = document.getElementById("overlay");

menuBtn.onclick = () => {

    sidebar.classList.add("show");

    overlay.classList.add("active");

};

overlay.onclick = () => {

    sidebar.classList.remove("show");

    overlay.classList.remove("active");

};


// Page Load


window.onload = () => {

    if (!userId) {

        window.location.href = "login.html";
        return;

    }

};


// Start Quiz


async function startQuiz() {

    technology =
        document.getElementById("technology").value.trim();

    difficulty =
        document.getElementById("difficulty").value;

    if (technology === "") {

        alert("Enter Technology");

        return;

    }

    if (difficulty === "") {

        alert("Select Difficulty");

        return;

    }

    try {

        const response = await fetch(

            `${BASE_URL}/quiz/${encodeURIComponent(technology)}`

        );

        if (!response.ok) {

            throw new Error("Questions not found");

        }

        questions = await response.json();

        if (questions.length === 0) {

            alert("No questions available.");

            return;

        }

        currentQuestion = 0;

        answers = new Array(questions.length);

        document.getElementById("technologyName").innerText =
            technology;

        document.getElementById("difficultyName").innerText =
            difficulty;

        document.getElementById("totalQuestions").innerText =
            questions.length;

        document.getElementById("startSection").style.display =
            "none";

        document.getElementById("quizSection").style.display =
            "block";

        document.getElementById("resultSection").style.display =
            "none";

        showQuestion();

    }

    catch (e) {

        console.log(e);

        alert("Unable to load questions.");

    }

}


// Show Current Question


function showQuestion() {

    const q = questions[currentQuestion];

    const progress =
        ((currentQuestion + 1) / questions.length) * 100;

    document.getElementById("questionContainer").innerHTML = `

<div class="progress-card">

    <div class="progress-top">

        <span>
            Question ${currentQuestion + 1}
            / ${questions.length}
        </span>

        <span>
            ${Math.round(progress)}%
        </span>

    </div>

    <div class="progress-bar">

        <div class="progress-fill"
             style="width:${progress}%">

        </div>

    </div>

</div>

<div class="question-card">

    <div class="question-number">

        Question ${currentQuestion + 1}

    </div>

    <div class="question">

        ${q.question}

    </div>

    <div class="options">

        <label class="option">

            <input
                type="radio"
                name="answer"
                value="A">

            ${q.optionA}

        </label>

        <label class="option">

            <input
                type="radio"
                name="answer"
                value="B">

            ${q.optionB}

        </label>

        <label class="option">

            <input
                type="radio"
                name="answer"
                value="C">

            ${q.optionC}

        </label>

        <label class="option">

            <input
                type="radio"
                name="answer"
                value="D">

            ${q.optionD}

        </label>

    </div>

    <div class="button-group">

        <button
            class="btn prev-btn"
            onclick="previousQuestion()"
            ${currentQuestion === 0 ? "disabled" : ""}>

            Previous

        </button>

        ${
            currentQuestion === questions.length - 1

            ?

            `<button
                class="btn submit-btn"
                onclick="submitQuiz()">

                Submit Quiz

            </button>`

            :

            `<button
                class="btn next-btn"
                onclick="nextQuestion()">

                Next

            </button>`

        }

    </div>

</div>

`;

    restoreSelection();

}


// Restore Previous Selection


function restoreSelection() {

    const selected = answers[currentQuestion];

    if (!selected) return;

    const radio =
        document.querySelector(
            `input[value="${selected}"]`
        );

    if (radio) {

        radio.checked = true;

    }

}


// Save Current Answer


function saveAnswer() {

    const selected = document.querySelector(
        'input[name="answer"]:checked'
    );

    if (!selected) {

        alert("Please select an option.");

        return false;

    }

    answers[currentQuestion] = selected.value;

    return true;

}


// Next Question


function nextQuestion() {

    if (!saveAnswer()) {

        return;

    }

    currentQuestion++;

    showQuestion();

}


// Previous Question


function previousQuestion() {

    saveAnswer();

    if (currentQuestion > 0) {

        currentQuestion--;

        showQuestion();

    }

}


// Submit Quiz


async function submitQuiz() {

    if (!saveAnswer()) {

        return;

    }

    const answerList = [];

    for (let i = 0; i < questions.length; i++) {

        answerList.push({

            questionId: questions[i].id,

            selectedAnswer: answers[i]

        });

    }

    const request = {

        userId: Number(userId),

        technology: technology,

        difficulty: difficulty,

        answers: answerList

    };

    try {

        const response = await fetch(

            `${BASE_URL}/quiz/submit`,

            {

                method: "POST",

                headers: {

                    "Content-Type": "application/json"

                },

                body: JSON.stringify(request)

            }

        );

        if (!response.ok) {

            throw new Error("Quiz submission failed");

        }

        const result = await response.json();

        showResult(result);

    }

    catch (e) {

        console.log(e);

        alert("Unable to submit quiz.");

    }

}



// Show Result


function showResult(result) {

    document.getElementById("quizSection").style.display = "none";

    document.getElementById("resultSection").style.display = "block";

    document.getElementById("score").innerText =
        result.score;

    document.getElementById("correct").innerText =
        result.correctAnswers;

    document.getElementById("wrong").innerText =
        result.wrongAnswers;

    document.getElementById("percentage").innerText =
        result.percentage.toFixed(2) + "%";

    document.getElementById("message").innerText =
        result.message;

}


// Restart Quiz


function restartQuiz() {

    questions = [];

    answers = [];

    currentQuestion = 0;

    document.getElementById("technology").value = "";

    document.getElementById("difficulty").value = "";

    document.getElementById("startSection").style.display =
        "block";

    document.getElementById("quizSection").style.display =
        "none";

    document.getElementById("resultSection").style.display =
        "none";

}


// Navigation


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

function goInterview() {

    window.location.href = "interview.html";

}

function logout() {

    localStorage.clear();

    window.location.href = "login.html";

}
