function validateCreateOrderForm() {
    return !(!validateSubService() | !validateSuggestedPrice() | !validateDoDate() | !validateAddress());
}

function validateSubService() {
    var serviceName = document.getElementById('subServiceName').value;
    if (serviceName.length === 0) {
        document.getElementById('subServiceError').innerHTML = 'Choose one sub service';
        return false;
    }
    else {
        document.getElementById('subServiceError').innerHTML = '';
        return true;
    }
}

function validateSuggestedPrice() {
    var suggestedPrice = document.getElementById('suggestedPrice').value;
    if (suggestedPrice.length === 0) {
        document.getElementById('suggestedPriceError').innerHTML = 'Suggested price is required';
        return false;
    }
    else {
        document.getElementById('suggestedPriceError').innerHTML = '';
        return true;
    }
}

function validateDoDate() {
    var doDate = document.getElementById('doDate').value;
    if (doDate.length === 0) {
        document.getElementById('doDateError').innerHTML = 'Choose do date';
        return false;
    }
    else {
        document.getElementById('doDateError').innerHTML = '';
        return true;
    }
}

function validateAddress() {
    console.log("validateAddress");
    var latitude = document.getElementById('latitude').value;
    var longitude = document.getElementById('longitude').value;
    if (latitude.length === 0 && longitude.length === 0) {
        document.getElementById('addressError').innerHTML = 'Choose your address on map';
        return false;
    }
    else {
        document.getElementById('addressError').innerHTML = '';
        return true;
    }
}

// ToDo: don't work
/*function setSubServices() {
    sessionStorage.removeItem("service");
    var serviceName = document.getElementById('serviceName').value;
    sessionStorage.setItem("service", serviceName);
    return true;
}*/

/*
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
}*/
