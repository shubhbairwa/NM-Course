package com.shubhcode.nmcourse;

public class UserProfilePic {
    private String imageUri;

    public UserProfilePic() {
    }

    public UserProfilePic(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
