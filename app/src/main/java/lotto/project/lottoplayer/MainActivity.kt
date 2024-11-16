package lotto.project.lottoplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lotto.project.lottoplayer.ui.theme.LottoPlayerTheme

class MainActivity : ComponentActivity() {
    val backgroundScope = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LottoPlayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        lotto.project.lottoplayer.Presentation.Components.AdmobBanner()
                    }) { _ ->
                    lotto.project.lottoplayer.Presentation.HomeScreen.homescreen()
                }
            }
        }


        backgroundScope.launch {
            // Initialize the Google Mobile Ads SDK on a background thread.
            MobileAds.initialize(this@MainActivity) {}
        }
    }
}

