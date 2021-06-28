function validateCreateSubServiceForm() {
    return !(!validateService() | !validateName() | !validateBasePrice());
}

function validateService() {
    var serviceName = document.getElementById('serviceName').value;
    if (serviceName.length === 0) {
        document.getElementById('serviceError').innerHTML = 'Choose one service';
        return false;
    }
    else {
        document.getElementById('serviceError').innerHTML = '';
        return true;
    }
}

function validateName() {
    var subServiceName = document.getElementById('subServiceName').value;
    if (subServiceName.length < 1 || subServiceName.length > 30) {
        document.getElementById('subServiceNameError').innerHTML = 'Sub service name should be between 1 and 30 characters long';
        return false;
    } else {
        document.getElementById('subServiceNameError').innerHTML = '';
        return true;
    }
}

function validateBasePrice() {
    var basePrice = document.getElementById('basePrice').value;
    if (basePrice.length === 0) {
        document.getElementById('basePriceError').innerHTML = 'Base price is required';
        return false;
    }
    else {
        document.getElementById('basePriceError').innerHTML = '';
        return true;
    }
}