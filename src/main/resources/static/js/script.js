document.addEventListener('DOMContentLoaded', function () {
    // Form validation for signup and login
    function validateForm(formId) {
        const form = document.getElementById(formId);
        form.addEventListener('submit', function (e) {
            const inputs = form.querySelectorAll('input');
            let valid = true;

            inputs.forEach(input => {
                if (!input.checkValidity()) {
                    valid = false;
                    input.classList.add('is-invalid');
                } else {
                    input.classList.remove('is-invalid');
                }
            });

            if (!valid) {
                e.preventDefault(); // Prevent form submission if invalid
            }
        });
    }

    // Call validation for signup and login forms
    validateForm('signupForm');
    validateForm('loginForm');
});
