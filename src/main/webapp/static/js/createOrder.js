function validateCreateOrderForm() {
    return !(!validateSubService() | !validateSuggestedPrice() | !validateDoDate() | !validateAddress());
}

function validateSubService() {
    var serviceName = document.getElementById('subServiceName').value;
    if (serviceName.length === 0) {
        document.getElementById('subServiceError').innerHTML = 'Choose one sub service';
        return false;
    } else {
        document.getElementById('subServiceError').innerHTML = '';
        return true;
    }
}

function validateSuggestedPrice() {
    var suggestedPrice = document.getElementById('suggestedPrice').value;
    if (suggestedPrice.length === 0) {
        document.getElementById('suggestedPriceError').innerHTML = 'Suggested price is required';
        return false;
    } else {
        document.getElementById('suggestedPriceError').innerHTML = '';
        return true;
    }
}

function validateDoDate() {
    var doDate = document.getElementById('doDate').value;
    if (doDate.length === 0) {
        document.getElementById('doDateError').innerHTML = 'Choose do date';
        return false;
    } else {
        document.getElementById('doDateError').innerHTML = '';
        return true;
    }
}

function validateAddress() {
    var latitude = document.getElementById('latitude').value;
    var longitude = document.getElementById('longitude').value;
    if (latitude.length === 0 && longitude.length === 0) {
        document.getElementById('addressError').innerHTML = 'Choose your address on map';
        return false;
    } else {
        document.getElementById('addressError').innerHTML = '';
        var apiKey = "service.cPDC4Tri7dDZsDZhoqg2VfUFK4oJ8AsuYYLM1DOd";
        $.ajax({
            type: "GET",
            url: "https://api.neshan.org/v2/reverse?lat=" + latitude + "&lng=" + longitude,
            headers: {"api-key": apiKey},
            success: function (result) {
                document.getElementById("state").value = result.state;
                document.getElementById("city").value = result.city;
                document.getElementById("formatted_address").value = result.formatted_address;
            }
        });
        return true;
    }
}
