const maxAllowedSize = 300 * 1024;
const allowedExtension = ["jpg"];

function checkFormatAndSizeProfilePicture () {
    var profilePicture = document.getElementById('profilePicture');
    if (profilePicture === null) {
        document.getElementById('profilePictureError').innerHTML = 'Choose a .jpg file';
        return false;
    }
    else {
        document.getElementById('profilePictureError').innerHTML = '';
        const {name: profilePictureName, size: profilePictureSize} = profilePicture.files[0];
        const profilePictureExtension = profilePictureName.split('.').pop();
        if (allowedExtension.includes(profilePictureExtension)) {
            if (profilePictureSize > maxAllowedSize) {
                document.getElementById('profilePicturePreview').innerHTML = '';
                document.getElementById('profilePictureError').innerHTML = 'Picture size is too large!';
                document.getElementById('profilePicturePreview').innerHTML = '';
                return false;
            } else {
                var reader = new FileReader();
                reader.onload = function (e) {
                    document.getElementById('profilePicturePreview').innerHTML = '<img src="' + e.target.result + '" alt="Oops!"/>';
                    document.getElementById('profilePictureError').innerHTML = '';
                };
                reader.readAsDataURL(profilePicture.files[0]);
            }
        } else {
            document.getElementById('profilePictureError').innerHTML = 'Invalid picture type';
            document.getElementById('profilePicturePreview').innerHTML = '';
            return false;
        }
    }
}
