document.addEventListener("DOMContentLoaded", function () {
    const checkbox = document.getElementById("terms");
    const createAccountBtn = document.getElementById("createAccountBtn");

    checkbox.addEventListener("change", function () {
        if (this.checked) {
            createAccountBtn.disabled = false;
            createAccountBtn.classList.add("enabled-btn");
        } else {
            createAccountBtn.disabled = true;
            createAccountBtn.classList.remove("enabled-btn");
            createAccountBtn.classList.add("disabled-btn");
        }


    });
    const closeBtn = document.getElementById("closeBtn");

    closeBtn.addEventListener("click", function () {
        window.location.href = "index.html"; // Redirect to home page
    });

    const signInWithGoogle = document.getElementById("signInWithGoogle");

    signInWithGoogle.addEventListener("click", function(){
       showMessage("This feature is under development");
    });

});

$(document).ready(function () {
    $("#signupForm").submit(function (event) {
        event.preventDefault(); // Prevent default form submission

        let formData = new FormData(this); // Serialize form data

        let jsonData = Object.fromEntries(formData.entries());
        let name = jsonData.name;
        let email = jsonData.email;
        let password = jsonData.password;
        let phone = jsonData.phone;
        let address = jsonData.address;

        $("#loader-overlay").removeClass("hidden");
        
        $.ajax({
            url: "SignupController/signup",
            type: "POST",
            data: {
                name,
                email,
                password,
                phone,
                address
            },
            dataType: "json",
            success: function (response) {
                console.log("Success:", response);
                showMessage(response.message);
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
                alert("Signup failed! Please try again.");
            },
            complete: function () {
                // Hide loader after request completion
                $("#loader-overlay").addClass("hidden");
            }
        });
    });
});

function showMessage(message) {
    let messageBox = document.getElementById("messageBox");
    messageBox.textContent = message;
    if (message === "User registered successfully") {
        messageBox.style.color = "green"; // Success message in green
    } else {
        messageBox.style.color = "red"; // Error message in red
    }
    messageBox.classList.remove("hidden");

    setTimeout(() => {
        messageBox.classList.add("hidden");
    }, 5000);
}

