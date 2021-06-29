const maxAllowedSize = 300 * 1024;
const allowedExtension = ["jpg"];

function checkFormatAndSizeProfilePicture() {
    var profilePicture = document.getElementById('picture');
    document.getElementById('pictureError').innerHTML = '';
    const {name: profilePictureName, size: profilePictureSize} = profilePicture.files[0];
    const profilePictureExtension = profilePictureName.split('.').pop();
    if (allowedExtension.includes(profilePictureExtension)) {
        if (profilePictureSize > maxAllowedSize) {
            document.getElementById('picturePreview').innerHTML = '';
            document.getElementById('pictureError').innerHTML = 'Picture size is too large!';
            document.getElementById('picturePreview').innerHTML = '';
            return false;
        } else {
            var reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById('picturePreview').innerHTML = '<img src="' + e.target.result + '" alt="Oops!"' + ' style="' + 'width: 195px; height: 195px"' + '/>';
                document.getElementById('pictureError').innerHTML = '';
                document.getElementById('pictureEmptyError').innerHTML = '';
            };
            reader.readAsDataURL(profilePicture.files[0]);
        }
    } else {
        document.getElementById('pictureError').innerHTML = 'Invalid picture type';
        document.getElementById('picturePreview').innerHTML = '';
        return false;
    }
}
