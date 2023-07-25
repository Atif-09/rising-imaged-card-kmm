import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import model.IslandDataClass
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.resource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        val listOfIsland = listOf(
            IslandDataClass(1, "Bali", "Indonesia", Color(0xFF3389e2), "bali.jpg"),
            IslandDataClass(2, "Yosemite", "California", Color(0xFFee6659), "yosemite.jpg"),
            IslandDataClass(3, "Rayyu", "Maldives", Color(0xFF4ecab0), "rayyu_maldives.jpg"),
            IslandDataClass(1, "Viti Levu", "Fiji", Color(0xFF7D5260), "vitilevu.jpg"),
            IslandDataClass(1, "Beqa", "Fiji", Color(0xFF625b71), "beqa.jpg")
        )

        val image = remember { mutableStateOf<ImageBitmap?>(null) }

        LaunchedEffect(Unit) {
            for (item in listOfIsland)
            image.value = resource(item.resImage).readBytes().toImageBitmap()
        }

        Column(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)) {
            ItemList(listOfIsland, image.value!!)
        }
    }
}

@Composable
fun ItemList(itemList: List<IslandDataClass>, image:ImageBitmap) {
    LazyColumn {
        items(itemList) { item ->
            CardImageView(item, image)
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CardImageView(item: IslandDataClass, image: ImageBitmap) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(top = 18.dp, bottom = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(start = 72.dp, end = 18.dp, top = 21.dp)
                .fillMaxSize()
                .background(
                    color = item.color,
                    shape = RoundedCornerShape(20.dp),

                    )
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = 16.dp, start = 81.dp)
                    .align(Alignment.TopStart)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = item.name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = item.country,
                        color = Color(0xFFcfd8dc),

                        )
                }
            }
        }

        Image(
            bitmap = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(0.35f)
                .padding(start = 18.dp)
                .clip(RoundedCornerShape(30.dp))

        )
    }
}

expect fun getPlatformName(): String