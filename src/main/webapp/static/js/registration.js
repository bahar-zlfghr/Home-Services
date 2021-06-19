const maxAllowedSize = 300 * 1024;
const allowedExtension = ["jpg"];

function checkFormatAndSizeProfilePicture () {
    var role = document.getElementById('role').value;

    if (role === "specialist") {
        var profilePicture = document.getElementById('profilePicture');

        const {name: profilePictureName, size: profilePictureSize} = profilePicture.files[0];
        const profilePictureExtension = profilePictureName.split('.').pop();

        if (allowedExtension.includes(profilePictureExtension)) {
            if (profilePictureSize > maxAllowedSize) {
                document.getElementById('profilePicturePreview').innerHTML = '';

                document.getElementById('profilePictureError').innerHTML = 'Picture size is too large!';
                document.getElementById('profilePicturePreview').innerHTML = '';

                return false;
            }
            else {
                var reader = new FileReader();
                reader.onload = function(e) {
                    document.getElementById('profilePicturePreview').innerHTML = '<img src="' + e.target.result + '" alt="Oops!"/>';
                    document.getElementById('profilePictureError').innerHTML = '';
                };
                reader.readAsDataURL(profilePicture.files[0]);
            }
        }
        else {
            document.getElementById('profilePictureError').innerHTML = 'Invalid picture type';
            document.getElementById('profilePicturePreview').innerHTML = '';
            return false;
        }
    }
}

$(document).ready(function(){
    $("select").change(function(){
        $(this).find("option:selected").each(function(){
            var optionValue = $(this).attr("value");
            if(optionValue){
                $(".box").not("." + optionValue).hide();
                $("." + optionValue).show();
            } else{
                $(".box").hide();
            }
        });
    }).change();
});
