package lotto.project.lottoplayer.Presentation.Components

import android.view.View
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdSize
//import com.google.android.gms.ads.AdView
import lotto.project.lottoplayer.Presentation.Uitls.LottoTypes

@Composable
fun AnimatedNumbers(
    showNumbers: Boolean,
    numberType: LottoTypes,
    numbers: List<Int>
) {
    AnimatedVisibility(
        visible = showNumbers,
        enter = slideInVertically(animationSpec = tween(1000)) + fadeIn(animationSpec = tween(1000)),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${numberType.value} Numbers:",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(numbers) { index, number ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = if (index == 5) Color.Red else Color.Blue,
                                shape = CircleShape
                            )
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = number.toString(),
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    // Add extra space after the 6th number
                    if (index == 5 && numbers.size > 6) {
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
        }
    }
}



@Composable
fun AdmobBanner(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val adView = remember { AdView(context) }

    DisposableEffect(lifecycleOwner,adView) {
        adView.setAdSize(AdSize.BANNER)
        adView.adUnitId = "ca-app-pub-3940256099942544/9214589741" //ca-app-pub-4493140136275030/5241017760
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        val observer = object : DefaultLifecycleObserver {
            override fun onPause(owner: LifecycleOwner) {
                adView.pause()
            }

            override fun onResume(owner: LifecycleOwner) {
                adView.resume()
                Toast.makeText(context,"resumed",Toast.LENGTH_SHORT).show()
            }

            override fun onDestroy(owner: LifecycleOwner) {
                adView.destroy()
                owner.lifecycle.removeObserver(this)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            adView.destroy()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    AndroidView(
        factory = { adView },
        modifier = modifier
    )
}