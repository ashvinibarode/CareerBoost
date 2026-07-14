const loginForm = document.querySelector("form");

loginForm.addEventListener("submit", async function (e) {

    e.preventDefault();

    const email = document.querySelector("input[type='email']").value.trim();
    const password = document.querySelector("input[type='password']").value.trim();

    if(email==="" || password===""){
        alert("Please fill all fields");
        return;
    }

    const user={

        email:email,
        password:password

    };

    try{

        const response=await fetch("https://careerboost-backend-zkdt.onrender.com/api/auth/login",{

            method:"POST",

            headers:{

                "Content-Type":"application/json"

            },

            body:JSON.stringify(user)

        });

        if(response.ok){

            const data=await response.json();

          localStorage.setItem("user", JSON.stringify(data));
          localStorage.setItem("userId", data.userId);
          localStorage.setItem("fullName", data.fullName);
          
            alert("Login Successful");

            window.location.href="dashboard.html";

        }

        else{

            alert("Invalid Email or Password");

        }

    }

    catch(error){

        console.log(error);

        alert("Server Not Running");

    }

});