$(document).ready(function () {
    $("#LoginForm").submit(function (event) {
        event.preventDefault(); // Prevent default form submission

        let formData = new FormData(this); // Serialize form data

        let jsonData = Object.fromEntries(formData.entries());
        let email = jsonData.email;
        let password = jsonData.password;

        $.ajax({
            url: "LoginController/login",
            type: "POST",
            data: {
                email,
                password
            },
            dataType: "json",
            success: function (response) {
                console.log("Success:", response);
                if (response.status === "success") {
                    window.location.href = response.redirectUrl; // Redirect to home page
                }
            },
            error: function (xhr, status, error) {
                let errorMessage = xhr.responseJSON ? xhr.responseJSON.message : "Login failed!";
                console.error("Error:", errorMessage);
                showMessage(errorMessage);
            }
        });
    });
});

function showMessage(message) {
    let messageBox = document.getElementById("messageBoxLogin");
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

