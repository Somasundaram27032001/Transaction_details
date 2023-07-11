package com.example.somasundaram2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.SurfaceControl
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.somasundaram2.ui.theme.Somasundaram2Theme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Somasundaram2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {


                }
            }
        }
    }
}


@Preview
@Composable
fun TransactionDetailsScreenPreview() {
    TransactionDetailsScreen(transaction = SurfaceControl.Transaction())
}




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailsScreen(transaction: SurfaceControl.Transaction) {
    val context = LocalContext.current



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Transaction Details",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back arrow click */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

            )
        },

        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 80.dp, vertical = 5.dp
                    )
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { /* Handle cancel transaction click */ }) {
                    Text(text = "Cancel Transaction", color = Color.White)
                }

                Button(onClick ={/* Handle Download Receipt click */}) {
                    Text(text = "Download Receipt")
                }

                Button(onClick = { shareTransaction(context, transaction) }) {
                    Text(text = "Share with Recipient")
                }
            }
        }

    )
    {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 80.dp)
        ) {

            item {
                Image(
                    painter = painterResource(id = R.drawable.download),
                    contentDescription = "ImageLogo",
                    modifier = Modifier.padding(horizontal = 140.dp, vertical =10.dp),

                )
                Text(
                    text = "Transaction Details",
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    modifier = Modifier.padding(horizontal = 105.dp)

                )


                TransactionDetailItem("Transaction ID: 123456")
                TransactionDetailItem("Recipient: John Doe")
                TransactionDetailItem("Transaction Status: Completed")
                TransactionDetailItem("Data Sent: 500 MB")
                TransactionDetailItem("You Sent: $50")
                TransactionDetailItem("Exchange Rate: 1 USD = 10 EUR")
                TransactionDetailItem("They Received: 500 EUR")
                TransactionDetailItem("Total: $50")
            }
        }
    }
}




@Composable
fun TransactionDetailItem(title: String) {
    Column(
        modifier = Modifier.padding(horizontal = 70.dp,  vertical = 10.dp)
    )


    {


        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun shareTransaction(context: Context, transaction: SurfaceControl.Transaction) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, transaction.toString())
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share Transaction"))
}



