$(document).ready(function () {
    $("#ForgotForm").submit(function (event) {
        event.preventDefault(); // Prevent default form submission

        let formData = new FormData(this); // Serialize form data

        let jsonData = Object.fromEntries(formData.entries());
        let email = jsonData.email;

        $.ajax({
            url: "LoginController/checkMail",
            type: "POST",
            data: {
                email
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