

const API_BASE = "https://careerboost-backend-zkdt.onrender.com";

const ANALYZE_API = API_BASE + "/ai/resume/pdf";
const JD_COMPARE_API = API_BASE + "/jd/analyze";


// Elements


const analyzeBtn = document.getElementById("analyzeBtn");
const compareBtn = document.getElementById("compareBtn");

const resumeFile = document.getElementById("resumeFile");
const jdResumeFile = document.getElementById("jdResumeFile");
const jobDescription = document.getElementById("jobDescription");


// Event Listeners


if (analyzeBtn) {
    analyzeBtn.addEventListener("click", analyzeResume);
}

if (compareBtn) {
    compareBtn.addEventListener("click", compareResume);
}


// Resume Analyzer


async function analyzeResume() {

    if (!resumeFile || !resumeFile.files.length) {

        alert("Please upload your resume.");

        return;

    }

    analyzeBtn.disabled = true;
    analyzeBtn.innerText = "Analyzing...";

    try {

        const formData = new FormData();

        formData.append("file", resumeFile.files[0]);

        const response = await fetch(ANALYZE_API, {

            method: "POST",

            body: formData

        });

        if (!response.ok) {

            throw new Error("Resume Analysis Failed");

        }

        const data = await response.json();

        renderResumeAnalysis(data);

    }

    catch (error) {

        console.error(error);

        alert("Unable to analyze resume.");

    }

    finally {

        analyzeBtn.disabled = false;

        analyzeBtn.innerText = "Analyze Resume";

    }

}



// Resume vs Job Description


async function compareResume() {

    if (!jdResumeFile || !jdResumeFile.files.length) {

        alert("Please upload your resume.");

        return;

    }

    if (!jobDescription || jobDescription.value.trim() === "") {

        alert("Enter Job Description.");

        return;

    }

    compareBtn.disabled = true;
    compareBtn.innerText = "Comparing...";

    try {

        const formData = new FormData();

        formData.append("file", jdResumeFile.files[0]);

        formData.append(
            "jobDescription",
            jobDescription.value
        );

        const response = await fetch(JD_COMPARE_API, {

            method: "POST",

            body: formData

        });

        if (!response.ok) {

            throw new Error("Comparison Failed");

        }

        const data = await response.json();

        renderJDAnalysis(data);

    }

    catch (error) {

        console.error(error);

        alert("Unable to compare resume.");

    }

    finally {

        compareBtn.disabled = false;

        compareBtn.innerText = "Compare Resume";

    }

}


// Resume Result


function renderResumeAnalysis(data) {

    if (document.getElementById("resumeScore"))
        document.getElementById("resumeScore").innerText =
            data.resumeScore ?? 0;

    if (document.getElementById("atsScore"))
        document.getElementById("atsScore").innerText =
            data.atsScore ?? 0;

    setList("strengthsList", data.strengths);

    setList("weaknessesList", data.weaknesses);

    setList("missingSkillsList", data.missingSkills);

    setKeywordTable(data.keywordAnalysis);

    setList("atsSuggestionsList", data.atsSuggestions);

    setList("projectSuggestionsList", data.projectSuggestions);

    setList("interviewTipsList", data.interviewTips);

    setList("roadmapList", data.learningRoadmap);

}


// JD Result


function renderJDAnalysis(data) {

    if (document.getElementById("matchScore"))
        document.getElementById("matchScore").innerText =
            (data.matchScore ?? 0) + "%";

    setList(
        "matchedSkillsList",
        data.matchedSkills
    );

    setList(
        "jdMissingSkillsList",
        data.missingSkills
    );

    setList(
        "missingKeywordsList",
        data.missingKeywords
    );

    setList(
        "keywordSuggestionsList",
        data.keywordSuggestions
    );

    setList(
        "improvementSuggestionsList",
        data.improvementSuggestions
    );

}


// Common List Function


function setList(id, items) {

    const ul = document.getElementById(id);

    // Agar current page par element hi nahi hai
    if (!ul) return;

    ul.innerHTML = "";

    if (!items || items.length === 0) {

        ul.innerHTML = "<li>No Data Available</li>";

        return;

    }

    items.forEach(item => {

        const li = document.createElement("li");

        li.textContent = item;

        ul.appendChild(li);

    });

}


// Keyword Analysis Table


function setKeywordTable(list) {

    const tbody = document.getElementById("keywordTable");

    // Resume page ke alawa kisi aur page par error na aaye
    if (!tbody) return;

    tbody.innerHTML = "";

    if (!list || list.length === 0) {

        tbody.innerHTML = `
            <tr>
                <td colspan="3" style="text-align:center;">
                    No Data Available
                </td>
            </tr>
        `;

        return;

    }

    list.forEach(keyword => {

        const row = document.createElement("tr");

        row.innerHTML = `

            <td>${keyword.keyword ?? "-"}</td>

            <td>${keyword.status ?? "-"}</td>

            <td>${keyword.context ?? "-"}</td>

        `;

        tbody.appendChild(row);

    });

}


// Optional Navigation


function goDashboard() {
    window.location.href = "dashboard.html";
}

function goProfile() {
    window.location.href = "profile.html";
}

function goResume() {
    window.location.href = "resume.html";
}

function goJobMatch() {
    window.location.href = "job-description.html";
}

function goJobMatch() {
    window.location.href = "quiz.html";
}

function goJobMatch() {
    window.location.href = "interview.html";
}

function logout() {

    localStorage.clear();

    window.location.href = "login.html";

}