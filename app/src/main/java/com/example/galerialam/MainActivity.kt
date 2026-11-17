package com.example.galerialam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

data class GalleryItem(
    val title: String,
    val description: String,
    val image: Int,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GaleriAlamApp()
        }
    }
}

@Composable
fun GaleriAlamApp() {

    val gallery = listOf(
        GalleryItem(
            "Gunung Merbabu",
            "Gunung Merbabu terkenal dengan padang savana yang luas, sabana hijau yang memukau, serta pemandangan matahari terbit yang sangat indah dari puncaknya.",
            R.drawable.gunung_merbabu
        ),
        GalleryItem(
            "Curug Citambur",
            "Air terjun megah di Cianjur, Jawa Barat, dengan ketinggian sekitar 100 meter dan dikelilingi tebing hijau serta kabut tipis yang menyejukkan.",
            R.drawable.curug_citambur
        ),
        GalleryItem(
            "Hutan Himalaya",
            "Hutan pegunungan alpine di kawasan Himalaya yang kaya akan flora dan fauna langka, serta suasana alam liar yang asri dan tenang.",
            R.drawable.hutan_himalaya
        ),
        GalleryItem(
            "Pantai Bali",
            "Pantai tropis eksotis di Bali dengan hamparan pasir putih bersih, ombak biru yang jernih, serta panorama matahari terbenam yang memukau.",
            R.drawable.pantai_bali
        ),
        GalleryItem(
            "Danau Toba",
            "Danau vulkanik terbesar di Indonesia yang berlokasi di Sumatera Utara, dikelilingi pegunungan bukit barisan yang megah dengan Pulau Samosir di tengahnya.",
            R.drawable.danau_toba
        )
    )

    var currentIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(currentIndex) {
        delay(4.seconds)
        currentIndex = (currentIndex + 1) % gallery.size
    }

    val currentImage = gallery[currentIndex]

    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F7F2)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "GALERI KEINDAHAN ALAM",
                fontSize = if (isTablet) 28.sp else 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF245C3A)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .widthIn(max = 480.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {

                    Image(
                        painter = painterResource(
                            id = currentImage.image
                        ),
                        contentDescription = currentImage.title,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(
                                min = 220.dp,
                                max = if (isTablet) 430.dp else 320.dp
                            )
                            .clickable {
                                currentIndex =
                                    (currentIndex + 1) % gallery.size
                            }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = currentImage.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = currentImage.description,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "${currentIndex + 1} dari ${gallery.size} gambar",
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 480.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = {
                        currentIndex =
                            ((currentIndex - 1 + gallery.size) % gallery.size)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF42649B)
                    )
                ) {
                    Text("Previous")
                }

                Button(
                    onClick = {
                        currentIndex =
                            (currentIndex + 1) % gallery.size
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF42649B)
                    )
                ) {
                    Text("Next")
                }
            }
        }
    }
}
@Preview(
    showBackground = true
)
@Composable
fun GaleriAlamPreview() {
    GaleriAlamApp()
}
