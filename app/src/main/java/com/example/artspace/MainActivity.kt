package com.example.artspace

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Art("Android")
                }
            }
        }
    }
}

@Composable
fun Art(name: String, modifier: Modifier = Modifier) {

    val sceneData by remember {
        mutableStateOf( arrayOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10
        ))
    }
    var itemData by remember {mutableStateOf(0)}
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        RenderPicture(sceneData[itemData])
        Spacer(modifier = Modifier.size(20.dp))
        RenderText(itemData)
        Spacer(modifier = Modifier
            .size(100.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(.9f),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(.35f),
                    onClick = { itemData-- },
                    enabled = itemData > 0
                ) {
                    Text(text = stringResource(id = R.string.previous_scene))
                }
                Button(
                    modifier = Modifier.fillMaxWidth(.55f),
                    onClick = { itemData++ },
                    enabled = itemData < sceneData.size - 1
                ) {
                    Text(text = stringResource(id = R.string.next_scene))
                }
            }
        }
    }
}

@Composable
fun RenderPicture(drawableId: Int) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .shadow(15.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(0.9f)
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(0.9f)
                    .align(Alignment.Center),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
fun RenderText(itemData: Int) {
    Column(
        modifier = Modifier
            .background(color = Color(0xFFECEBF4))
            .padding(10.dp)
            .fillMaxWidth(.9f),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringArrayResource(id = R.array.art_name)[itemData],
            fontSize = 24.sp,
            fontWeight = FontWeight.Light
        )
        Row() {
            Text(text = "Owen Wilson")
            Text(text = "(2024)")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtPreview() {
    ArtSpaceTheme {
        Art("Android")
    }
}