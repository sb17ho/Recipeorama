# Recipeorama
**App Name:** Recipeorama <br>
**Authors:** Simar Bhamra & Fahad Ansar <br>
**Platform:** Android <br>
**Programming Language:** Kotlin <br>
**Tools Used:** Room Database, Retrofit API, Firebase, MealsDB API <br>
# Overview
Recipeorama is an application where the users have the functionality to look up recipes and can add the recipe to grocery list where the user is provided with the required ingredients and the user can click on the required items to add it to buy list. The app makes use of MealsDB to fetch the recipes from the API database by also making use of retrofit and okhttp3. The grocery is saved on device via Room Database and the user is provided with functionality to backup to firebase and restore in case the application is deleted or a different device is used. The user is logged via Google Sign-in via Firebase.
## Target Audience
The application can be used by any generation since anyone can look up recipes which they want to make and add to Grocery List if any missing ingredients are required for the recipe and needs to be purchased
# APIs
The **MealsDB API** offers recipe database that is capable of returning JSON formatted data which is then accessed via **Retrofit2 API** and **Okhttp3** to integrate it into the application
# Design
