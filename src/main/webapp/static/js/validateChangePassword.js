function validateChangePasswordForm() {
    return !(!validatePreviousPassword() | !validateNewPassword());
}

function validatePreviousPassword() {
    var previousPassword = document.getElementById('previousPassword').value;
    if (previousPassword.length === 0) {
        document.getElementById('previousPasswordError').innerHTML = 'Enter previous password';
        return false;
    }
    else {
        document.getElementById('previousPasswordError').innerHTML = '';
        return true;
    }
}

function validateNewPassword() {
    var nextPassword = document.getElementById('newPassword').value;
    if (nextPassword.length === 0) {
        document.getElementById('newPasswordError').innerHTML = 'Enter new password';
        return false;
    }
    else {
        document.getElementById('newPasswordError').innerHTML = '';
        return true;
    }
}
