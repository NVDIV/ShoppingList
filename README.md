# ShoppingList

This is a simple Android app built with Jetpack Compose, allowing users to create and manage a shopping list. Users can add products with names and quantities, mark items as purchased, and delete items from the list.

## Features

- Add Product: Add new products to the shopping list by entering the product name and quantity.
- Mark as Purchased: Tap the "Done" icon to mark items as purchased, and toggle back to "not purchased" by tapping the icon again.
- Delete Product: Remove products from the list by tapping the "Delete" icon.

## Getting Started

1) Clone the repository:

~~~ 
git clone <repository_url>
~~~

2) Open in Android Studio:

  Open the project in Android Studio.
  Make sure to have the latest version of Android Studio installed.

3) Build and Run:

  Build and run the app on an Android emulator or physical device.
  
## Code Overview

- MainActivity: The main entry point of the app.
- Product: Data class representing each product with fields for ID, name, quantity, and purchase status.
- ComposableShoppingList: The main composable function displaying the shopping list and handling product addition.
- ProductRow: Composable for each row in the shopping list, showing product details and action icons.
