function validateAssignSpecialistToServiceForm() {
    return !(!validateService() | !validateSpecialist());
}

function validateService() {
    var subService = document.getElementById('serviceName').value;
    if (subService.length === 0) {
        document.getElementById('serviceError').innerHTML = 'Choose one service';
        return false;
    }
    else {
        document.getElementById('serviceError').innerHTML = '';
        return true;
    }
}

function validateSpecialist() {
    var specialist = document.querySelector('input[name="specialistEmail"]:checked');
    if (specialist === null) {
        document.getElementById('specialistError').innerHTML = 'Choose one specialist';
        return false;
    }
    else {
        document.getElementById('specialistError').innerHTML = '';
        return true;
    }
}