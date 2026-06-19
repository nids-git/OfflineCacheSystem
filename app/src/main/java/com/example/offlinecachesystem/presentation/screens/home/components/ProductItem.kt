package com.example.offlinecachesystem.presentation.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import com.example.offlinecachesystem.domain.model.Product
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            }
    ) {

        Row(
            modifier = Modifier.padding(12.dp)
        ) {

            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier.height(100.dp)
            )

            Column(
                modifier = Modifier.padding(start = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(product.title)

                Text("₹ ${product.price}")

                Text(product.category)
            }
        }
    }
}

