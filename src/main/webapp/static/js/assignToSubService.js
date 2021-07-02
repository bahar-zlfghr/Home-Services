function validateAssignToSubServiceForm() {
    return !(!validateSubService());
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
