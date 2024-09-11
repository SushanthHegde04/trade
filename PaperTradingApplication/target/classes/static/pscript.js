document.getElementById('signupButton').addEventListener('click', function () {
    document.getElementById('signupBox').style.display = 'block';
    document.getElementById('loginBox').style.display = 'none';
});

document.getElementById('loginButton').addEventListener('click', function () {
    document.getElementById('loginBox').style.display = 'block';
    document.getElementById('signupBox').style.display = 'none';
});

document.getElementById('signupForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const formData = new FormData(this);
    const jsonData = {};
    formData.forEach((value, key) => jsonData[key] = value);
    
    // Log the data being sent
    console.log('Signup Form Data:', jsonData);

    fetch('/users/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    }).then(response => response.json())
        .then(data => {
            // Log the response from the server
            console.log('Signup Response:', data);
            console.log("data",data.userId);
           sessionStorage.setItem("UserId",data.userId);
            alert('Sign up successful');
            //window.location.href="UserDetails.html"
              window.location.href = 'userDetails.html';
            document.getElementById('signupBox').style.display = 'none';
        }).catch(error => {
            console.error('Error signing up:', error);
            alert('Error signing up');
        });
});
document.getElementById('loginForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const formData = new FormData(this);
    const jsonData = {};
    formData.forEach((value, key) => jsonData[key] = value);
    
    // Log the data being sent
    console.log('Login Form Data:', jsonData);

    fetch('/users/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
    .then(response => {
        // Check if response is OK
        if (!response.ok) {
            return response.json().then(data => {
                throw new Error(data.message); // Throw error if not OK
            });
        }
        return response.json(); // Parse JSON response
    })
    .then(data => {
        // Log the response from the server
        console.log('Login Response:', data);
       //lert(data.message); // Show success message

        // Store userId in sessionStorage
        sessionStorage.setItem("UserId", data.userId);

        // Hide the login box and redirect to userDetails page
        document.getElementById('loginBox').style.display = 'none';
        window.location.href = '/userDetails.html';
    })
    .catch(error => {
        // Log and show error if login failed
        console.error('Error logging in:', error);
        alert(error.message || 'Error logging in');
    });
});
