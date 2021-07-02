function validateAssignToServiceForm() {
    return !(!validateService());
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
