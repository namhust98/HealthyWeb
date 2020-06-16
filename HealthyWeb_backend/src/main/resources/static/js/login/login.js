
$(document).ready(function () {
    $('.submit-form-login').click(function () {
        let userName = document.getElementById("login").value;
        let pass = document.getElementById("password").value;
        console.log(userName);
        console.log(pass);
        $.ajax({
            url: 'api/v1/login',
            type: 'post',
            dataType: 'json',
            // data: JSON.stringify(formLogin),
            data: {
                "userName": userName,
                "password": pass
            },
            // contentType: 'application/json',
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            success: function (data) {
                console.log(data);
                // Check browser support
                if (typeof(Storage) !== "undefined") {
                    // Store
                    localStorage.setItem("accessToken", "test add access token to localStorage");
                    // Retrieve
                    // document.getElementById("result").innerHTML = localStorage.getItem("list");
                } else {
                    // document.getElementById("result").innerHTML = "Sorry, your browser does not support Web Storage...";
                }
                alert("Register success");
            },
            error: function(err) {
                // alert(err.responseJSON.message);
                console.log("error");
            }
        });
    });
})