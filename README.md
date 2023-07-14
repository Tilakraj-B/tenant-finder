# Tenant Finder Android Application

Tenant Finder is an Android application designed to connect tenants and property owners looking to rent out their properties. The application allows users to create an account using Firebase Authentication, enabling them to log in securely. Whether you're a tenant or a property owner, Tenant Finder provides an intuitive platform for listing and finding rental properties.

## Features

- **Firebase Authentication:** Securely create an account and log in to the application using Firebase Authentication.
- **Tenant Finder Profile:** As a tenant, create and manage your profile with essential details such as name, contact information, and preferences.
- **Property Listing:** As a property owner (tenant finder), easily add new properties along with images and relevant details for potential tenants to view.
- **Contact Tenant Finder:** Interested tenants can contact the property owner directly through the application using the provided contact information.
  
## Dependencies

The following dependencies are used in this project:

- Firebase Authentication: `implementation 'com.google.firebase:firebase-auth:20.0.1'`
- Firebase Realtime Database: `implementation 'com.google.firebase:firebase-database:20.0.1'`
- Picasso (for image loading): `implementation 'com.squareup.picasso:picasso:2.71828'`
- RecyclerView: `implementation 'androidx.recyclerview:recyclerview:1.2.1'`
- Other AndroidX libraries: `implementation 'androidx.appcompat:appcompat:1.3.1'`, `implementation 'androidx.constraintlayout:constraintlayout:2.0.4'`, etc.

Make sure to add the necessary dependencies to your project's `build.gradle` file.

## Usage

1. Launch the Tenant Finder application on your Android device or emulator.
2. If you're a new user, create an account using the provided registration form.
3. Log in to the application using your registered email and password.
4. As a tenant finder, navigate to the "Add Property" section and provide all the required details, including images of the property.
5. As a tenant, browse through the available listings. Select a property to view detailed information.
6. Contact the tenant finder directly using the provided contact information if you're interested in a property.

