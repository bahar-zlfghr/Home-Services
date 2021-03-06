function validateAssignSpecialistToSubServiceForm() {
    return !(!validateSubService() | !validateSpecialist());
}

function validateSubService() {
    var subService = document.getElementById('subServiceName').value;
    if (subService.length === 0) {
        document.getElementById('subServiceError').innerHTML = 'Choose one sub service';
        return false;
    }
    else {
        document.getElementById('subServiceError').innerHTML = '';
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