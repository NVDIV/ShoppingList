package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import java.util.UUID

// Class representing product
data class Product(
    val id: String,
    val name: String,
    val quantity: Int,
    var isPurchased: Boolean = false
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        // enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    ComposableShoppingList()
                }
            }
        }
    }
}

@Composable
fun ComposableShoppingList() {

    var showDialog by remember{ mutableStateOf(false) }
    var productName by remember { mutableStateOf("") }
    var productQuantity by remember { mutableStateOf("") }
    var productList by rememberSaveable { mutableStateOf(listOf<Product>()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            items(productList) { product ->
                ProductRow(
                    product = product,
                    onPurchaseClick = {
                        productList = productList.map {
                            if (it.id == product.id) {
                                it.copy(isPurchased = !it.isPurchased)
                            } else {
                                it
                            }
                        }
                    },
                    onDeleteClick = {
                        productList = productList.filter { it.id != product.id }
                    }
                )
            }
        }
        Button (
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Add Product")
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = {
                    if (productName.isNotBlank()) {
                        val newProduct = Product(
                            id = UUID.randomUUID().toString(),
                            productName,
                            productQuantity.toIntOrNull() ?: 1
                        )
                        productList = productList + newProduct
                        showDialog = false
                        productName = ""
                        productQuantity = ""
                    }
                })
                {
                    Text("ADD")
                } },
            title = {Text("Add product to list")},
            text = {
                Column {
                    TextField(value = productName, onValueChange = { productName = it })
                    TextField(value = productQuantity, onValueChange = { productQuantity = it })
                }
            }
        )
    }
}

@Composable
fun ProductRow (product: Product, onPurchaseClick: () -> Unit, onDeleteClick: () -> Unit) {
    Row (
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = product.name)
        Text(text = product.quantity.toString())
        IconButton(onClick = onPurchaseClick) {
            Icon(imageVector = if (product.isPurchased == true) Icons.Default.Done else Icons.Default.Clear,
                "purchase icon")
        }
        IconButton(onClick = onDeleteClick) {
            Icon(imageVector = Icons.Default.Delete, "delete icon")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppingListTheme {
        ComposableShoppingList()
    }
}